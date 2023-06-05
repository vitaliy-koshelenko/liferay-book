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

import com.liferaybook.courses.manager.model.Lecture;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Lecture in entity cache.
 *
 * @author Vitaliy Koshelenko
 * @generated
 */
public class LectureCacheModel implements CacheModel<Lecture>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof LectureCacheModel)) {
			return false;
		}

		LectureCacheModel lectureCacheModel = (LectureCacheModel)object;

		if (lectureId == lectureCacheModel.lectureId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, lectureId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", lectureId=");
		sb.append(lectureId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", courseId=");
		sb.append(courseId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", videoLink=");
		sb.append(videoLink);
		sb.append(", urlTitle=");
		sb.append(urlTitle);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Lecture toEntityModel() {
		LectureImpl lectureImpl = new LectureImpl();

		if (uuid == null) {
			lectureImpl.setUuid("");
		}
		else {
			lectureImpl.setUuid(uuid);
		}

		lectureImpl.setLectureId(lectureId);
		lectureImpl.setCompanyId(companyId);
		lectureImpl.setGroupId(groupId);
		lectureImpl.setUserId(userId);

		if (userName == null) {
			lectureImpl.setUserName("");
		}
		else {
			lectureImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			lectureImpl.setCreateDate(null);
		}
		else {
			lectureImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			lectureImpl.setModifiedDate(null);
		}
		else {
			lectureImpl.setModifiedDate(new Date(modifiedDate));
		}

		lectureImpl.setCourseId(courseId);

		if (name == null) {
			lectureImpl.setName("");
		}
		else {
			lectureImpl.setName(name);
		}

		if (description == null) {
			lectureImpl.setDescription("");
		}
		else {
			lectureImpl.setDescription(description);
		}

		if (videoLink == null) {
			lectureImpl.setVideoLink("");
		}
		else {
			lectureImpl.setVideoLink(videoLink);
		}

		if (urlTitle == null) {
			lectureImpl.setUrlTitle("");
		}
		else {
			lectureImpl.setUrlTitle(urlTitle);
		}

		lectureImpl.resetOriginalValues();

		return lectureImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		lectureId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		courseId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		videoLink = objectInput.readUTF();
		urlTitle = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(lectureId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(courseId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (videoLink == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(videoLink);
		}

		if (urlTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(urlTitle);
		}
	}

	public String uuid;
	public long lectureId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long courseId;
	public String name;
	public String description;
	public String videoLink;
	public String urlTitle;

}