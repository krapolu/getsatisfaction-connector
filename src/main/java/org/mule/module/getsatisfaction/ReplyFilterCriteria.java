/**
 * Mule GetStaisfaction Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.module.getsatisfaction;

public enum ReplyFilterCriteria {
    /**
     * Return promoted replies (both star promoted and company chosen replies)
     */
    BEST("best"),
    /**
     * Return only star promoted replies
     */
    STAR_PROMOTED("star_promoted"),
    /**
     * Return only company promoted replies
     */
    COMPANY_PROMOTED("company_promoted");

    private String code;

    private ReplyFilterCriteria(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
