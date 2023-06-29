package com.liferaybook.courses.web.struts;

import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.struts.FindStrutsAction;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;

@Component(property = "path=/courses/find_course", service = StrutsAction.class)
public class FindCourseStrutsAction extends FindStrutsAction {

	@Override
	public long getGroupId(long primaryKey) {
		Course course = courseLocalService.fetchCourse(primaryKey);
		return course.getGroupId();
	}

	@Override
	public String getPrimaryKeyParameterName() {
		return "courseId";
	}

	@Override
	public void setPrimaryKeyParameter(PortletURL portletURL, long primaryKey) {
		Course course = courseLocalService.fetchCourse(primaryKey);
		portletURL.getRenderParameters().setValue("courseId", String.valueOf(course.getCourseId()));
	}

	@Override
	protected void addRequiredParameters(HttpServletRequest httpServletRequest, String portletId, PortletURL portletURL) {
		portletURL.getRenderParameters().setValue("mvcRenderCommandName", "/courses/view_course");
	}

	@Override
	protected PortletLayoutFinder getPortletLayoutFinder() {
		return _portletLayoutFinder;
	}

	@Reference
	private CourseLocalService courseLocalService;

	@Reference(target = "(model.class.name=com.liferaybook.courses.manager.model.Course)")
	private PortletLayoutFinder _portletLayoutFinder;

}