package com.liferaybook.courses.internal.security.permission.resource;

import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
import com.liferay.portal.kernel.security.permission.resource.*;
import com.liferaybook.courses.internal.security.permission.resource.logic.SubscribedCourseModelResourcePermissionLogic;
import com.liferaybook.courses.manager.constants.CoursesConstants;
import com.liferaybook.courses.manager.constants.CoursesPortletKeys;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Course",
	service = ModelResourcePermission.class
)
public class CourseModelResourcePermissionWrapper extends BaseModelResourcePermissionWrapper<Course> {

	@Override
	protected ModelResourcePermission<Course> doGetModelResourcePermission() {
		return ModelResourcePermissionFactory.create(
				Course.class, Course::getCourseId,
				courseLocalService::getCourse,
				portletResourcePermission,
				(modelResourcePermission, consumer) -> {
					consumer.accept(subscribedCourseModelResourcePermissionLogic);
					consumer.accept(new StagedModelPermissionLogic<>(stagingPermission, CoursesPortletKeys.COURSES, Course::getCourseId));
				}
		);
	}

	@Reference
	private StagingPermission stagingPermission;
	@Reference
	private CourseLocalService courseLocalService;
	@Reference(target = "(resource.name=" + CoursesConstants.RESOURCE_NAME + ")")
	private PortletResourcePermission portletResourcePermission;
	@Reference
	private SubscribedCourseModelResourcePermissionLogic subscribedCourseModelResourcePermissionLogic;

}