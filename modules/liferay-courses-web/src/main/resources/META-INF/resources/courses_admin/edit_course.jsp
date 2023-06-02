<%@ include file="init.jsp" %>

<% LiferayCourse course = (LiferayCourse) request.getAttribute("course"); %>

<portlet:actionURL name="/courses/edit_course" var="editCourseURL" />

<aui:form action="${editCourseURL}" method="post" name="fm">
    <aui:input name="courseId" type="hidden" value="${course.courseId}" />
    <clay:container-fluid>
        <clay:sheet size="full">
            <clay:sheet-header>
                <liferay-ui:error exception="<%= DuplicateCourseNameException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= CourseNameLengthException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= CourseDescriptionLengthException.class %>" message="${errorMsg}" />
                <h2 class="sheet-title">
                    <c:choose>
                        <c:when test="${course.courseId gt 0}">
                            <liferay-ui:message key="courses-edit" arguments="<%= new Object[]{course.getCourseId(), course.getName()} %>"  />
                        </c:when>
                        <c:otherwise>
                            <liferay-ui:message key="courses-add-new" />
                        </c:otherwise>
                    </c:choose>
                </h2>
            </clay:sheet-header>
            <clay:sheet-section>
                <aui:input name="name" label="courses-name" value="${course.name}" />
                <aui:input type="textarea" name="description" label="courses-description" value="${course.description}" />
            </clay:sheet-section>
            <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
                <div class="btn-group">
                    <div class="btn-group-item">
                        <clay:button displayType="primary" label="Save" type="submit" />
                        <clay:link href="${coursesListUrl}" type="button" displayType="secondary" label="back" />
                    </div>
                </div>
            </clay:sheet-footer>
        </clay:sheet>
    </clay:container-fluid>
</aui:form>