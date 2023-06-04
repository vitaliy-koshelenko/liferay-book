package com.liferaybook.courses.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferaybook.courses.web.constants.LiferayCoursesListPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=" + LiferayCoursesListPortletKeys.CATEGORY_NAME,
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.name=" + LiferayCoursesListPortletKeys.PORTLET_ID,
		"javax.portlet.display-name=" + LiferayCoursesListPortletKeys.DISPLAY_NAME,
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=" + LiferayCoursesListPortletKeys.VIEW_JSP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"com.liferay.portlet.css-class-wrapper=liferay-courses-list-wrapper",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class LiferayCoursesListPortlet extends MVCPortlet {

}