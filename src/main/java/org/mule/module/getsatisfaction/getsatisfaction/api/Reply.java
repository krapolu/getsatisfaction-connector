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

public class Reply extends Post
{
    private Topic topic;
    private String content;
    private Emotitag emotitag;
    
    /**
     * 
     */
    public Reply()
    {
    }

    public Topic getTopic()
    {
        return topic;
    }

    public void setTopic(Topic topic)
    {
        this.topic = topic;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Emotitag getEmotitag()
    {
        return emotitag;
    }

    public void setEmotitag(Emotitag emotitag)
    {
        this.emotitag = emotitag;
    }

    public String toString()
    {
        return getTopic() + " -> " + getUser() + ": " + getContent();
    }    
}


