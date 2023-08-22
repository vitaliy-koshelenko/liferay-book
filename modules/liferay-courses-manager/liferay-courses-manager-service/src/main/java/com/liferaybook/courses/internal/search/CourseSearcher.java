package com.liferaybook.courses.internal.search;

import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Field;
import com.liferaybook.courses.manager.constants.CourseField;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Course",
	service = BaseSearcher.class
)
public class CourseSearcher extends BaseSearcher {

	public CourseSearcher() {
		setDefaultSelectedFieldNames(
				Field.COMPANY_ID,
				Field.GROUP_ID,
				Field.UID,
				CourseField.URL_TITLE,
				Field.ENTRY_CLASS_NAME,
				Field.ENTRY_CLASS_PK,
				Field.TITLE,
				Field.DESCRIPTION,
				Field.ASSET_TAG_NAMES,
				Field.ASSET_CATEGORY_IDS,
				Field.USER_NAME,
				Field.CREATE_DATE,
				Field.MODIFIED_DATE
		);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return _CLASS_NAME;
	}

	private static final String _CLASS_NAME = Course.class.getName();

}