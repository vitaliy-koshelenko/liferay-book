<%@ include file="init.jsp" %>

<%
    CourseLocalService coursesService = (CourseLocalService) request.getAttribute(CourseLocalService.class.getName());
%>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-section>
            <c:if test="<%= CoursesPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), CoursesActionKeys.ADD_COURSE) %>">
                <div class="w-100 mb-2 text-right">
                    <portlet:renderURL var="addCourseURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
                    </portlet:renderURL>
                    <clay:link href="${addCourseURL}" label="+" type="button" displayType="primary" />
                </div>
            </c:if>
            <liferay-ui:search-container total="<%= coursesService.getGroupCoursesCount(scopeGroupId) %>" delta="4" emptyResultsMessage="courses-empty-list">
                <liferay-ui:search-container-results results="<%= coursesService.getPrioritizedGroupCourses(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd())  %>"/>
                <liferay-ui:search-container-row className="com.liferaybook.courses.manager.model.Course" modelVar="course" keyProperty="courseId">
                    <portlet:renderURL var="courseDetailsURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/view_course" />
                        <portlet:param name="urlTitle" value="${course.urlTitle}" />
                    </portlet:renderURL>
                    <liferay-ui:search-container-column-text name="courses-course-id">
                        <c:choose>
                            <c:when test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), ActionKeys.VIEW) %>">
                                <a href="${courseDetailsURL}">${course.courseId}</a>
                            </c:when>
                            <c:otherwise>
                                ${course.courseId}
                            </c:otherwise>
                        </c:choose>
                    </liferay-ui:search-container-column-text>
                    <liferay-ui:search-container-column-text name="courses-name" value="${course.name}" />
                    <liferay-ui:search-container-column-text name="courses-priority" value="${course.priority}" />
                    <liferay-ui:search-container-column-text name="courses-categories" value="${course.categoryNamesString}" />
                    <liferay-ui:search-container-column-text name="courses-tags" value="${course.tagNamesString}" />
                    <liferay-ui:search-container-column-text name="courses-user" value="${course.userName}" />
                    <liferay-ui:search-container-column-text name="courses-create-date">
                        <fmt:formatDate var="courseCreateDate" value="${course.createDate}" pattern="dd-MM-yyyy HH:mm" />
                        ${courseCreateDate}
                    </liferay-ui:search-container-column-text>
                    <liferay-ui:search-container-column-text name="courses-modified-date">
                        <fmt:formatDate var="courseModifiedDate" value="${course.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
                        ${courseModifiedDate}
                    </liferay-ui:search-container-column-text>
                    <liferay-ui:search-container-column-text>
                        <liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>"
                                markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
                            <c:if test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), ActionKeys.VIEW) %>">
                                <liferay-ui:icon message="details" url="${courseDetailsURL}" />
                            </c:if>
                            <c:if test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), ActionKeys.UPDATE) %>">
                                <portlet:renderURL var="editCourseURL">
                                    <portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
                                    <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                                </portlet:renderURL>
                                <liferay-ui:icon message="edit" url="${editCourseURL}" />
                            </c:if>
                            <c:if test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), ActionKeys.PERMISSIONS) %>">
                                <liferay-security:permissionsURL
                                        modelResource="<%= Course.class.getName() %>"
                                        modelResourceDescription="<%= course.getName() %>"
                                        resourcePrimKey="<%= String.valueOf(course.getCourseId()) %>"
                                        var="permissionsURL"
                                        windowState="<%= LiferayWindowState.POP_UP.toString() %>"
                                />
                                <liferay-ui:icon message="permissions" method="get"
                                                 url="<%= permissionsURL %>" useDialog="<%= true %>" />
                            </c:if>
                            <c:if test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), ActionKeys.DELETE) %>">
                                <portlet:actionURL name="/courses/delete_course" var="deleteCourseURL">
                                    <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                                </portlet:actionURL>
                                <liferay-ui:icon-delete message="delete" confirmation="courses-delete-confirmation" url="${deleteCourseURL}" />
                            </c:if>
                        </liferay-ui:icon-menu>
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>
                <liferay-ui:search-iterator markupView="lexicon" />
            </liferay-ui:search-container>
        </clay:sheet-section>
    </clay:sheet>
</clay:container-fluid>