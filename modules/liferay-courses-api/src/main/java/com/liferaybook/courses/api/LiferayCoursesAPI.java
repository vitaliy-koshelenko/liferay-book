package com.liferaybook.courses.api;

import java.util.List;

public interface LiferayCoursesAPI {

    int getCoursesCount();

    List<LiferayCourse> getCourses(int start, int end);

    LiferayCourse getCourse(Long courseId);

    void saveCourse(String name, String description);

    void updateCourse(Long courseId, String name, String description);

    void deleteCourse(Long courseId);

}