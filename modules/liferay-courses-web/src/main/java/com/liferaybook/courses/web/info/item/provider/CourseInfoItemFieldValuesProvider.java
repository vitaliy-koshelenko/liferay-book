package com.liferaybook.courses.web.info.item.provider;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.info.item.provider.AssetEntryInfoItemFieldSetProvider;
import com.liferay.expando.info.item.provider.ExpandoInfoItemFieldSetProvider;
import com.liferay.info.field.InfoFieldValue;
import com.liferay.info.item.InfoItemFieldValues;
import com.liferay.info.item.InfoItemReference;
import com.liferay.info.item.field.reader.InfoItemFieldReaderFieldSetProvider;
import com.liferay.info.item.provider.InfoItemFieldValuesProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.template.info.item.provider.TemplateInfoItemFieldSetProvider;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.web.info.item.CourseInfoItemFields;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(
	property = Constants.SERVICE_RANKING + ":Integer=1",
	service = InfoItemFieldValuesProvider.class
)
public class CourseInfoItemFieldValuesProvider implements InfoItemFieldValuesProvider<Course> {

	@Override
	public InfoItemFieldValues getInfoItemFieldValues(Course course) {
		try {
			return InfoItemFieldValues.builder()
					.infoFieldValues(getCourseInfoFieldValues(course))
					.infoFieldValues(fieldSetProvider.getInfoFieldValues(Course.class.getName(), course.getCourseId()))
					.infoFieldValues(expandoInfoItemFieldSetProvider.getInfoFieldValues(Course.class.getName(), course))
					.infoFieldValues(infoItemFieldReaderFieldSetProvider.getInfoFieldValues(Course.class.getName(), course))
					.infoFieldValues(templateInfoItemFieldSetProvider.getInfoFieldValues(Course.class.getName(), course))
					.infoItemReference(new InfoItemReference(Course.class.getName(), course.getCourseId()))
					.build();
		}
		catch (Exception e) {
			throw new RuntimeException("Caught unexpected exception", e);
		}
	}

	private List<InfoFieldValue<Object>> getCourseInfoFieldValues(Course course) throws PortalException {
		List<InfoFieldValue<Object>> values = new ArrayList<>();
		values.add(new InfoFieldValue<>(CourseInfoItemFields.nameInfoField, course.getName()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.descriptionInfoField, course.getDescription()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.urlTitleInfoField, course.getUrlTitle()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.ratingsInfoField, course.getRating()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.viewCountInfoField, course.getViewCount()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.commentsCountInfoField, course.getCommentsCount()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.subscribedUsersInfoField, course.getSubscribedUsersCount()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.createDateInfoField, course.getCreateDate()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.modifiedDateInfoField, course.getModifiedDate()));
		values.add(new InfoFieldValue<>(CourseInfoItemFields.lecturesCountInfoField, course.getLecturesCount()));
		User user = userLocalService.fetchUser(course.getUserId());
		if (user != null) {
			values.add(new InfoFieldValue<>(CourseInfoItemFields.authorNameInfoField, user.getFullName()));
		}
		ThemeDisplay themeDisplay = getThemeDisplay();
		if (themeDisplay != null) {
			values.add(new InfoFieldValue<>(CourseInfoItemFields.displayPageURLInfoField, getDisplayPageURL(course)));
		}
		// ...
		return values;
	}

	private String getDisplayPageURL(Course course) throws PortalException {
		return assetDisplayPageFriendlyURLProvider.getFriendlyURL(Course.class.getName(), course.getCourseId(), getThemeDisplay());
	}

	private ThemeDisplay getThemeDisplay() {
		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();
		return serviceContext != null ? serviceContext.getThemeDisplay() : null;
	}

	@Reference
	private UserLocalService userLocalService;
	@Reference
	private AssetEntryInfoItemFieldSetProvider fieldSetProvider;
	@Reference
	private ExpandoInfoItemFieldSetProvider expandoInfoItemFieldSetProvider;
	@Reference
	private InfoItemFieldReaderFieldSetProvider infoItemFieldReaderFieldSetProvider;
	@Reference
	private TemplateInfoItemFieldSetProvider templateInfoItemFieldSetProvider;
	@Reference
	private AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider;

}