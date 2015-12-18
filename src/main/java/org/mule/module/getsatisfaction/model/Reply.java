/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.getsatisfaction.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Reply extends Post implements Serializable
{
    
    private String content;
    
    private Emotitag emotitag;

    private Date createdAt;
    private boolean employee;
    private boolean champion;
    private Author author;
    private String url;
    private int starCount;
    private boolean starPromoted;
    private boolean starred;
    private boolean companyPromoted;
    private int commentCount;
    private String commentsUrl;
    private long id;
    private long topicId;
    
    /**
     * 
     */
    public Reply()
    {
    }
    
    @JsonProperty("content")
    public String getContent()
    {
        return content;
    }
    
    @JsonProperty("content")
    public void setContent(String content)
    {
        this.content = content;
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

    public String toString()
    {
        return getTopicId() + " -> " + getUser() + ": " + getContent();
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
    public int getStarCount()
    {
        return starCount;
    }

    @JsonProperty("star_count")
    public void setStarCount(int starCount)
    {
        this.starCount = starCount;
    }
    
    @JsonIgnore
    public boolean isStarPromoted()
    {
        return starPromoted;
    }

    @JsonProperty("star_promoted")
    public void setStarPromoted(boolean starPromoted)
    {
        this.starPromoted = starPromoted;
    }
    
    @JsonIgnore
    public boolean isStarred()
    {
        return starred;
    }

    @JsonProperty("starred")
    public void setStarred(boolean starred)
    {
        this.starred = starred;
    }
    
    @JsonIgnore
    public boolean isCompanyPromoted()
    {
        return companyPromoted;
    }

    @JsonProperty("company_promoted")
    public void setCompanyPromoted(boolean companyPromoted)
    {
        this.companyPromoted = companyPromoted;
    }
    
    @JsonIgnore
    public int getCommentCount()
    {
        return commentCount;
    }

    @JsonProperty("comment_count")
    public void setCommentCount(int commentCount)
    {
        this.commentCount = commentCount;
    }
    
    @JsonIgnore
    public String getCommentsUrl()
    {
        return commentsUrl;
    }

    @JsonProperty("comments_url")
    public void setCommentsUrl(String commentsUrl)
    {
        this.commentsUrl = commentsUrl;
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
    
    @JsonProperty("topic_id")
    public void setTopicId(long topicId)
    {
        this.topicId = topicId;
    }
    
    @JsonIgnore
    public long getTopicId()
    {
        return topicId;
    }

    @Override
    @JsonIgnore
    public boolean isTopic()
    {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isReply()
    {
        return true;
    }
}


