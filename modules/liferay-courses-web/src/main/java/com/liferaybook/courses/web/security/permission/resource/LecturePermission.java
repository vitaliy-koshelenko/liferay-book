package com.liferaybook.courses.web.security.permission.resource;

import com.liferay.osgi.util.service.Snapshot;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Lecture;

public class LecturePermission {

	public static boolean contains(PermissionChecker permissionChecker, Lecture lecture, String actionId) throws PortalException {
		ModelResourcePermission<Lecture> modelResourcePermission = SNAPSHOT.get();
		return modelResourcePermission.contains(permissionChecker, lecture, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long lectured, String actionId) throws PortalException {
		ModelResourcePermission<Lecture> modelResourcePermission = SNAPSHOT.get();
		return modelResourcePermission.contains(permissionChecker, lectured, actionId);
	}

	private static final Snapshot<ModelResourcePermission<Lecture>> SNAPSHOT =
			new Snapshot<>(LecturePermission.class, Snapshot.cast(ModelResourcePermission.class),
			"(model.class.name=" + Lecture.class.getName() + ")");

}