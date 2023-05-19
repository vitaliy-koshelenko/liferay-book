package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCourse;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/edit_course"
	},
	service = MVCRenderCommand.class
)
public class EditCourseMVCRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse){
		long courseId = ParamUtil.getLong(renderRequest, "courseId");
		LiferayCourse course = liferayCoursesAPI.getCourse(courseId);
		renderRequest.setAttribute("course", course);
		return LiferayCoursesPortletKeys.EDIT_COURSE_JSP;
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}