package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.servlet.ServletRequest;

public class CourseItemSelectorViewDescriptor implements ItemSelectorViewDescriptor<Course> {

    private final ServletRequest servletRequest;
    private final PortletURL portletURL;
    private final CourseLocalService courseLocalService;

    public CourseItemSelectorViewDescriptor(ServletRequest servletRequest, PortletURL portletURL,
                                            CourseLocalService courseLocalService) {
        this.servletRequest = servletRequest;
        this.portletURL = portletURL;
        this.courseLocalService = courseLocalService;
    }

    @Override
    public ItemDescriptor getItemDescriptor(Course course) {
        return new CourseItemDescriptor(course);
    }

    @Override
    public ItemSelectorReturnType getItemSelectorReturnType() {
        return new CourseItemSelectorReturnType();
    }

    @Override
    public SearchContainer<Course> getSearchContainer() {
        PortletRequest portletRequest = (PortletRequest)servletRequest.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        SearchContainer<Course> searchContainer = new SearchContainer<>(portletRequest, portletURL, null, null);
        long groupId = themeDisplay.getScopeGroupId();
        searchContainer.setResultsAndTotal(courseLocalService.getGroupCourses(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
        return searchContainer;
    }

    @Override
    public boolean isShowBreadcrumb() {
        return false;
    }

}
