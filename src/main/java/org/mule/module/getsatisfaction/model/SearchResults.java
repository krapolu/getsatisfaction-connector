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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResults<T> implements Serializable
{
    private int total;
    private T data;
    
    /**
     * 
     */
    public SearchResults()
    {
    }

    @JsonProperty("total")
    public int getTotal()
    {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total)
    {
        this.total = total;
    }

    @JsonProperty("data")
    public T getData()
    {
        return data;
    }

    @JsonProperty("data")
    public void setData(T data)
    {
        this.data = data;
    }

}


