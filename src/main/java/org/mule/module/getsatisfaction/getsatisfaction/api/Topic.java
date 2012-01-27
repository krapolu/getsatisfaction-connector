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

import java.util.List;

public class Topic extends Post
{
    private String id;
    private String subject;
    private String additionalDetails;
    private TopicStyle style;
    private List<Product> products;
    private List<String> keywords;
    private Emotitag emotitag;
    private String status;
    
    /**
     * 
     */
    public Topic()
    {
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getAdditionalDetails()
    {
        return additionalDetails;
    }

    public void setAdditionalDetails(String additionalDetails)
    {
        this.additionalDetails = additionalDetails;
    }

    public TopicStyle getStyle()
    {
        return style;
    }

    public void setStyle(TopicStyle style)
    {
        this.style = style;
    }

    public List<Product> getProducts()
    {
        return products;
    }

    public void setProducts(List<Product> products)
    {
        this.products = products;
    }

    public List<String> getKeywords()
    {
        return keywords;
    }

    public void setKeywords(List<String> keywords)
    {
        this.keywords = keywords;
    }

    public Emotitag getEmotitag()
    {
        return emotitag;
    }

    public void setEmotitag(Emotitag emotitag)
    {
        this.emotitag = emotitag;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public String toString()
    {
        return getStyle() + " -> " + getSubject();
    }
}


