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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Emotitag implements Serializable
{
   
    private String face;
    private String feeling;
    private int intensity;
    
    /**
     * 
     */
    public Emotitag()
    {
    }

    @JsonProperty("face")
    public String getFace()
    {
        return face;
    }

    @JsonProperty("face")
    public void setFace(String face)
    {
        this.face = face;
    }

    @JsonProperty("feeling")
    public String getFeeling()
    {
        return feeling;
    }

    @JsonProperty("feeling")
    public void setFeeling(String feeling)
    {
        this.feeling = feeling;
    }

    @JsonProperty("intensity")
    public int getIntensity()
    {
        return intensity;
    }

    @JsonProperty("intensity")
    public void setIntensity(int intensity)
    {
        this.intensity = intensity;
    }

}


