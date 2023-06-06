package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.constants.LiferayCourseDisplayPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesListPortletKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.COURSE;

@Component(
		property = {
				"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCoursesListPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCourseDisplayPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
				"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		String viewPath = getViewPath(renderRequest);
		if (LiferayCourseDisplayPortletKeys.VIEW_JSP.equals(viewPath)) {
			PortletPreferences preferences = renderRequest.getPreferences();
			long courseId = PrefsParamUtil.getLong(preferences, renderRequest, "courseId");
			Course course = liferayCoursesAPI.getCourse(courseId);
			renderRequest.setAttribute(COURSE, course);
		} else {
			renderRequest.setAttribute(LiferayCoursesAPI.class.getName(), liferayCoursesAPI);
		}
		return viewPath;
	}

	private String getViewPath(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletName = portletDisplay.getPortletName();
		if (LiferayCoursesAdminPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCoursesAdminPortletKeys.VIEW_JSP;
		} else if (LiferayCoursesListPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCoursesListPortletKeys.VIEW_JSP;
		} else if (LiferayCourseDisplayPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCourseDisplayPortletKeys.VIEW_JSP;
		} else {
			return LiferayCoursesPortletKeys.VIEW_JSP;
		}
	}

	@Reference
	private LiferayCoursesAPI liferayCoursesAPI;

}