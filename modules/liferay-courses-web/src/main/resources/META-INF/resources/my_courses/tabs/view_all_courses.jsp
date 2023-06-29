<%
    CourseLocalService coursesService = (CourseLocalService) request.getAttribute(CourseLocalService.class.getName());
    PortletURL iteratorURL = PortletURLBuilder.create(renderResponse.createRenderURL())
            .setMVCRenderCommandName("/")
            .setParameter("tab", "all")
            .buildPortletURL();
%>

<liferay-ui:search-container total="<%= coursesService.getGroupCoursesCount(scopeGroupId) %>" iteratorURL="<%= iteratorURL %>"
                             delta="4" emptyResultsMessage="courses-empty-list">
    <liferay-ui:search-container-results results="<%= coursesService.getGroupCourses(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd())  %>"/>
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
            <c:choose>
                <c:when test="${course.isUserSubscribed(user.userId)}">
                    <portlet:actionURL name="/courses/subscribe" var="unsubscribeURL">
                        <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                        <portlet:param name="subscribe" value="false" />
                        <portlet:param name="tab" value="all" />
                    </portlet:actionURL>
                    <clay:link href="${unsubscribeURL}" label="unsubscribe"
                               small="true" type="button" displayType="danger"/>
                </c:when>
                <c:otherwise>
                    <portlet:actionURL name="/courses/subscribe" var="subscribeURL">
                        <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                        <portlet:param name="subscribe" value="true" />
                        <portlet:param name="tab" value="all" />
                    </portlet:actionURL>
                    <clay:link href="${subscribeURL}" label="subscribe"
                               small="true" type="button" displayType="info"/>
                </c:otherwise>
            </c:choose>
        </liferay-ui:search-container-column-text>
    </liferay-ui:search-container-row>
    <liferay-ui:search-iterator markupView="lexicon" />
</liferay-ui:search-container>