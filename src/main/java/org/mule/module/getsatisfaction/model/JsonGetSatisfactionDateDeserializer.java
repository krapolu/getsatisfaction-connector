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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;

public class JsonGetSatisfactionDateDeserializer extends JsonDeserializer<Date>
{
    private final static String FORMAT = "yyyy/MM/dd HH:mm:ss z";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT);
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        String date = jp.getText();
        try
        {
            return DATE_FORMAT.parse(date);
        }
        catch(ParseException ex)
        {
            throw new JsonMappingException("Cannot convert [" + date + "] with format [" + FORMAT + "]. " + ex.getMessage());
        }
    }


}


