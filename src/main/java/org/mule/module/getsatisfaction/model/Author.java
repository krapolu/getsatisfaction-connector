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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Author implements Serializable
{

    private String profileUrl;
    private boolean champion;
    private boolean employee;
    private String canonicalName;
    private String avatarUrlSmall;
    private String avatar;
    private String avatarUrlMedium;
    private String url;
    private String avatarUrlMini;
    private String avatarUrlLarge;
    private String name;
    private long id;
    private String title;
    
    /**
     * 
     */
    public Author()
    {
    }
    
    @JsonIgnore
    public String getProfileUrl()
    {
        return profileUrl;
    }

    @JsonProperty("at_sfn")
    public void setProfileUrl(String profileUrl)
    {
        this.profileUrl = profileUrl;
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
    public String getCanonicalName()
    {
        return canonicalName;
    }

    @JsonProperty("canonical_name")
    public void setCanonicalName(String canonicalName)
    {
        this.canonicalName = canonicalName;
    }

    @JsonIgnore
    public String getAvatarUrlSmall()
    {
        return avatarUrlSmall;
    }

    @JsonProperty("avatar_url_small")
    public void setAvatarUrlSmall(String avatarUrlSmall)
    {
        this.avatarUrlSmall = avatarUrlSmall;
    }

    @JsonIgnore
    public String getAvatar()
    {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    @JsonIgnore
    public String getAvatarUrlMedium()
    {
        return avatarUrlMedium;
    }

    @JsonProperty("avatar_url_medium")
    public void setAvatarUrlMedium(String avatarUrlMedium)
    {
        this.avatarUrlMedium = avatarUrlMedium;
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
    public String getAvatarUrlMini()
    {
        return avatarUrlMini;
    }

    @JsonProperty("avatar_url_mini")
    public void setAvatarUrlMini(String avatarUrlMini)
    {
        this.avatarUrlMini = avatarUrlMini;
    }

    @JsonIgnore
    public String getAvatarUrlLarge()
    {
        return avatarUrlLarge;
    }

    @JsonProperty("avatar_url_large")
    public void setAvatarUrlLarge(String avatarUrlLarge)
    {
        this.avatarUrlLarge = avatarUrlLarge;
    }

    @JsonIgnore
    public String getName()
    {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    @JsonIgnore
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
    public String getTitle()
    {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title)
    {
        this.title = title;
    }

}


