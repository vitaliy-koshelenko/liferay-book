package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCourse;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/view_lectures"
	},
	service = MVCRenderCommand.class
)
public class ViewCourseLecturesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long courseId = ParamUtil.getLong(renderRequest, "courseId");
		LiferayCourse course = liferayCoursesAPI.getCourse(courseId);
		renderRequest.setAttribute("course", course);
		renderRequest.setAttribute(LiferayCoursesAPI.class.getName(), liferayCoursesAPI);
		return LiferayCoursesAdminPortletKeys.VIEW_LECTURES_JSP;
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}