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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalServiceUtil;

/**
 * @author Vitaliy Koshelenko
 */
public class LectureImpl extends LectureBaseImpl {

    public Course getCourse() {
        long courseId = getCourseId();
        return CourseLocalServiceUtil.fetchCourse(courseId);
    }

    public String getEmbedVideoLink() {
        String embedLink = StringPool.BLANK;
        String videoLink = getVideoLink();
        if (Validator.isNotNull(videoLink)) {
            embedLink = videoLink.replace("/watch?v=", "/embed/");
        }
        return embedLink;
    }


}