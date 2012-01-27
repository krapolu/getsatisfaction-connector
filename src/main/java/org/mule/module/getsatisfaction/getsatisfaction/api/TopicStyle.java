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

public enum TopicStyle
{
    QUESTION("question"),
    IDEA("idea"),
    PROBLEM("problem"),
    PRAISE("praise"),
    UPDATE("update");
    
    String type;
    
    private TopicStyle(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String toString()
    {
        return getType();
    }
}


