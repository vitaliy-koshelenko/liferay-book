package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/edit_course"
	},
	service = MVCRenderCommand.class
)
public class EditCourseMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse){
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		Course course = liferayCoursesAPI.getCourse(courseId);
		renderRequest.setAttribute(COURSE, course);
		return LiferayCoursesAdminPortletKeys.EDIT_COURSE_JSP;
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}