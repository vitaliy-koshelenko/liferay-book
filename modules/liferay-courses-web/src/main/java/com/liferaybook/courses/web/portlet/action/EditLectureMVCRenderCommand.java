package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

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
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		long lectureId = ParamUtil.getLong(renderRequest, LECTURE_ID);
		Lecture lecture = lectureLocalService.fetchLecture(lectureId);
		renderRequest.setAttribute(LECTURE, lecture);
		renderRequest.setAttribute(COURSE_ID, courseId);
		return LiferayCoursesAdminPortletKeys.EDIT_LECTURE_JSP;
	}

	@Reference
	private LectureLocalService lectureLocalService;

}