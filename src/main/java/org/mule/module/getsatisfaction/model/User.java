/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
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
public class User implements Serializable
{
    private String uid;
    private String fullName;
    private String email;
    
    private boolean following;
    private boolean meToo;
    private boolean starred;
    
    /**
     * 
     */
    public User()
    {
        this(null, null, null);
    }

    public User(String email, String fullName, String uid)
    {
        setEmail(email);
        setFullName(fullName);
        setUid(uid);
    }
    
    @JsonIgnore
    public String getUid()
    {
        return uid;
    }
    
    @JsonIgnore
    public void setUid(String uid)
    {
        this.uid = uid;
    }

    @JsonIgnore
    public String getFullName()
    {
        return fullName;
    }

    @JsonIgnore
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    
    @JsonIgnore
    public String getEmail()
    {
        return email;
    }

    @JsonIgnore
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String toString() {
        return getEmail();
    }

    @JsonIgnore
    public boolean getMeToo()
    {
        return meToo;
    }

    @JsonProperty("me_too")
    public void setMeToo(boolean meToo)
    {
        this.meToo = meToo;
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
    public boolean isStarred()
    {
        return starred;
    }

    @JsonProperty("starred")
    public void setStarred(boolean starred)
    {
        this.starred = starred;
    }    
}


