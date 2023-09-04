package com.liferaybook.courses.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.search.spi.model.query.contributor.QueryConfigContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.QueryConfigContributorHelper;
import com.liferaybook.courses.manager.constants.CourseField;
import org.osgi.service.component.annotations.Component;

import java.util.HashSet;
import java.util.Set;

@Component(
	property = "service.ranking:Integer=" + CourseFieldNamesQueryConfigContributor.RANKING,
	service = QueryConfigContributor.class
)
public class CourseFieldNamesQueryConfigContributor implements QueryConfigContributor {

	public static final int RANKING = 100;

	@Override
	public void contributeQueryConfigurations(SearchContext searchContext, QueryConfigContributorHelper helper) {

		// Get Query Config
		QueryConfig queryConfig = searchContext.getQueryConfig();

		// Get Selected Field Names
		String[] defaultSelectedFieldNames = helper.getDefaultSelectedFieldNames();
		Set<String> selectedFieldNames = new HashSet<>();
		if (ArrayUtil.isNotEmpty(defaultSelectedFieldNames)) {
			selectedFieldNames = SetUtil.fromArray(defaultSelectedFieldNames);
		}

		// Add Additional Fields
		selectedFieldNames.add(Field.PRIORITY);
		selectedFieldNames.add(CourseField.VIEW_COUNT);

		// Update Selected Field Names
		queryConfig.addSelectedFieldNames(selectedFieldNames.toArray(new String[0]));
	}

}