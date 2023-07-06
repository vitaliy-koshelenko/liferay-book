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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetLinkConstants;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.AssetLinkLocalService;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.manager.exception.CourseDescriptionLengthException;
import com.liferaybook.courses.manager.exception.CourseNameLengthException;
import com.liferaybook.courses.manager.exception.DuplicateCourseNameException;
import com.liferaybook.courses.manager.exception.DuplicateUrlTitleException;
import com.liferaybook.courses.manager.model.*;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalService;
import com.liferaybook.courses.manager.service.LectureLocalService;
import com.liferaybook.courses.manager.service.base.CourseLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

	@Indexable(type = IndexableType.REINDEX)
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
		course = courseLocalService.updateCourse(course);
		updateAsset(userId, course, serviceContext);
		return course;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Course updateCourse(long userId, long courseId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		validate(courseId, course.getGroupId(), name, description, urlTitle);
		course.setUserId(userId);
		course.setName(name);
		course.setDescription(description);
		course.setUrlTitle(urlTitle);
		course = courseLocalService.updateCourse(course);
		updateAsset(userId, course, serviceContext);
		return course;
	}

	private void updateAsset(long userId, Course course, ServiceContext serviceContext) throws PortalException {
		String summary = StringUtil.shorten(course.getDescription(), 500);
		long[] assetCategoryIds = serviceContext.getAssetCategoryIds();
		String[] assetTagNames = serviceContext.getAssetTagNames();
		double priority = serviceContext.getAssetPriority();
		AssetEntry assetEntry = assetEntryLocalService.updateEntry(
				userId, course.getGroupId(), course.getCreateDate(),
				course.getModifiedDate(), Course.class.getName(),
				course.getCourseId(), course.getUuid(), 0, assetCategoryIds,
				assetTagNames, true, true, null, null, null, null,
				ContentTypes.TEXT_HTML, course.getName(), course.getDescription(),
				summary, null, null, 0, 0, priority);

		long[] assetLinkEntryIds = serviceContext.getAssetLinkEntryIds();
		assetLinkLocalService.updateLinks(
				userId, assetEntry.getEntryId(), assetLinkEntryIds,
				AssetLinkConstants.TYPE_RELATED);
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

	@Override
	public Course deleteCourse(Course course) {
		try {
			return courseLocalService.removeCourse(course);
		} catch (PortalException e) {
			return ReflectionUtil.throwException(e);
		}
	}

	@Override
	public Course deleteCourse(long courseId) throws PortalException {
		Course course = coursePersistence.findByPrimaryKey(courseId);
		return deleteCourse(course);
	}

	@Indexable(type = IndexableType.DELETE)
	public Course removeCourse(Course course) throws PortalException {
		// Delete Course Lectures
		List<Lecture> lectures = course.getLectures();
		if (ListUtil.isNotEmpty(lectures)) {
			for (Lecture lecture: lectures) {
				lectureLocalService.deleteLecture(lecture);
			}
		}

		// Delete Course Subscriptions
		List<CourseSubscription> subscriptions = course.getSubscriptions();
		if (ListUtil.isNotEmpty(subscriptions)) {
			for (CourseSubscription subscription: subscriptions) {
				courseSubscriptionLocalService.deleteCourseSubscription(subscription);
			}
		}

		// Delete Course
		course = coursePersistence.remove(course);

		// Delete Asset Entry
		assetEntryLocalService.deleteEntry(Course.class.getName(), course.getCourseId());

		return course;
	}

	public int getUserCoursesCount(long groupId, long userId) {
		DSLQuery dslQuery = buildUserCoursesDSLQuery(groupId, userId);
		List<Course> courses = courseLocalService.dslQuery(dslQuery);
		return courses.size();
	}

	public List<Course> getUserCourses(long groupId, long userId, int start, int end) {
		GroupByStep baseQuery = (GroupByStep) buildUserCoursesDSLQuery(groupId, userId);
		DSLQuery dslQuery = baseQuery.limit(start, end);
		return courseLocalService.dslQuery(dslQuery);
	}

	private DSLQuery buildUserCoursesDSLQuery(long groupId, long userId) {
		return DSLQueryFactoryUtil
				.select(CourseTable.INSTANCE)
				.from(CourseTable.INSTANCE)
				.innerJoinON(CourseSubscriptionTable.INSTANCE, CourseSubscriptionTable.INSTANCE
						.courseId.eq(CourseTable.INSTANCE.courseId))
				.where(
						CourseTable.INSTANCE.groupId.eq(groupId)
								.and(CourseSubscriptionTable.INSTANCE.userId.eq(userId))
				);
	}

	public int getGroupCoursesCount(long groupId) {
		return coursePersistence.countByGroupId(groupId);
	}

	public List<Course> getGroupCourses(long groupId, int start, int end) {
		return coursePersistence.findByGroupId(groupId, start, end);
	}

	@Reference
	private AssetEntryLocalService assetEntryLocalService;
	@Reference
	private AssetLinkLocalService assetLinkLocalService;
	@Reference
	private LectureLocalService lectureLocalService;
	@Reference
	private CourseSubscriptionLocalService courseSubscriptionLocalService;

}