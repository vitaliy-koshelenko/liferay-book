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
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.manager.exception.CourseDescriptionLengthException;
import com.liferaybook.courses.manager.exception.CourseNameLengthException;
import com.liferaybook.courses.manager.exception.DuplicateCourseNameException;
import com.liferaybook.courses.manager.exception.DuplicateUrlTitleException;
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

	public Course getCourseByUrlTitle(long groupId, String urlTitle) {
		return coursePersistence.fetchByGroupIdAndUrlTitle(groupId, urlTitle);
	}

	public List<Course> getUserCourses(long groupId, long userId) {
		return courseFinder.findSiteCoursesForUser(groupId, userId);
	}

	public Course addCourse(long userId, long groupId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException {
		long courseId = counterLocalService.increment();
		validate(courseId, groupId, name, description, urlTitle);
		User user = userLocalService.fetchUser(userId);
		Course course = coursePersistence.create(courseId);
		course.setCompanyId(serviceContext.getCompanyId());
		course.setGroupId(groupId);
		course.setUserId(userId);
		course.setUserName(user.getFullName());
		course.setName(name);
		course.setDescription(description);
		course.setUrlTitle(urlTitle);
		return courseLocalService.updateCourse(course);
	}

	public Course updateCourse(long userId, long courseId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		validate(courseId, course.getGroupId(), name, description, urlTitle);
		course.setUserId(userId);
		course.setName(name);
		course.setDescription(description);
		course.setUrlTitle(urlTitle);
		return courseLocalService.updateCourse(course);
	}

	private void validate(long courseId, long groupId, String name, String description, String urlTitle) throws PortalException {
		int nameMinLength = 5;
		int nameMaxLength = ModelHintsUtil.getMaxLength(Course.class.getName(), "name");
		if (Validator.isNull(name) || name.length() < nameMinLength || name.length() > nameMaxLength) {
			throw new CourseNameLengthException(String.format("Course name should have from %d to %d characters.", nameMinLength, nameMaxLength));
		}
		if (Validator.isNotNull(description)) {
			int descriptionMaxLength = ModelHintsUtil.getMaxLength(Course.class.getName(), "description");
			if (description.length() > descriptionMaxLength) {
				throw new CourseDescriptionLengthException(String.format("Course description has more than %d characters.", descriptionMaxLength));
			}
		}
		Course course = coursePersistence.fetchByGroupIdAndName(groupId, name);
		if (course != null && course.getCourseId() != courseId) {
			throw new DuplicateCourseNameException(String.format("Course name '%s' is already in use.", name));
		}
		course = coursePersistence.fetchByGroupIdAndUrlTitle(groupId, urlTitle);
		if (course != null && course.getCourseId() != courseId) {
			throw new DuplicateUrlTitleException(String.format("Course with urlTitle='%s' already exists.", urlTitle));
		}
	}

	public int getGroupCoursesCount(long groupId) {
		return coursePersistence.countByGroupId(groupId);
	}

	public List<Course> getGroupCourses(long groupId, int start, int end) {
		return coursePersistence.findByGroupId(groupId, start, end);
	}

}