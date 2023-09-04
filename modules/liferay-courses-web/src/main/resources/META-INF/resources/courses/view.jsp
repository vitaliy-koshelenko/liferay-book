<%@ include file="init.jsp" %>

<clay:container-fluid cssClass="pt-1">
    <clay:row>
        <clay:col size="4">
            <aui:form action="${searchCoursesURL}" method="post" name="fm">
                <aui:fieldset>
                    <h2>Search Courses</h2>
                    <aui:input name="searchTerm" label="courses-search-term" value="${displayContext.searchContext.searchTerm}" />
                    <aui:select name="author" label="courses-search-author" value="${displayContext.searchContext.author}">
                        <aui:option value="" label="courses-search-select-author" />
                        <c:forEach var="author" items="${authors}">
                            <aui:option value="${author}" label="${author}" />
                        </c:forEach>
                    </aui:select>
                    <aui:select name="categoryId" label="courses-search-category" value="${displayContext.searchContext.categoryId}">
                        <aui:option value="0" label="courses-search-select-category" />
                        <c:forEach var="category" items="${categories}">
                            <aui:option value="${category.categoryId}" label="${category.name}" />
                        </c:forEach>
                    </aui:select>
                    <div class="text-right">
                        <clay:link href="${resetCoursesURL}" type="button" displayType="secondary" label="Reset"  />
                        <clay:button displayType="primary" label="Search" type="submit" cssClass="ml-2" />
                    </div>
                </aui:fieldset>
            </aui:form>
        </clay:col>
        <clay:col size="8">
            <c:if test="<%= CoursesPermission.contains(permissionChecker, themeDisplay.getScopeGroupId(), CoursesActionKeys.ADD_COURSE) %>">
                <div class="w-100 mb-2 text-right">
                    <portlet:renderURL var="addCourseURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
                    </portlet:renderURL>
                    <clay:link href="${addCourseURL}" label="+" type="button" displayType="primary" />
                </div>
            </c:if>
            <liferay-ui:search-container searchContainer="${displayContext.searchContainer}">
                <liferay-ui:search-container-row className="com.liferaybook.courses.web.search.model.CourseInfo" modelVar="course" keyProperty="courseId">
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
                    <liferay-ui:search-container-column-text name="courses-view-count" value="${course.viewCount}" />
                    <liferay-ui:search-container-column-text name="courses-user" value="${course.userName}" />
                    <liferay-ui:search-container-column-text name="courses-create-date" value="${course.createDate}" />
                    <liferay-ui:search-container-column-text name="courses-modified-date" value="${course.modifiedDate}" />
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
        </clay:col>
    </clay:row>
</clay:container-fluid>