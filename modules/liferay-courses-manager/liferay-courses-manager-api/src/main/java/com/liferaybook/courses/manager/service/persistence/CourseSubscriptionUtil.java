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

package com.liferaybook.courses.manager.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferaybook.courses.manager.model.CourseSubscription;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the course subscription service. This utility wraps <code>com.liferaybook.courses.manager.service.persistence.impl.CourseSubscriptionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @see CourseSubscriptionPersistence
 * @generated
 */
public class CourseSubscriptionUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CourseSubscription courseSubscription) {
		getPersistence().clearCache(courseSubscription);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CourseSubscription> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CourseSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CourseSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CourseSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CourseSubscription update(
		CourseSubscription courseSubscription) {

		return getPersistence().update(courseSubscription);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CourseSubscription update(
		CourseSubscription courseSubscription, ServiceContext serviceContext) {

		return getPersistence().update(courseSubscription, serviceContext);
	}

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public static CourseSubscription findByCourseIdAndUserId(
			long courseId, long userId)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByCourseIdAndUserId(courseId, userId);
	}

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId) {

		return getPersistence().fetchByCourseIdAndUserId(courseId, userId);
	}

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId, boolean useFinderCache) {

		return getPersistence().fetchByCourseIdAndUserId(
			courseId, userId, useFinderCache);
	}

	/**
	 * Removes the course subscription where courseId = &#63; and userId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the course subscription that was removed
	 */
	public static CourseSubscription removeByCourseIdAndUserId(
			long courseId, long userId)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().removeByCourseIdAndUserId(courseId, userId);
	}

	/**
	 * Returns the number of course subscriptions where courseId = &#63; and userId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	public static int countByCourseIdAndUserId(long courseId, long userId) {
		return getPersistence().countByCourseIdAndUserId(courseId, userId);
	}

	/**
	 * Returns all the course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching course subscriptions
	 */
	public static List<CourseSubscription> findByCourseId(long courseId) {
		return getPersistence().findByCourseId(courseId);
	}

	/**
	 * Returns a range of all the course subscriptions where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @return the range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByCourseId(
		long courseId, int start, int end) {

		return getPersistence().findByCourseId(courseId, start, end);
	}

	/**
	 * Returns an ordered range of all the course subscriptions where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().findByCourseId(
			courseId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course subscriptions where courseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param courseId the course ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCourseId(
			courseId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public static CourseSubscription findByCourseId_First(
			long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByCourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByCourseId_First(
		long courseId,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().fetchByCourseId_First(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public static CourseSubscription findByCourseId_Last(
			long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByCourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByCourseId_Last(
		long courseId,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().fetchByCourseId_Last(
			courseId, orderByComparator);
	}

	/**
	 * Returns the course subscriptions before and after the current course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseSubscriptionId the primary key of the current course subscription
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public static CourseSubscription[] findByCourseId_PrevAndNext(
			long courseSubscriptionId, long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByCourseId_PrevAndNext(
			courseSubscriptionId, courseId, orderByComparator);
	}

	/**
	 * Removes all the course subscriptions where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	public static void removeByCourseId(long courseId) {
		getPersistence().removeByCourseId(courseId);
	}

	/**
	 * Returns the number of course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching course subscriptions
	 */
	public static int countByCourseId(long courseId) {
		return getPersistence().countByCourseId(courseId);
	}

	/**
	 * Returns all the course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching course subscriptions
	 */
	public static List<CourseSubscription> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	 * Returns a range of all the course subscriptions where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @return the range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	 * Returns an ordered range of all the course subscriptions where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course subscriptions where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching course subscriptions
	 */
	public static List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public static CourseSubscription findByUserId_First(
			long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByUserId_First(
		long userId, OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public static CourseSubscription findByUserId_Last(
			long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public static CourseSubscription fetchByUserId_Last(
		long userId, OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the course subscriptions before and after the current course subscription in the ordered set where userId = &#63;.
	 *
	 * @param courseSubscriptionId the primary key of the current course subscription
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public static CourseSubscription[] findByUserId_PrevAndNext(
			long courseSubscriptionId, long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByUserId_PrevAndNext(
			courseSubscriptionId, userId, orderByComparator);
	}

	/**
	 * Removes all the course subscriptions where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Caches the course subscription in the entity cache if it is enabled.
	 *
	 * @param courseSubscription the course subscription
	 */
	public static void cacheResult(CourseSubscription courseSubscription) {
		getPersistence().cacheResult(courseSubscription);
	}

	/**
	 * Caches the course subscriptions in the entity cache if it is enabled.
	 *
	 * @param courseSubscriptions the course subscriptions
	 */
	public static void cacheResult(
		List<CourseSubscription> courseSubscriptions) {

		getPersistence().cacheResult(courseSubscriptions);
	}

	/**
	 * Creates a new course subscription with the primary key. Does not add the course subscription to the database.
	 *
	 * @param courseSubscriptionId the primary key for the new course subscription
	 * @return the new course subscription
	 */
	public static CourseSubscription create(long courseSubscriptionId) {
		return getPersistence().create(courseSubscriptionId);
	}

	/**
	 * Removes the course subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription that was removed
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public static CourseSubscription remove(long courseSubscriptionId)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().remove(courseSubscriptionId);
	}

	public static CourseSubscription updateImpl(
		CourseSubscription courseSubscription) {

		return getPersistence().updateImpl(courseSubscription);
	}

	/**
	 * Returns the course subscription with the primary key or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public static CourseSubscription findByPrimaryKey(long courseSubscriptionId)
		throws com.liferaybook.courses.manager.exception.
			NoSuchCourseSubscriptionException {

		return getPersistence().findByPrimaryKey(courseSubscriptionId);
	}

	/**
	 * Returns the course subscription with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription, or <code>null</code> if a course subscription with the primary key could not be found
	 */
	public static CourseSubscription fetchByPrimaryKey(
		long courseSubscriptionId) {

		return getPersistence().fetchByPrimaryKey(courseSubscriptionId);
	}

	/**
	 * Returns all the course subscriptions.
	 *
	 * @return the course subscriptions
	 */
	public static List<CourseSubscription> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the course subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @return the range of course subscriptions
	 */
	public static List<CourseSubscription> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the course subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of course subscriptions
	 */
	public static List<CourseSubscription> findAll(
		int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the course subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CourseSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of course subscriptions
	 * @param end the upper bound of the range of course subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of course subscriptions
	 */
	public static List<CourseSubscription> findAll(
		int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the course subscriptions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of course subscriptions.
	 *
	 * @return the number of course subscriptions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CourseSubscriptionPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CourseSubscriptionPersistence _persistence;

}