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
import com.liferaybook.courses.manager.exception.DuplicateLectureNameException;
import com.liferaybook.courses.manager.exception.LectureDescriptionLengthException;
import com.liferaybook.courses.manager.exception.LectureNameLengthException;
import com.liferaybook.courses.manager.exception.LectureVideoLinkException;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.base.LectureLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Vitaliy Koshelenko
 */
@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Lecture",
	service = AopService.class
)
public class LectureLocalServiceImpl extends LectureLocalServiceBaseImpl {

	public Lecture addLecture(long userId, long courseId, String name, String description, String videoLink, ServiceContext serviceContext) throws PortalException {
		long lectureId = counterLocalService.increment();
		validate(lectureId, courseId, name, description, videoLink);
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
		return lectureLocalService.updateLecture(lecture);
	}

	public Lecture updateLecture(long userId, long lectureId, String name, String description, String videoLink, ServiceContext serviceContext) throws PortalException {
		Lecture lecture = lecturePersistence.fetchByPrimaryKey(lectureId);
		validate(lectureId, lecture.getCourseId(), name, description, videoLink);
		lecture.setUserId(userId);
		lecture.setName(name);
		lecture.setDescription(description);
		lecture.setVideoLink(videoLink);
		return lectureLocalService.updateLecture(lecture);
	}

	private void validate(long lectureId, long courseId, String name, String description, String videoLink) throws PortalException {
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

}