package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.item.InfoItemClassDetails;
import com.liferay.info.item.InfoItemDetails;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemDetailsProvider.class
)
public class CourseInfoItemDetailsProvider implements InfoItemDetailsProvider<Course> {

	@Override
	public InfoItemClassDetails getInfoItemClassDetails() {
		return new InfoItemClassDetails(Course.class.getName());
	}

	@Override
	public InfoItemDetails getInfoItemDetails(Course course) {
		InfoItemClassDetails infoItemClassDetails = getInfoItemClassDetails();
		InfoItemReference infoItemReference = new InfoItemReference(Course.class.getName(), course.getCourseId());
		return new InfoItemDetails(infoItemClassDetails, infoItemReference);
	}

}