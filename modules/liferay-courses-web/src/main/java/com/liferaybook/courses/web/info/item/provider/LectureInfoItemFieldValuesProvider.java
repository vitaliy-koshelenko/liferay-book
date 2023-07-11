package com.liferaybook.courses.web.info.item.provider;

import com.liferay.asset.info.item.provider.AssetEntryInfoItemFieldSetProvider;
import com.liferay.info.exception.NoSuchInfoItemException;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.web.info.item.LectureInfoItemFields;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemFieldValuesProvider.class
)
public class LectureInfoItemFieldValuesProvider implements InfoItemFieldValuesProvider<Lecture> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(Lecture lecture) {
		try {
			return InfoItemFieldValues.builder()
					.infoFieldValues(getLectureInfoFieldValues(lecture))
					.infoFieldValues(fieldSetProvider.getInfoFieldValues(Course.class.getName(), lecture.getCourseId()))
					.infoItemReference(new InfoItemReference(Lecture.class.getName(), lecture.getCourseId()))
					.build();
		}
		catch (NoSuchInfoItemException e) {
			throw new RuntimeException("Caught unexpected exception", e);
		}
	}

	private List<InfoFieldValue<Object>> getLectureInfoFieldValues(Lecture lecture) {
		List<InfoFieldValue<Object>> lectureFieldValues = new ArrayList<>();
		lectureFieldValues.add(new InfoFieldValue<>(LectureInfoItemFields.nameInfoField, lecture.getName()));
		lectureFieldValues.add(new InfoFieldValue<>(LectureInfoItemFields.descriptionInfoField, lecture.getDescription()));
		lectureFieldValues.add(new InfoFieldValue<>(LectureInfoItemFields.videoUrlInfoField, lecture.getVideoLink()));
		return lectureFieldValues;
	}
	
	@Reference
	private AssetEntryInfoItemFieldSetProvider fieldSetProvider;

}