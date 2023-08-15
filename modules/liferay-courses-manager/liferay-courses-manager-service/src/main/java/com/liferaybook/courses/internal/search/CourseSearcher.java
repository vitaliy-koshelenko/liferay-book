package com.liferaybook.courses.internal.search;

import com.liferay.portal.kernel.search.BaseSearcher;
import com.liferay.portal.kernel.search.Field;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.liferaybook.courses.manager.model.Course",
	service = BaseSearcher.class
)
public class CourseSearcher extends BaseSearcher {

	public CourseSearcher() {
		setDefaultSelectedFieldNames(
				Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
				Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
				Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
				Field.TITLE, Field.UID);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return _CLASS_NAME;
	}

	private static final String _CLASS_NAME = Course.class.getName();

}