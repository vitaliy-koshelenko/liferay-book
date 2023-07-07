/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferaybook.courses.manager.model.impl;

import com.liferay.asset.entry.rel.model.AssetEntryAssetCategoryRel;
import com.liferay.asset.entry.rel.service.AssetEntryAssetCategoryRelLocalServiceUtil;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferaybook.courses.manager.model.Course;
import com.liferaybook.courses.manager.model.CourseSubscription;
import com.liferaybook.courses.manager.model.Lecture;
import com.liferaybook.courses.manager.service.CourseSubscriptionLocalServiceUtil;
import com.liferaybook.courses.manager.service.LectureLocalServiceUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaliy Koshelenko
 */
public class CourseImpl extends CourseBaseImpl {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    public List<Lecture> getLectures() {
        long courseId = getCourseId();
        return LectureLocalServiceUtil.getCourseLectures(courseId);
    }

    public List<CourseSubscription> getSubscriptions() {
        long courseId = getCourseId();
        return CourseSubscriptionLocalServiceUtil.getSubscriptionsForCourse(courseId);
    }

    public boolean isUserSubscribed(long userId) {
        long courseId = getCourseId();
        return CourseSubscriptionLocalServiceUtil.isSubscribed(userId, courseId);
    }

    public String getCreateDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(getCreateDate());
    }

    public String getModifiedDateString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(getModifiedDate());
    }

    public AssetEntry getAssetEntry() {
        long classNameId = PortalUtil.getClassNameId(Course.class.getName());
        return AssetEntryLocalServiceUtil.fetchEntry(classNameId, getCourseId());
    }

    public long getViewCount() {
        return getAssetEntry().getViewCount();
    }

    public List<AssetCategory> getCategories() {
        AssetEntry assetEntry = getAssetEntry();
        List<AssetEntryAssetCategoryRel> entryCategoryRels = AssetEntryAssetCategoryRelLocalServiceUtil
                .getAssetEntryAssetCategoryRelsByAssetEntryId(assetEntry.getEntryId());
        return entryCategoryRels.stream()
                .map(rel -> AssetCategoryLocalServiceUtil.fetchCategory(rel.getAssetCategoryId()))
                .collect(Collectors.toList());
    }

    public List<String> getCategoryNames() {
        return getCategories().stream()
                .map(AssetCategory::getName).collect(Collectors.toList());
    }

    public String getCategoryNamesString() {
        return toString(getCategoryNames());
    }

    public String getTagNamesString() {
        return toString(getTagNames());
    }

    public List<String> getTagNames() {
        long classNameId = PortalUtil.getClassNameId(Course.class.getName());
        return Arrays.asList(AssetTagLocalServiceUtil.getTagNames(classNameId, getCourseId()));
    }

    private String toString(List<String> array) {
        return ListUtil.isNotEmpty(array)
                ? StringUtil.merge(array, StringPool.COMMA_AND_SPACE) : StringPool.DASH;
    }

}