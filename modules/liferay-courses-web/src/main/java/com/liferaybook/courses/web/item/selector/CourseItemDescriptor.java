package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferaybook.courses.manager.model.Course;

import java.util.Locale;

public class CourseItemDescriptor implements ItemSelectorViewDescriptor.ItemDescriptor {

    private final Course course;

    public CourseItemDescriptor(Course course) {
        this.course = course;
    }

    @Override
    public String getPayload() {
        JSONObject courseJson = JSONFactoryUtil.createJSONObject();
        courseJson.put("urlTitle", course.getUrlTitle());
        courseJson.put("name", course.getName());
        courseJson.put("description", course.getDescription());
        return courseJson.toString();
    }
    @Override
    public String getTitle(Locale locale) {
        return course.getName();
    }
    @Override
    public String getSubtitle(Locale locale) {
        return course.getDescription();
    }
    @Override
    public String getIcon() {
        return null;
    }
    @Override
    public String getImageURL() {
        return null;
    }

}