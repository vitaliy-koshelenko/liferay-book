package com.liferaybook.courses.web.portlet;

import com.liferay.portal.kernel.portlet.BasePortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;

@Component(
        property = "model.class.name=com.liferaybook.courses.manager.model.Course",
        service = PortletLayoutFinder.class
)
public class LiferayCoursesPortletLayoutFinder extends BasePortletLayoutFinder {

    @Override
    protected String[] getPortletIds() {
        return _PORTLET_IDS;
    }

    private static final String[] _PORTLET_IDS = {
        LiferayCoursesPortletKeys.PORTLET_ID
    };

}