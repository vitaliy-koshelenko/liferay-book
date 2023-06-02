package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.api.LiferayLecture;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/edit_lecture"
	},
	service = MVCRenderCommand.class
)
public class EditLectureMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse){
		long courseId = ParamUtil.getLong(renderRequest, "courseId");
		long lectureId = ParamUtil.getLong(renderRequest, "lectureId");
		LiferayLecture lecture = liferayCoursesAPI.getLecture(lectureId);
		renderRequest.setAttribute("lecture", lecture);
		renderRequest.setAttribute("courseId", courseId);
		return LiferayCoursesAdminPortletKeys.EDIT_LECTURE_JSP;
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}