package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

public interface CoursesMVCCommand {

    String MVC_RENDER_COMMAND_NAME = "mvcRenderCommandName";

    default String getPortletId(PortletRequest portletRequest) {
        ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
        return portletDisplay.getId();
    }

}
