package com.liferaybook.courses.internal.search.spi.model.result.contributor;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
	service = ModelVisibilityContributor.class
)
public class CourseModelVisibilityContributor implements ModelVisibilityContributor {

	@Override
	public boolean isVisible(long classPK, int status) {
		try {
			Course course = courseLocalService.getCourse(classPK);
			return course.getLecturesCount() > 0;
			//return isVisible(course.getStatus(), status); //todo: check WorkflowStatus after Workflow implementation
		}
		catch (PortalException portalException) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to check visibility for blogs entry ", portalException);
			}
			return false;
		}
	}

	@Reference
	private CourseLocalService courseLocalService;

	private static final Log _log = LogFactoryUtil.getLog(CourseModelVisibilityContributor.class);

}