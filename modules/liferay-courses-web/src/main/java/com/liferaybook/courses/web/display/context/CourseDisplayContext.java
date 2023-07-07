package com.liferaybook.courses.web.display.context;

import com.liferay.portal.kernel.comment.CommentManagerUtil;
import com.liferay.portal.kernel.comment.Discussion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContextFunction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;

import javax.portlet.PortletRequest;

public class CourseDisplayContext {

    private final Course course;
    private final PortletRequest portletRequest;
    private final ThemeDisplay themeDisplay;
    private Discussion discussion;

    public CourseDisplayContext(Course course, PortletRequest portletRequest) {
        this.course = course;
        this.portletRequest = portletRequest;
        this.themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
    }

    public Discussion getDiscussion() throws PortalException {
        if (discussion == null) {
            discussion = CommentManagerUtil.getDiscussion(
                    themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
                    Course.class.getName(), course.getCourseId(),
                    new ServiceContextFunction(portletRequest));
        }
        return discussion;
    }

}