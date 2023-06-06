package com.liferaybook.courses.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferaybook.courses.web.constants.  LiferayCourseDisplayPortletKeys;
import org.osgi.service.component.annotations.Component;

import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=" +   LiferayCourseDisplayPortletKeys.CATEGORY_NAME,
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.name=" +   LiferayCourseDisplayPortletKeys.PORTLET_ID,
		"javax.portlet.display-name=" +   LiferayCourseDisplayPortletKeys.DISPLAY_NAME,
		"javax.portlet.init-param.template-path=/META-INF/resources/",
		"javax.portlet.init-param.view-template=" +   LiferayCourseDisplayPortletKeys.VIEW_JSP,
		"javax.portlet.init-param.config-template=" +   LiferayCourseDisplayPortletKeys.CONFIG_JSP,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false",
		"com.liferay.portlet.css-class-wrapper=liferay-course-display-wrapper",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class LiferayCourseDisplayPortlet extends MVCPortlet {

}