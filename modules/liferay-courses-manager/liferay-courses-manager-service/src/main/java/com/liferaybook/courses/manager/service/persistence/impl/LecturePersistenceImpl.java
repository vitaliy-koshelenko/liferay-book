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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import com.liferaybook.courses.manager.exception.NoSuchLectureException;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.model.LectureTable;
import com.liferaybook.courses.manager.model.impl.LectureImpl;
import com.liferaybook.courses.manager.model.impl.LectureModelImpl;
import com.liferaybook.courses.manager.service.persistence.LecturePersistence;
import com.liferaybook.courses.manager.service.persistence.LectureUtil;
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
 * The persistence implementation for the lecture service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Vitaliy Koshelenko
 * @generated
 */
@Component(service = LecturePersistence.class)
public class LecturePersistenceImpl
	extends BasePersistenceImpl<Lecture> implements LecturePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>LectureUtil</code> to access the lecture persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		LectureImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;

	public LecturePersistenceImpl() {
		setModelClass(Lecture.class);

		setModelImplClass(LectureImpl.class);
		setModelPKClass(long.class);

		setTable(LectureTable.INSTANCE);
	}

	/**
	 * Caches the lecture in the entity cache if it is enabled.
	 *
	 * @param lecture the lecture
	 */
	@Override
	public void cacheResult(Lecture lecture) {
		entityCache.putResult(
			LectureImpl.class, lecture.getPrimaryKey(), lecture);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the lectures in the entity cache if it is enabled.
	 *
	 * @param lectures the lectures
	 */
	@Override
	public void cacheResult(List<Lecture> lectures) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (lectures.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (Lecture lecture : lectures) {
			if (entityCache.getResult(
					LectureImpl.class, lecture.getPrimaryKey()) == null) {

				cacheResult(lecture);
			}
		}
	}

	/**
	 * Clears the cache for all lectures.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(LectureImpl.class);

		finderCache.clearCache(LectureImpl.class);
	}

	/**
	 * Clears the cache for the lecture.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Lecture lecture) {
		entityCache.removeResult(LectureImpl.class, lecture);
	}

	@Override
	public void clearCache(List<Lecture> lectures) {
		for (Lecture lecture : lectures) {
			entityCache.removeResult(LectureImpl.class, lecture);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(LectureImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(LectureImpl.class, primaryKey);
		}
	}

	/**
	 * Creates a new lecture with the primary key. Does not add the lecture to the database.
	 *
	 * @param lectureId the primary key for the new lecture
	 * @return the new lecture
	 */
	@Override
	public Lecture create(long lectureId) {
		Lecture lecture = new LectureImpl();

		lecture.setNew(true);
		lecture.setPrimaryKey(lectureId);

		lecture.setCompanyId(CompanyThreadLocal.getCompanyId());

		return lecture;
	}

	/**
	 * Removes the lecture with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lectureId the primary key of the lecture
	 * @return the lecture that was removed
	 * @throws NoSuchLectureException if a lecture with the primary key could not be found
	 */
	@Override
	public Lecture remove(long lectureId) throws NoSuchLectureException {
		return remove((Serializable)lectureId);
	}

	/**
	 * Removes the lecture with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lecture
	 * @return the lecture that was removed
	 * @throws NoSuchLectureException if a lecture with the primary key could not be found
	 */
	@Override
	public Lecture remove(Serializable primaryKey)
		throws NoSuchLectureException {

		Session session = null;

		try {
			session = openSession();

			Lecture lecture = (Lecture)session.get(
				LectureImpl.class, primaryKey);

			if (lecture == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLectureException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(lecture);
		}
		catch (NoSuchLectureException noSuchEntityException) {
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
	protected Lecture removeImpl(Lecture lecture) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lecture)) {
				lecture = (Lecture)session.get(
					LectureImpl.class, lecture.getPrimaryKeyObj());
			}

			if (lecture != null) {
				session.delete(lecture);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (lecture != null) {
			clearCache(lecture);
		}

		return lecture;
	}

	@Override
	public Lecture updateImpl(Lecture lecture) {
		boolean isNew = lecture.isNew();

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(lecture);
			}
			else {
				lecture = (Lecture)session.merge(lecture);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(LectureImpl.class, lecture, false, true);

		if (isNew) {
			lecture.setNew(false);
		}

		lecture.resetOriginalValues();

		return lecture;
	}

	/**
	 * Returns the lecture with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lecture
	 * @return the lecture
	 * @throws NoSuchLectureException if a lecture with the primary key could not be found
	 */
	@Override
	public Lecture findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLectureException {

		Lecture lecture = fetchByPrimaryKey(primaryKey);

		if (lecture == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLectureException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return lecture;
	}

	/**
	 * Returns the lecture with the primary key or throws a <code>NoSuchLectureException</code> if it could not be found.
	 *
	 * @param lectureId the primary key of the lecture
	 * @return the lecture
	 * @throws NoSuchLectureException if a lecture with the primary key could not be found
	 */
	@Override
	public Lecture findByPrimaryKey(long lectureId)
		throws NoSuchLectureException {

		return findByPrimaryKey((Serializable)lectureId);
	}

	/**
	 * Returns the lecture with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lectureId the primary key of the lecture
	 * @return the lecture, or <code>null</code> if a lecture with the primary key could not be found
	 */
	@Override
	public Lecture fetchByPrimaryKey(long lectureId) {
		return fetchByPrimaryKey((Serializable)lectureId);
	}

	/**
	 * Returns all the lectures.
	 *
	 * @return the lectures
	 */
	@Override
	public List<Lecture> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LectureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectures
	 * @param end the upper bound of the range of lectures (not inclusive)
	 * @return the range of lectures
	 */
	@Override
	public List<Lecture> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LectureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectures
	 * @param end the upper bound of the range of lectures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lectures
	 */
	@Override
	public List<Lecture> findAll(
		int start, int end, OrderByComparator<Lecture> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the lectures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LectureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectures
	 * @param end the upper bound of the range of lectures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of lectures
	 */
	@Override
	public List<Lecture> findAll(
		int start, int end, OrderByComparator<Lecture> orderByComparator,
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

		List<Lecture> list = null;

		if (useFinderCache) {
			list = (List<Lecture>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_LECTURE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_LECTURE;

				sql = sql.concat(LectureModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<Lecture>)QueryUtil.list(
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
	 * Removes all the lectures from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Lecture lecture : findAll()) {
			remove(lecture);
		}
	}

	/**
	 * Returns the number of lectures.
	 *
	 * @return the number of lectures
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_LECTURE);

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
		return "lectureId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_LECTURE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return LectureModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the lecture persistence.
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

		_setLectureUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setLectureUtilPersistence(null);

		entityCache.removeCache(LectureImpl.class.getName());
	}

	private void _setLectureUtilPersistence(
		LecturePersistence lecturePersistence) {

		try {
			Field field = LectureUtil.class.getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, lecturePersistence);
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

	private static final String _SQL_SELECT_LECTURE =
		"SELECT lecture FROM Lecture lecture";

	private static final String _SQL_COUNT_LECTURE =
		"SELECT COUNT(lecture) FROM Lecture lecture";

	private static final String _ORDER_BY_ENTITY_ALIAS = "lecture.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No Lecture exists with the primary key ";

	private static final Log _log = LogFactoryUtil.getLog(
		LecturePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}