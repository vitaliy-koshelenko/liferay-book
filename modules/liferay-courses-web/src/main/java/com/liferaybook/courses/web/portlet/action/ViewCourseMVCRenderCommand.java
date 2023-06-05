package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.constants.LiferayCoursesListPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"javax.portlet.name=" + LiferayCoursesListPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/view_course"
	},
	service = MVCRenderCommand.class
)
public class ViewCourseMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		Course course = liferayCoursesAPI.getCourse(courseId);
		if (course == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			String urlTitle = ParamUtil.getString(renderRequest, URL_TITLE);
			course = liferayCoursesAPI.getCourse(groupId, urlTitle);
		}
		renderRequest.setAttribute(COURSE, course);
		return getViewPath(renderRequest);
	}

	private String getViewPath(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletName = portletDisplay.getPortletName();
		if (LiferayCoursesListPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCoursesListPortletKeys.VIEW_COURSE_JSP;
		} else {
			return LiferayCoursesPortletKeys.VIEW_COURSE_JSP;
		}
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;
}