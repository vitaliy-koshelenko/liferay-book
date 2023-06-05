package com.liferaybook.courses.service;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.*;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalService;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(service = LiferayCoursesAPI.class)
public class LiferayCoursesService implements LiferayCoursesAPI {

	// =====================================  COURSES ==================================================================

	@Override
	public int getCoursesCount(long groupId) {
		return courseLocalService.getGroupCoursesCount(groupId);
	}

	@Override
	public List<Course> getCourses(long groupId, int start, int end) {
		return courseLocalService.getGroupCourses(groupId, start, end);
	}

	@Override
	public Course getCourse(Long courseId) {
		return courseLocalService.fetchCourse(courseId);
	}

	@Override
	public Course getCourse(long groupId, String urlTitle) {
		return courseLocalService.getCourseByUrlTitle(groupId, urlTitle);
	}

	@Override
	public void saveCourse(long userId, long groupId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException {
		courseLocalService.addCourse(userId, groupId, name, description, urlTitle, serviceContext);
	}

	@Override
	public void updateCourse(long userId, Long courseId, String name, String description, String urlTitle, ServiceContext serviceContext) throws PortalException {
		courseLocalService.updateCourse(userId, courseId, name, description, urlTitle, serviceContext);
	}

	@Override
	public void deleteCourse(Long courseId) throws PortalException {
		Course course = courseLocalService.fetchCourse(courseId);
		if (course != null) {
			// Delete Course Lectures
			List<Lecture> lectures = course.getLectures();
			if (ListUtil.isNotEmpty(lectures)) {
				for (Lecture lecture: lectures) {
					lectureLocalService.deleteLecture(lecture);
				}
			}
			// Delete Course Subscriptions
			List<CourseSubscription> subscriptions = course.getSubscriptions();
			if (ListUtil.isNotEmpty(subscriptions)) {
				for (CourseSubscription subscription: subscriptions) {
					courseSubscriptionLocalService.deleteCourseSubscription(subscription);
				}
			}
			// Delete Course
			courseLocalService.deleteCourse(course);
		}
	}

	// =====================================  MY COURSES ===============================================================

	@Override
	public int getMyCoursesCount(long groupId, long userId) {
		DSLQuery dslQuery = buildMyCoursesDSLQuery(groupId, userId);
		List<Course> courses = courseLocalService.dslQuery(dslQuery);
		return courses.size();
	}
	@Override
	public List<Course> getMyCourses(long groupId, long userId, int start, int end) {
		GroupByStep baseQuery = (GroupByStep) buildMyCoursesDSLQuery(groupId, userId);
		DSLQuery dslQuery = baseQuery.limit(start, end);
		return courseLocalService.dslQuery(dslQuery);
	}

	@Override
	public void subscribe(long userId, long courseId) {
		courseSubscriptionLocalService.addSubscription(userId, courseId);
	}

	@Override
	public void unsubscribe(long userId, long courseId) {
		courseSubscriptionLocalService.removeSubscription(userId, courseId);
	}

	private DSLQuery buildMyCoursesDSLQuery(long groupId, long userId) {
		return DSLQueryFactoryUtil
				.select(CourseTable.INSTANCE)
				.from(CourseTable.INSTANCE)
				.innerJoinON(CourseSubscriptionTable.INSTANCE, CourseSubscriptionTable.INSTANCE
						.courseId.eq(CourseTable.INSTANCE.courseId))
				.where(
						CourseTable.INSTANCE.groupId.eq(groupId)
								.and(CourseSubscriptionTable.INSTANCE.userId.eq(userId))
				);
	}

	// =====================================  LECTURES =================================================================

	@Override
	public int getLecturesCount(long courseId) {
		return lectureLocalService.getCourseLecturesCount(courseId);
	}

	@Override
	public List<Lecture> getLectures(long courseId, int start, int end) {
		return lectureLocalService.getCourseLectures(courseId, start, end);
	}

	@Override
	public Lecture getLecture(Long lectureId) {
		return lectureLocalService.fetchLecture(lectureId);
	}

	@Override
	public void saveLecture(long userId, long courseId, String name, String description, String videoLink,
							String urlTitle, ServiceContext serviceContext) throws PortalException {
		lectureLocalService.addLecture(userId, courseId, name, description, videoLink, urlTitle, serviceContext);
	}

	@Override
	public void updateLecture(long userId, long lectureId, String name, String description, String videoLink,
							  String urlTitle, ServiceContext serviceContext) throws PortalException {
		lectureLocalService.updateLecture(userId, lectureId, name, description, videoLink, urlTitle, serviceContext);
	}

	@Override
	public void deleteLecture(Long lectureId) throws PortalException {
		lectureLocalService.deleteLecture(lectureId);
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LectureLocalService lectureLocalService;
	@Reference
	private CourseSubscriptionLocalService courseSubscriptionLocalService;

}