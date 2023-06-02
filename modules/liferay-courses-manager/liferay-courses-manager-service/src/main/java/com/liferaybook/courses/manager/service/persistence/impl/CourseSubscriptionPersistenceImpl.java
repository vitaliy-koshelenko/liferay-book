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

package com.liferaybook.courses.manager.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.liferaybook.courses.manager.exception.NoSuchCourseSubscriptionException;
import com.liferaybook.courses.manager.model.CourseSubscription;
import com.liferaybook.courses.manager.model.CourseSubscriptionTable;
import com.liferaybook.courses.manager.model.impl.CourseSubscriptionImpl;
import com.liferaybook.courses.manager.model.impl.CourseSubscriptionModelImpl;
import com.liferaybook.courses.manager.service.persistence.CourseSubscriptionPersistence;
import com.liferaybook.courses.manager.service.persistence.CourseSubscriptionUtil;
import com.liferaybook.courses.manager.service.persistence.impl.constants.lbPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the course subscription service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @generated
 */
@Component(service = CourseSubscriptionPersistence.class)
public class CourseSubscriptionPersistenceImpl
	extends BasePersistenceImpl<CourseSubscription>
	implements CourseSubscriptionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CourseSubscriptionUtil</code> to access the course subscription persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CourseSubscriptionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByCourseIdAndUserId;
	private FinderPath _finderPathCountByCourseIdAndUserId;

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription findByCourseIdAndUserId(
			long courseId, long userId)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByCourseIdAndUserId(
			courseId, userId);

		if (courseSubscription == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("courseId=");
			sb.append(courseId);

			sb.append(", userId=");
			sb.append(userId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchCourseSubscriptionException(sb.toString());
		}

		return courseSubscription;
	}

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId) {

		return fetchByCourseIdAndUserId(courseId, userId, true);
	}

	/**
	 * Returns the course subscription where courseId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByCourseIdAndUserId(
		long courseId, long userId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {courseId, userId};
		}

		Object result = null;

		if (useFinderCache) {
			result = dummyFinderCache.getResult(
				_finderPathFetchByCourseIdAndUserId, finderArgs, this);
		}

		if (result instanceof CourseSubscription) {
			CourseSubscription courseSubscription = (CourseSubscription)result;

			if ((courseId != courseSubscription.getCourseId()) ||
				(userId != courseSubscription.getUserId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEIDANDUSERID_COURSEID_2);

			sb.append(_FINDER_COLUMN_COURSEIDANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseId);

				queryPos.add(userId);

				List<CourseSubscription> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						dummyFinderCache.putResult(
							_finderPathFetchByCourseIdAndUserId, finderArgs,
							list);
					}
				}
				else {
					CourseSubscription courseSubscription = list.get(0);

					result = courseSubscription;

					cacheResult(courseSubscription);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CourseSubscription)result;
		}
	}

	/**
	 * Removes the course subscription where courseId = &#63; and userId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the course subscription that was removed
	 */
	@Override
	public CourseSubscription removeByCourseIdAndUserId(
			long courseId, long userId)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = findByCourseIdAndUserId(
			courseId, userId);

		return remove(courseSubscription);
	}

	/**
	 * Returns the number of course subscriptions where courseId = &#63; and userId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	@Override
	public int countByCourseIdAndUserId(long courseId, long userId) {
		FinderPath finderPath = _finderPathCountByCourseIdAndUserId;

		Object[] finderArgs = new Object[] {courseId, userId};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEIDANDUSERID_COURSEID_2);

			sb.append(_FINDER_COLUMN_COURSEIDANDUSERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseId);

				queryPos.add(userId);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COURSEIDANDUSERID_COURSEID_2 =
		"courseSubscription.courseId = ? AND ";

	private static final String _FINDER_COLUMN_COURSEIDANDUSERID_USERID_2 =
		"courseSubscription.userId = ?";

	private FinderPath _finderPathWithPaginationFindByCourseId;
	private FinderPath _finderPathWithoutPaginationFindByCourseId;
	private FinderPath _finderPathCountByCourseId;

	/**
	 * Returns all the course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the matching course subscriptions
	 */
	@Override
	public List<CourseSubscription> findByCourseId(long courseId) {
		return findByCourseId(
			courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CourseSubscription> findByCourseId(
		long courseId, int start, int end) {

		return findByCourseId(courseId, start, end, null);
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
	@Override
	public List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return findByCourseId(courseId, start, end, orderByComparator, true);
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
	@Override
	public List<CourseSubscription> findByCourseId(
		long courseId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCourseId;
				finderArgs = new Object[] {courseId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCourseId;
			finderArgs = new Object[] {courseId, start, end, orderByComparator};
		}

		List<CourseSubscription> list = null;

		if (useFinderCache) {
			list = (List<CourseSubscription>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseSubscription courseSubscription : list) {
					if (courseId != courseSubscription.getCourseId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CourseSubscriptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseId);

				list = (List<CourseSubscription>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					dummyFinderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription findByCourseId_First(
			long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByCourseId_First(
			courseId, orderByComparator);

		if (courseSubscription != null) {
			return courseSubscription;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseId=");
		sb.append(courseId);

		sb.append("}");

		throw new NoSuchCourseSubscriptionException(sb.toString());
	}

	/**
	 * Returns the first course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByCourseId_First(
		long courseId,
		OrderByComparator<CourseSubscription> orderByComparator) {

		List<CourseSubscription> list = findByCourseId(
			courseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription findByCourseId_Last(
			long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByCourseId_Last(
			courseId, orderByComparator);

		if (courseSubscription != null) {
			return courseSubscription;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("courseId=");
		sb.append(courseId);

		sb.append("}");

		throw new NoSuchCourseSubscriptionException(sb.toString());
	}

	/**
	 * Returns the last course subscription in the ordered set where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByCourseId_Last(
		long courseId,
		OrderByComparator<CourseSubscription> orderByComparator) {

		int count = countByCourseId(courseId);

		if (count == 0) {
			return null;
		}

		List<CourseSubscription> list = findByCourseId(
			courseId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CourseSubscription[] findByCourseId_PrevAndNext(
			long courseSubscriptionId, long courseId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = findByPrimaryKey(
			courseSubscriptionId);

		Session session = null;

		try {
			session = openSession();

			CourseSubscription[] array = new CourseSubscriptionImpl[3];

			array[0] = getByCourseId_PrevAndNext(
				session, courseSubscription, courseId, orderByComparator, true);

			array[1] = courseSubscription;

			array[2] = getByCourseId_PrevAndNext(
				session, courseSubscription, courseId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseSubscription getByCourseId_PrevAndNext(
		Session session, CourseSubscription courseSubscription, long courseId,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COURSESUBSCRIPTION_WHERE);

		sb.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CourseSubscriptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(courseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						courseSubscription)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CourseSubscription> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course subscriptions where courseId = &#63; from the database.
	 *
	 * @param courseId the course ID
	 */
	@Override
	public void removeByCourseId(long courseId) {
		for (CourseSubscription courseSubscription :
				findByCourseId(
					courseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(courseSubscription);
		}
	}

	/**
	 * Returns the number of course subscriptions where courseId = &#63;.
	 *
	 * @param courseId the course ID
	 * @return the number of matching course subscriptions
	 */
	@Override
	public int countByCourseId(long courseId) {
		FinderPath finderPath = _finderPathCountByCourseId;

		Object[] finderArgs = new Object[] {courseId};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_COURSEID_COURSEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(courseId);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COURSEID_COURSEID_2 =
		"courseSubscription.courseId = ?";

	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching course subscriptions
	 */
	@Override
	public List<CourseSubscription> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CourseSubscription> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
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
	@Override
	public List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
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
	@Override
	public List<CourseSubscription> findByUserId(
		long userId, int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<CourseSubscription> list = null;

		if (useFinderCache) {
			list = (List<CourseSubscription>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (CourseSubscription courseSubscription : list) {
					if (userId != courseSubscription.getUserId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CourseSubscriptionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<CourseSubscription>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					dummyFinderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription findByUserId_First(
			long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByUserId_First(
			userId, orderByComparator);

		if (courseSubscription != null) {
			return courseSubscription;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCourseSubscriptionException(sb.toString());
	}

	/**
	 * Returns the first course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByUserId_First(
		long userId, OrderByComparator<CourseSubscription> orderByComparator) {

		List<CourseSubscription> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription
	 * @throws NoSuchCourseSubscriptionException if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription findByUserId_Last(
			long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByUserId_Last(
			userId, orderByComparator);

		if (courseSubscription != null) {
			return courseSubscription;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchCourseSubscriptionException(sb.toString());
	}

	/**
	 * Returns the last course subscription in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching course subscription, or <code>null</code> if a matching course subscription could not be found
	 */
	@Override
	public CourseSubscription fetchByUserId_Last(
		long userId, OrderByComparator<CourseSubscription> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<CourseSubscription> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public CourseSubscription[] findByUserId_PrevAndNext(
			long courseSubscriptionId, long userId,
			OrderByComparator<CourseSubscription> orderByComparator)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = findByPrimaryKey(
			courseSubscriptionId);

		Session session = null;

		try {
			session = openSession();

			CourseSubscription[] array = new CourseSubscriptionImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, courseSubscription, userId, orderByComparator, true);

			array[1] = courseSubscription;

			array[2] = getByUserId_PrevAndNext(
				session, courseSubscription, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CourseSubscription getByUserId_PrevAndNext(
		Session session, CourseSubscription courseSubscription, long userId,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_COURSESUBSCRIPTION_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(CourseSubscriptionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						courseSubscription)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CourseSubscription> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the course subscriptions where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (CourseSubscription courseSubscription :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(courseSubscription);
		}
	}

	/**
	 * Returns the number of course subscriptions where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching course subscriptions
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)dummyFinderCache.getResult(
			finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COURSESUBSCRIPTION_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"courseSubscription.userId = ?";

	public CourseSubscriptionPersistenceImpl() {
		setModelClass(CourseSubscription.class);

		setModelImplClass(CourseSubscriptionImpl.class);
		setModelPKClass(long.class);

		setTable(CourseSubscriptionTable.INSTANCE);
	}

	/**
	 * Caches the course subscription in the entity cache if it is enabled.
	 *
	 * @param courseSubscription the course subscription
	 */
	@Override
	public void cacheResult(CourseSubscription courseSubscription) {
		dummyEntityCache.putResult(
			CourseSubscriptionImpl.class, courseSubscription.getPrimaryKey(),
			courseSubscription);

		dummyFinderCache.putResult(
			_finderPathFetchByCourseIdAndUserId,
			new Object[] {
				courseSubscription.getCourseId(), courseSubscription.getUserId()
			},
			courseSubscription);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the course subscriptions in the entity cache if it is enabled.
	 *
	 * @param courseSubscriptions the course subscriptions
	 */
	@Override
	public void cacheResult(List<CourseSubscription> courseSubscriptions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (courseSubscriptions.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CourseSubscription courseSubscription : courseSubscriptions) {
			if (dummyEntityCache.getResult(
					CourseSubscriptionImpl.class,
					courseSubscription.getPrimaryKey()) == null) {

				cacheResult(courseSubscription);
			}
		}
	}

	/**
	 * Clears the cache for all course subscriptions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		dummyEntityCache.clearCache(CourseSubscriptionImpl.class);

		dummyFinderCache.clearCache(CourseSubscriptionImpl.class);
	}

	/**
	 * Clears the cache for the course subscription.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CourseSubscription courseSubscription) {
		dummyEntityCache.removeResult(
			CourseSubscriptionImpl.class, courseSubscription);
	}

	@Override
	public void clearCache(List<CourseSubscription> courseSubscriptions) {
		for (CourseSubscription courseSubscription : courseSubscriptions) {
			dummyEntityCache.removeResult(
				CourseSubscriptionImpl.class, courseSubscription);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		dummyFinderCache.clearCache(CourseSubscriptionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			dummyEntityCache.removeResult(
				CourseSubscriptionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CourseSubscriptionModelImpl courseSubscriptionModelImpl) {

		Object[] args = new Object[] {
			courseSubscriptionModelImpl.getCourseId(),
			courseSubscriptionModelImpl.getUserId()
		};

		dummyFinderCache.putResult(
			_finderPathCountByCourseIdAndUserId, args, Long.valueOf(1));
		dummyFinderCache.putResult(
			_finderPathFetchByCourseIdAndUserId, args,
			courseSubscriptionModelImpl);
	}

	/**
	 * Creates a new course subscription with the primary key. Does not add the course subscription to the database.
	 *
	 * @param courseSubscriptionId the primary key for the new course subscription
	 * @return the new course subscription
	 */
	@Override
	public CourseSubscription create(long courseSubscriptionId) {
		CourseSubscription courseSubscription = new CourseSubscriptionImpl();

		courseSubscription.setNew(true);
		courseSubscription.setPrimaryKey(courseSubscriptionId);

		return courseSubscription;
	}

	/**
	 * Removes the course subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription that was removed
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	@Override
	public CourseSubscription remove(long courseSubscriptionId)
		throws NoSuchCourseSubscriptionException {

		return remove((Serializable)courseSubscriptionId);
	}

	/**
	 * Removes the course subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the course subscription
	 * @return the course subscription that was removed
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	@Override
	public CourseSubscription remove(Serializable primaryKey)
		throws NoSuchCourseSubscriptionException {

		Session session = null;

		try {
			session = openSession();

			CourseSubscription courseSubscription =
				(CourseSubscription)session.get(
					CourseSubscriptionImpl.class, primaryKey);

			if (courseSubscription == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCourseSubscriptionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(courseSubscription);
		}
		catch (NoSuchCourseSubscriptionException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CourseSubscription removeImpl(
		CourseSubscription courseSubscription) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(courseSubscription)) {
				courseSubscription = (CourseSubscription)session.get(
					CourseSubscriptionImpl.class,
					courseSubscription.getPrimaryKeyObj());
			}

			if (courseSubscription != null) {
				session.delete(courseSubscription);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (courseSubscription != null) {
			clearCache(courseSubscription);
		}

		return courseSubscription;
	}

	@Override
	public CourseSubscription updateImpl(
		CourseSubscription courseSubscription) {

		boolean isNew = courseSubscription.isNew();

		if (!(courseSubscription instanceof CourseSubscriptionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(courseSubscription.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					courseSubscription);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in courseSubscription proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CourseSubscription implementation " +
					courseSubscription.getClass());
		}

		CourseSubscriptionModelImpl courseSubscriptionModelImpl =
			(CourseSubscriptionModelImpl)courseSubscription;

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(courseSubscription);
			}
			else {
				courseSubscription = (CourseSubscription)session.merge(
					courseSubscription);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		dummyEntityCache.putResult(
			CourseSubscriptionImpl.class, courseSubscriptionModelImpl, false,
			true);

		cacheUniqueFindersCache(courseSubscriptionModelImpl);

		if (isNew) {
			courseSubscription.setNew(false);
		}

		courseSubscription.resetOriginalValues();

		return courseSubscription;
	}

	/**
	 * Returns the course subscription with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the course subscription
	 * @return the course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	@Override
	public CourseSubscription findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCourseSubscriptionException {

		CourseSubscription courseSubscription = fetchByPrimaryKey(primaryKey);

		if (courseSubscription == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCourseSubscriptionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return courseSubscription;
	}

	/**
	 * Returns the course subscription with the primary key or throws a <code>NoSuchCourseSubscriptionException</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription
	 * @throws NoSuchCourseSubscriptionException if a course subscription with the primary key could not be found
	 */
	@Override
	public CourseSubscription findByPrimaryKey(long courseSubscriptionId)
		throws NoSuchCourseSubscriptionException {

		return findByPrimaryKey((Serializable)courseSubscriptionId);
	}

	/**
	 * Returns the course subscription with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param courseSubscriptionId the primary key of the course subscription
	 * @return the course subscription, or <code>null</code> if a course subscription with the primary key could not be found
	 */
	@Override
	public CourseSubscription fetchByPrimaryKey(long courseSubscriptionId) {
		return fetchByPrimaryKey((Serializable)courseSubscriptionId);
	}

	/**
	 * Returns all the course subscriptions.
	 *
	 * @return the course subscriptions
	 */
	@Override
	public List<CourseSubscription> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<CourseSubscription> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<CourseSubscription> findAll(
		int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<CourseSubscription> findAll(
		int start, int end,
		OrderByComparator<CourseSubscription> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CourseSubscription> list = null;

		if (useFinderCache) {
			list = (List<CourseSubscription>)dummyFinderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COURSESUBSCRIPTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COURSESUBSCRIPTION;

				sql = sql.concat(CourseSubscriptionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CourseSubscription>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					dummyFinderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the course subscriptions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CourseSubscription courseSubscription : findAll()) {
			remove(courseSubscription);
		}
	}

	/**
	 * Returns the number of course subscriptions.
	 *
	 * @return the number of course subscriptions
	 */
	@Override
	public int countAll() {
		Long count = (Long)dummyFinderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_COURSESUBSCRIPTION);

				count = (Long)query.uniqueResult();

				dummyFinderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return dummyEntityCache;
	}

	@Override
	protected String getPKDBName() {
		return "courseSubscriptionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COURSESUBSCRIPTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CourseSubscriptionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the course subscription persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByCourseIdAndUserId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCourseIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"courseId", "userId"}, true);

		_finderPathCountByCourseIdAndUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCourseIdAndUserId",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"courseId", "userId"}, false);

		_finderPathWithPaginationFindByCourseId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCourseId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"courseId"}, true);

		_finderPathWithoutPaginationFindByCourseId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCourseId",
			new String[] {Long.class.getName()}, new String[] {"courseId"},
			true);

		_finderPathCountByCourseId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCourseId",
			new String[] {Long.class.getName()}, new String[] {"courseId"},
			false);

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_setCourseSubscriptionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCourseSubscriptionUtilPersistence(null);

		dummyEntityCache.removeCache(CourseSubscriptionImpl.class.getName());
	}

	private void _setCourseSubscriptionUtilPersistence(
		CourseSubscriptionPersistence courseSubscriptionPersistence) {

		try {
			Field field = CourseSubscriptionUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, courseSubscriptionPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = lbPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = lbPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = lbPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private static final String _SQL_SELECT_COURSESUBSCRIPTION =
		"SELECT courseSubscription FROM CourseSubscription courseSubscription";

	private static final String _SQL_SELECT_COURSESUBSCRIPTION_WHERE =
		"SELECT courseSubscription FROM CourseSubscription courseSubscription WHERE ";

	private static final String _SQL_COUNT_COURSESUBSCRIPTION =
		"SELECT COUNT(courseSubscription) FROM CourseSubscription courseSubscription";

	private static final String _SQL_COUNT_COURSESUBSCRIPTION_WHERE =
		"SELECT COUNT(courseSubscription) FROM CourseSubscription courseSubscription WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseSubscription.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseSubscription exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CourseSubscription exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseSubscriptionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return dummyFinderCache;
	}

}