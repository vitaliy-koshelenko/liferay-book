package com.liferaybook.courses.web.info.item;

import com.liferay.info.field.InfoField;
import com.liferay.info.field.type.DateInfoFieldType;
import com.liferay.info.field.type.NumberInfoFieldType;
import com.liferay.info.field.type.TextInfoFieldType;
import com.liferay.info.field.type.URLInfoFieldType;
import com.liferay.info.localized.InfoLocalizedValue;
import com.liferaybook.courses.manager.model.Course;

public class CourseInfoItemFields {

    // --------------------------------------- GENERAL -----------------------------------------------------------------

    public static final InfoField<TextInfoFieldType> nameInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("name")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-name"))
                    .build();

    public static final InfoField<TextInfoFieldType> descriptionInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("description")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-description"))
                    .build();

    public static final InfoField<TextInfoFieldType> urlTitleInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("urlTitle")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-url-title"))
                    .build();


    // --------------------------------------- STATS -------------------------------------------------------------------

    public static final InfoField<NumberInfoFieldType> ratingsInfoField =
            BuilderHolder._builder
                    .infoFieldType(NumberInfoFieldType.INSTANCE)
                    .name("ratings")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-ratings"))
                    .build();

    public static final InfoField<NumberInfoFieldType> viewCountInfoField =
            BuilderHolder._builder
                    .infoFieldType(NumberInfoFieldType.INSTANCE)
                    .name("viewCount")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-view-count"))
                    .build();

    public static final InfoField<NumberInfoFieldType> commentsCountInfoField =
            BuilderHolder._builder
                    .infoFieldType(NumberInfoFieldType.INSTANCE)
                    .name("commentsCount")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-comments-count"))
                    .build();
    public static final InfoField<NumberInfoFieldType> subscribedUsersInfoField =
            BuilderHolder._builder
                    .infoFieldType(NumberInfoFieldType.INSTANCE)
                    .name("subscribedUsers")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-subscribed-users"))
                    .build();

    // --------------------------------------- AUDIT -------------------------------------------------------------------

    public static final InfoField<TextInfoFieldType> authorNameInfoField =
            BuilderHolder._builder
                    .infoFieldType(TextInfoFieldType.INSTANCE)
                    .name("authorName")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-author"))
                    .build();

    public static final InfoField<DateInfoFieldType> createDateInfoField =
            BuilderHolder._builder
                    .infoFieldType(DateInfoFieldType.INSTANCE)
                    .name("createDate")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-created-on"))
                    .build();

    public static final InfoField<DateInfoFieldType> modifiedDateInfoField =
            BuilderHolder._builder
                    .infoFieldType(DateInfoFieldType.INSTANCE)
                    .name("modifiedDate")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-last-modified-on"))
                    .build();

    public static final InfoField<NumberInfoFieldType> lecturesCountInfoField =
            BuilderHolder._builder
                    .infoFieldType(NumberInfoFieldType.INSTANCE)
                    .name("lecturesCount")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-lectures-count"))
                    .build();

    // ----------------------------------- DISPLAY PAGE ----------------------------------------------------------------

    public static final InfoField<URLInfoFieldType> displayPageURLInfoField =
            BuilderHolder._builder
                    .infoFieldType(URLInfoFieldType.INSTANCE)
                    .name("displayPageURL")
                    .labelInfoLocalizedValue(InfoLocalizedValue.localize(CourseInfoItemFields.class, "field-course-display-url"))
                    .build();

    private static class BuilderHolder {
        private static final InfoField.NamespacedBuilder _builder = InfoField.builder(Course.class.getSimpleName());

    }

}