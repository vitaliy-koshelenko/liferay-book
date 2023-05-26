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

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CourseSubscription}.
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @see CourseSubscription
 * @generated
 */
public class CourseSubscriptionWrapper
	extends BaseModelWrapper<CourseSubscription>
	implements CourseSubscription, ModelWrapper<CourseSubscription> {

	public CourseSubscriptionWrapper(CourseSubscription courseSubscription) {
		super(courseSubscription);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("courseSubscriptionId", getCourseSubscriptionId());
		attributes.put("courseId", getCourseId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long courseSubscriptionId = (Long)attributes.get(
			"courseSubscriptionId");

		if (courseSubscriptionId != null) {
			setCourseSubscriptionId(courseSubscriptionId);
		}

		Long courseId = (Long)attributes.get("courseId");

		if (courseId != null) {
			setCourseId(courseId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public CourseSubscription cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the course ID of this course subscription.
	 *
	 * @return the course ID of this course subscription
	 */
	@Override
	public long getCourseId() {
		return model.getCourseId();
	}

	/**
	 * Returns the course subscription ID of this course subscription.
	 *
	 * @return the course subscription ID of this course subscription
	 */
	@Override
	public long getCourseSubscriptionId() {
		return model.getCourseSubscriptionId();
	}

	/**
	 * Returns the primary key of this course subscription.
	 *
	 * @return the primary key of this course subscription
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this course subscription.
	 *
	 * @return the user ID of this course subscription
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this course subscription.
	 *
	 * @return the user uuid of this course subscription
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the course ID of this course subscription.
	 *
	 * @param courseId the course ID of this course subscription
	 */
	@Override
	public void setCourseId(long courseId) {
		model.setCourseId(courseId);
	}

	/**
	 * Sets the course subscription ID of this course subscription.
	 *
	 * @param courseSubscriptionId the course subscription ID of this course subscription
	 */
	@Override
	public void setCourseSubscriptionId(long courseSubscriptionId) {
		model.setCourseSubscriptionId(courseSubscriptionId);
	}

	/**
	 * Sets the primary key of this course subscription.
	 *
	 * @param primaryKey the primary key of this course subscription
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this course subscription.
	 *
	 * @param userId the user ID of this course subscription
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this course subscription.
	 *
	 * @param userUuid the user uuid of this course subscription
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected CourseSubscriptionWrapper wrap(
		CourseSubscription courseSubscription) {

		return new CourseSubscriptionWrapper(courseSubscription);
	}

}