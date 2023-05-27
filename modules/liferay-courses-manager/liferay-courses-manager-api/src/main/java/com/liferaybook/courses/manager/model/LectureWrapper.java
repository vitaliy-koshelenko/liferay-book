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

package com.liferaybook.courses.manager.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Lecture}.
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @see Lecture
 * @generated
 */
public class LectureWrapper
	extends BaseModelWrapper<Lecture>
	implements Lecture, ModelWrapper<Lecture> {

	public LectureWrapper(Lecture lecture) {
		super(lecture);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("lectureId", getLectureId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("courseId", getCourseId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("videoLink", getVideoLink());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long lectureId = (Long)attributes.get("lectureId");

		if (lectureId != null) {
			setLectureId(lectureId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long courseId = (Long)attributes.get("courseId");

		if (courseId != null) {
			setCourseId(courseId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String videoLink = (String)attributes.get("videoLink");

		if (videoLink != null) {
			setVideoLink(videoLink);
		}
	}

	@Override
	public Lecture cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this lecture.
	 *
	 * @return the company ID of this lecture
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	@Override
	public Course getCourse() {
		return model.getCourse();
	}

	/**
	 * Returns the course ID of this lecture.
	 *
	 * @return the course ID of this lecture
	 */
	@Override
	public long getCourseId() {
		return model.getCourseId();
	}

	/**
	 * Returns the create date of this lecture.
	 *
	 * @return the create date of this lecture
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this lecture.
	 *
	 * @return the description of this lecture
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the group ID of this lecture.
	 *
	 * @return the group ID of this lecture
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the lecture ID of this lecture.
	 *
	 * @return the lecture ID of this lecture
	 */
	@Override
	public long getLectureId() {
		return model.getLectureId();
	}

	/**
	 * Returns the modified date of this lecture.
	 *
	 * @return the modified date of this lecture
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the name of this lecture.
	 *
	 * @return the name of this lecture
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this lecture.
	 *
	 * @return the primary key of this lecture
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this lecture.
	 *
	 * @return the user ID of this lecture
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this lecture.
	 *
	 * @return the user name of this lecture
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this lecture.
	 *
	 * @return the user uuid of this lecture
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this lecture.
	 *
	 * @return the uuid of this lecture
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the video link of this lecture.
	 *
	 * @return the video link of this lecture
	 */
	@Override
	public String getVideoLink() {
		return model.getVideoLink();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this lecture.
	 *
	 * @param companyId the company ID of this lecture
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the course ID of this lecture.
	 *
	 * @param courseId the course ID of this lecture
	 */
	@Override
	public void setCourseId(long courseId) {
		model.setCourseId(courseId);
	}

	/**
	 * Sets the create date of this lecture.
	 *
	 * @param createDate the create date of this lecture
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this lecture.
	 *
	 * @param description the description of this lecture
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this lecture.
	 *
	 * @param groupId the group ID of this lecture
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the lecture ID of this lecture.
	 *
	 * @param lectureId the lecture ID of this lecture
	 */
	@Override
	public void setLectureId(long lectureId) {
		model.setLectureId(lectureId);
	}

	/**
	 * Sets the modified date of this lecture.
	 *
	 * @param modifiedDate the modified date of this lecture
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this lecture.
	 *
	 * @param name the name of this lecture
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this lecture.
	 *
	 * @param primaryKey the primary key of this lecture
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this lecture.
	 *
	 * @param userId the user ID of this lecture
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this lecture.
	 *
	 * @param userName the user name of this lecture
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this lecture.
	 *
	 * @param userUuid the user uuid of this lecture
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this lecture.
	 *
	 * @param uuid the uuid of this lecture
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the video link of this lecture.
	 *
	 * @param videoLink the video link of this lecture
	 */
	@Override
	public void setVideoLink(String videoLink) {
		model.setVideoLink(videoLink);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected LectureWrapper wrap(Lecture lecture) {
		return new LectureWrapper(lecture);
	}

}