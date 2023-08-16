package com.liferaybook.courses.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchSettings;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
	service = ModelPreFilterContributor.class
)
public class CourseModelPreFilterContributor implements ModelPreFilterContributor {

	@Override
	public void contribute(BooleanFilter booleanFilter,
						   ModelSearchSettings modelSearchSettings, SearchContext searchContext) {


	}

	private static final Log _log = LogFactoryUtil.getLog(CourseModelPreFilterContributor.class);

}