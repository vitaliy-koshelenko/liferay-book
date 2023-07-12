package com.liferaybook.courses.web.info.item.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "service.ranking:Integer=100", service = InfoItemRenderer.class
)
public class CourseAbstractInfoItemRenderer extends BaseCourseInfoItemRenderer {

    @Override
    public String getLabelKey() {
        return ABSTRACT_LABEL_KEY;
    }

    @Override
    public String getJspName() {
        return ABSTRACT_JSP;
    }

}