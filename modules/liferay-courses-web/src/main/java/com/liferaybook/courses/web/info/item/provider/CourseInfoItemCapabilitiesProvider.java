package com.liferaybook.courses.web.info.item.provider;

import com.liferay.info.item.capability.InfoItemCapability;
import com.liferay.info.item.provider.InfoItemCapabilitiesProvider;
import com.liferay.layout.page.template.info.item.capability.DisplayPageInfoItemCapability;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferaybook.courses.manager.model.Course;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(service = InfoItemCapabilitiesProvider.class)
public class CourseInfoItemCapabilitiesProvider implements InfoItemCapabilitiesProvider<Course> {

    @Override
    public List<InfoItemCapability> getInfoItemCapabilities() {
        return ListUtil.fromArray(displayPageInfoItemCapability);
    }

    @Reference
    private DisplayPageInfoItemCapability displayPageInfoItemCapability;
}