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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Course service. Represents a row in the &quot;lb_Course&quot; database table, with each column mapped to a property of this class.
 *
 * @author Vitaliy Koshelenko
 * @see CourseModel
 * @generated
 */
@ImplementationClassName(
	"com.liferaybook.courses.manager.model.impl.CourseImpl"
)
@ProviderType
public interface Course extends CourseModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferaybook.courses.manager.model.impl.CourseImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Course, Long> COURSE_ID_ACCESSOR =
		new Accessor<Course, Long>() {

			@Override
			public Long get(Course course) {
				return course.getCourseId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Course> getTypeClass() {
				return Course.class;
			}

		};

	public java.util.List<Lecture> getLectures();

	public java.util.List<CourseSubscription> getSubscriptions();

	public boolean isUserSubscribed(long userId);

	public String getCreateDateString();

	public String getModifiedDateString();

	public com.liferay.asset.kernel.model.AssetEntry getAssetEntry();

	public java.util.List<com.liferay.asset.kernel.model.AssetCategory>
		getCategories();

	public java.util.List<String> getCategoryNames();

	public String getCategoryNamesString();

	public java.util.List<String> getTagNames();

	public String getTagNamesString();

}