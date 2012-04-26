/**
 * Mule GetStaisfaction Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.getsatisfaction;

import com.gsfn.FastPass;
import net.oauth.OAuthException;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.mule.api.ConnectionException;
import org.mule.api.ConnectionExceptionCode;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Transformer;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestExceptionOn;
import org.mule.api.annotations.rest.RestHttpClient;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.annotations.rest.RestUriParam;
import org.mule.module.getsatisfaction.model.Product;
import org.mule.module.getsatisfaction.model.Reply;
import org.mule.module.getsatisfaction.model.ReplyFilterCriteria;
import org.mule.module.getsatisfaction.model.SearchResults;
import org.mule.module.getsatisfaction.model.SortCriteria;
import org.mule.module.getsatisfaction.model.Status;
import org.mule.module.getsatisfaction.model.Style;
import org.mule.module.getsatisfaction.model.Topic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Cloud Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name = "getsatisfaction", schemaVersion = "1.0")
public abstract class GetSatisfactionConnector {
    /**
     * FastPass Key
     */
    @Configurable
    private String key;

    /**
     * FastPass Secret
     */
    @Configurable
    private String secret;

    /**
     * Email address of the user
     */
    private String email;

    /**
     * Full name of the user
     */
    private String fullName;

    /**
     * User Identification
     */
    private String uid;

    /**
     * REST Http Client
     */
    @RestHttpClient
    private HttpClient httpClient;

    /**
     * Create a new connection to get satisfaction
     *
     * @param email    The email of the user
     * @param uid      User id
     * @param fullName Full name of the user
     * @throws ConnectionException If a connection cannot be made
     */
    @Connect
    public void connect(@ConnectionKey String uid, String email, String fullName) throws ConnectionException {
        this.email = email;
        this.uid = uid;
        this.fullName = fullName;

        String url = null;
        try {
            String fastpass = URLEncoder.encode(FastPass.url(getKey(), getSecret(), getEmail(), getFullName(), getUid()), "UTF-8");
            url = "http://getsatisfaction.com/mulesoft?fastpass=" + fastpass;
        } catch (OAuthException e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", e.getMessage(), e);
        } catch (IOException e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", e.getMessage(), e);
        } catch (URISyntaxException e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", e.getMessage(), e);
        }
        HttpMethod method = new GetMethod(url);

        try {
            int response = httpClient.executeMethod(method);
            Cookie[] cookies = httpClient.getState().getCookies();
            httpClient.getState().clearCookies();
            for( Cookie cookie : cookies ) {
                cookie.setDomain(".getsatisfaction.com");
            }
            httpClient.getState().addCookies(cookies);
            if (response != 200) {
                throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, Integer.toString(response), "Server Error (Possbile Invalid Credentials)");
            }
        } catch (IOException e) {
            throw new ConnectionException(ConnectionExceptionCode.UNKNOWN, "", e.getMessage(), e);
        }
    }


    /**
     * Disconnect
     */
    @Disconnect
    public void disconnect() {
        this.email = null;
        this.uid = null;
        this.fullName = null;

        // destroy all the cookies
        this.httpClient.getState().clearCookies();
    }

    /**
     * Are we connected
     */
    @ValidateConnection
    public boolean isConnected() {
        for (Cookie cookie : httpClient.getState().getCookies()) {
            if (cookie.getName().equals("logged_in") &&
                    cookie.getValue().equals("true")) {
                return true;
            }
        }

        return false;
    }

    /**
     * Connection ID
     */
    @ConnectionIdentifier
    public String connectionId() {
        for (Cookie cookie : httpClient.getState().getCookies()) {
            if (cookie.getName().equals("token_hash")) {
                return cookie.getValue();
            }
        }

        return null;
    }

    /**
     * Get a specific topic
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-topic}
     *
     * @param idOrSlug Topic Slug or Id
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/topic/{idOrSlug}.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract Topic getTopic(@RestUriParam("idOrSlug") String idOrSlug) throws IOException;

    /**
     * Get all topics in all public Get Satisfaction communities
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-topics}
     *
     * @param search           Search for a particular string
     * @param sortBy           Sort criteria
     * @param style            Filter by style
     * @param activeSince      Filter by the time of the last activity in the community. Time is set in seconds since epoch.
     * @param userId           Filter by used id
     * @param products         Filter by product name in API queries where the company is specified (for example, /companies/[company name or company ID]/topics)
     * @param tag              Filter by tag
     * @param status           Filter by status
     * @param userDefinedCodes Filter by User Defined Code (in communities with UDCs enabled)
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/topics.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Topic> getTopics(@RestQueryParam("search") @Optional String search,
                                          @RestQueryParam("sort") @Optional SortCriteria sortBy,
                                          @RestQueryParam("style") @Optional Style style,
                                          @RestQueryParam("active_since") @Optional Long activeSince,
                                          @RestQueryParam("user_id") @Optional String userId,
                                          @RestQueryParam(value = "product", separatedBy = ",") @Optional List<String> products,
                                          @RestQueryParam("tag") @Optional String tag,
                                          @RestQueryParam(value = "status", separatedBy = ",") @Optional List<Status> status,
                                          @RestQueryParam(value = "user_defined_code", separatedBy = ",") @Optional List<String> userDefinedCodes) throws IOException;

    /**
     * Get a topic in a particular community
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-topic-by-company}
     *
     * @param idOrSlug Topic Slug or Id
     * @param company  Company Id
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/companies/{company}/topics/{idOrSlug}.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract Topic getTopicByCompany(@RestUriParam("company") String company, @RestUriParam("idOrSlug") String idOrSlug) throws IOException;

    /**
     * Get all topics in a particular community
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-topics-by-company}
     *
     * @param company Company Id
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/companies/{company}/topics.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Topic> getTopicsByCompany(@RestUriParam("company") String company) throws IOException;

    /**
     * Get all topics in a particular community for a specific product
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-topics-by-company-and-product}
     *
     * @param company Company Id
     * @param product Product Id
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/companies/{company}/products/{product}/topics.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Topic> getTopicsByCompanyAndProduct(@RestUriParam("company") String company, @RestUriParam("product") String product) throws IOException;

    /**
     * Get all products in a particular community
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-products-by-company}
     *
     * @param company Company Id
     * @return A JSON representing a topic
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/companies/{company}/products.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Product> getProductsByCompany(@RestUriParam("company") String company) throws IOException;

    /**
     * Get all replies
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-replies}
     *
     * @param filterBy        Filter by
     * @param includeComments Ixclude comments from the returned replies
     * @return A JSON representing a reply
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/replies.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Reply> getReplies(@RestQueryParam("filter") @Optional ReplyFilterCriteria filterBy,
                                           @RestQueryParam("include_comments") @Optional @Default("false") Boolean includeComments) throws IOException;

    /**
     * Get all replies for a topic
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-replies-by-topic}
     *
     * @param idOrSlug        Topic Slug or Id
     * @param filterBy        Filter by
     * @param includeComments Ixclude comments from the returned replies
     * @return A JSON representing a reply
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/topics/{idOrSlug}/replies.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Reply> getRepliesByTopic(@RestUriParam("idOrSlug") String idOrSlug,
                                                  @RestQueryParam("filter") @Optional ReplyFilterCriteria filterBy,
                                                  @RestQueryParam("include_comments") @Optional @Default("false") Boolean includeComments) throws IOException;

    /**
     * Get all replies made by a user
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:get-replies-by-user}
     *
     * @param userId          User Id
     * @param filterBy        Filter by
     * @param includeComments Ixclude comments from the returned replies
     * @return A JSON representing a reply
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/people/{userId}/replies.json", method = org.mule.api.annotations.rest.HttpMethod.GET)
    @RestExceptionOn(statusCodeIsNot = {200})
    public abstract List<Reply> getRepliesByUser(@RestUriParam("userId") String userId,
                                                 @RestQueryParam("filter") @Optional ReplyFilterCriteria filterBy,
                                                 @RestQueryParam("include_comments") @Optional @Default("false") Boolean includeComments) throws IOException;

    /**
     * Create topic at company
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:create-topic-at-company}
     *
     * @param companyId Company Id
     * @param topic     Topic to create
     * @return A JSON representing a reply
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/companies/{companyId}/topics",
            method = org.mule.api.annotations.rest.HttpMethod.POST,
            contentType = "application/json")
    @RestExceptionOn(statusCodeIsNot = {200, 201, 202})
    public abstract String createTopicAtCompany(@RestUriParam("companyId") String companyId,
                                                Topic topic) throws IOException;

    /**
     * Create reply at company
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:create-reply-for-topic}
     *
     * @param idOrSlug  Topic Id or Slug
     * @param reply     Reply to reply
     * @return A JSON representing a reply
     * @throws IOException If there is a communication error
     */
    @Processor
    @RestCall(uri = "http://api.getsatisfaction.com/topics/{idOrSlug}/replies",
            method = org.mule.api.annotations.rest.HttpMethod.POST,
            contentType = "application/json")
    @RestExceptionOn(statusCodeIsNot = {200, 201, 202})
    public abstract String createReplyForTopic(@RestUriParam("idOrSlug") String idOrSlug,
                                               Reply reply) throws IOException;


    /**
     * Create a JSON representation of a {@link Topic} object
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-topic-to-json}
     *
     * @param topic Topic to transform
     * @return A JSON representation of a {@link Topic} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {Topic.class})
    public static String transformTopicToJson(Topic topic) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return "{ \"topic\": " + mapper.writeValueAsString(topic) + " }";
    }

    /**
     * Create a JSON representation of a {@link Reply} object
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-reply-to-json}
     *
     * @param reply Reply to transform
     * @return A JSON representation of a {@link Reply} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {Reply.class})
    public static String transformReplyToJson(Reply reply) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return "{ \"reply\": " + mapper.writeValueAsString(reply) + " }";
    }

    /**
     * Transform JSON to a list of topics
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-json-to-topics}
     *
     * @param json JSON string to transform
     * @return A list of {@link Topic} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {String.class})
    public static List<Topic> transformJsonToTopics(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        SearchResults<List<Topic>> result = mapper.readValue(json, new TypeReference<SearchResults<List<Topic>>>() {
        });

        return result.getData();
    }

    /**
     * Transform JSON to a topic
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-json-to-topic}
     *
     * @param json JSON string to transform
     * @return A {@link Topic} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {String.class})
    public static Topic transformJsonToTopic(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Topic result = mapper.readValue(json, Topic.class);

        return result;
    }

    /**
     * Transform JSON to a list of products
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-json-to-products}
     *
     * @param json JSON string to transform
     * @return A list of {@link Product} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {String.class})
    public static List<Product> transformJsonToProducts(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        SearchResults<List<Product>> result = mapper.readValue(json, new TypeReference<SearchResults<List<Product>>>() {
        });

        return result.getData();
    }

    /**
     * Transform JSON to a list of replies
     * <p/>
     * {@sample.xml ../../../doc/mule-module-getsatisfaction.xml.sample getsatisfaction:transform-json-to-replies}
     *
     * @param json JSON string to transform
     * @return A list of {@link Reply} object
     * @throws IOException
     */
    @Transformer(sourceTypes = {String.class})
    public static List<Reply> transformJsonToReplies(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        SearchResults<List<Reply>> result = mapper.readValue(json, new TypeReference<SearchResults<List<Reply>>>() {
        });

        return result.getData();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
