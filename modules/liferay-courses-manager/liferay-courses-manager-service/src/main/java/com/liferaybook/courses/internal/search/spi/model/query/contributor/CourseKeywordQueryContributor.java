package com.liferaybook.courses.internal.search.spi.model.query.contributor;

import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.search.query.QueryHelper;
import com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor;
import com.liferay.portal.search.spi.model.query.contributor.helper.KeywordQueryContributorHelper;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(
	property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
	service = KeywordQueryContributor.class
)
public class CourseKeywordQueryContributor implements KeywordQueryContributor {

	@Override
	public void contribute(String keywords, BooleanQuery booleanQuery,
						   KeywordQueryContributorHelper keywordQueryContributorHelper) {

	}

	@Reference
	private QueryHelper queryHelper;

}