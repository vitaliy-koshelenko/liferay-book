<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="portlet" uri="http://xmlns.jcp.org/portlet_3_0"  %>
<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

<%@ page import="com.liferaybook.courses.api.LiferayCoursesAPI" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<portlet:renderURL var="coursesListUrl" />