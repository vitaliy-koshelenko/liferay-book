package com.liferaybook.courses.web.portlet.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferaybook.courses.web.constants.LiferayCourseDisplayPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "javax.portlet.name=" + LiferayCourseDisplayPortletKeys.PORTLET_ID,
        service = ConfigurationAction.class
)
public class CourseDisplayConfigurationAction extends DefaultConfigurationAction {

}