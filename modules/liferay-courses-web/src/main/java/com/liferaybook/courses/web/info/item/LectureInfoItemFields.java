package com.liferaybook.courses.web.info.item;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.field.type.URLInfoFieldType;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferaybook.courses.manager.model.Lecture;

public class LectureInfoItemFields {

    public static final InfoField<TextInfoFieldType> nameInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("name")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-lecture-name"))
                    .build();

    public static final InfoField<TextInfoFieldType> descriptionInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("description")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-lecture-description"))
                    .build();

    public static final InfoField<URLInfoFieldType> videoUrlInfoField =
            BuilderHolder._builder
                    .infoFieldType(URLInfoFieldType.INSTANCE)
                    .name("videoUrl")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-lecture-video-url"))
                    .build();

    private static class BuilderHolder {
        private static final InfoField.NamespacedBuilder _builder = InfoField.builder(Lecture.class.getSimpleName());

    }

}
