package com.liferaybook.courses.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;


@Component(
        property = "indexer.class.name=com.liferaybook.courses.manager.model.Course",
        service = ModelDocumentContributor.class
)
public class CourseModelDocumentContributor implements ModelDocumentContributor<Course> {

    public static final String URL_TITLE = "urlTitle";

    @Override
    public void contribute(Document document, Course course) {
        document.addText(Field.TITLE, course.getName());
        document.addText(Field.DESCRIPTION, course.getDescription());
        document.addText(Field.USER_NAME, course.getUserName());
        document.addDate(Field.CREATE_DATE, course.getModifiedDate());
        document.addDate(Field.MODIFIED_DATE, course.getCreateDate());
        document.addKeywordSortable(URL_TITLE, course.getUrlTitle());
    }

}