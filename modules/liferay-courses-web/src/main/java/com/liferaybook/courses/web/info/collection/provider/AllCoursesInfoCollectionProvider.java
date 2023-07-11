package com.liferaybook.courses.web.info.collection.provider;

import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(service = InfoCollectionProvider.class)
public class AllCoursesInfoCollectionProvider implements InfoCollectionProvider<Course> {

    @Override
    public InfoPage<Course> getCollectionInfoPage(CollectionQuery query) {
        InfoPage<Course> coursePage = null;
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            HttpServletRequest request = serviceContext.getRequest();
            long groupId = portal.getScopeGroupId(request);

            Pagination pagination = query.getPagination();
            int start = pagination.getStart();
            int end = pagination.getEnd();
            List<Course> courses = courseLocalService.getNotEmptyCourses(groupId, start, end);
            int coursesCount = courseLocalService.getNotEmptyCoursesCount(groupId);

            coursePage = InfoPage.of(courses, pagination, coursesCount);

        } catch (Exception e) {
            coursePage = InfoPage.of(Collections.emptyList(), query.getPagination(), 0);
        }
        return coursePage;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "all-courses");
    }

    @Reference
    private Portal portal;
    @Reference
    private CourseLocalService courseLocalService;

}
