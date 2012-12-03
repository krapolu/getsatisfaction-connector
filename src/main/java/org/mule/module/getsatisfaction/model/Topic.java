/**
 * Mule GetStaisfaction Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.getsatisfaction.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Topic extends Post implements Serializable
{
    /** URL for the topic page */
    private String url;
    private String topicUrl;
    private int followerCount;
    private Date createdAt;
    private Date lastActiveAt;
    private String userDefinedCode;
    private String mostRecentActivity;
    private boolean employee;
    private boolean champion;
    private long companyId;
    private Author author;
    private boolean hasPromotedReplies;
    private long id;
    private String subject;
    private String content;
    private Style style;
    private List<Product> products;
    private List<String> keywords;
    private Emotitag emotitag;
    private String status;
    private String slug;
    private boolean following;
    private String meToo;
    private int meTooCount;
    private int replyCount;
    private int activeReplies;
    private int productCount;
    
    /**
     * 
     */
    public Topic()
    {
    }

    @JsonProperty("subject")
    public String getSubject()
    {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    @JsonProperty("additional_detail")
    public String getContent()
    {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content)
    {
        this.content = content;
    }

    @JsonProperty("style")
    public String getStyle()
    {
        return style != null ? style.toString() : null;
    }

    @JsonProperty("style")
    public void setStyle(String style)
    {
        this.style = Style.valueOf(style.toUpperCase());
    }

    @JsonIgnore
    public void setStyle(Style style)
    {
        this.style = style;
    }
    
    @JsonIgnore
    public List<Product> getProducts()
    {
        return products;
    }

    @JsonIgnore
    public void setProducts(List<Product> products)
    {
        this.products = products;
    }

    @JsonProperty("products")
    public String getProductsList()
    {
        if(getProducts() != null)
        {
            StringBuilder sb = new StringBuilder();
            
            for(Iterator<Product> it = getProducts().iterator(); it.hasNext(); )
            {
                sb.append(it.next().getName());
                if(it.hasNext())
                {
                    sb.append(", ");
                }
                
            }
            
            return sb.toString();
        }
        else
        {
            return null;
        }
    }
    
    @JsonIgnore
    public List<String> getKeywords()
    {
        return keywords;
    }

    @JsonIgnore
    public void setKeywords(List<String> keywords)
    {
        this.keywords = keywords;
    }

    @JsonProperty("keywords")
    public String getKeywordsList()
    {
        if(getKeywords() != null)
        {
            StringBuilder sb = new StringBuilder();
            
            for(Iterator<String> it = getKeywords().iterator(); it.hasNext(); )
            {
                sb.append(it.next());
                if(it.hasNext())
                {
                    sb.append(", ");
                }
                
            }
            
            return sb.toString();
        }
        else
        {
            return null;
        }        
    }
    
    @JsonProperty("emotitag")
    public Emotitag getEmotitag()
    {
        return emotitag;
    }

    @JsonProperty("emotitag")
    public void setEmotitag(Emotitag emotitag)
    {
        this.emotitag = emotitag;
    }

    @JsonProperty("id")
    public long getId()
    {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id)
    {
        this.id = id;
    }
    
    @JsonIgnore
    public String getStatus()
    {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String toString()
    {
        return getStyle() + " -> " + getSubject();
    }

    @JsonIgnore
    public String getUrl()
    {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url)
    {
        this.url = url;
    }

    @JsonIgnore
    public String getTopicUrl()
    {
        return topicUrl;
    }

    @JsonProperty("at_sfn")
    public void setTopicUrl(String topicUrl)
    {
        this.topicUrl = topicUrl;
    }

    @JsonIgnore
    public int getFollowerCount()
    {
        return followerCount;
    }

    @JsonProperty("follower_count")
    public void setFollowerCount(int followerCount)
    {
        this.followerCount = followerCount;
    }

    @JsonIgnore
    public Date getCreatedAt()
    {
        return createdAt;
    }

    @JsonProperty("created_at")
    @JsonDeserialize(using=JsonGetSatisfactionDateDeserializer.class)
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Date getLastActiveAt()
    {
        return lastActiveAt;
    }

    @JsonProperty("last_active_at")
    @JsonDeserialize(using=JsonGetSatisfactionDateDeserializer.class)
    public void setLastActiveAt(Date lastActiveAt)
    {
        this.lastActiveAt = lastActiveAt;
    }

    @JsonIgnore
    public String getUserDefinedCode()
    {
        return userDefinedCode;
    }

    @JsonProperty("user_defined_code")
    public void setUserDefinedCode(String userDefinedCode)
    {
        this.userDefinedCode = userDefinedCode;
    }

    @JsonIgnore
    public String getMostRecentActivity()
    {
        return mostRecentActivity;
    }

    @JsonProperty("most_recent_activity")
    public void setMostRecentActivity(String mostRecentActivity)
    {
        this.mostRecentActivity = mostRecentActivity;
    }

    @JsonIgnore
    public boolean isEmployee()
    {
        return employee;
    }

    @JsonProperty("employee")
    public void setEmployee(boolean employee)
    {
        this.employee = employee;
    }

    @JsonIgnore
    public boolean isChampion()
    {
        return champion;
    }

    @JsonProperty("champion")
    public void setChampion(boolean champion)
    {
        this.champion = champion;
    }

    @JsonIgnore
    public long getCompanyId()
    {
        return companyId;
    }

    @JsonProperty("company_id")
    public void setCompanyId(long companyId)
    {
        this.companyId = companyId;
    }

    @JsonIgnore
    public Author getAuthor()
    {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author)
    {
        this.author = author;
    }

    @JsonIgnore
    public boolean isHasPromotedReplies()
    {
        return hasPromotedReplies;
    }

    @JsonProperty("has_promoted_replies")
    public void setHasPromotedReplies(boolean hasPromotedReplies)
    {
        this.hasPromotedReplies = hasPromotedReplies;
    }

    @JsonIgnore
    public String getSlug()
    {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug)
    {
        this.slug = slug;
    }

    @JsonIgnore
    public boolean isFollowing()
    {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(boolean following)
    {
        this.following = following;
    }

    @JsonIgnore
    public String getMeToo()
    {
        return meToo;
    }

    @JsonProperty("me_too")
    public void setMeToo(String meToo)
    {
        this.meToo = meToo;
    }

    @JsonIgnore
    public int getMeTooCount()
    {
        return meTooCount;
    }

    @JsonProperty("me_too_count")
    public void setMeTooCount(int meTooCount)
    {
        this.meTooCount = meTooCount;
    }

    @JsonIgnore
    public int getReplyCount()
    {
        return replyCount;
    }

    @JsonProperty("reply_count")
    public void setReplyCount(int replyCount)
    {
        this.replyCount = replyCount;
    }

    @JsonIgnore
    public int getActiveReplies()
    {
        return activeReplies;
    }

    @JsonProperty("active_replies")
    public void setActiveReplies(int activeReplies)
    {
        this.activeReplies = activeReplies;
    }

    @JsonIgnore
    public int getProductCount()
    {
        return productCount;
    }

    @JsonProperty("product_count")
    public void setProductCount(int productCount)
    {
        this.productCount = productCount;
    }
    
    @Override
    @JsonIgnore
    public boolean isTopic()
    {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isReply()
    {
        return false;
    }    
}


