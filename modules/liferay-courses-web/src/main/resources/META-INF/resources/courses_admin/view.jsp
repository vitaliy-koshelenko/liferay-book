<%@ include file="init.jsp" %>

<% LiferayCoursesAPI coursesAPI = (LiferayCoursesAPI) request.getAttribute(LiferayCoursesAPI.class.getName()); %>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-section>
            <div class="w-100 text-right">
                <portlet:renderURL var="addCourseURL">
                    <portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
                </portlet:renderURL>
                <clay:link href="${addCourseURL}" label="+" type="button" displayType="primary" />
            </div>
            <liferay-ui:search-container total="<%= coursesAPI.getCoursesCount(scopeGroupId) %>" delta="4" emptyResultsMessage="courses-empty-list">
                <liferay-ui:search-container-results results="<%= coursesAPI.getCourses(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd())  %>"/>
                <liferay-ui:search-container-row className="com.liferaybook.courses.manager.model.Course" modelVar="course" keyProperty="courseId">
                    <portlet:renderURL var="viewLecturesURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/view_lectures" />
                        <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                    </portlet:renderURL>
                    <liferay-ui:search-container-column-text name="courses-course-id">
                        <a href="${viewLecturesURL}">${course.courseId}</a>
                    </liferay-ui:search-container-column-text>
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
                            <liferay-ui:icon message="lectures" url="${viewLecturesURL}" />
                            <portlet:renderURL var="editCourseURL">
                                <portlet:param name="mvcRenderCommandName" value="/courses/edit_course" />
                                <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                            </portlet:renderURL>
                            <liferay-ui:icon message="edit" url="${editCourseURL}" />
                            <portlet:actionURL name="/courses/delete_course" var="deleteCourseURL">
                                <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                            </portlet:actionURL>
                            <liferay-ui:icon-delete message="delete" confirmation="courses-delete-confirmation" url="${deleteCourseURL}" />
                        </liferay-ui:icon-menu>
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>
                <liferay-ui:search-iterator markupView="lexicon" />
            </liferay-ui:search-container>
        </clay:sheet-section>
    </clay:sheet>
</clay:container-fluid>