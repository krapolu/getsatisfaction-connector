/**
 * (c) 2003-2015 MuleSoft, Inc. The software in this package is
 * published under the terms of the CPAL v1.0 license, a copy of which
 * has been included with this distribution in the LICENSE.md file.
 */

package org.mule.module.getsatisfaction.model;

public enum SortCriteria {
    /**
     * Sort by the most recently created topic
     */
    RECENTLY_CREATED("recently_created"),
    /**
     * Sort topics by most recent activity
     */
    RECENTLY_ACTIVE("recently_active"),
    /**
     * Sort by the highest number of topic replies
     */
    MOST_ME_TOOS("most_me_toos"),
    /**
     * Sort by the highest number of topic replies
     */
    MOST_REPLIES("most_replies"),
    /**
     * Filter out all answered questions
     */
    UNANSWERED("unanswered");

    private String code;

    private SortCriteria(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
