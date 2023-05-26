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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;lb_CourseSubscription&quot; database table.
 *
 * @author Vitaliy Koshelenko
 * @see CourseSubscription
 * @generated
 */
public class CourseSubscriptionTable
	extends BaseTable<CourseSubscriptionTable> {

	public static final CourseSubscriptionTable INSTANCE =
		new CourseSubscriptionTable();

	public final Column<CourseSubscriptionTable, Long> courseSubscriptionId =
		createColumn(
			"courseSubscriptionId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<CourseSubscriptionTable, Long> courseId = createColumn(
		"courseId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<CourseSubscriptionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private CourseSubscriptionTable() {
		super("lb_CourseSubscription", CourseSubscriptionTable::new);
	}

}