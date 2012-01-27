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


public class AuthenticationContext
{
    private String csrfParam;
    private String csrfToken;
    
    /**
     * 
     */
    public AuthenticationContext()
    {
    }

    public String getCsrfParam()
    {
        return csrfParam;
    }

    public void setCsrfParam(String csrfParam)
    {
        this.csrfParam = csrfParam;
    }

    public String getCsrfToken()
    {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken)
    {
        this.csrfToken = csrfToken;
    }
}


