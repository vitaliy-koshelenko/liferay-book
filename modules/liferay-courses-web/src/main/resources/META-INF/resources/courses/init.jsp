<%@ include file="../init.jsp" %>

<portlet:renderURL var="searchCoursesURL">
    <portlet:param name="mvcRenderCommandName" value="/courses/search" />
</portlet:renderURL>

<portlet:renderURL var="resetCoursesURL">
    <portlet:param name="mvcRenderCommandName" value="/courses/reset" />
    <portlet:param name="cmd" value="reset" />
</portlet:renderURL>