package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.exception.InfoItemPermissionException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemPermissionProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = InfoItemPermissionProvider.class)
public class CourseInfoItemPermissionProvider implements InfoItemPermissionProvider<Course> {

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, InfoItemReference infoItemReference, String actionId) throws InfoItemPermissionException {
        Course course = null;
        InfoItemIdentifier infoItemIdentifier = infoItemReference.getInfoItemIdentifier();
        if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
            long courseId = ((ClassPKInfoItemIdentifier) infoItemIdentifier).getClassPK();
            course = courseLocalService.fetchCourse(courseId);
        } else if (infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier) {
            GroupUrlTitleInfoItemIdentifier identifier = (GroupUrlTitleInfoItemIdentifier) infoItemIdentifier;
            course = courseLocalService.getCourseByUrlTitle(identifier.getGroupId(), identifier.getUrlTitle());
        }
        return course != null && hasPermission(permissionChecker, course, actionId);
    }

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, Course course, String actionId) throws InfoItemPermissionException {
        try {
            return courseModelResourcePermission.contains(permissionChecker, course, actionId);
        }
        catch (PortalException portalException) {
            throw new InfoItemPermissionException(course.getCourseId(), portalException);
        }
    }

    @Reference
    private CourseLocalService courseLocalService;
    @Reference(target = "(model.class.name=com.liferaybook.courses.manager.model.Course)")
    private ModelResourcePermission<Course> courseModelResourcePermission;
}