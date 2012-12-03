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