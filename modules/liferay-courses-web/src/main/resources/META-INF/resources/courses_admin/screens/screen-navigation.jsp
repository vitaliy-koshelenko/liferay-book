<%
    PortletURL currentURL = PortletURLBuilder.create(renderResponse.createRenderURL())
            .setMVCRenderCommandName("/")
            .buildPortletURL();
%>
<liferay-frontend:screen-navigation
        key="<%= CoursesScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY %>"
        portletURL="<%= currentURL %>" />