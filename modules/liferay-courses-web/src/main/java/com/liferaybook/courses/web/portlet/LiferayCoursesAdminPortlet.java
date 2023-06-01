package com.liferaybook.courses.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferaybook.courses.web.constants.LiferayCoursesAdminPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	property = {
		"com.liferay.portlet.display-category=" + LiferayCoursesAdminPortletKeys.CATEGORY_NAME,
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.name=" + LiferayCoursesAdminPortletKeys.PORTLET_ID,
		"javax.portlet.display-name=" + LiferayCoursesAdminPortletKeys.DISPLAY_NAME,
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=" + LiferayCoursesAdminPortletKeys.VIEW_JSP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator",
		"com.liferay.portlet.css-class-wrapper=liferay-courses-admin-wrapper",
		"com.liferay.portlet.header-portlet-css=/courses_admin/css/main.css",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class LiferayCoursesAdminPortlet extends MVCPortlet {

}