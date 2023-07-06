package com.liferaybook.courses.web.info.collection.provider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(service = InfoCollectionProvider.class)
public class MyCoursesInfoCollectionProvider implements InfoCollectionProvider<AssetEntry> {

    @Override
    public InfoPage<AssetEntry> getCollectionInfoPage(CollectionQuery query) {
        InfoPage<AssetEntry> assetEntryPage = null;
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            HttpServletRequest request = serviceContext.getRequest();
            long groupId = portal.getScopeGroupId(request);
            long userId = portal.getUserId(request);

            Pagination pagination = query.getPagination();
            int start = pagination.getStart();
            int end = pagination.getEnd();
            List<Course> userCourses = courseLocalService
                    .getUserCourses(groupId, userId, start, end);
            int userCoursesCount = courseLocalService.getUserCoursesCount(groupId, userId);

            long classNameId = portal.getClassNameId(Course.class.getName());
            List<AssetEntry> assetEntries = toAssetEntries(classNameId, userCourses);

            assetEntryPage = InfoPage.of(assetEntries, pagination, userCoursesCount);

        } catch (Exception e) {
            assetEntryPage = InfoPage.of(Collections.emptyList(), query.getPagination(), 0);
        }
        return assetEntryPage;
    }

    private List<AssetEntry> toAssetEntries(long classNameId, List<Course> courses) {
        List<AssetEntry> assetEntries = new ArrayList<>();
        for (Course course: courses) {
            AssetEntry assetEntry = assetEntryLocalService.fetchEntry(classNameId, course.getCourseId());
            assetEntries.add(assetEntry);
        }
        return assetEntries;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "my-courses");
    }

    @Reference
    private Portal portal;
    @Reference
    private CourseLocalService courseLocalService;
    @Reference
    private AssetEntryLocalService assetEntryLocalService;

}
