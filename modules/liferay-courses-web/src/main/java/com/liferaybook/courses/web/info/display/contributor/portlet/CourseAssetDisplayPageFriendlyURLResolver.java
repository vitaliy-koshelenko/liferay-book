package com.liferaybook.courses.web.info.display.contributor.portlet;

import com.liferay.asset.display.page.portlet.BaseAssetDisplayPageFriendlyURLResolver;
import com.liferay.portal.kernel.portlet.FriendlyURLResolver;
import com.liferaybook.courses.web.constants.FriendlyURLConstants;
import org.osgi.service.component.annotations.Component;

@Component(service = FriendlyURLResolver.class)
public class CourseAssetDisplayPageFriendlyURLResolver extends BaseAssetDisplayPageFriendlyURLResolver {

    @Override
    public String getURLSeparator() {
        return FriendlyURLConstants.URL_SEPARATOR_COURSE;
    }

}