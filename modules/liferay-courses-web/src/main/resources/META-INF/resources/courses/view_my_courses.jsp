<%@ include file="init.jsp" %>

<%
    long userId = user.getUserId();
    LiferayCoursesAPI coursesAPI = (LiferayCoursesAPI) request.getAttribute(LiferayCoursesAPI.class.getName());
    PortletURL iteratorURL = PortletURLBuilder.create(renderResponse.createRenderURL())
            .setMVCRenderCommandName("/")
            .setParameter("tab", "my")
            .buildPortletURL();
%>

<liferay-ui:search-container total="<%= coursesAPI.getMyCoursesCount(scopeGroupId, userId) %>" iteratorURL="<%= iteratorURL %>"
                             delta="4" emptyResultsMessage="No Courses Found">
    <liferay-ui:search-container-results results="<%= coursesAPI.getMyCourses(scopeGroupId, userId, searchContainer.getStart(), searchContainer.getEnd())  %>"/>
    <liferay-ui:search-container-row className="com.liferaybook.courses.manager.model.Course" modelVar="course" keyProperty="courseId">
        <liferay-ui:search-container-column-text name="courses-course-id" value="${course.courseId}" />
        <liferay-ui:search-container-column-text name="courses-name" value="${course.name}" />
        <liferay-ui:search-container-column-text name="courses-description" value="${course.description}" />
        <liferay-ui:search-container-column-text name="courses-user"
                                                 value="${course.userName}" />
        <liferay-ui:search-container-column-text name="courses-create-date">
            <fmt:formatDate var="courseCreateDate" value="${course.createDate}" pattern="dd-MM-yyyy HH:mm" />
            ${courseCreateDate}
        </liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text name="courses-modified-date">
            <fmt:formatDate var="courseModifiedDate" value="${course.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
            ${courseModifiedDate}
        </liferay-ui:search-container-column-text>
        <liferay-ui:search-container-column-text>
            <liferay-ui:icon-menu direction="left-side" icon="" markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
                <portlet:actionURL name="/courses/subscribe" var="unsubscribeURL">
                    <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                    <portlet:param name="subscribe" value="false" />
                    <portlet:param name="tab" value="my" />
                </portlet:actionURL>
                <liferay-ui:icon message="unsubscribe" url="${unsubscribeURL}" />
            </liferay-ui:icon-menu>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>