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
		entityCache.putResult(
			CourseSubscriptionImpl.class, courseSubscription.getPrimaryKey(),
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
			if (entityCache.getResult(
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
		entityCache.clearCache(CourseSubscriptionImpl.class);

		finderCache.clearCache(CourseSubscriptionImpl.class);
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
		entityCache.removeResult(
			CourseSubscriptionImpl.class, courseSubscription);
	}

	@Override
	public void clearCache(List<CourseSubscription> courseSubscriptions) {
		for (CourseSubscription courseSubscription : courseSubscriptions) {
			entityCache.removeResult(
				CourseSubscriptionImpl.class, courseSubscription);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CourseSubscriptionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(CourseSubscriptionImpl.class, primaryKey);
		}
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

		entityCache.putResult(
			CourseSubscriptionImpl.class, courseSubscription, false, true);

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
			list = (List<CourseSubscription>)finderCache.getResult(
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
					finderCache.putResult(finderPath, finderArgs, list);
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
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_COURSESUBSCRIPTION);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
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
		return entityCache;
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

		_setCourseSubscriptionUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCourseSubscriptionUtilPersistence(null);

		entityCache.removeCache(CourseSubscriptionImpl.class.getName());
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

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COURSESUBSCRIPTION =
		"SELECT courseSubscription FROM CourseSubscription courseSubscription";

	private static final String _SQL_COUNT_COURSESUBSCRIPTION =
		"SELECT COUNT(courseSubscription) FROM CourseSubscription courseSubscription";

	private static final String _ORDER_BY_ENTITY_ALIAS = "courseSubscription.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CourseSubscription exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		CourseSubscriptionPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}