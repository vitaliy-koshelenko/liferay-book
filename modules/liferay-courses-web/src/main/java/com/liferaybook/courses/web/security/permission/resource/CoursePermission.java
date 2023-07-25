package com.liferaybook.courses.web.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = CoursePermission.class)
public class CoursePermission {

	public static boolean contains(PermissionChecker permissionChecker, Course course, String actionId) throws PortalException {
		return courseModelResourcePermission.contains(permissionChecker, course, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long courseId, String actionId) throws PortalException {
		return courseModelResourcePermission.contains(permissionChecker, courseId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferaybook.courses.manager.model.Course)",
		unbind = "-"
	)
	protected void setEntryModelPermission(ModelResourcePermission<Course> modelResourcePermission) {
		courseModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Course> courseModelResourcePermission;

}