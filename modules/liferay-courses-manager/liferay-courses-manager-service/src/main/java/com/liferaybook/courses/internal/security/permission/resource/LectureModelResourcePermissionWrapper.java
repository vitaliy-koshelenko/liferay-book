package com.liferaybook.courses.internal.security.permission.resource;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.*;
import com.liferaybook.courses.manager.constants.CoursesConstants;
import com.liferaybook.courses.manager.constants.CoursesPortletKeys;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Lecture",
	service = ModelResourcePermission.class
)
public class LectureModelResourcePermissionWrapper extends BaseModelResourcePermissionWrapper<Lecture> {

	@Override
	protected ModelResourcePermission<Lecture> doGetModelResourcePermission() {
		return ModelResourcePermissionFactory.create(
				Lecture.class, Lecture::getLectureId,
				lectureLocalService::getLecture,
				portletResourcePermission,
				(modelResourcePermission, consumer) -> consumer.accept(
						new StagedModelPermissionLogic<>(stagingPermission, CoursesPortletKeys.COURSES, Lecture::getLectureId))
		);
	}

	@Reference
	private StagingPermission stagingPermission;
	@Reference
	private LectureLocalService lectureLocalService;
	@Reference(target = "(resource.name=" + CoursesConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission portletResourcePermission;

}