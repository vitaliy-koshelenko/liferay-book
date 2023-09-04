package com.liferaybook.courses.web.search.service;

import com.liferaybook.courses.web.search.model.CourseDisplayContext;
import com.liferaybook.courses.web.search.model.CourseSearchContext;

public interface CourseSearcher {

    CourseDisplayContext search(CourseSearchContext searchContext);

}