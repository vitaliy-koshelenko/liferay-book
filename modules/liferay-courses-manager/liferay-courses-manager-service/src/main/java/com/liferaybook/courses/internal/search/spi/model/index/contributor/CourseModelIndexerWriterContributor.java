package com.liferaybook.courses.internal.search.spi.model.index.contributor;

import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
	service = ModelIndexerWriterContributor.class
)
public class CourseModelIndexerWriterContributor implements ModelIndexerWriterContributor<Course> {

	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable,
						  ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
		batchIndexingActionable.setPerformActionMethod((Course course) ->
				batchIndexingActionable.addDocuments(modelIndexerWriterDocumentHelper.getDocument(course))
		);
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return modelIndexerWriterDocumentHelper.
			getBatchIndexingActionable(courseLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(Course course) {
		return course.getCompanyId();
	}

	@Reference
	private CourseLocalService courseLocalService;
	@Reference
	private DynamicQueryBatchIndexingActionableFactory modelIndexerWriterDocumentHelper;

}