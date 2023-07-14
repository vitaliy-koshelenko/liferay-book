package com.liferaybook.courses.web.info.collection.provider;

import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalService;
import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.FilteredInfoCollectionProvider;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.filter.CategoriesInfoFilter;
import com.liferay.info.filter.InfoFilter;
import com.liferay.info.filter.KeywordsInfoFilter;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.info.filter.AuthorInfoFilter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Component(service = InfoCollectionProvider.class)
public class AllCoursesInfoCollectionProvider implements FilteredInfoCollectionProvider<Course> {

    @Override
    public List<InfoFilter> getSupportedInfoFilters() {
        return Arrays.asList(
                new CategoriesInfoFilter(),
                new KeywordsInfoFilter(),
                new AuthorInfoFilter()
        );
    }

    @Override
    public InfoPage<Course> getCollectionInfoPage(CollectionQuery query) {
        InfoPage<Course> coursePage = null;
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            HttpServletRequest request = serviceContext.getRequest();
            long groupId = portal.getScopeGroupId(request);

            List<Course> courses = courseLocalService.getGroupCourses(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            List<Course> filteredCourses = new ArrayList<>();

            Map<String, String[]> parameterMap = request.getParameterMap();
            List<String> filterParams = parameterMap.keySet().stream()
                    .filter(parameter -> parameter.startsWith("filter_")).collect(Collectors.toList());

            if (ListUtil.isNotEmpty(filterParams)) {
                String keywordsParam = filterParams.stream().filter(filter -> filter.contains("keywords")).findFirst().orElse(null);
                String categoryParam = filterParams.stream().filter(filter -> filter.contains("category")).findFirst().orElse(null);
                String authorParam = filterParams.stream().filter(filter -> filter.contains("author")).findFirst().orElse(null);
                for (Course course: courses) {
                    if (keywordsParam != null) {
                        String keywords = ParamUtil.getString(request, keywordsParam).trim().toLowerCase();
                        String courseName = course.getName() !=null ? course.getName() : StringPool.BLANK;
                        String courseDescription = course.getDescription() != null ? course.getDescription() : StringPool.BLANK;
                        boolean hasKeywords = courseName.trim().toLowerCase().contains(keywords) || courseDescription.trim().toLowerCase().contains(keywords);
                        if (!hasKeywords) {
                            continue;
                        }
                    }
                    if (categoryParam != null) {
                        long categoryId = ParamUtil.getLong(request, categoryParam);
                        long assetEntryId = course.getAssetEntry().getEntryId();
                        long[] categoryIds = assetEntryAssetCategoryRelLocalService.getAssetCategoryPrimaryKeys(assetEntryId);
                        if (!ArrayUtil.contains(categoryIds, categoryId)) {
                            continue;
                        }
                    }
                    if (authorParam != null) {
                        String author = ParamUtil.getString(request, authorParam);
                        if (!StringUtil.equalsIgnoreCase(course.getUserName(), author)) {
                            continue;
                        }
                    }
                    filteredCourses.add(course);
                }
            } else {
                filteredCourses = new ArrayList<>(courses);
            }

            Pagination pagination = query.getPagination();
            List<Course> coursesPage = ListUtil.subList(filteredCourses, pagination.getStart(), pagination.getEnd());
            coursePage = InfoPage.of(coursesPage, pagination, filteredCourses.size());

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
    @Reference
    private AssetEntryAssetCategoryRelLocalService assetEntryAssetCategoryRelLocalService;

}
