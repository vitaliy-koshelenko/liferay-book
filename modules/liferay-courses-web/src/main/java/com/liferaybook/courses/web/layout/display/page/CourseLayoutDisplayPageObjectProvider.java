package com.liferaybook.courses.web.layout.display.page;

import com.liferay.layout.display.page.LayoutDisplayPageObjectProvider;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferaybook.courses.manager.model.Course;

import java.util.Locale;

public class CourseLayoutDisplayPageObjectProvider implements LayoutDisplayPageObjectProvider<Course> {

    private final Course course;
    public CourseLayoutDisplayPageObjectProvider(Course course) {
        this.course = course;
    }
    @Override
    public long getClassNameId() {
        return PortalUtil.getClassNameId(Course.class.getName());
    }

    @Override
    public long getClassPK() {
        return course.getCourseId();
    }

    @Override
    public long getClassTypeId() {
        return 0;
    }

    @Override
    public Course getDisplayObject() {
        return course;
    }

    @Override
    public long getGroupId() {
        return course.getGroupId();
    }

    @Override
    public String getTitle(Locale locale) {
        return course.getName();
    }

    @Override
    public String getDescription(Locale locale) {
        return course.getDescription();
    }

    @Override
    public String getKeywords(Locale locale) {
        return StringPool.BLANK;
    }

    @Override
    public String getURLTitle(Locale locale) {
        return course.getUrlTitle();
    }

}