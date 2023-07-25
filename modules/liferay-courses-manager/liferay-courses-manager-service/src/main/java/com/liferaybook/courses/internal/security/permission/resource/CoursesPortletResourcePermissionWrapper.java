package com.liferaybook.courses.internal.security.permission.resource;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.BasePortletResourcePermissionWrapper;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.StagedPortletPermissionLogic;
import com.liferaybook.courses.manager.constants.CoursesConstants;
import com.liferaybook.courses.manager.constants.CoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "resource.name=" + CoursesConstants.RESOURCE_NAME,
	service = PortletResourcePermission.class
)
public class CoursesPortletResourcePermissionWrapper extends BasePortletResourcePermissionWrapper {

	@Override
	protected PortletResourcePermission doGetPortletResourcePermission() {
		return PortletResourcePermissionFactory.create(
				CoursesConstants.RESOURCE_NAME,
				new StagedPortletPermissionLogic(stagingPermission, CoursesPortletKeys.COURSES));
	}

	@Reference
	private StagingPermission stagingPermission;

}