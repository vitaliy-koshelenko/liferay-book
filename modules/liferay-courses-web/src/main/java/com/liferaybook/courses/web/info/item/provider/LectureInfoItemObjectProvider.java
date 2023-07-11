package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "info.item.identifier=com.liferay.info.item.ClassPKInfoItemIdentifier",
                "info.item.identifier=com.liferay.info.item.GroupUrlTitleInfoItemIdentifier",
                "service.ranking:Integer=98"
        },
        service = InfoItemObjectProvider.class
)
public class LectureInfoItemObjectProvider implements InfoItemObjectProvider<Lecture> {

    @Override
    public Lecture getInfoItem(InfoItemIdentifier infoItemIdentifier) throws NoSuchInfoItemException {
        if (!(infoItemIdentifier instanceof ClassPKInfoItemIdentifier) && !(infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier)) {
            throw new NoSuchInfoItemException("Unsupported info item identifier type " + infoItemIdentifier);
        }
        Lecture lecture = null;
        if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
            long lectureId = ((ClassPKInfoItemIdentifier) infoItemIdentifier).getClassPK();
            lecture = lectureLocalService.fetchLecture(lectureId);
        } else {
            GroupUrlTitleInfoItemIdentifier identifier = (GroupUrlTitleInfoItemIdentifier) infoItemIdentifier;
            lecture = lectureLocalService.getLectureByUrlTitle(identifier.getGroupId(), identifier.getUrlTitle());
        }
        if (lecture == null) {
            throw new NoSuchInfoItemException("Unable to get lecture with info item identifier " + infoItemIdentifier);
        }
        return lecture;
    }

    @Override
    public Lecture getInfoItem(long classPK) throws NoSuchInfoItemException {
        InfoItemIdentifier infoItemIdentifier = new ClassPKInfoItemIdentifier(classPK);
        return getInfoItem(infoItemIdentifier);
    }

    @Reference
    private LectureLocalService lectureLocalService;

}