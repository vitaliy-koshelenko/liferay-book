package com.liferaybook.courses.api;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.Lecture;

import java.util.List;

public interface LiferayCoursesAPI {

    // =====================================  COURSES ==================================================================

    int getCoursesCount(long groupId);

    List<Course> getCourses(long groupId, int start, int end);

    Course getCourse(Long courseId);

    Course getCourse(long groupId, String urlTitle);

    void saveCourse(long userId, long groupId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException;

    void updateCourse(long userId, Long courseId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException;

    void deleteCourse(Long courseId) throws PortalException;

    // =====================================  MY COURSES ===============================================================

    int getMyCoursesCount(long groupId, long userId);

    List<Course> getMyCourses(long groupId, long userId, int start, int end);

    void subscribe(long userId, long courseId);

    void unsubscribe(long userId, long courseId);

    // =====================================  LECTURES =================================================================

    int getLecturesCount(long courseId);

    List<Lecture> getLectures(long courseId, int start, int end);

    Lecture getLecture(Long lectureId);

    Lecture getLecture(long groupId, String urlTitle);

    void saveLecture(long userId, long courseId, String name, String description, String videoLink,
                     String urlTitle, ServiceContext serviceContext) throws PortalException;

    void updateLecture(long userId, long lectureId, String name, String description, String videoLink,
                       String urlTitle, ServiceContext serviceContext) throws PortalException;

    void deleteLecture(Long lectureId) throws PortalException;

}