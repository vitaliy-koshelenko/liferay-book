package com.liferaybook.courses.service;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferaybook.courses.api.LiferayCourse;
import com.liferaybook.courses.api.LiferayCoursesAPI;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component(service = LiferayCoursesAPI.class)
public class LiferayCoursesService implements LiferayCoursesAPI {

	private List<LiferayCourse> courses;

	@Activate
	public void init() {
		courses = new ArrayList<>();
		courses.add(new LiferayCourse(1L, "7.4 Business User", "7.4 Guide for Business Users"));
		courses.add(new LiferayCourse(2L, "7.4 Back-End Developer", "7.4 Guide for Back-End Developers"));
		courses.add(new LiferayCourse(3L, "7.4 Front-End Developer", "7.4 Guide for Front-End Developers"));
		courses.add(new LiferayCourse(4L, "7.3 Business Practitioner", "7.3 Guide for Business Practitioners"));
		courses.add(new LiferayCourse(5L, "7.3 Front-End Developer", "7.3 Guide for Front-End Developers"));
		courses.add(new LiferayCourse(6L, "7.3 Back-End Developer", "7.3 Guide for Back-End Developers"));
		courses.add(new LiferayCourse(7L, "7.3 Solution Architect", "7.3 Guide for Solution Architects"));
		courses.add(new LiferayCourse(8L, "7.3 Site Reliability Engineer", "7.3 Guide for Site Reliability Engineer"));
		courses.add(new LiferayCourse(9L, "7.2 Fundamentals", "7.2 Fundamentals Guide"));
		courses.add(new LiferayCourse(10L, "7.2 Content Creator", "7.2 Guide for Content Creators"));
		courses.add(new LiferayCourse(11L, "7.2 Back-End Developer", "7.2 Guide for Back-End Developers"));
		courses.add(new LiferayCourse(12L, "7.2 Front-End Developer", "7.2 Guide for Front-End Developers"));
	}

	@Override
	public int getCoursesCount() {
		return courses.size();
	}

	@Override
	public List<LiferayCourse> getCourses(int start, int end) {
		return ListUtil.subList(courses, start, end);
	}

	@Override
	public LiferayCourse getCourse(Long courseId) {
		return courses.stream()
				.filter(course -> course.getCourseId().equals(courseId))
				.findFirst().orElse(null);
	}

	@Override
	public void saveCourse(String name, String description) {
		Long courseId = courses.stream()
				.max(Comparator.comparing(LiferayCourse::getCourseId)).get().getCourseId() + 1;
		LiferayCourse course = new LiferayCourse(courseId, name, description);
		courses.add(course);
	}

	@Override
	public void updateCourse(Long courseId, String name, String description) {
		LiferayCourse course = getCourse(courseId);
		if (course != null) {
			course.setName(name);
			course.setDescription(description);
		}
	}

	@Override
	public void deleteCourse(Long courseId) {
		courses.removeIf(course -> course.getCourseId().equals(courseId));
	}

}