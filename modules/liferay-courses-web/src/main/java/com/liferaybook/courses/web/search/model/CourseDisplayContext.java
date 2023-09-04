package com.liferaybook.courses.web.search.model;

import com.liferay.portal.kernel.dao.search.SearchContainer;

public class CourseDisplayContext {

    private final SearchContainer<CourseInfo> searchContainer;
    private final CourseSearchContext searchContext;

    public CourseDisplayContext(SearchContainer<CourseInfo> searchContainer, CourseSearchContext searchContext) {
        this.searchContainer = searchContainer;
        this.searchContext = searchContext;
    }

    public SearchContainer<CourseInfo> getSearchContainer() {
        return searchContainer;
    }

    public CourseSearchContext getSearchContext() {
        return searchContext;
    }
}
