/**
 * Mule GetStaisfaction Connector
 *
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

import java.util.LinkedList;
import java.util.List;

import org.mule.module.getsatisfaction.model.Style;
import org.mule.module.getsatisfaction.model.Topic;

Topic t = new Topic();

t.setSubject("MuleSoft and the origin of the Universe");
t.setStyle(Style.IDEA);

List<String> keywords = new LinkedList<String>();
keywords.add("MuleSoft");
keywords.add("Universe");
t.setKeywords(keywords);

return t;