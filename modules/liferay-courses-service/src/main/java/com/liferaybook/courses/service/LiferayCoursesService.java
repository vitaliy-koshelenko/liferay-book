package com.liferaybook.courses.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferaybook.courses.api.LiferayCourse;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.api.LiferayLecture;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.manager.service.LectureLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = LiferayCoursesAPI.class)
public class LiferayCoursesService implements LiferayCoursesAPI {

	@Override
	public int getCoursesCount(long groupId) {
		return courseLocalService.getGroupCoursesCount(groupId);
	}

	@Override
	public List<LiferayCourse> getCourses(long groupId, int start, int end) {
		List<Course> courses = courseLocalService
				.getGroupCourses(groupId, start, end);
		return convertToLiferayCourses(courses);
	}

	@Override
	public LiferayCourse getCourse(Long courseId) {
		Course course = courseLocalService.fetchCourse(courseId);
		return convertToLiferayCourse(course);
	}

	@Override
	public void saveCourse(long userId, long groupId, String name, String description, ServiceContext serviceContext) throws PortalException {
		courseLocalService.addCourse(userId, groupId, name, description, serviceContext);
	}

	@Override
	public void updateCourse(long userId, Long courseId, String name, String description, ServiceContext serviceContext) throws PortalException {
		courseLocalService.updateCourse(userId, courseId, name, description, serviceContext);
	}

	@Override
	public void deleteCourse(Long courseId) throws PortalException {
		courseLocalService.deleteCourse(courseId);
	}

	@Override
	public int getLecturesCount(long courseId) {
		return lectureLocalService.getCourseLecturesCount(courseId);
	}

	@Override
	public List<LiferayLecture> getLectures(long courseId, int start, int end) {
		List<Lecture> lectures = lectureLocalService.getCourseLectures(courseId, start, end);
		return convertToLiferayLectures(lectures);
	}

	@Override
	public LiferayLecture getLecture(Long lectureId) {
		Lecture lecture = lectureLocalService.fetchLecture(lectureId);
		return convertToLiferayLecture(lecture);
	}

	@Override
	public void saveLecture(long userId, long courseId, String name, String description, String videoLink, ServiceContext serviceContext) throws PortalException {
		lectureLocalService.addLecture(userId, courseId, name, description, videoLink, serviceContext);
	}

	@Override
	public void updateLecture(long userId, long lectureId, String name, String description, String videoLink, ServiceContext serviceContext) throws PortalException {
		lectureLocalService.updateLecture(userId, lectureId, name, description, videoLink, serviceContext);
	}

	@Override
	public void deleteLecture(Long lectureId) throws PortalException {
		lectureLocalService.deleteLecture(lectureId);
	}

	private LiferayCourse convertToLiferayCourse(Course course) {
		LiferayCourse liferayCourse = null;
		if (course != null) {
			liferayCourse = new LiferayCourse();
			liferayCourse.setCourseId(course.getCourseId());
			liferayCourse.setName(course.getName());
			liferayCourse.setDescription(course.getDescription());
			liferayCourse.setUserName(course.getUserName());
			liferayCourse.setCreateDate(course.getCreateDate());
			liferayCourse.setModifiedDate(course.getModifiedDate());
		}
		return liferayCourse;
	}

	private List<LiferayCourse> convertToLiferayCourses(List<Course> courses) {
		return courses.stream().map(this::convertToLiferayCourse)
				.collect(Collectors.toList());
	}

	private LiferayLecture convertToLiferayLecture(Lecture lecture) {
		LiferayLecture liferayLecture = null;
		if (lecture != null) {
			liferayLecture = new LiferayLecture();
			liferayLecture.setLectureId(lecture.getLectureId());
			liferayLecture.setName(lecture.getName());
			liferayLecture.setDescription(lecture.getDescription());
			liferayLecture.setVideoLink(lecture.getVideoLink());
			liferayLecture.setUserName(lecture.getUserName());
			liferayLecture.setCreateDate(lecture.getCreateDate());
			liferayLecture.setModifiedDate(lecture.getModifiedDate());
		}
		return liferayLecture;
	}

	private List<LiferayLecture> convertToLiferayLectures(List<Lecture> lectures) {
		return lectures.stream().map(this::convertToLiferayLecture)
				.collect(Collectors.toList());
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private LectureLocalService lectureLocalService;

}