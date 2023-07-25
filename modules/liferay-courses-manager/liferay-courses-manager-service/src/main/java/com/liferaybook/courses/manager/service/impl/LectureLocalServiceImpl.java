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

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.*;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;
import com.liferaybook.courses.manager.exception.*;
import com.liferaybook.courses.manager.model.*;
import com.liferaybook.courses.manager.service.base.LectureLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

/**
 * @author Vitaliy Koshelenko
 */
@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Lecture",
	service = AopService.class
)
public class LectureLocalServiceImpl extends LectureLocalServiceBaseImpl {

	public Lecture getLectureByUrlTitle(long groupId, String urlTitle) {
		return lecturePersistence.fetchByGroupIdAndUrlTitle(groupId, urlTitle);
	}

	public Lecture addLecture(long userId, long courseId, String name, String description, String videoLink,
							  String urlTitle, ServiceContext serviceContext) throws PortalException {
		long lectureId = counterLocalService.increment();
		validate(lectureId, courseId, serviceContext.getScopeGroupId(), name, description, videoLink, urlTitle);
		User user = userLocalService.fetchUser(userId);
		Lecture lecture = lecturePersistence.create(lectureId);
		lecture.setCourseId(courseId);
		lecture.setCompanyId(serviceContext.getCompanyId());
		lecture.setGroupId(serviceContext.getScopeGroupId());
		lecture.setUserId(userId);
		lecture.setUserName(user.getFullName());
		lecture.setName(name);
		lecture.setDescription(description);
		lecture.setVideoLink(videoLink);
		lecture.setUrlTitle(urlTitle);
		lecture = lectureLocalService.updateLecture(lecture);

		// Resources
		resourceLocalService.addModelResources(lecture.getCompanyId(), lecture.getGroupId(), lecture.getUserId(),
				Lecture.class.getName(), lecture.getLectureId(), serviceContext.getModelPermissions());

		return lecture;
	}

	public Lecture updateLecture(long userId, long lectureId, String name, String description, String videoLink,
								 String urlTitle, ServiceContext serviceContext) throws PortalException {
		Lecture lecture = lecturePersistence.fetchByPrimaryKey(lectureId);
		validate(lectureId, lecture.getCourseId(), serviceContext.getScopeGroupId(), name, description, videoLink, urlTitle);
		lecture.setName(name);
		lecture.setDescription(description);
		lecture.setVideoLink(videoLink);
		lecture.setUrlTitle(urlTitle);
		lecture = lectureLocalService.updateLecture(lecture);

		// Resource
		boolean hasResourcePermission = hasResourcePermission(lecture);
		if (!hasResourcePermission) {
			resourceLocalService.addResources(lecture.getCompanyId(), lecture.getGroupId(), lecture.getUserId(),
					Lecture.class.getName(), lecture.getLectureId(), false, serviceContext);
		}

		return lecture;
	}

	private boolean hasResourcePermission(Lecture lecture) throws PortalException {
		long companyId = lecture.getCompanyId();
		Role role = roleLocalService.getRole(companyId, RoleConstants.OWNER);
		ResourcePermission resourcePermission = resourcePermissionLocalService
				.fetchResourcePermission(companyId, Lecture.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(lecture.getLectureId()), role.getRoleId());
		return resourcePermission != null;
	}

	@Override
	public Lecture deleteLecture(long lectureId) throws PortalException {
		Lecture lecture = lecturePersistence.fetchByPrimaryKey(lectureId);
		return deleteLecture(lecture);
	}

	@Override
	public Lecture deleteLecture(Lecture lecture) {
		try {
			return removeLecture(lecture);
		} catch (PortalException e) {
			return ReflectionUtil.throwException(e);
		}
	}

	private Lecture removeLecture(Lecture lecture) throws PortalException {

		// Delete Resource
		resourceLocalService.deleteResource(
				lecture.getCompanyId(), Lecture.class.getName(),
				ResourceConstants.SCOPE_INDIVIDUAL, lecture.getLectureId());

		// Delete Lecture
		lecture = lecturePersistence.remove(lecture);

		return lecture;
	}

	public int getUserLecturesCount(long groupId, long userId) {
		DSLQuery dslQuery = buildUserLecturesDSLQuery(groupId, userId);
		List<Course> courses = lectureLocalService.dslQuery(dslQuery);
		return courses.size();
	}

	public List<Lecture> getUserLectures(long groupId, long userId, int start, int end) {
		GroupByStep baseQuery = (GroupByStep) buildUserLecturesDSLQuery(groupId, userId);
		DSLQuery dslQuery = baseQuery.limit(start, end);
		return lectureLocalService.dslQuery(dslQuery);
	}
	private DSLQuery buildUserLecturesDSLQuery(long groupId, long userId) {
		return DSLQueryFactoryUtil
				.select(LectureTable.INSTANCE)
				.from(LectureTable.INSTANCE)
				.innerJoinON(CourseTable.INSTANCE, CourseTable.INSTANCE.courseId.eq(LectureTable.INSTANCE.courseId))
				.innerJoinON(CourseSubscriptionTable.INSTANCE, CourseSubscriptionTable.INSTANCE.courseId.eq(CourseTable.INSTANCE.courseId))
				.where(
						CourseTable.INSTANCE.groupId.eq(groupId)
								.and(CourseSubscriptionTable.INSTANCE.userId.eq(userId))
				);
	}

	private void validate(long lectureId, long courseId, long groupId, String name, String description, String videoLink, String urlTitle) throws PortalException {
		int nameMinLength = 5;
		int nameMaxLength = ModelHintsUtil.getMaxLength(Lecture.class.getName(), "name");
		if (Validator.isNull(name) || name.length() < nameMinLength || name.length() > nameMaxLength) {
			throw new LectureNameLengthException(String.format("Lecture name should have from %d to %d characters.", nameMinLength, nameMaxLength));
		}
		if (Validator.isNotNull(description)) {
			int descriptionMaxLength = ModelHintsUtil.getMaxLength(Lecture.class.getName(), "description");
			if (description.length() > descriptionMaxLength) {
				throw new LectureDescriptionLengthException(String.format("Lecture description has more than %d characters.", descriptionMaxLength));
			}
		}
		if (Validator.isNull(videoLink) || !videoLink.contains("youtube.com")) {
			throw new LectureVideoLinkException("Video link should be a valid YouTube link.");
		}
		Lecture lecture = lecturePersistence.fetchByCourseIdAndName(courseId, name);
		if (lecture != null && lecture.getLectureId() != lectureId) {
			throw new DuplicateLectureNameException(String.format("Lecture name '%s' is already in use.", name));
		}
		lecture = lecturePersistence.fetchByGroupIdAndUrlTitle(groupId, urlTitle);
		if (lecture != null && lecture.getGroupId() != groupId) {
			throw new DuplicateUrlTitleException(String.format("Lecture with urlTitle='%s' already exists.", urlTitle));
		}
	}

	public List<Lecture> getCourseLectures(long courseId) {
		return lecturePersistence.findByCourseId(courseId);
	}

	public int getCourseLecturesCount(long courseId) {
		return lecturePersistence.countByCourseId(courseId);
	}

	public List<Lecture> getCourseLectures(long courseId, int start, int end) {
		return lecturePersistence.findByCourseId(courseId, start, end);
	}

	@Reference
	private RoleLocalService roleLocalService;
	@Reference
	private ResourcePermissionLocalService resourcePermissionLocalService;

}