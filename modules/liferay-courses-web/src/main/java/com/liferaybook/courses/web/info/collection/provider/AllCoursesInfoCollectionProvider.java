package com.liferaybook.courses.web.info.collection.provider;

import com.liferay.info.collection.provider.CollectionQuery;
import com.liferay.info.collection.provider.FilteredInfoCollectionProvider;
import com.liferay.info.collection.provider.InfoCollectionProvider;
import com.liferay.info.filter.CategoriesInfoFilter;
import com.liferay.info.filter.InfoFilter;
import com.liferay.info.filter.KeywordsInfoFilter;
import com.liferay.info.pagination.InfoPage;
import com.liferay.info.pagination.Pagination;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.*;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.info.filter.AuthorInfoFilter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

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
    public InfoPage<Course> getCollectionInfoPage(CollectionQuery collectionQuery) {
        try {
            ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
            HttpServletRequest request = serviceContext.getRequest();
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            Pagination pagination = collectionQuery.getPagination();

            // Build Search Request
            SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory
                    .builder()
                    .emptySearchEnabled(true)
                    .modelIndexerClasses(Course.class)
                    .withSearchContext(searchContext -> {

                        // Scope
                        searchContext.setCompanyId(themeDisplay.getCompanyId());
                        searchContext.setGroupIds(new long[]{themeDisplay.getScopeGroupId()});

                        // Pagination
                        searchContext.setStart(pagination.getStart());
                        searchContext.setEnd(pagination.getEnd());
                        //_log.info("Get courses, START: " + pagination.getStart());
                        //_log.info("Get courses, END: " + pagination.getEnd());

                        // "AND" Search to Match ALL Filters
                        searchContext.setAndSearch(true);

                        // Category
                        long categoryId = getCategoryId(request);
                        if (categoryId > 0) {
                            searchContext.setAssetCategoryIds(new long[]{categoryId});
                        }

                        // Author
                        String author = getAuthor(request);
                        if (Validator.isNotNull(author)) {
                            Map<String, Serializable> attributes = searchContext.getAttributes();
                            attributes.put(Field.USER_NAME, author);
                            searchContext.setAttributes(attributes);
                        }
                    });

            // Add Keywords as Query
            String keywords = getKeywords(request);
            if (Validator.isNotNull(keywords)) {
                BooleanQuery booleanQuery = queries.booleanQuery();
                booleanQuery.addShouldQueryClauses(
                        queries.match(Field.TITLE, keywords),
                        queries.match(Field.DESCRIPTION, keywords)
                );
                searchRequestBuilder = searchRequestBuilder.query(booleanQuery);
            }

            SearchRequest searchRequest = searchRequestBuilder.build();

            // Get Search Response
            SearchResponse searchResponse = searcher.search(searchRequest);
            List<Document> documents = ListUtil.subList(searchResponse.getDocuments(), pagination.getStart(), pagination.getEnd());
            int totalCount = searchResponse.getTotalHits();

            // Convert Documents to Courses
            List<Course> courses = new ArrayList<>();
            for (Document document: documents) {
                Long courseId = document.getLong("entryClassPK");
                Course course = courseLocalService.fetchCourse(courseId);
                courses.add(course);
            }

            // Return Paginated Result
            return InfoPage.of(courses, collectionQuery.getPagination(), totalCount);

        } catch (Exception e) {
            return InfoPage.of(Collections.emptyList(), collectionQuery.getPagination(), 0);
        }
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "all-courses");
    }

    private String getKeywords(HttpServletRequest request) {
        return getFilterParam(request, "keywords");
    }

    private String getAuthor(HttpServletRequest request) {
        return getFilterParam(request, "author");
    }

    private long getCategoryId(HttpServletRequest request) {
        long categoryId = 0;
        String category = getFilterParam(request, "category");
        if (Validator.isNotNull(category)) {
            try {
                categoryId = Long.parseLong(category);
            } catch (Exception e) {
                _log.error(String.format("Failed to parse categoryId '%s', cause: %s", category, e.getMessage()));
            }
        }
        return categoryId;
    }

    private String getFilterParam(HttpServletRequest request, String filterName) {
        String paramPrefix = "filter_" + filterName;
        String paramName = request.getParameterMap().keySet().stream()
                .filter(name -> name.startsWith(paramPrefix))
                .findFirst().orElse(null);
        return Validator.isNotNull(paramName) ? ParamUtil.getString(request, paramName).trim() : null;
    }

    @Reference
    private Queries queries;
    @Reference
    private Searcher searcher;
    @Reference
    private CourseLocalService courseLocalService;
    @Reference
    private SearchRequestBuilderFactory searchRequestBuilderFactory;

    private static final Log _log = LogFactoryUtil.getLog(AllCoursesInfoCollectionProvider.class);

}