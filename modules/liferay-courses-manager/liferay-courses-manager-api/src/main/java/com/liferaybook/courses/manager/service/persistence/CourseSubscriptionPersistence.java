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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferaybook.courses.manager.exception.NoSuchCourseSubscriptionException;
import com.liferaybook.courses.manager.model.CourseSubscription;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the course subscription service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @see CourseSubscriptionUtil
 * @generated
 */
@ProviderType
public interface CourseSubscriptionPersistence
	extends BasePersistence<CourseSubscription> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CourseSubscriptionUtil} to access the course subscription persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public CourseSubscription findByCourseIdAndUserId(
			long courseId, long userId)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId);

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId, boolean useFinderCache);

	/**
	 * Removes the course subscription where courseId = &#63; and userId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the course subscription that was removed
	 */
	public CourseSubscription removeByCourseIdAndUserId(
			long courseId, long userId)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the number of course subscriptions where courseId = &#63; and userId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	public int countByCourseIdAndUserId(long courseId, long userId);

	/**
	 * Returns all the course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching course subscriptions
	 */
	public java.util.List<CourseSubscription> findByCourseId(long courseId);

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
	public java.util.List<CourseSubscription> findByCourseId(
		long courseId, int start, int end);

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
	public java.util.List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

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
	public java.util.List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public CourseSubscription findByCourseId_First(
			long courseId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByCourseId_First(
		long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public CourseSubscription findByCourseId_Last(
			long courseId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByCourseId_Last(
		long courseId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

	/**
	 * Returns the course subscriptions before and after the current course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseSubscriptionId the primary key of the current course subscription
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public CourseSubscription[] findByCourseId_PrevAndNext(
			long courseSubscriptionId, long courseId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Removes all the course subscriptions where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	public void removeByCourseId(long courseId);

	/**
	 * Returns the number of course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching course subscriptions
	 */
	public int countByCourseId(long courseId);

	/**
	 * Returns all the course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching course subscriptions
	 */
	public java.util.List<CourseSubscription> findByUserId(long userId);

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
	public java.util.List<CourseSubscription> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

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
	public java.util.List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public CourseSubscription findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	public CourseSubscription findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	public CourseSubscription fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

	/**
	 * Returns the course subscriptions before and after the current course subscription in the ordered set where userId = &#63;.
	 *
	 * @param courseSubscriptionId the primary key of the current course subscription
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public CourseSubscription[] findByUserId_PrevAndNext(
			long courseSubscriptionId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
				orderByComparator)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Removes all the course subscriptions where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	public int countByUserId(long userId);

	/**
	 * Caches the course subscription in the entity cache if it is enabled.
	 *
	 * @param courseSubscription the course subscription
	 */
	public void cacheResult(CourseSubscription courseSubscription);

	/**
	 * Caches the course subscriptions in the entity cache if it is enabled.
	 *
	 * @param courseSubscriptions the course subscriptions
	 */
	public void cacheResult(
		java.util.List<CourseSubscription> courseSubscriptions);

	/**
	 * Creates a new course subscription with the primary key. Does not add the course subscription to the database.
	 *
	 * @param courseSubscriptionId the primary key for the new course subscription
	 * @return the new course subscription
	 */
	public CourseSubscription create(long courseSubscriptionId);

	/**
	 * Removes the course subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription that was removed
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public CourseSubscription remove(long courseSubscriptionId)
		throws NoSuchCourseSubscriptionException;

	public CourseSubscription updateImpl(CourseSubscription courseSubscription);

	/**
	 * Returns the course subscription with the primary key or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	public CourseSubscription findByPrimaryKey(long courseSubscriptionId)
		throws NoSuchCourseSubscriptionException;

	/**
	 * Returns the course subscription with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription, or <code>null</code> if a course subscription with the primary key could not be found
	 */
	public CourseSubscription fetchByPrimaryKey(long courseSubscriptionId);

	/**
	 * Returns all the course subscriptions.
	 *
	 * @return the course subscriptions
	 */
	public java.util.List<CourseSubscription> findAll();

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
	public java.util.List<CourseSubscription> findAll(int start, int end);

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
	public java.util.List<CourseSubscription> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator);

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
	public java.util.List<CourseSubscription> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CourseSubscription>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the course subscriptions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of course subscriptions.
	 *
	 * @return the number of course subscriptions
	 */
	public int countAll();

}