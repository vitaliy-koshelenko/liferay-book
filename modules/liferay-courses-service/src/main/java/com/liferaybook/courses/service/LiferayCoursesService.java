package com.liferaybook.courses.service;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferaybook.courses.api.LiferayCourse;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(service = LiferayCoursesAPI.class)
public class LiferayCoursesService implements LiferayCoursesAPI {

	@Override
	public int getCoursesCount() {
		return courseLocalService.getCoursesCount();
	}

	@Override
	public List<LiferayCourse> getCourses(int start, int end) {
		List<Course> courses = courseLocalService.getCourses(start, end);
		return convertToLiferayCourses(courses);
	}

	@Override
	public LiferayCourse getCourse(Long courseId) {
		Course course = courseLocalService.fetchCourse(courseId);
		return convertToLiferayCourse(course);
	}

	@Override
	public void saveCourse(String name, String description) {
		long courseId = counterLocalService.increment();
		Course course = courseLocalService.createCourse(courseId);
		course.setName(name);
		course.setDescription(description);
		courseLocalService.updateCourse(course);
	}

	@Override
	public void updateCourse(Long courseId, String name, String description) {
		Course course = courseLocalService.fetchCourse(courseId);
		if (course != null) {
			course.setName(name);
			course.setDescription(description);
			courseLocalService.updateCourse(course);
		}
	}

	@Override
	public void deleteCourse(Long courseId) {
		try {
			courseLocalService.deleteCourse(courseId);
		} catch (PortalException e) {
			_log.error(e.getCause(), e);
		}
	}

	private LiferayCourse convertToLiferayCourse(Course course) {
		LiferayCourse liferayCourse = null;
		if (course != null) {
			liferayCourse = new LiferayCourse();
			BeanPropertiesUtil.copyProperties(course, liferayCourse);
		}
		return liferayCourse;
	}

	private List<LiferayCourse> convertToLiferayCourses(List<Course> courses) {
		return courses.stream().map(this::convertToLiferayCourse)
				.collect(Collectors.toList());
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private CounterLocalService counterLocalService;

	private static final Log _log = LogFactoryUtil
			.getLog(LiferayCoursesService.class);
}