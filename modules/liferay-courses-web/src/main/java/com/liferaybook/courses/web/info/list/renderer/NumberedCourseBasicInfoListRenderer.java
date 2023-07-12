package com.liferaybook.courses.web.info.list.renderer;

import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.info.taglib.list.renderer.NumberedBasicInfoListRenderer;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;

@Component(service = InfoListRenderer.class)
public class NumberedCourseBasicInfoListRenderer extends BaseCourseBasicInfoListRenderer
	implements NumberedBasicInfoListRenderer<Course> {

}