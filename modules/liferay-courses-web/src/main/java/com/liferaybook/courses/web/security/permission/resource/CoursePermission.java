package com.liferaybook.courses.web.security.permission.resource;

import com.liferay.osgi.util.service.Snapshot;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Course;

public class CoursePermission {

	public static boolean contains(PermissionChecker permissionChecker, Course course, String actionId) throws PortalException {
		ModelResourcePermission<Course> modelResourcePermission = SNAPSHOT.get();
		return modelResourcePermission.contains(permissionChecker, course, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long courseId, String actionId) throws PortalException {
		ModelResourcePermission<Course> modelResourcePermission = SNAPSHOT.get();
		return modelResourcePermission.contains(permissionChecker, courseId, actionId);
	}

	private static final Snapshot<ModelResourcePermission<Course>> SNAPSHOT =
			new Snapshot<>(CoursePermission.class, Snapshot.cast(ModelResourcePermission.class),
			"(model.class.name=" + Course.class.getName() + ")");
}