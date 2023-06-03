package com.liferaybook.courses.web.screen.navigation;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.portal.kernel.language.LanguageUtil;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
        property = "screen.navigation.category.order:Integer=20",
        service = ScreenNavigationCategory.class
)
public class LecturesScreenNavigationCategory implements ScreenNavigationCategory {

    @Override
    public String getCategoryKey() {
        return CoursesScreenNavigationEntryConstants.CATEGORY_LECTURES;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "screen-category-lectures");
    }

    @Override
    public String getScreenNavigationKey() {
        return CoursesScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY;
    }

}