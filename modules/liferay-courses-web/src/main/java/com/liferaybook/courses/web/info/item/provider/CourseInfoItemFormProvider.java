package com.liferaybook.courses.web.info.item.provider;

import com.liferay.asset.info.item.provider.AssetEntryInfoItemFieldSetProvider;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.expando.info.item.provider.ExpandoInfoItemFieldSetProvider;
import com.liferay.info.field.InfoFieldSet;
import com.liferay.info.form.InfoForm;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.provider.InfoItemFormProvider;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.info.item.CourseInfoItemFields;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;
import java.util.Set;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemFormProvider.class
)
public class CourseInfoItemFormProvider implements InfoItemFormProvider<Course> {

	@Override
	public InfoForm getInfoForm() {
		return getInfoForm(infoItemFieldSetProvider.getInfoFieldSet(Course.class.getName()));
	}

	@Override
	public InfoForm getInfoForm(Course course) {
		try {
			AssetEntry assetEntry = assetEntryLocalService.getEntry(Course.class.getName(), course.getCourseId());
			InfoFieldSet infoFieldSet = infoItemFieldSetProvider.getInfoFieldSet(assetEntry);
			return getInfoForm(infoFieldSet);
		}
		catch (PortalException e) {
			throw new RuntimeException("Unable to get asset entry for course " + course.getCourseId(), e);
		}
	}

	@Override
	public InfoForm getInfoForm(String formVariationKey, long groupId) {
		InfoFieldSet infoFieldSet = infoItemFieldSetProvider.getInfoFieldSet(Course.class.getName(), 0, groupId);
		return getInfoForm(infoFieldSet);
	}

	private InfoForm getInfoForm(InfoFieldSet fieldSet) {
		Set<Locale> availableLocales = language.getAvailableLocales();
		InfoLocalizedValue.Builder<String> infoLocalizedValueBuilder = InfoLocalizedValue.builder();
		for (Locale locale : availableLocales) {
			infoLocalizedValueBuilder.value(locale, ResourceActionsUtil.getModelResource(locale, Course.class.getName()));
		}
		return InfoForm.builder()
				.infoFieldSetEntry(getGeneralInfoFieldSet())
				.infoFieldSetEntry(getStatsInfoFieldSet())
				.infoFieldSetEntry(getAuditInfoFieldSet())
				.infoFieldSetEntry(getDisplayInfoFieldSet())
				.infoFieldSetEntry(expandoInfoItemFieldSetProvider.getInfoFieldSet(Course.class.getName()))
				.infoFieldSetEntry(templateInfoItemFieldSetProvider.getInfoFieldSet(Course.class.getName()))
				.infoFieldSetEntry(fieldSet)
				.infoFieldSetEntry(infoItemFieldReaderFieldSetProvider.getInfoFieldSet(Course.class.getName()))
				.labelInfoLocalizedValue(infoLocalizedValueBuilder.build())
				.name(Course.class.getName())
				.build();
	}

	private InfoFieldSet getGeneralInfoFieldSet() {
		return InfoFieldSet.builder()
				.infoFieldSetEntry(CourseInfoItemFields.nameInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.descriptionInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.urlTitleInfoField)
				.labelInfoLocalizedValue(InfoLocalizedValue.localize("com.liferay.journal.lang", "section-general-information"))
				.name("general-information")
				.build();
	}

	private InfoFieldSet getStatsInfoFieldSet() {
		return InfoFieldSet.builder()
				.infoFieldSetEntry(CourseInfoItemFields.ratingsInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.viewCountInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.commentsCountInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.subscribedUsersInfoField)
				.labelInfoLocalizedValue(InfoLocalizedValue.localize("com.liferay.journal.lang", "section-stats-information"))
				.name("stats-information")
				.build();
	}

	private InfoFieldSet getAuditInfoFieldSet() {
		return InfoFieldSet.builder()
				.infoFieldSetEntry(CourseInfoItemFields.authorNameInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.createDateInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.modifiedDateInfoField)
				.infoFieldSetEntry(CourseInfoItemFields.lecturesCountInfoField)
				.labelInfoLocalizedValue(InfoLocalizedValue.localize("com.liferay.journal.lang", "section-audit-information"))
				.name("audit-information")
				.build();
	}

	private InfoFieldSet getDisplayInfoFieldSet() {
		return InfoFieldSet.builder()
				.infoFieldSetEntry(CourseInfoItemFields.displayPageURLInfoField)
				.labelInfoLocalizedValue(InfoLocalizedValue.localize("com.liferay.journal.lang", "section-display-information"))
				.name("display-information")
				.build();
	}

	@Reference
	private Language language;
	@Reference
	private AssetEntryLocalService assetEntryLocalService;
	@Reference
	private AssetEntryInfoItemFieldSetProvider infoItemFieldSetProvider;
	@Reference
	private ExpandoInfoItemFieldSetProvider expandoInfoItemFieldSetProvider;
	@Reference
	private TemplateInfoItemFieldSetProvider templateInfoItemFieldSetProvider;
	@Reference
	private InfoItemFieldReaderFieldSetProvider infoItemFieldReaderFieldSetProvider;

}