package com.liferaybook.courses.web.search.model;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public class CourseSearchContext {

    private long companyId;
    private long groupId;
    private String searchTerm;
    private String author;
    private long categoryId;
    private final PortletRequest portletRequest;
    private final PortletResponse portletResponse;

    public CourseSearchContext(PortletRequest portletRequest, PortletResponse portletResponse) {
        this.portletRequest = portletRequest;
        this.portletResponse = portletResponse;
    }

    public PortletRequest getPortletRequest() {
        return portletRequest;
    }

    public PortletResponse getPortletResponse() {
        return portletResponse;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
}