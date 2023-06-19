package com.liferaybook.courses.web.portlet.action;

import com.liferay.application.list.PanelCategoryRegistry;
import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.configuration.CourseDisplayPortletInstanceConfiguration;
import com.liferaybook.courses.web.constants.LiferayCourseDisplayPortletKeys;
import com.liferaybook.courses.web.item.selector.CourseItemSelectorCriterion;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(
        property = "javax.portlet.name=" + LiferayCourseDisplayPortletKeys.PORTLET_ID,
        service = ConfigurationAction.class
)
public class CourseDisplayConfigurationAction extends DefaultConfigurationAction {

    @Override
    public void include(PortletConfig portletConfig, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
            PortletResponse portletResponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);

            // Item Selector
            ItemSelectorCriterion itemSelectorCriterion = new CourseItemSelectorCriterion();
            PortletURL itemSelectorURL = itemSelector.getItemSelectorURL(
                    RequestBackedPortletURLFactoryUtil.create(portletRequest),
                    portletResponse.getNamespace() + "selectCourse",
                    itemSelectorCriterion);
            request.setAttribute("itemSelectorURL", itemSelectorURL);

            // Configuration
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
            CourseDisplayPortletInstanceConfiguration configuration = ConfigurationProviderUtil.getConfiguration(
                    CourseDisplayPortletInstanceConfiguration.class,
                    new ParameterMapSettingsLocator(
                            request.getParameterMap(),
                            new PortletInstanceSettingsLocator(themeDisplay.getLayout(), portletDisplay.getPortletResource())
                    )
            );
            request.setAttribute("configuration", configuration);

            // Selected Course
            String courseURLTitle = configuration.urlTitle();
            Course course = courseLocalService.getCourseByUrlTitle(themeDisplay.getSiteGroupId(), courseURLTitle);
            request.setAttribute("course", course);

        } catch (Exception e) {
            _log.error(e.getMessage(), e);
        }
        super.include(portletConfig, request, response);
    }

    @Reference
    private ItemSelector itemSelector;
    @Reference
    private CourseLocalService courseLocalService;

    private static final Log _log = LogFactoryUtil.getLog(PanelCategoryRegistry.class);

}