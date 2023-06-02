package com.liferaybook.courses.web.application.list;

import com.liferay.application.list.BaseJSPPanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import com.liferaybook.courses.web.application.list.constants.LiferayBookPanelKeys;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;

@Component(
	property = {
		"panel.app.order:Integer=201",
		"panel.category.key=" + LiferayBookPanelKeys.LIFERAY_BOOK
	},
	service = PanelApp.class
)
public class LiferayCoursesJSPPanelApp extends BaseJSPPanelApp {

	@Override
	public String getJspPath() {
		return "/liferay_book/info.jsp";
	}

	@Override
	public Portlet getPortlet() {
		return _portlet;
	}

	@Override
	public String getPortletId() {
		return LiferayCoursesAdminPortletKeys.PORTLET_ID;
	}

	@Override
	protected ServletContext getServletContext() {
		return _servletContext;
	}

	@Reference(target = "(javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID + ")")
	private Portlet _portlet;
	@Reference(target = "(osgi.web.symbolicname=com.liferaybook.courses.web)")
	private ServletContext _servletContext;

}