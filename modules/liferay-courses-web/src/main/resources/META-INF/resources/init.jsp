<%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-asset" uri="http://liferay.com/tld/asset" %>
<%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment" %>
<%@ taglib prefix="liferay-frontend" uri="http://liferay.com/tld/frontend" %>
<%@ taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@ taglib prefix="liferay-ratings" uri="http://liferay.com/tld/ratings" %>
<%@ taglib prefix="liferay-theme" uri="http://liferay.com/tld/theme" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix="portlet" uri="http://xmlns.jcp.org/portlet_3_0"  %>

<%@ page import="com.liferay.asset.kernel.model.AssetVocabularyConstants" %>
<%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPNavigationItemList" %>
<%@ page import="com.liferay.petra.string.StringPool" %>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>
<%@ page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PrefsParamUtil" %>
<%@ page import="com.liferaybook.courses.manager.exception.*" %>
<%@ page import="com.liferaybook.courses.manager.model.Course" %>
<%@ page import="com.liferaybook.courses.manager.model.Lecture" %>
<%@ page import="com.liferaybook.courses.manager.service.CourseLocalService" %>
<%@ page import="com.liferaybook.courses.manager.service.LectureLocalService" %>
<%@ page import="com.liferaybook.courses.web.display.context.CoursesDisplayContext" %>
<%@ page import="javax.portlet.PortletURL" %>

<liferay-theme:defineObjects />
<liferay-frontend:defineObjects />
<portlet:defineObjects />

<portlet:renderURL var="coursesListUrl" />