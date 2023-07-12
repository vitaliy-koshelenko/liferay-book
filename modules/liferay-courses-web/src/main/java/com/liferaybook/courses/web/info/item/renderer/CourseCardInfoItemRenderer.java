package com.liferaybook.courses.web.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "service.ranking:Integer=300", service = InfoItemRenderer.class
)
public class CourseCardInfoItemRenderer extends BaseCourseInfoItemRenderer {
	@Override
	public String getLabelKey() {
		return CARD_LABEL_KEY;
	}

	@Override
	public String getJspName() {
		return CARD_JSP;
	}
}