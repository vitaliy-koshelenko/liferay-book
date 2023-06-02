package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.LECTURE;
import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.LECTURE_ID;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/view_lecture"
	},
	service = MVCRenderCommand.class
)
public class ViewLectureMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long lectureId = ParamUtil.getLong(renderRequest, LECTURE_ID);
		Lecture lecture = liferayCoursesAPI.getLecture(lectureId);
		renderRequest.setAttribute(LECTURE, lecture);
		return LiferayCoursesPortletKeys.VIEW_LECTURE_JSP;
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}