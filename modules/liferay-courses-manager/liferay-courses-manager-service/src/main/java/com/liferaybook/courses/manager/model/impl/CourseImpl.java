/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferaybook.courses.manager.model.impl;

import com.liferaybook.courses.manager.model.CourseSubscription;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalServiceUtil;
import com.liferaybook.courses.manager.service.LectureLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Vitaliy Koshelenko
 */
public class CourseImpl extends CourseBaseImpl {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    public List<Lecture> getLectures() {
        long courseId = getCourseId();
        return LectureLocalServiceUtil.getCourseLectures(courseId);
    }

    public List<CourseSubscription> getSubscriptions() {
        long courseId = getCourseId();
        return CourseSubscriptionLocalServiceUtil.getSubscriptionsForCourse(courseId);
    }

    public boolean isUserSubscribed(long userId) {
        long courseId = getCourseId();
        return CourseSubscriptionLocalServiceUtil.isSubscribed(userId, courseId);
    }

    public String getCreateDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(getCreateDate());
    }

    public String getModifiedDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(getModifiedDate());
    }

}