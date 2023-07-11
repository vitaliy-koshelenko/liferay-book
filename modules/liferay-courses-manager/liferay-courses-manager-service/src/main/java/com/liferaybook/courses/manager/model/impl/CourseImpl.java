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
import com.liferay.message.boards.model.MBMessage;
import com.liferay.message.boards.service.MBMessageLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.ratings.kernel.model.RatingsStats;
import com.liferay.ratings.kernel.service.RatingsStatsLocalServiceUtil;
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
    private static final int RATINGS_SCALE = 5;

    public List<Lecture> getLectures() {
        return LectureLocalServiceUtil.getCourseLectures(getCourseId());
    }

    public int getLecturesCount() {
        return LectureLocalServiceUtil.getCourseLecturesCount(getCourseId());
    }

    public List<CourseSubscription> getSubscriptions() {
        return CourseSubscriptionLocalServiceUtil.getSubscriptionsForCourse(getCourseId());
    }

    public boolean isUserSubscribed(long userId) {
        return CourseSubscriptionLocalServiceUtil.isSubscribed(userId, getCourseId());
    }

    public int getSubscribedUsersCount() {
        List<CourseSubscription> subscriptions = CourseSubscriptionLocalServiceUtil.getSubscriptionsForCourse(getCourseId());
        return subscriptions.size();
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

    public int getCommentsCount() {
        List<MBMessage> comments = MBMessageLocalServiceUtil.getMessages(Course.class.getName(), getCourseId(), WorkflowConstants.STATUS_APPROVED);
        int commentsCount = comments.size();
        if (commentsCount > 0) {
            commentsCount--; // Exclude "root" message
        }
        return commentsCount;
    }

    public long getViewCount() {
        return getAssetEntry().getViewCount();
    }

    public double getPriority() {
        AssetEntry assetEntry = getAssetEntry();
        return assetEntry != null ? assetEntry.getPriority() : GetterUtil.DEFAULT_DOUBLE;
    }

    public double getRating() {
        RatingsStats stats = RatingsStatsLocalServiceUtil.fetchStats(Course.class.getName(), getCourseId());
        return stats != null ? stats.getAverageScore() * RATINGS_SCALE : GetterUtil.DEFAULT_DOUBLE;
    }

    public String getDisplayPageURL(ThemeDisplay themeDisplay) {
        if (themeDisplay == null) {
            return StringPool.BLANK;
        }
        long groupId = themeDisplay.getScopeGroupId();
        Group group = GroupLocalServiceUtil.fetchGroup(groupId);
        StringBuilder sb = new StringBuilder("/web");
        sb.append(group.getFriendlyURL());
        sb.append("/course/");
        sb.append(getUrlTitle());
        return sb.toString();
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