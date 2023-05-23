package com.liferaybook.courses.api;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

public interface LiferayCoursesAPI {

    int getCoursesCount(long groupId);

    List<LiferayCourse> getCourses(long groupId, int start, int end);

    LiferayCourse getCourse(Long courseId);

    void saveCourse(long groupId, String name, String description) throws PortalException;

    void updateCourse(Long courseId, String name, String description) throws PortalException;

    void deleteCourse(Long courseId) throws PortalException;

}