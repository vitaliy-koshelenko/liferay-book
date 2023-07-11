package com.liferaybook.courses.web.info.collection.provider;

import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(service = RelatedInfoItemCollectionProvider.class)
public class CourseLecturesRelatedInfoItemCollectionProvider
        implements RelatedInfoItemCollectionProvider<Course, Lecture> {

    @Override
    public InfoPage<Lecture> getCollectionInfoPage(CollectionQuery collectionQuery) {
        try {
            Object relatedItem = collectionQuery.getRelatedItem();
            if (!(relatedItem instanceof Course)) {
                return InfoPage.of(Collections.emptyList(), collectionQuery.getPagination(), 0);
            }
            Course course = (Course) relatedItem;
            long courseId = course.getCourseId();

            Pagination pagination = collectionQuery.getPagination();
            int start = pagination.getStart();
            int end = pagination.getEnd();

            int lecturesCount = lectureLocalService.getCourseLecturesCount(courseId);
            List<Lecture> lectures = lectureLocalService.getCourseLectures(courseId, start, end);
            return InfoPage.of(lectures, pagination, lecturesCount);

        } catch (Exception e) {
            _log.error("Unable to get lectures, cause: " + e.getMessage(), e);
        }
        return InfoPage.of(Collections.emptyList(), collectionQuery.getPagination(), 0);
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "course-lectures");
    }

    @Reference
    private LectureLocalService lectureLocalService;

    private static final Log _log = LogFactoryUtil.getLog(CourseLecturesRelatedInfoItemCollectionProvider.class);

}
