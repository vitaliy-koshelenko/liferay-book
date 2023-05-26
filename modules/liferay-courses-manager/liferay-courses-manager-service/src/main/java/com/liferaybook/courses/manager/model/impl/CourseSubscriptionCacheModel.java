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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.liferaybook.courses.manager.model.CourseSubscription;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing CourseSubscription in entity cache.
 *
 * @author Vitaliy Koshelenko
 * @generated
 */
public class CourseSubscriptionCacheModel
	implements CacheModel<CourseSubscription>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CourseSubscriptionCacheModel)) {
			return false;
		}

		CourseSubscriptionCacheModel courseSubscriptionCacheModel =
			(CourseSubscriptionCacheModel)object;

		if (courseSubscriptionId ==
				courseSubscriptionCacheModel.courseSubscriptionId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, courseSubscriptionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{courseSubscriptionId=");
		sb.append(courseSubscriptionId);
		sb.append(", courseId=");
		sb.append(courseId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CourseSubscription toEntityModel() {
		CourseSubscriptionImpl courseSubscriptionImpl =
			new CourseSubscriptionImpl();

		courseSubscriptionImpl.setCourseSubscriptionId(courseSubscriptionId);
		courseSubscriptionImpl.setCourseId(courseId);
		courseSubscriptionImpl.setUserId(userId);

		courseSubscriptionImpl.resetOriginalValues();

		return courseSubscriptionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		courseSubscriptionId = objectInput.readLong();

		courseId = objectInput.readLong();

		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(courseSubscriptionId);

		objectOutput.writeLong(courseId);

		objectOutput.writeLong(userId);
	}

	public long courseSubscriptionId;
	public long courseId;
	public long userId;

}