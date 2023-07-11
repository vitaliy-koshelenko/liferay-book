package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.item.ClassPKInfoItemIdentifier;
import com.liferay.info.item.GroupUrlTitleInfoItemIdentifier;
import com.liferay.info.item.InfoItemIdentifier;
import com.liferay.info.item.provider.InfoItemObjectProvider;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "info.item.identifier=com.liferay.info.item.ClassPKInfoItemIdentifier",
                "info.item.identifier=com.liferay.info.item.GroupUrlTitleInfoItemIdentifier",
                "service.ranking:Integer=99"
        },
        service = InfoItemObjectProvider.class
)
public class CourseInfoItemObjectProvider implements InfoItemObjectProvider<Course> {

    @Override
    public Course getInfoItem(InfoItemIdentifier infoItemIdentifier) throws NoSuchInfoItemException {
        if (!(infoItemIdentifier instanceof ClassPKInfoItemIdentifier) && !(infoItemIdentifier instanceof GroupUrlTitleInfoItemIdentifier)) {
            throw new NoSuchInfoItemException("Unsupported info item identifier type " + infoItemIdentifier);
        }
        Course course = null;
        if (infoItemIdentifier instanceof ClassPKInfoItemIdentifier) {
            long courseId = ((ClassPKInfoItemIdentifier) infoItemIdentifier).getClassPK();
            course = courseLocalService.fetchCourse(courseId);
        } else {
            GroupUrlTitleInfoItemIdentifier identifier = (GroupUrlTitleInfoItemIdentifier) infoItemIdentifier;
            course = courseLocalService.getCourseByUrlTitle(identifier.getGroupId(), identifier.getUrlTitle());
        }
        if (course == null) {
            throw new NoSuchInfoItemException("Unable to get course with info item identifier " + infoItemIdentifier);
        }
        return course;
    }

    @Override
    public Course getInfoItem(long classPK) throws NoSuchInfoItemException {
        InfoItemIdentifier infoItemIdentifier = new ClassPKInfoItemIdentifier(classPK);
        return getInfoItem(infoItemIdentifier);
    }

    @Reference
    private CourseLocalService courseLocalService;

}