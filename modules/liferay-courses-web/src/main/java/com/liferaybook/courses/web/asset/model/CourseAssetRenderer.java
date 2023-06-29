package com.liferaybook.courses.web.asset.model;

import com.liferay.asset.display.page.portlet.AssetDisplayPageFriendlyURLProvider;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletLayoutFinder;
import com.liferay.portal.kernel.portlet.PortletLayoutFinderRegistryUtil;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferaybook.courses.manager.model.Course;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class CourseAssetRenderer extends BaseJSPAssetRenderer<Course> {

    private final Course _course;

    public CourseAssetRenderer(Course course) {
        _course = course;
    }

    @Override
    public Course getAssetObject() {
        return _course;
    }

    @Override
    public long getGroupId() {
        return _course.getGroupId();
    }

    @Override
    public long getUserId() {
        return _course.getUserId();
    }

    @Override
    public String getUserName() {
        return _course.getUserName();
    }

    @Override
    public String getUuid() {
        return _course.getUuid();
    }

    @Override
    public String getClassName() {
        return Course.class.getName();
    }

    @Override
    public long getClassPK() {
        return _course.getCourseId();
    }

    @Override
    public String getSummary(PortletRequest portletRequest, PortletResponse portletResponse) {
        return StringUtil.shorten(_course.getDescription(), AssetHelper.ASSET_ENTRY_ABSTRACT_LENGTH);
    }

    @Override
    public String getTitle(Locale locale) {
        return _course.getName();
    }

    @Override
    public String getJspPath(HttpServletRequest httpServletRequest, String template) {
        if (template.equals(TEMPLATE_ABSTRACT) || template.equals(TEMPLATE_FULL_CONTENT)) {
            return "/courses/asset/" + template + ".jsp";
        }
        return null;
    }

    @Override
    public boolean include(HttpServletRequest request, HttpServletResponse response, String template) throws Exception {
        request.setAttribute("course", _course);
        return super.include(request, response, template);
    }

    @Override
    public String getURLView(LiferayPortletResponse liferayPortletResponse, WindowState windowState) throws Exception {
        AssetRendererFactory<Course> assetRendererFactory = getAssetRendererFactory();
        return PortletURLBuilder
                .create(assetRendererFactory.getURLView(liferayPortletResponse, windowState))
                .setMVCRenderCommandName("/courses/view_course")
                .setParameter("courseId", _course.getCourseId())
                .setWindowState(windowState)
                .buildString();
    }

    @Override
    public String getViewInContextMessage() {
        return "view-details";
    }

    @Override
    public String getURLViewInContext(LiferayPortletRequest liferayPortletRequest, LiferayPortletResponse liferayPortletResponse,
                                      String noSuchEntryRedirect) throws PortalException {
        ThemeDisplay themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(WebKeys.THEME_DISPLAY);
        return getURLViewInContext(themeDisplay, noSuchEntryRedirect);
    }

    @Override
    public String getURLViewInContext(ThemeDisplay themeDisplay, String noSuchEntryRedirect) throws PortalException {
        if (_assetDisplayPageFriendlyURLProvider != null) {
            String friendlyURL = _assetDisplayPageFriendlyURLProvider.getFriendlyURL( getClassName(), getClassPK(), themeDisplay);
            if (Validator.isNotNull(friendlyURL)) {
                return friendlyURL;
            }
        }
        if (!_hasViewInContextGroupLayout(_course.getGroupId(), themeDisplay)) {
            return null;
        }
        return getURLViewInContext(themeDisplay, noSuchEntryRedirect, "/courses/find_course", "courseId", _course.getCourseId());
    }

    private boolean _hasViewInContextGroupLayout(long groupId, ThemeDisplay themeDisplay) {
        try {
            PortletLayoutFinder portletLayoutFinder = PortletLayoutFinderRegistryUtil.getPortletLayoutFinder(Course.class.getName());
            PortletLayoutFinder.Result result = portletLayoutFinder.find(themeDisplay, groupId);
            return (result != null) && !Validator.isNull(result.getPortletId());
        }
        catch (PortalException portalException) {
            if (_log.isDebugEnabled()) {
                _log.debug(portalException);
            }
            return false;
        }
    }



    public void setAssetDisplayPageFriendlyURLProvider(AssetDisplayPageFriendlyURLProvider assetDisplayPageFriendlyURLProvider) {
        _assetDisplayPageFriendlyURLProvider = assetDisplayPageFriendlyURLProvider;
    }

    private AssetDisplayPageFriendlyURLProvider _assetDisplayPageFriendlyURLProvider;

    private static final Log _log = LogFactoryUtil.getLog(CourseAssetRenderer.class);

}