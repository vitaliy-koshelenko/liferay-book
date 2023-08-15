package com.liferaybook.courses.internal.search.spi.model.result.contributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
	property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
	service = ModelSummaryContributor.class
)
public class CourseModelSummaryContributor implements ModelSummaryContributor {

	private static final int MAX_CONTENT_LENGTH = 200;

	@Override
	public Summary getSummary(Document document, Locale locale, String snippet) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;
		String title = document.get(prefix + Field.TITLE, Field.TITLE);
		String content = document.get(prefix + Field.CONTENT, Field.CONTENT);
		Summary summary = new Summary(title, content);
		summary.setMaxContentLength(MAX_CONTENT_LENGTH);
		return summary;
	}

}