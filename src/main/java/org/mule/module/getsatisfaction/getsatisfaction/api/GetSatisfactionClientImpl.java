/*
 * $Id$
 * --------------------------------------------------------------------------------------
 * Copyright (c) MuleSource, Inc.  All rights reserved.  http://www.mulesource.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.getsatisfaction.getsatisfaction.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import com.gsfn.FastPass;

public class GetSatisfactionClientImpl implements GetSatisfactionClient
{
    private static String KEY = "1fjo088bougl";
    private static String SECRET = "aqfi8q65z031wlhqsrj37rhuv1qlwy78";

    private String key = KEY;
    private String secret = SECRET;
    
    public static void main(String args[])
    {
        try {
            GetSatisfactionClientImpl c = new GetSatisfactionClientImpl();
            User u = new User();
            u.setEmail("mariano.capurro@mulesoft.com");
            u.setFullName("Mariano Capurro");
            u.setUid("mariano.capurro");
            
           
            /*
            List<Product> p = new ArrayList<Product>();
            p.add(new Product("mulesoft_mule_community_edition"));
            
            Topic topic = new Topic();
            topic.setUser(u);
            topic.setStyle(TopicStyle.QUESTION);
            topic.setSubject("Test from my computer " + new java.util.Date());
            topic.setAdditionalDetails("More information about by question: "  + new java.util.Date());
            topic.setProducts(p);
            
            c.create(topic);
            */
            Topic t = new Topic();
            t.setId("test_from_my_computer_sat_jan_07_11_45_26_gmt_03_00_2012");
            
            Reply reply = new Reply();
            reply.setTopic(t);
            reply.setUser(u);
            reply.setContent("This is my reply to my own crazy question: " + new java.util.Date());
            
            c.create(reply);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * 
     */
    public GetSatisfactionClientImpl()
    {
    }

    private String getProductsParam(List<Product> products)
    {
        StringBuilder sb = new StringBuilder();
        
        for(Iterator<Product> it = products.iterator(); it.hasNext(); )
        {
            sb.append(it.next().getId());
            if(it.hasNext())
            {
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    @Override
    public void create(Topic topic) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        httpClient.setCookieStore(cookieStore);
        try
        {
            AuthenticationContext authCtx = authenticate(topic.getUser(), httpClient);
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(authCtx.getCsrfParam(), authCtx.getCsrfToken()));
            params.add(new BasicNameValuePair("topic[style]", topic.getStyle().toString()));
            params.add(new BasicNameValuePair("topic[subject]", topic.getSubject()));
            params.add(new BasicNameValuePair("topic[additional_detail]", topic.getAdditionalDetails()));
            params.add(new BasicNameValuePair("topic[products][]", getProductsParam(topic.getProducts())));
            //:TODO: Add all parameters!
            
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            
            HttpPost post = new HttpPost("http://getsatisfaction.com/mulesoft/topics");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            
            //TODO: Process response
            
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }
    }

    @Override
    public void create(Reply reply) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        httpClient.setCookieStore(cookieStore);
        httpClient.setRedirectStrategy(new RedirectStrategy()
        {
            
            @Override
            public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context)
                throws ProtocolException
            {
                return false;
            }
            
            @Override
            public HttpUriRequest getRedirect(HttpRequest request, HttpResponse response, HttpContext context)
                throws ProtocolException
            {
                return null;
            }
        });
        
        try
        {
            AuthenticationContext authCtx = authenticate(reply.getUser(), httpClient);
            
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair(authCtx.getCsrfParam(), authCtx.getCsrfToken()));
            params.add(new BasicNameValuePair("topic[status]", reply.getTopic().getStatus()));
            params.add(new BasicNameValuePair("reply[content]", reply.getContent()));
            //:TODO: Add all parameters!
            
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
            
            HttpPost post = new HttpPost("http://getsatisfaction.com/mulesoft/topics/" + reply.getTopic().getId() + "/replies");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            
            //TODO: Handle return status of 500
            System.out.println(response);
        }
        finally
        {
            httpClient.getConnectionManager().shutdown();
        }        
    }

    private AuthenticationContext authenticate(User user, DefaultHttpClient httpclient) throws Exception
    {
        AuthenticationContext ctx = new AuthenticationContext();
        
        HttpGet get = null;
        String url = "http://getsatisfaction.com/mulesoft?fastpass=" + URLEncoder.encode(FastPass.url(getKey(), getSecret(), user.getEmail(), user.getFullName(), user.getUid()), "UTF-8");
        get = new HttpGet(url);

        HttpResponse response = httpclient.execute(get);
        
        parseResponse(response.getEntity(), ctx);

        return ctx;
    }
    
    private String getContent(HttpEntity entity) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        
        BufferedReader in = null;
        
        try
        {
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String line;
            while((line = in.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n\r");
            }
        }
        finally
        {
            if(in != null)
            {
                in.close();
            }
        }        
        
        return sb.toString();
    }
    
    private void parseResponse(HttpEntity entity, AuthenticationContext ctx) throws Exception
    {
        BufferedReader in = null;
        Pattern p = Pattern.compile("\\s*<meta\\s*name=\"(.+)\"\\s*content=\"(.+)\"\\s*/>.*");
        
        try
        {
            in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String line;
            while((line = in.readLine()) != null)
            {
                Matcher m = p.matcher(line);
                if(m.matches()) 
                {
                    String name = m.group(1);
                    String content = m.group(2);
                    if("csrf-param".equalsIgnoreCase(name)) 
                    {
                        ctx.setCsrfParam(content);
                    } else if("csrf-token".equalsIgnoreCase(name)) {
                        ctx.setCsrfToken(content);
                    }
                }
            }
        }
        finally
        {
            if(in != null)
            {
                in.close();
            }
        }
    }
    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getSecret()
    {
        return secret;
    }
    public void setSecret(String secret)
    {
        this.secret = secret;
    }
}


