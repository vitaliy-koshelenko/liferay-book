package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.Objects;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
	property = {
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"mvc.command.name=/courses/view_lectures"
	},
	service = MVCRenderCommand.class
)
public class ViewLecturesMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		Course course = liferayCoursesAPI.getCourse(courseId);
		renderRequest.setAttribute(COURSE, course);
		renderRequest.setAttribute(LiferayCoursesAPI.class.getName(), liferayCoursesAPI);
		boolean isAdminPortlet = Objects.equals(getPortletId(renderRequest), LiferayCoursesAdminPortletKeys.PORTLET_ID);
		if (isAdminPortlet) {
			return LiferayCoursesAdminPortletKeys.VIEW_LECTURES_JSP;
		} else {
			return LiferayCoursesPortletKeys.VIEW_LECTURES_JSP;
		}
	}

	private String getPortletId(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		return portletDisplay.getPortletName();
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}