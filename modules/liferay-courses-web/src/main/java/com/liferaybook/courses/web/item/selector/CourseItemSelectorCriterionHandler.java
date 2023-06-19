package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.BaseItemSelectorCriterionHandler;
import com.liferay.item.selector.ItemSelectorCriterionHandler;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

@Component(service = ItemSelectorCriterionHandler.class)
public class CourseItemSelectorCriterionHandler extends BaseItemSelectorCriterionHandler<CourseItemSelectorCriterion> {

    @Override
    public Class<CourseItemSelectorCriterion> getItemSelectorCriterionClass() {
        return CourseItemSelectorCriterion.class;
    }

    @Activate
    @Override
    protected void activate(BundleContext bundleContext) {
        super.activate(bundleContext);
    }

    @Deactivate
    @Override
    protected void deactivate() {
        super.deactivate();
    }

}