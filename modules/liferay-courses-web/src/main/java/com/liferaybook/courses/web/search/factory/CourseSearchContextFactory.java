package com.liferaybook.courses.web.search.factory;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.web.search.model.CourseSearchContext;
import com.liferaybook.courses.web.search.model.CourseSearchContextKeys;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public class CourseSearchContextFactory {

    public static final String RESET_COMMAND = "reset";

    public static CourseSearchContext getInstance(PortletRequest portletRequest, PortletResponse portletResponse) {

        CourseSearchContext context = new CourseSearchContext(portletRequest, portletResponse);

        ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        context.setCompanyId(themeDisplay.getCompanyId());
        context.setGroupId(themeDisplay.getScopeGroupId());

        String cmd = ParamUtil.getString(portletRequest, Constants.CMD);
        if (RESET_COMMAND.equals(cmd)) {
            context.setSearchTerm(StringPool.BLANK);
            context.setAuthor(StringPool.BLANK);
            context.setCategoryId(0);
        } else {
            context.setSearchTerm(ParamUtil.getString(portletRequest, CourseSearchContextKeys.SEARCH_TERM));
            context.setAuthor(ParamUtil.getString(portletRequest, CourseSearchContextKeys.AUTHOR));
            context.setCategoryId(ParamUtil.getLong(portletRequest, CourseSearchContextKeys.CATEGORY_ID));
        }

        return context;
    }

}