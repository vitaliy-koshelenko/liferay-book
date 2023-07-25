package com.liferaybook.courses.web.security.permission.resource;

import com.liferay.osgi.util.service.Snapshot;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferaybook.courses.manager.constants.CoursesConstants;

public class CoursesPermission {

	public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) {
		PortletResourcePermission portletResourcePermission = _portletResourcePermissionSnapshot.get();
		return portletResourcePermission.contains(permissionChecker, groupId, actionId);
	}

	private static final Snapshot<PortletResourcePermission>
		_portletResourcePermissionSnapshot = new Snapshot<>(CoursesPermission.class, PortletResourcePermission.class,
			"(resource.name=" + CoursesConstants.RESOURCE_NAME + ")");

}