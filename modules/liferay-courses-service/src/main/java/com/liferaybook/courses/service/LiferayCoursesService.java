package com.liferaybook.courses.service;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.PortalException;
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
	public void saveCourse(String name, String description) throws PortalException {
		courseLocalService.addCourse(name, description);
	}

	@Override
	public void updateCourse(Long courseId, String name, String description) throws PortalException {
		courseLocalService.updateCourse(courseId, name, description);
	}

	@Override
	public void deleteCourse(Long courseId) throws PortalException {
		courseLocalService.deleteCourse(courseId);
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

}