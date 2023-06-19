package com.liferaybook.courses.web.item.selector;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewDescriptor;
import com.liferay.item.selector.ItemSelectorViewDescriptorRenderer;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.Portal;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
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

        JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

        itemSelectorViewDescriptorRenderer.renderHTML(
                servletRequest,
                servletResponse,
                itemSelectorCriterion,
                portletURL,
                itemSelectedEventName,
                search,
                new ItemSelectorViewDescriptor<Course>() {
                    @Override
                    public ItemDescriptor getItemDescriptor(Course course) {
                        return new ItemDescriptor() {
                            @Override
                            public String getIcon() {
                                return null;
                            }
                            @Override
                            public String getImageURL() {
                                return null;
                            }
                            @Override
                            public String getPayload() {
                                return jsonSerializer.serialize(course);
                            }
                            @Override
                            public String getSubtitle(Locale locale) {
                                return course.getDescription();
                            }
                            @Override
                            public String getTitle(Locale locale) {
                                return course.getName();
                            }
                        };
                    }

                    @Override
                    public ItemSelectorReturnType getItemSelectorReturnType() {
                        return new CourseItemSelectorReturnType();
                    }

                    @Override
                    public SearchContainer<Course> getSearchContainer() throws PortalException {
                        PortletRequest portletRequest = (PortletRequest)servletRequest.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
                        SearchContainer<Course> searchContainer = new SearchContainer<>(portletRequest, portletURL, null, null);
                        long groupId = portal.getScopeGroupId(portletRequest);
                        searchContainer.setResultsAndTotal(courseLocalService.getGroupCourses(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS));
                        return searchContainer;
                    }

                    @Override
                    public boolean isShowBreadcrumb() {
                        return false;
                    }
                }
        );

    }

    @Reference
    private Portal portal;
    @Reference
    private CourseLocalService courseLocalService;
    @Reference
    private ItemSelectorViewDescriptorRenderer<CourseItemSelectorCriterion> itemSelectorViewDescriptorRenderer;


}

