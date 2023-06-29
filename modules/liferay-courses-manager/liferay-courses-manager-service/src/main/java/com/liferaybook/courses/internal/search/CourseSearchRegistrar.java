package com.liferaybook.courses.internal.search;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchRegistrarHelper;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;
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
        _serviceRegistration = _modelSearchRegistrarHelper.register(
                Course.class, bundleContext,
                modelSearchDefinition -> {
                    modelSearchDefinition.setDefaultSelectedFieldNames(
                            Field.ASSET_TAG_NAMES, Field.COMPANY_ID, Field.CONTENT,
                            Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
                            Field.GROUP_ID, Field.MODIFIED_DATE, Field.SCOPE_GROUP_ID,
                            Field.TITLE, Field.UID);
                    modelSearchDefinition.setModelIndexWriteContributor(_modelIndexWriterContributor);
                    modelSearchDefinition.setModelSummaryContributor(_modelSummaryContributor);
                });
    }

    @Deactivate
    protected void deactivate() {
        _serviceRegistration.unregister();
    }

    private ServiceRegistration<?> _serviceRegistration;

    @Reference
    private ModelSearchRegistrarHelper _modelSearchRegistrarHelper;

    @Reference(target = "(indexer.class.name=com.liferaybook.courses.manager.model.Course)" )
    private ModelIndexerWriterContributor<Course> _modelIndexWriterContributor;

    @Reference(target = "(indexer.class.name=com.liferaybook.courses.manager.model.Course)")
    private ModelSummaryContributor _modelSummaryContributor;

}