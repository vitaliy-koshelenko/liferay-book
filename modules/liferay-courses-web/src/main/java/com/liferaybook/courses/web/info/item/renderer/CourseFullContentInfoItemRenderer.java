package com.liferaybook.courses.web.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "service.ranking:Integer=200", service = InfoItemRenderer.class
)
public class CourseFullContentInfoItemRenderer extends BaseCourseInfoItemRenderer {

	@Override
	public String getLabelKey() {
		return FULL_CONTENT_LABEL_KEY;
	}

	@Override
	public String getJspName() {
		return FULL_CONTENT_JSP_NAME;
	}

}