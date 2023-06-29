package com.liferaybook.courses.web.asset.model;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.blogs.constants.BlogsPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.service.CourseLocalService;
import com.liferaybook.courses.web.constants.LiferayCoursesPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;
import javax.servlet.ServletContext;
import java.util.Locale;

@Component(
        property = "javax.portlet.name=" + LiferayCoursesPortletKeys.PORTLET_ID,
        service = AssetRendererFactory.class
)
public class CourseAssetRendererFactory extends BaseAssetRendererFactory<Course> {

    public static final String TYPE = "course";
    public static final String TYPE_NAME = "Liferay Course";

    public CourseAssetRendererFactory() {
        setClassName(Course.class.getName());
        setLinkable(true);
        setPortletId(LiferayCoursesPortletKeys.PORTLET_ID);
        setSearchable(true);
    }

    @Override
    public AssetRenderer<Course> getAssetRenderer(long classPK, int type) {
        Course course = courseLocalService.fetchCourse(classPK);
        CourseAssetRenderer courseAssetRenderer = new CourseAssetRenderer(course);
        courseAssetRenderer.setAssetRendererType(type);
        courseAssetRenderer.setServletContext(servletContext);
        courseAssetRenderer.setAssetDisplayPageFriendlyURLProvider(assetDisplayPageFriendlyURLProvider);
        return courseAssetRenderer;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getTypeName(Locale locale) {
        return TYPE_NAME;
    }

    @Override
    public PortletURL getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) {
        LiferayPortletURL liferayPortletURL =
                liferayPortletResponse.createLiferayPortletURL(LiferayCoursesPortletKeys.PORTLET_ID, PortletRequest.RENDER_PHASE);
        try {
            liferayPortletURL.setWindowState(windowState);
        }
        catch (WindowStateException windowStateException) {
            if (_log.isDebugEnabled()) {
                _log.debug(windowStateException);
            }
        }
        return liferayPortletURL;
    }

    @Reference(target = "(osgi.web.symbolicname=com.liferaybook.courses.web)")
    private ServletContext servletContext;
    @Reference
    private CourseLocalService courseLocalService;
    @Reference
    private AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider;

    private static final Log _log = LogFactoryUtil.getLog(CourseAssetRendererFactory.class);

}