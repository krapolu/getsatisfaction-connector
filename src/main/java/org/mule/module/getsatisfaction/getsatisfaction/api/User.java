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

public class User
{
    private String uid;
    private String fullName;
    private String email;
    
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
    
    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String toString() {
        return getEmail();
    }

}


