/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.getsatisfaction.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Product implements Serializable
{
    private long id;
    private String name;
    
    /**
     * 
     */
    public Product()
    {
        this(null);
    }
       
    public Product(String name)
    {
        super();
        setName(name);
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
    
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }
}


