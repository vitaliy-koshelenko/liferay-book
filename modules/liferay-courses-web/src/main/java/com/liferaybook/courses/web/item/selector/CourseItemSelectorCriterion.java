package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.BaseItemSelectorCriterion;

public class CourseItemSelectorCriterion extends BaseItemSelectorCriterion {

    public CourseItemSelectorCriterion() {
        setDesiredItemSelectorReturnTypes(new CourseItemSelectorReturnType());
    }

}