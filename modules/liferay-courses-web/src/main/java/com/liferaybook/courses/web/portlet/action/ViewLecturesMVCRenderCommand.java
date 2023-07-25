package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.MyLiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Objects;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.COURSE;
import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.COURSE_ID;

@Component(
	property = {
		"javax.portlet.name=" + MyLiferayCoursesPortletKeys.PORTLET_ID,
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"mvc.command.name=" + CourseMVCCommandKeys.COURSE_LECTURES
	},
	service = MVCRenderCommand.class
)
public class ViewLecturesMVCRenderCommand implements MVCRenderCommand, CoursesMVCCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		Course course = courseLocalService.fetchCourse(courseId);
		renderRequest.setAttribute(COURSE, course);
		renderRequest.setAttribute(LectureLocalService.class.getName(), lectureLocalService);
		boolean isAdminPortlet = Objects.equals(getPortletId(renderRequest), LiferayCoursesAdminPortletKeys.PORTLET_ID);
		if (isAdminPortlet) {
			return LiferayCoursesAdminPortletKeys.VIEW_LECTURES_JSP;
		} else {
			return MyLiferayCoursesPortletKeys.VIEW_LECTURES_JSP;
		}
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LectureLocalService lectureLocalService;

}