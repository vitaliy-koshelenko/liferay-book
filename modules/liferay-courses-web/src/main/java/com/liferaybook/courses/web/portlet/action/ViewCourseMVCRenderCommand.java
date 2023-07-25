package com.liferaybook.courses.web.portlet.action;

import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.constants.CourseMVCCommandKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import com.liferaybook.courses.web.constants.MyLiferayCoursesPortletKeys;
import com.liferaybook.courses.web.display.context.CourseDisplayContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import static com.liferaybook.courses.web.constants.LiferayCoursesConstants.*;

@Component(
	property = {
		"javax.portlet.name=" + MyLiferayCoursesPortletKeys.PORTLET_ID,
		"javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
		"mvc.command.name=" + CourseMVCCommandKeys.COURSE_DETAILS
	},
	service = MVCRenderCommand.class
)
public class ViewCourseMVCRenderCommand implements MVCRenderCommand, CoursesMVCCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) {
		long courseId = ParamUtil.getLong(renderRequest, COURSE_ID);
		Course course = courseLocalService.fetchCourse(courseId);
		if (course == null) {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long groupId = themeDisplay.getScopeGroupId();
			String urlTitle = ParamUtil.getString(renderRequest, URL_TITLE);
			course = courseLocalService.getCourseByUrlTitle(groupId, urlTitle);
		}
		renderRequest.setAttribute(COURSE, course);

		incrementViewCount(course);

		String portletName = getPortletId(renderRequest);
		if (LiferayCoursesPortletKeys.PORTLET_ID.equals(portletName)) {
			CourseDisplayContext courseContext = new CourseDisplayContext(course, renderRequest);
			renderRequest.setAttribute(COURSE_CONTEXT, courseContext);
			return LiferayCoursesPortletKeys.VIEW_COURSE_JSP;
		} else {
			return MyLiferayCoursesPortletKeys.VIEW_COURSE_JSP;
		}
	}

	private void incrementViewCount(Course course) {
		try {
			AssetEntry assetEntry = course.getAssetEntry();
			assetEntryService.incrementViewCounter(assetEntry);
		} catch (Exception e) {
			_log.error("Failed to increment view counter for Course, cause: " + e.getMessage());
		}
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private AssetEntryService assetEntryService;

	private static final Log _log = LogFactoryUtil.getLog(PanelCategoryRegistry.class);

}