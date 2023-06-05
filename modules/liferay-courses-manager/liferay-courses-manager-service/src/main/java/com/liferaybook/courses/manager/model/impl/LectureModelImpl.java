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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.model.LectureModel;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.sql.Blob;
import java.sql.Types;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the Lecture service. Represents a row in the &quot;lb_Lecture&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface <code>LectureModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LectureImpl}.
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @see LectureImpl
 * @generated
 */
public class LectureModelImpl
	extends BaseModelImpl<Lecture> implements LectureModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lecture model instance should use the <code>Lecture</code> interface instead.
	 */
	public static final String TABLE_NAME = "lb_Lecture";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"lectureId", Types.BIGINT},
		{"companyId", Types.BIGINT}, {"groupId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"courseId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"videoLink", Types.VARCHAR},
		{"urlTitle", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lectureId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("courseId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("videoLink", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("urlTitle", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE =
		"create table lb_Lecture (uuid_ VARCHAR(75) null,lectureId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,courseId LONG,name VARCHAR(100) null,description VARCHAR(1000) null,videoLink VARCHAR(100) null,urlTitle VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table lb_Lecture";

	public static final String ORDER_BY_JPQL =
		" ORDER BY lecture.lectureId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY lb_Lecture.lectureId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COMPANYID_COLUMN_BITMASK = 1L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long COURSEID_COLUMN_BITMASK = 2L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long GROUPID_COLUMN_BITMASK = 4L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long NAME_COLUMN_BITMASK = 8L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long URLTITLE_COLUMN_BITMASK = 16L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link #getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long UUID_COLUMN_BITMASK = 32L;

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *		#getColumnBitmask(String)}
	 */
	@Deprecated
	public static final long LECTUREID_COLUMN_BITMASK = 64L;

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
	}

	public LectureModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lectureId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLectureId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lectureId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Lecture.class;
	}

	@Override
	public String getModelClassName() {
		return Lecture.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Lecture, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Lecture, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Lecture, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName, attributeGetterFunction.apply((Lecture)this));
		}

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Lecture, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Lecture, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(Lecture)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<Lecture, Object>>
		getAttributeGetterFunctions() {

		return AttributeGetterFunctionsHolder._attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Lecture, Object>>
		getAttributeSetterBiConsumers() {

		return AttributeSetterBiConsumersHolder._attributeSetterBiConsumers;
	}

	private static class AttributeGetterFunctionsHolder {

		private static final Map<String, Function<Lecture, Object>>
			_attributeGetterFunctions;

		static {
			Map<String, Function<Lecture, Object>> attributeGetterFunctions =
				new LinkedHashMap<String, Function<Lecture, Object>>();

			attributeGetterFunctions.put("uuid", Lecture::getUuid);
			attributeGetterFunctions.put("lectureId", Lecture::getLectureId);
			attributeGetterFunctions.put("companyId", Lecture::getCompanyId);
			attributeGetterFunctions.put("groupId", Lecture::getGroupId);
			attributeGetterFunctions.put("userId", Lecture::getUserId);
			attributeGetterFunctions.put("userName", Lecture::getUserName);
			attributeGetterFunctions.put("createDate", Lecture::getCreateDate);
			attributeGetterFunctions.put(
				"modifiedDate", Lecture::getModifiedDate);
			attributeGetterFunctions.put("courseId", Lecture::getCourseId);
			attributeGetterFunctions.put("name", Lecture::getName);
			attributeGetterFunctions.put(
				"description", Lecture::getDescription);
			attributeGetterFunctions.put("videoLink", Lecture::getVideoLink);
			attributeGetterFunctions.put("urlTitle", Lecture::getUrlTitle);

			_attributeGetterFunctions = Collections.unmodifiableMap(
				attributeGetterFunctions);
		}

	}

	private static class AttributeSetterBiConsumersHolder {

		private static final Map<String, BiConsumer<Lecture, Object>>
			_attributeSetterBiConsumers;

		static {
			Map<String, BiConsumer<Lecture, ?>> attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<Lecture, ?>>();

			attributeSetterBiConsumers.put(
				"uuid", (BiConsumer<Lecture, String>)Lecture::setUuid);
			attributeSetterBiConsumers.put(
				"lectureId", (BiConsumer<Lecture, Long>)Lecture::setLectureId);
			attributeSetterBiConsumers.put(
				"companyId", (BiConsumer<Lecture, Long>)Lecture::setCompanyId);
			attributeSetterBiConsumers.put(
				"groupId", (BiConsumer<Lecture, Long>)Lecture::setGroupId);
			attributeSetterBiConsumers.put(
				"userId", (BiConsumer<Lecture, Long>)Lecture::setUserId);
			attributeSetterBiConsumers.put(
				"userName", (BiConsumer<Lecture, String>)Lecture::setUserName);
			attributeSetterBiConsumers.put(
				"createDate",
				(BiConsumer<Lecture, Date>)Lecture::setCreateDate);
			attributeSetterBiConsumers.put(
				"modifiedDate",
				(BiConsumer<Lecture, Date>)Lecture::setModifiedDate);
			attributeSetterBiConsumers.put(
				"courseId", (BiConsumer<Lecture, Long>)Lecture::setCourseId);
			attributeSetterBiConsumers.put(
				"name", (BiConsumer<Lecture, String>)Lecture::setName);
			attributeSetterBiConsumers.put(
				"description",
				(BiConsumer<Lecture, String>)Lecture::setDescription);
			attributeSetterBiConsumers.put(
				"videoLink",
				(BiConsumer<Lecture, String>)Lecture::setVideoLink);
			attributeSetterBiConsumers.put(
				"urlTitle", (BiConsumer<Lecture, String>)Lecture::setUrlTitle);

			_attributeSetterBiConsumers = Collections.unmodifiableMap(
				(Map)attributeSetterBiConsumers);
		}

	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_uuid = uuid;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUuid() {
		return getColumnOriginalValue("uuid_");
	}

	@Override
	public long getLectureId() {
		return _lectureId;
	}

	@Override
	public void setLectureId(long lectureId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_lectureId = lectureId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_companyId = companyId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCompanyId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("companyId"));
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_groupId = groupId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalGroupId() {
		return GetterUtil.getLong(this.<Long>getColumnOriginalValue("groupId"));
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException portalException) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_userName = userName;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getCourseId() {
		return _courseId;
	}

	@Override
	public void setCourseId(long courseId) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_courseId = courseId;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public long getOriginalCourseId() {
		return GetterUtil.getLong(
			this.<Long>getColumnOriginalValue("courseId"));
	}

	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_name = name;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalName() {
		return getColumnOriginalValue("name");
	}

	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_description = description;
	}

	@Override
	public String getVideoLink() {
		if (_videoLink == null) {
			return "";
		}
		else {
			return _videoLink;
		}
	}

	@Override
	public void setVideoLink(String videoLink) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_videoLink = videoLink;
	}

	@Override
	public String getUrlTitle() {
		if (_urlTitle == null) {
			return "";
		}
		else {
			return _urlTitle;
		}
	}

	@Override
	public void setUrlTitle(String urlTitle) {
		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		_urlTitle = urlTitle;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), replaced by {@link
	 *             #getColumnOriginalValue(String)}
	 */
	@Deprecated
	public String getOriginalUrlTitle() {
		return getColumnOriginalValue("urlTitle");
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(Lecture.class.getName()));
	}

	public long getColumnBitmask() {
		if (_columnBitmask > 0) {
			return _columnBitmask;
		}

		if ((_columnOriginalValues == null) ||
			(_columnOriginalValues == Collections.EMPTY_MAP)) {

			return 0;
		}

		for (Map.Entry<String, Object> entry :
				_columnOriginalValues.entrySet()) {

			if (!Objects.equals(
					entry.getValue(), getColumnValue(entry.getKey()))) {

				_columnBitmask |= _columnBitmasks.get(entry.getKey());
			}
		}

		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), Lecture.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Lecture toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, Lecture>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LectureImpl lectureImpl = new LectureImpl();

		lectureImpl.setUuid(getUuid());
		lectureImpl.setLectureId(getLectureId());
		lectureImpl.setCompanyId(getCompanyId());
		lectureImpl.setGroupId(getGroupId());
		lectureImpl.setUserId(getUserId());
		lectureImpl.setUserName(getUserName());
		lectureImpl.setCreateDate(getCreateDate());
		lectureImpl.setModifiedDate(getModifiedDate());
		lectureImpl.setCourseId(getCourseId());
		lectureImpl.setName(getName());
		lectureImpl.setDescription(getDescription());
		lectureImpl.setVideoLink(getVideoLink());
		lectureImpl.setUrlTitle(getUrlTitle());

		lectureImpl.resetOriginalValues();

		return lectureImpl;
	}

	@Override
	public Lecture cloneWithOriginalValues() {
		LectureImpl lectureImpl = new LectureImpl();

		lectureImpl.setUuid(this.<String>getColumnOriginalValue("uuid_"));
		lectureImpl.setLectureId(
			this.<Long>getColumnOriginalValue("lectureId"));
		lectureImpl.setCompanyId(
			this.<Long>getColumnOriginalValue("companyId"));
		lectureImpl.setGroupId(this.<Long>getColumnOriginalValue("groupId"));
		lectureImpl.setUserId(this.<Long>getColumnOriginalValue("userId"));
		lectureImpl.setUserName(
			this.<String>getColumnOriginalValue("userName"));
		lectureImpl.setCreateDate(
			this.<Date>getColumnOriginalValue("createDate"));
		lectureImpl.setModifiedDate(
			this.<Date>getColumnOriginalValue("modifiedDate"));
		lectureImpl.setCourseId(this.<Long>getColumnOriginalValue("courseId"));
		lectureImpl.setName(this.<String>getColumnOriginalValue("name"));
		lectureImpl.setDescription(
			this.<String>getColumnOriginalValue("description"));
		lectureImpl.setVideoLink(
			this.<String>getColumnOriginalValue("videoLink"));
		lectureImpl.setUrlTitle(
			this.<String>getColumnOriginalValue("urlTitle"));

		return lectureImpl;
	}

	@Override
	public int compareTo(Lecture lecture) {
		long primaryKey = lecture.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Lecture)) {
			return false;
		}

		Lecture lecture = (Lecture)object;

		long primaryKey = lecture.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isEntityCacheEnabled() {
		return true;
	}

	/**
	 * @deprecated As of Athanasius (7.3.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public boolean isFinderCacheEnabled() {
		return true;
	}

	@Override
	public void resetOriginalValues() {
		_columnOriginalValues = Collections.emptyMap();

		_setModifiedDate = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<Lecture> toCacheModel() {
		LectureCacheModel lectureCacheModel = new LectureCacheModel();

		lectureCacheModel.uuid = getUuid();

		String uuid = lectureCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			lectureCacheModel.uuid = null;
		}

		lectureCacheModel.lectureId = getLectureId();

		lectureCacheModel.companyId = getCompanyId();

		lectureCacheModel.groupId = getGroupId();

		lectureCacheModel.userId = getUserId();

		lectureCacheModel.userName = getUserName();

		String userName = lectureCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			lectureCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			lectureCacheModel.createDate = createDate.getTime();
		}
		else {
			lectureCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			lectureCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			lectureCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		lectureCacheModel.courseId = getCourseId();

		lectureCacheModel.name = getName();

		String name = lectureCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			lectureCacheModel.name = null;
		}

		lectureCacheModel.description = getDescription();

		String description = lectureCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			lectureCacheModel.description = null;
		}

		lectureCacheModel.videoLink = getVideoLink();

		String videoLink = lectureCacheModel.videoLink;

		if ((videoLink != null) && (videoLink.length() == 0)) {
			lectureCacheModel.videoLink = null;
		}

		lectureCacheModel.urlTitle = getUrlTitle();

		String urlTitle = lectureCacheModel.urlTitle;

		if ((urlTitle != null) && (urlTitle.length() == 0)) {
			lectureCacheModel.urlTitle = null;
		}

		return lectureCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Lecture, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			(5 * attributeGetterFunctions.size()) + 2);

		sb.append("{");

		for (Map.Entry<String, Function<Lecture, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<Lecture, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("\"");
			sb.append(attributeName);
			sb.append("\": ");

			Object value = attributeGetterFunction.apply((Lecture)this);

			if (value == null) {
				sb.append("null");
			}
			else if (value instanceof Blob || value instanceof Date ||
					 value instanceof Map || value instanceof String) {

				sb.append(
					"\"" + StringUtil.replace(value.toString(), "\"", "'") +
						"\"");
			}
			else {
				sb.append(value);
			}

			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, Lecture>
			_escapedModelProxyProviderFunction =
				ProxyUtil.getProxyProviderFunction(
					Lecture.class, ModelWrapper.class);

	}

	private String _uuid;
	private long _lectureId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _courseId;
	private String _name;
	private String _description;
	private String _videoLink;
	private String _urlTitle;

	public <T> T getColumnValue(String columnName) {
		columnName = _attributeNames.getOrDefault(columnName, columnName);

		Function<Lecture, Object> function =
			AttributeGetterFunctionsHolder._attributeGetterFunctions.get(
				columnName);

		if (function == null) {
			throw new IllegalArgumentException(
				"No attribute getter function found for " + columnName);
		}

		return (T)function.apply((Lecture)this);
	}

	public <T> T getColumnOriginalValue(String columnName) {
		if (_columnOriginalValues == null) {
			return null;
		}

		if (_columnOriginalValues == Collections.EMPTY_MAP) {
			_setColumnOriginalValues();
		}

		return (T)_columnOriginalValues.get(columnName);
	}

	private void _setColumnOriginalValues() {
		_columnOriginalValues = new HashMap<String, Object>();

		_columnOriginalValues.put("uuid_", _uuid);
		_columnOriginalValues.put("lectureId", _lectureId);
		_columnOriginalValues.put("companyId", _companyId);
		_columnOriginalValues.put("groupId", _groupId);
		_columnOriginalValues.put("userId", _userId);
		_columnOriginalValues.put("userName", _userName);
		_columnOriginalValues.put("createDate", _createDate);
		_columnOriginalValues.put("modifiedDate", _modifiedDate);
		_columnOriginalValues.put("courseId", _courseId);
		_columnOriginalValues.put("name", _name);
		_columnOriginalValues.put("description", _description);
		_columnOriginalValues.put("videoLink", _videoLink);
		_columnOriginalValues.put("urlTitle", _urlTitle);
	}

	private static final Map<String, String> _attributeNames;

	static {
		Map<String, String> attributeNames = new HashMap<>();

		attributeNames.put("uuid_", "uuid");

		_attributeNames = Collections.unmodifiableMap(attributeNames);
	}

	private transient Map<String, Object> _columnOriginalValues;

	public static long getColumnBitmask(String columnName) {
		return _columnBitmasks.get(columnName);
	}

	private static final Map<String, Long> _columnBitmasks;

	static {
		Map<String, Long> columnBitmasks = new HashMap<>();

		columnBitmasks.put("uuid_", 1L);

		columnBitmasks.put("lectureId", 2L);

		columnBitmasks.put("companyId", 4L);

		columnBitmasks.put("groupId", 8L);

		columnBitmasks.put("userId", 16L);

		columnBitmasks.put("userName", 32L);

		columnBitmasks.put("createDate", 64L);

		columnBitmasks.put("modifiedDate", 128L);

		columnBitmasks.put("courseId", 256L);

		columnBitmasks.put("name", 512L);

		columnBitmasks.put("description", 1024L);

		columnBitmasks.put("videoLink", 2048L);

		columnBitmasks.put("urlTitle", 4096L);

		_columnBitmasks = Collections.unmodifiableMap(columnBitmasks);
	}

	private long _columnBitmask;
	private Lecture _escapedModel;

}