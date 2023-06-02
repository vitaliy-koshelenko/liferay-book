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

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferaybook.courses.manager.model.Lecture;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for Lecture. This utility wraps
 * <code>com.liferaybook.courses.manager.service.impl.LectureLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Vitaliy Koshelenko
 * @see LectureLocalService
 * @generated
 */
public class LectureLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferaybook.courses.manager.service.impl.LectureLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the lecture to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LectureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lecture the lecture
	 * @return the lecture that was added
	 */
	public static Lecture addLecture(Lecture lecture) {
		return getService().addLecture(lecture);
	}

	public static Lecture addLecture(
			long userId, long courseId, String name, String description,
			String videoLink,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addLecture(
			userId, courseId, name, description, videoLink, serviceContext);
	}

	/**
	 * Creates a new lecture with the primary key. Does not add the lecture to the database.
	 *
	 * @param lectureId the primary key for the new lecture
	 * @return the new lecture
	 */
	public static Lecture createLecture(long lectureId) {
		return getService().createLecture(lectureId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the lecture from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LectureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lecture the lecture
	 * @return the lecture that was removed
	 */
	public static Lecture deleteLecture(Lecture lecture) {
		return getService().deleteLecture(lecture);
	}

	/**
	 * Deletes the lecture with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LectureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lectureId the primary key of the lecture
	 * @return the lecture that was removed
	 * @throws PortalException if a lecture with the primary key could not be found
	 */
	public static Lecture deleteLecture(long lectureId) throws PortalException {
		return getService().deleteLecture(lectureId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.LectureModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.LectureModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static Lecture fetchLecture(long lectureId) {
		return getService().fetchLecture(lectureId);
	}

	/**
	 * Returns the lecture matching the UUID and group.
	 *
	 * @param uuid the lecture's UUID
	 * @param groupId the primary key of the group
	 * @return the matching lecture, or <code>null</code> if a matching lecture could not be found
	 */
	public static Lecture fetchLectureByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchLectureByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static List<Lecture> getCourseLectures(long courseId) {
		return getService().getCourseLectures(courseId);
	}

	public static List<Lecture> getCourseLectures(
		long courseId, int start, int end) {

		return getService().getCourseLectures(courseId, start, end);
	}

	public static int getCourseLecturesCount(long courseId) {
		return getService().getCourseLecturesCount(courseId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the lecture with the primary key.
	 *
	 * @param lectureId the primary key of the lecture
	 * @return the lecture
	 * @throws PortalException if a lecture with the primary key could not be found
	 */
	public static Lecture getLecture(long lectureId) throws PortalException {
		return getService().getLecture(lectureId);
	}

	/**
	 * Returns the lecture matching the UUID and group.
	 *
	 * @param uuid the lecture's UUID
	 * @param groupId the primary key of the group
	 * @return the matching lecture
	 * @throws PortalException if a matching lecture could not be found
	 */
	public static Lecture getLectureByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return getService().getLectureByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the lectures.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferaybook.courses.manager.model.impl.LectureModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectures
	 * @param end the upper bound of the range of lectures (not inclusive)
	 * @return the range of lectures
	 */
	public static List<Lecture> getLectures(int start, int end) {
		return getService().getLectures(start, end);
	}

	/**
	 * Returns all the lectures matching the UUID and company.
	 *
	 * @param uuid the UUID of the lectures
	 * @param companyId the primary key of the company
	 * @return the matching lectures, or an empty list if no matches were found
	 */
	public static List<Lecture> getLecturesByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getLecturesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of lectures matching the UUID and company.
	 *
	 * @param uuid the UUID of the lectures
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of lectures
	 * @param end the upper bound of the range of lectures (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching lectures, or an empty list if no matches were found
	 */
	public static List<Lecture> getLecturesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<Lecture> orderByComparator) {

		return getService().getLecturesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of lectures.
	 *
	 * @return the number of lectures
	 */
	public static int getLecturesCount() {
		return getService().getLecturesCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the lecture in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect LectureLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param lecture the lecture
	 * @return the lecture that was updated
	 */
	public static Lecture updateLecture(Lecture lecture) {
		return getService().updateLecture(lecture);
	}

	public static Lecture updateLecture(
			long userId, long lectureId, String name, String description,
			String videoLink,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateLecture(
			userId, lectureId, name, description, videoLink, serviceContext);
	}

	public static LectureLocalService getService() {
		return _service;
	}

	private static volatile LectureLocalService _service;

}