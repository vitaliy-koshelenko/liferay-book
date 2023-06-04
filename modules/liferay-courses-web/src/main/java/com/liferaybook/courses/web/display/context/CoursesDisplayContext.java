package com.liferaybook.courses.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemListBuilder;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CoursesDisplayContext {

    public CoursesDisplayContext(RenderRequest renderRequest, RenderResponse renderResponse) {
        this.renderRequest = renderRequest;
        this.renderResponse = renderResponse;
        this.request = PortalUtil.getHttpServletRequest(renderRequest);
    }

    public List<NavigationItem> getNavigationItems() {
        String tab = ParamUtil.getString(request, "tab", "my");
        return NavigationItemListBuilder
          .add(
            navigationItem -> {
                navigationItem.setActive("my".equals(tab));
                navigationItem.setHref(renderResponse.createRenderURL(), "tab", "my");
                navigationItem.setLabel(LanguageUtil.get(request, "tab-my-courses"));
            }
          ).add(
            navigationItem -> {
                navigationItem.setActive("all".equals(tab));
                navigationItem.setHref(renderResponse.createRenderURL(), "tab", "all");
                navigationItem.setLabel(LanguageUtil.get(request, "tab-all-courses"));
            }
          ).build();
    }

    private final HttpServletRequest request;
    private final RenderRequest renderRequest;
    private final RenderResponse renderResponse;
}