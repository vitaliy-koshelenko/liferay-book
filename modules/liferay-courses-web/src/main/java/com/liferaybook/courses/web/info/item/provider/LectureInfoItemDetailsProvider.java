package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.item.InfoItemClassDetails;
import com.liferay.info.item.InfoItemDetails;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemDetailsProvider;
import com.liferaybook.courses.manager.model.Lecture;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemDetailsProvider.class
)
public class LectureInfoItemDetailsProvider implements InfoItemDetailsProvider<Lecture> {

	@Override
	public InfoItemClassDetails getInfoItemClassDetails() {
		return new InfoItemClassDetails(Lecture.class.getName());
	}

	@Override
	public InfoItemDetails getInfoItemDetails(Lecture lecture) {
		InfoItemClassDetails infoItemClassDetails = getInfoItemClassDetails();
		InfoItemReference infoItemReference = new InfoItemReference(Lecture.class.getName(), lecture.getLectureId());
		return new InfoItemDetails(infoItemClassDetails, infoItemReference);
	}

}