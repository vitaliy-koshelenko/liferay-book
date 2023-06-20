package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletURL;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Component(
        property = "item.selector.view.order:Integer=100",
        service = ItemSelectorView.class
)
public class CourseItemSelectorView implements ItemSelectorView<CourseItemSelectorCriterion> {

    @Override
    public Class<? extends CourseItemSelectorCriterion> getItemSelectorCriterionClass() {
        return CourseItemSelectorCriterion.class;
    }

    @Override
    public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
        return Collections.singletonList(new CourseItemSelectorReturnType());
    }

    @Override
    public String getTitle(Locale locale) {
        return "Courses";
    }

    @Override
    public void renderHTML(ServletRequest servletRequest, ServletResponse servletResponse,
                           CourseItemSelectorCriterion itemSelectorCriterion, PortletURL portletURL,
                           String itemSelectedEventName, boolean search) throws IOException, ServletException {

        CourseItemSelectorViewDescriptor viewDescriptor = new CourseItemSelectorViewDescriptor(servletRequest, portletURL, courseLocalService);
        itemSelectorViewDescriptorRenderer.renderHTML(
                servletRequest,
                servletResponse,
                itemSelectorCriterion,
                portletURL,
                itemSelectedEventName,
                search,
                viewDescriptor
        );

    }

    @Reference
    private CourseLocalService courseLocalService;
    @Reference
    private ItemSelectorViewDescriptorRenderer<CourseItemSelectorCriterion> itemSelectorViewDescriptorRenderer;

}