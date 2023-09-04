package com.liferaybook.courses.web.portlet.action;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PrefsParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.web.constants.*;
import com.liferaybook.courses.web.search.factory.CourseSearchContextFactory;
import com.liferaybook.courses.web.search.model.CourseDisplayContext;
import com.liferaybook.courses.web.search.model.CourseSearchContext;
import com.liferaybook.courses.web.search.service.CourseSearcher;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import java.util.List;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
		property = {
				"javax.portlet.name=" + MyLiferayCoursesPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCourseDisplayPortletKeys.PORTLET_ID,
				"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
				"mvc.command.name=/",
				"mvc.command.name=" + CourseMVCCommandKeys.SEARCH_COURSES,
				"mvc.command.name=" + CourseMVCCommandKeys.RESET_COURSES
		},
		service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		String viewPath = getViewPath(renderRequest);
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		if (LiferayCourseDisplayPortletKeys.VIEW_JSP.equals(viewPath)) {
			// Course Display
			PortletPreferences preferences = renderRequest.getPreferences();
			String urlTitle = PrefsParamUtil.getString(preferences, renderRequest, URL_TITLE);
			Course course = courseLocalService.getCourseByUrlTitle(groupId, urlTitle);
			renderRequest.setAttribute(COURSE, course);
			String displayStyle = PrefsParamUtil.getString(preferences, renderRequest, DISPLAY_STYLE, DISPLAY_STYLE_TABLE);
			renderRequest.setAttribute(DISPLAY_STYLE, displayStyle);
		} else if (LiferayCoursesPortletKeys.VIEW_JSP.equals(viewPath)) {

			// Courses List
			CourseSearchContext searchContext = CourseSearchContextFactory.getInstance(renderRequest, renderResponse);
			CourseDisplayContext displayContext = courseSearcher.search(searchContext);
			renderRequest.setAttribute(DISPLAY_CONTEXT, displayContext);

			List<String> authors = courseLocalService.getCourseAuthorNames(groupId);
			renderRequest.setAttribute(AUTHORS, authors);

			List<AssetCategory> categories = courseLocalService.getCourseCategories(groupId);
			renderRequest.setAttribute(CATEGORIES, categories);

		} else {
			renderRequest.setAttribute(CourseLocalService.class.getName(), courseLocalService);
			renderRequest.setAttribute(LectureLocalService.class.getName(), lectureLocalService);
		}
		return viewPath;
	}

	private String getViewPath(RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		String portletName = portletDisplay.getPortletName();
		if (LiferayCoursesAdminPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCoursesAdminPortletKeys.VIEW_JSP;
		} else if (LiferayCoursesPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCoursesPortletKeys.VIEW_JSP;
		} else if (LiferayCourseDisplayPortletKeys.PORTLET_ID.equals(portletName)) {
			return LiferayCourseDisplayPortletKeys.VIEW_JSP;
		} else {
			return MyLiferayCoursesPortletKeys.VIEW_JSP;
		}
	}

	@Reference
	private CourseSearcher courseSearcher;
	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LectureLocalService lectureLocalService;

	private static final Log _log = LogFactoryUtil.getLog(ViewMVCRenderCommand.class);
}