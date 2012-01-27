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

public class Emotitag
{
    private String face;
    private String feeling;
    private String intensity;
    
    /**
     * 
     */
    public Emotitag()
    {
    }

    public String getFace()
    {
        return face;
    }

    public void setFace(String face)
    {
        this.face = face;
    }

    public String getFeeling()
    {
        return feeling;
    }

    public void setFeeling(String feeling)
    {
        this.feeling = feeling;
    }

    public String getIntensity()
    {
        return intensity;
    }

    public void setIntensity(String intensity)
    {
        this.intensity = intensity;
    }

}


