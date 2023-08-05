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
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.base.CourseServiceBaseImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Vitaliy Koshelenko
 */
@Component(
	property = {
		"json.web.service.context.name=lb",
		"json.web.service.context.path=Course"
	},
	service = AopService.class
)
public class CourseServiceImpl extends CourseServiceBaseImpl {

	@Override
	public Course getCourse(long courseId) throws PortalException {
		Course course = courseLocalService.getCourse(courseId);
		_courseModelResourcePermission.check(getPermissionChecker(), course, ActionKeys.VIEW);
		return course;
	}

	@Reference(
			policy = ReferencePolicy.DYNAMIC,
			policyOption = ReferencePolicyOption.GREEDY,
			target = "(model.class.name=com.liferaybook.courses.manager.model.Course)"
	)
	private volatile ModelResourcePermission<Course> _courseModelResourcePermission;

}