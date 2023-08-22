package com.liferaybook.courses.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
import com.liferaybook.courses.manager.constants.CourseField;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(service = {})
public class CourseSearchRegistrar {

    @Activate
    protected void activate(BundleContext bundleContext) {
        serviceRegistration = modelSearchRegistrarHelper.register(
                Course.class, bundleContext,
                modelSearchDefinition -> {
                    modelSearchDefinition.setDefaultSelectedFieldNames(
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
                    modelSearchDefinition.setModelIndexWriteContributor(modelIndexWriterContributor);
                    modelSearchDefinition.setModelSummaryContributor(modelSummaryContributor);
                });
    }

    @Deactivate
    protected void deactivate() {
        serviceRegistration.unregister();
    }

    @Reference
    private ModelSearchRegistrarHelper modelSearchRegistrarHelper;
    @Reference(target = "(indexer.class.name=com.liferaybook.courses.manager.model.Course)" )
    private ModelIndexerWriterContributor<Course> modelIndexWriterContributor;
    @Reference(target = "(indexer.class.name=com.liferaybook.courses.manager.model.Course)")
    private ModelSummaryContributor modelSummaryContributor;

    private ServiceRegistration<?> serviceRegistration;

}