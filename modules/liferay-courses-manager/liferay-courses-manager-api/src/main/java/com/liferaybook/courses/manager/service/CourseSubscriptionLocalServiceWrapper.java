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

package com.liferaybook.courses.manager.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CourseSubscriptionLocalService}.
 *
 * @author Vitaliy Koshelenko
 * @see CourseSubscriptionLocalService
 * @generated
 */
public class CourseSubscriptionLocalServiceWrapper
	implements CourseSubscriptionLocalService,
			   ServiceWrapper<CourseSubscriptionLocalService> {

	public CourseSubscriptionLocalServiceWrapper() {
		this(null);
	}

	public CourseSubscriptionLocalServiceWrapper(
		CourseSubscriptionLocalService courseSubscriptionLocalService) {

		_courseSubscriptionLocalService = courseSubscriptionLocalService;
	}

	/**
	 * Adds the course subscription to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseSubscriptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseSubscription the course subscription
	 * @return the course subscription that was added
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
		addCourseSubscription(
			com.liferaybook.courses.manager.model.CourseSubscription
				courseSubscription) {

		return _courseSubscriptionLocalService.addCourseSubscription(
			courseSubscription);
	}

	@Override
	public void addSubscription(long userId, long courseId) {
		_courseSubscriptionLocalService.addSubscription(userId, courseId);
	}

	/**
	 * Creates a new course subscription with the primary key. Does not add the course subscription to the database.
	 *
	 * @param courseSubscriptionId the primary key for the new course subscription
	 * @return the new course subscription
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
		createCourseSubscription(long courseSubscriptionId) {

		return _courseSubscriptionLocalService.createCourseSubscription(
			courseSubscriptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseSubscriptionLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the course subscription from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseSubscriptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseSubscription the course subscription
	 * @return the course subscription that was removed
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
		deleteCourseSubscription(
			com.liferaybook.courses.manager.model.CourseSubscription
				courseSubscription) {

		return _courseSubscriptionLocalService.deleteCourseSubscription(
			courseSubscription);
	}

	/**
	 * Deletes the course subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseSubscriptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription that was removed
	 * @throws PortalException if a course subscription with the primary key could not be found
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
			deleteCourseSubscription(long courseSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseSubscriptionLocalService.deleteCourseSubscription(
			courseSubscriptionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseSubscriptionLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _courseSubscriptionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _courseSubscriptionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _courseSubscriptionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _courseSubscriptionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _courseSubscriptionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _courseSubscriptionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _courseSubscriptionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _courseSubscriptionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
		fetchCourseSubscription(long courseSubscriptionId) {

		return _courseSubscriptionLocalService.fetchCourseSubscription(
			courseSubscriptionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _courseSubscriptionLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the course subscription with the primary key.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription
	 * @throws PortalException if a course subscription with the primary key could not be found
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
			getCourseSubscription(long courseSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseSubscriptionLocalService.getCourseSubscription(
			courseSubscriptionId);
	}

	/**
	 * Returns a range of all the course subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @return the range of course subscriptions
	 */
	@Override
	public java.util.List
		<com.liferaybook.courses.manager.model.CourseSubscription>
			getCourseSubscriptions(int start, int end) {

		return _courseSubscriptionLocalService.getCourseSubscriptions(
			start, end);
	}

	/**
	 * Returns the number of course subscriptions.
	 *
	 * @return the number of course subscriptions
	 */
	@Override
	public int getCourseSubscriptionsCount() {
		return _courseSubscriptionLocalService.getCourseSubscriptionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _courseSubscriptionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _courseSubscriptionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _courseSubscriptionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List
		<com.liferaybook.courses.manager.model.CourseSubscription>
			getSubscriptionsForCourse(long courseId) {

		return _courseSubscriptionLocalService.getSubscriptionsForCourse(
			courseId);
	}

	@Override
	public java.util.List
		<com.liferaybook.courses.manager.model.CourseSubscription>
			getSubscriptionsForUser(long userId) {

		return _courseSubscriptionLocalService.getSubscriptionsForUser(userId);
	}

	@Override
	public boolean isSubscribed(long userId, long courseId) {
		return _courseSubscriptionLocalService.isSubscribed(userId, courseId);
	}

	@Override
	public void removeSubscription(long userId, long courseId) {
		_courseSubscriptionLocalService.removeSubscription(userId, courseId);
	}

	/**
	 * Updates the course subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CourseSubscriptionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param courseSubscription the course subscription
	 * @return the course subscription that was updated
	 */
	@Override
	public com.liferaybook.courses.manager.model.CourseSubscription
		updateCourseSubscription(
			com.liferaybook.courses.manager.model.CourseSubscription
				courseSubscription) {

		return _courseSubscriptionLocalService.updateCourseSubscription(
			courseSubscription);
	}

	@Override
	public CourseSubscriptionLocalService getWrappedService() {
		return _courseSubscriptionLocalService;
	}

	@Override
	public void setWrappedService(
		CourseSubscriptionLocalService courseSubscriptionLocalService) {

		_courseSubscriptionLocalService = courseSubscriptionLocalService;
	}

	private CourseSubscriptionLocalService _courseSubscriptionLocalService;

}