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
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = InfoItemPermissionProvider.class)
public class LectureInfoItemPermissionProvider implements InfoItemPermissionProvider<Lecture> {

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, InfoItemReference infoItemReference, String actionId) throws InfoItemPermissionException {
        Lecture lecture = null;
        InfoItemIdentifier infoItemIdentifier = infoItemReference.getInfoItemIdentifier();
        if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
            long lectureId = ((ClassPKInfoItemIdentifier) infoItemIdentifier).getClassPK();
            lecture = lectureLocalService.fetchLecture(lectureId);
        } else if (infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier) {
            GroupUrlTitleInfoItemIdentifier identifier = (GroupUrlTitleInfoItemIdentifier) infoItemIdentifier;
            lecture = lectureLocalService.getLectureByUrlTitle(identifier.getGroupId(), identifier.getUrlTitle());
        }
        return lecture != null && hasPermission(permissionChecker, lecture, actionId);
    }

    @Override
    public boolean hasPermission(PermissionChecker permissionChecker, Lecture lecture, String actionId) throws InfoItemPermissionException {
        try {
            return lectureModelResourcePermission.contains(permissionChecker, lecture, actionId);
        }
        catch (PortalException portalException) {
            throw new InfoItemPermissionException(lecture.getLectureId(), portalException);
        }
    }

    @Reference
    private LectureLocalService lectureLocalService;
    @Reference(target = "(model.class.name=com.liferaybook.courses.manager.model.Lecture)")
    private ModelResourcePermission<Lecture> lectureModelResourcePermission;

}