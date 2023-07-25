package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import com.liferaybook.courses.web.constants.MyLiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
	property = {
		"javax.portlet.name=" + MyLiferayCoursesPortletKeys.PORTLET_ID,
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"mvc.command.name=" + CourseMVCCommandKeys.VIEW_LECTURE
	},
	service = MVCRenderCommand.class
)
public class ViewLectureMVCRenderCommand implements MVCRenderCommand, CoursesMVCCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long lectureId = ParamUtil.getLong(renderRequest, LECTURE_ID);
		Lecture lecture = lectureLocalService.fetchLecture(lectureId);
		if (lecture == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			String urlTitle = ParamUtil.getString(renderRequest, URL_TITLE);
			lecture = lectureLocalService.getLectureByUrlTitle(groupId, urlTitle);
		}
		renderRequest.setAttribute(LECTURE, lecture);
		String portletId = getPortletId(renderRequest);
		return LiferayCoursesPortletKeys.PORTLET_ID.equals(portletId) ? LiferayCoursesPortletKeys.VIEW_LECTURE_JSP
				: MyLiferayCoursesPortletKeys.VIEW_LECTURE_JSP;
	}

	@Reference
	private LectureLocalService lectureLocalService;

}