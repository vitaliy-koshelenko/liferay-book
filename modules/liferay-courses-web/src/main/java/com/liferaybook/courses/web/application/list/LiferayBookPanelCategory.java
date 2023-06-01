package com.liferaybook.courses.web.application.list;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferaybook.courses.web.application.list.constants.LiferayBookPanelKeys;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
        property = {
                "panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION,
                "panel.category.order:Integer=301"
        },
        service = PanelCategory.class
)
public class LiferayBookPanelCategory extends BasePanelCategory {

    @Override
    public String getKey() {
        return LiferayBookPanelKeys.LIFERAY_BOOK;
    }

    @Override
    public String getLabel(Locale locale) {
        return LanguageUtil.get(locale, "category." + LiferayBookPanelKeys.LIFERAY_BOOK);
    }

}