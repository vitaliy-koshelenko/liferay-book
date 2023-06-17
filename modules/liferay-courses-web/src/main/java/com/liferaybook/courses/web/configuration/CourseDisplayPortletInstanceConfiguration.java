package com.liferaybook.courses.web.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "liferay-book",
        scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
        id = "com.liferaybook.courses.web.configuration" +
                ".CourseDisplayPortletInstanceConfiguration",
        localization = "content/Language",
        name = "course-display-portlet-instance-configuration-name"
)
public interface CourseDisplayPortletInstanceConfiguration {

    @Meta.AD(name = "url-title", deflt = "")
    public String urlTitle();

    @Meta.AD(name = "display-style", optionLabels = {"table", "cards"},
            optionValues = {"table", "cards"}, deflt = "table")
    public String displayStyle();

}