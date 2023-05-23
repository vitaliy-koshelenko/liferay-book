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

package com.liferaybook.courses.manager.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.base.CourseLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Vitaliy Koshelenko
 */
@Component(
		property = "model.class.name=com.liferaybook.courses.manager.model.Course",
		service = AopService.class
)
public class CourseLocalServiceImpl extends CourseLocalServiceBaseImpl {

	public Course addCourse(long userId, long groupId, String name, String description) throws PortalException {
		User user = userLocalService.fetchUser(userId);
		long companyId = user.getCompanyId();
		long courseId = counterLocalService.increment();
		Course course = coursePersistence.create(courseId);
		course.setCompanyId(companyId);
		course.setGroupId(groupId);
		course.setUserId(userId);
		course.setUserName(user.getFullName());
		course.setName(name);
		course.setDescription(description);
		return courseLocalService.updateCourse(course);
	}

	public Course updateCourse(long userId, long courseId, String name, String description) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		course.setUserId(userId);
		course.setName(name);
		course.setDescription(description);
		return courseLocalService.updateCourse(course);
	}

	public int getGroupCoursesCount(long groupId) {
		return coursePersistence.countByGroupId(groupId);
	}

	public List<Course> getGroupCourses(long groupId, int start, int end) {
		return coursePersistence.findByGroupId(groupId, start, end);
	}

}