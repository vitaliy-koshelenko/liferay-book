package com.liferaybook.courses.web.application.list;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import com.liferaybook.courses.web.application.list.constants.LiferayBookPanelKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = {
		"panel.app.order:Integer=201",
		"panel.category.key=" + LiferayBookPanelKeys.LIFERAY_BOOK
	},
	service = PanelApp.class
)
public class LiferayCoursesPanelApp extends BasePanelApp {

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Override
	public String getPortletId() {
		return LiferayCoursesAdminPortletKeys.PORTLET_ID;
	}

	@Reference(
		target = "(javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID + ")"
	)
	private Portlet _portlet;

}