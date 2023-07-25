package com.liferaybook.courses.web.security.permission.resource;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Lecture;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = LecturePermission.class)
public class LecturePermission {

	public static boolean contains(PermissionChecker permissionChecker, Lecture lecture, String actionId) throws PortalException {
		return lectureModelResourcePermission.contains(permissionChecker, lecture, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long lectureId, String actionId) throws PortalException {
		return lectureModelResourcePermission.contains(permissionChecker, lectureId, actionId);
	}

	@Reference(
		target = "(model.class.name=com.liferaybook.courses.manager.model.Lecture)",
		unbind = "-"
	)
	protected void setEntryModelPermission(ModelResourcePermission<Lecture> modelResourcePermission) {
		lectureModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Lecture> lectureModelResourcePermission;

}