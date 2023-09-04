package com.liferaybook.courses.web.search.service.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.*;
import com.liferaybook.courses.manager.constants.CourseField;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.search.model.CourseDisplayContext;
import com.liferaybook.courses.web.search.model.CourseInfo;
import com.liferaybook.courses.web.search.model.CourseSearchContext;
import com.liferaybook.courses.web.search.model.CourseSearchContextKeys;
import com.liferaybook.courses.web.search.service.CourseSearcher;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component(
        immediate = true,
        service = CourseSearcher.class
)
public class CourseSearcherImpl implements CourseSearcher {

    public static final int DELTA_DEFAULT = 2;

    @Override
    public CourseDisplayContext search(CourseSearchContext context) {

        CourseDisplayContext displayContext = null;
        try {
            List<CourseInfo> courses = new ArrayList<>();
            SearchRequestBuilder searchRequestBuilder = searchRequestBuilderFactory
                    .builder()
                    .emptySearchEnabled(true)
                    .modelIndexerClasses(Course.class)
                    .withSearchContext(searchContext -> {

                        // Scope
                        searchContext.setCompanyId(context.getCompanyId());
                        searchContext.setGroupIds(new long[]{context.getGroupId()});

                        // "AND" Search to Match ALL Filters
                        searchContext.setAndSearch(true);

                        long categoryId = context.getCategoryId();
                        if (categoryId > 0) {
                            searchContext.setAssetCategoryIds(new long[]{categoryId});
                        }

                        String author = context.getAuthor();
                        if (Validator.isNotNull(author)) {
                            Map<String, Serializable> attributes = searchContext.getAttributes();
                            attributes.put(Field.USER_NAME, author);
                            searchContext.setAttributes(attributes);
                        }
                    });

            String searchTerm = context.getSearchTerm();
            if (Validator.isNotNull(searchTerm)) {
                BooleanQuery booleanQuery = queries.booleanQuery();
                booleanQuery.addShouldQueryClauses(
                        queries.match(Field.TITLE, searchTerm),
                        queries.match(Field.DESCRIPTION, searchTerm)
                );
                searchRequestBuilder = searchRequestBuilder.query(booleanQuery);
            }

            SearchRequest searchRequest = searchRequestBuilder.build();

            SearchResponse searchResponse = searcher.search(searchRequest);

            List<Document> documents = searchResponse.getDocuments();
            for (Document document: documents) {
                CourseInfo course = convertToCourse(document);
                courses.add(course);
            }

            displayContext = getDisplayContext(courses, context);

        } catch (Exception e) {
            _log.error("Failed to fetch courses list, cause: " + e.getMessage(), e);
        }

        return displayContext;
    }

    private CourseDisplayContext getDisplayContext(List<CourseInfo> courses, CourseSearchContext searchContext) {
        PortletRequest portletRequest = searchContext.getPortletRequest();
        SearchContainer<CourseInfo> searchContainer = new SearchContainer<>(
                portletRequest,
                getSearchContainerURL(searchContext),
                null,
                "courses-search-no-courses"
        );
        searchContainer.setDelta(DELTA_DEFAULT);
        searchContainer.setResultsAndTotal(courses);
        return new CourseDisplayContext(searchContainer, searchContext);
    }

    private RenderURL getSearchContainerURL(CourseSearchContext searchContext) {
        RenderResponse portletResponse = (RenderResponse)searchContext.getPortletResponse();
        RenderURL renderURL = portletResponse.createRenderURL();
        // Set Search Parameters for Iterator URL
        String searchTerm = searchContext.getSearchTerm();
        if (Validator.isNotNull(searchTerm)) {
            renderURL.getRenderParameters().setValue(CourseSearchContextKeys.SEARCH_TERM, searchTerm);
        }
        String author = searchContext.getAuthor();
        if (Validator.isNotNull(author)) {
            renderURL.getRenderParameters().setValue(CourseSearchContextKeys.AUTHOR, author);
        }
        long categoryId = searchContext.getCategoryId();
        if (categoryId > 0) {
            renderURL.getRenderParameters().setValue(CourseSearchContextKeys.CATEGORY_ID, String.valueOf(categoryId));
        }
        return renderURL;
    }

    private CourseInfo convertToCourse(Document document) {
        CourseInfo course = new CourseInfo();
        course.setCourseId(document.getLong(CourseField.ENTRY_CLASS_PK));
        course.setUrlTitle(document.getString(CourseField.URL_TITLE));
        course.setName(document.getString(Field.TITLE));
        course.setDescription(document.getString(Field.DESCRIPTION));
        course.setUserName(document.getString(Field.USER_NAME));
        course.setCreateDate(formatDate(document.getDate(Field.CREATE_DATE)));
        course.setModifiedDate(formatDate(document.getDate(Field.MODIFIED_DATE)));
        course.setPriority(getDoubleValue(document, Field.PRIORITY));
        course.setViewCount(getIntValue(document, CourseField.VIEW_COUNT));
        return course;
    }

    private double getDoubleValue(Document document, String fieldName) {
        Double fieldValue = document.getDouble(fieldName);
        return fieldValue != null ? fieldValue : 0.;
    }

    private int getIntValue(Document document, String fieldName) {
        Integer fieldValue = document.getInteger(fieldName);
        return fieldValue != null ? fieldValue : 0;
    }

    private String formatDate(String esDate) {
        String displayDate = StringPool.BLANK;
        try {
            DateFormat esFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = esFormatter.parse(esDate);
            DateFormat displayFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            displayDate = displayFormatter.format(date);
        } catch (Exception e) {
            _log.error(String.format("Failed to parse date from search engine '%s', cause: '%s'.", esDate, e.getMessage()));
        }
        return displayDate;
    }

    @Reference
    private Queries queries;
    @Reference
    private Searcher searcher;
    @Reference
    private SearchRequestBuilderFactory searchRequestBuilderFactory;

    private static final Log _log = LogFactoryUtil.getLog(CourseSearcherImpl.class);
}