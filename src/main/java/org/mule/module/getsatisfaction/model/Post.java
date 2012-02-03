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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class Post
{

    private User user;
    
    /**
     * 
     */
    public Post()
    {
    }

    @JsonIgnore
    public User getUser()
    {
        return user;
    }

    @JsonProperty("authenticated_user")
    public void setUser(User user)
    {
        this.user = user;
    }

    public abstract boolean isTopic();
    
    public abstract boolean isReply();
}


