package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.web.info.item.LectureInfoItemFields;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.Set;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemFormProvider.class
)
public class LectureInfoItemFormProvider implements InfoItemFormProvider<Lecture> {

	@Override
	public InfoForm getInfoForm() {
		Set<Locale> availableLocales = language.getAvailableLocales();
		InfoLocalizedValue.Builder<String> infoLocalizedValueBuilder = InfoLocalizedValue.builder();
		for (Locale locale : availableLocales) {
			infoLocalizedValueBuilder.value(locale, ResourceActionsUtil.getModelResource(locale, Lecture.class.getName()));
		}
		return InfoForm.builder()
				.infoFieldSetEntry(getGeneralInfoFieldSet())
				.labelInfoLocalizedValue(infoLocalizedValueBuilder.build())
				.name(Lecture.class.getName())
				.build();
	}

	@Override
	public InfoForm getInfoForm(Lecture lecture) {
		return getInfoForm();
	}

	@Override
	public InfoForm getInfoForm(String formVariationKey, long groupId) {
		return getInfoForm();
	}

	private InfoFieldSet getGeneralInfoFieldSet() {
		return InfoFieldSet.builder()
				.infoFieldSetEntry(LectureInfoItemFields.nameInfoField)
				.infoFieldSetEntry(LectureInfoItemFields.descriptionInfoField)
				.infoFieldSetEntry(LectureInfoItemFields.videoUrlInfoField)
				.labelInfoLocalizedValue(InfoLocalizedValue.localize("com.liferay.journal.lang", "section-general-information"))
				.name("general-information")
				.build();
	}

	@Reference
	private Language language;

}