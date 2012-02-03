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

public class Product
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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}


