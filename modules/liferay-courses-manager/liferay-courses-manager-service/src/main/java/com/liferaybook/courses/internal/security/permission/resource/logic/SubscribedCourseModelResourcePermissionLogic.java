package com.liferaybook.courses.internal.security.permission.resource.logic;

import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionLogic;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = SubscribedCourseModelResourcePermissionLogic.class)
public class SubscribedCourseModelResourcePermissionLogic implements ModelResourcePermissionLogic<Course> {

    @Override
    public Boolean contains(PermissionChecker permissionChecker, String name, Course course, String actionId) {
        if (ActionKeys.VIEW.equals(actionId)) {
            long userId = permissionChecker.getUserId();
            boolean iSubscribed = courseSubscriptionLocalService.isSubscribed(userId, course.getCourseId());
            if (iSubscribed) {
                return true;
            }
        }
        return null;
    }

    @Reference
    private CourseSubscriptionLocalService courseSubscriptionLocalService;
}