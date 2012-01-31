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

public enum Status {
    /**
     * None
     */
    NONE("question"),
    /**
     * Pending
     */
    PENDING("pending"),
    /**
     * Active
     */
    ACTIVE("active"),
    /**
     * Complete
     */
    COMPLETE("complete"),
    /**
     * Rejected
     */
    REJECTED("rejected");

    private String code;

    private Status(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
