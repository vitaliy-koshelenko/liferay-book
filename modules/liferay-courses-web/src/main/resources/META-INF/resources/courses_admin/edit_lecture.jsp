<%@ page import="com.liferaybook.courses.manager.constants.CoursesPortletKeys" %>
<%@ include file="init.jsp" %>

<%
    Lecture lecture = (Lecture) request.getAttribute("lecture");
    Long courseId = (Long) request.getAttribute("courseId");
%>

<portlet:actionURL name="/courses/edit_lecture" var="editLectureURL" />

<aui:form action="${editLectureURL}" method="post" name="fm">
    <aui:input name="courseId" type="hidden" value="${courseId}" />
    <aui:input name="lectureId" type="hidden" value="${lecture.lectureId}" />
    <clay:container-fluid>
        <clay:sheet size="full">
            <clay:sheet-header>
                <liferay-ui:error exception="<%= DuplicateUrlTitleException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= DuplicateLectureNameException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= LectureNameLengthException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= LectureDescriptionLengthException.class %>" message="${errorMsg}" />
                <liferay-ui:error exception="<%= LectureVideoLinkException.class %>" message="${errorMsg}" />
                <h2 class="sheet-title">
                    <c:choose>
                        <c:when test="${lecture.lectureId gt 0}">
                            <liferay-ui:message key="lectures-edit" arguments="<%= new Object[]{lecture.getLectureId(), lecture.getName()} %>"  />
                        </c:when>
                        <c:otherwise>
                            <liferay-ui:message key="lectures-add-new" />
                        </c:otherwise>
                    </c:choose>
                </h2>
            </clay:sheet-header>
            <clay:sheet-section>
                <aui:input name="name" required="true" label="lectures-name" value="${lecture.name}" onChange='<%= liferayPortletResponse.getNamespace() + "changeUrlTitle(this);" %>' />
                <aui:input type="textarea" name="description" label="lectures-description" value="${lecture.description}" />
                <aui:input name="urlTitle" required="true" label="lectures-url-title" value="${lecture.urlTitle}" />
                <aui:input name="videoLink" required="true" label="lectures-video-link" value="${lecture.videoLink}" />
                <c:if test="${lecture eq null or lecture.isNew()}">
                    <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>"
                                  label="fieldset-lecture-permissions" cssClass="courses-fieldset">
                        <liferay-ui:input-permissions modelName="<%= Lecture.class.getName() %>" />
                        <aui:input name="addEntryResources" type="hidden" value="<%= true %>" />
                    </aui:fieldset>
                </c:if>
            </clay:sheet-section>
            <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
                <div class="btn-group">
                    <div class="btn-group-item">
                        <clay:button displayType="primary" label="Save" type="submit" />
                        <c:choose>
                            <c:when test="<%= CoursesPortletKeys.COURSES.equals(portletDisplay.getPortletName()) %>">
                                <%-- Course Details --%>
                                <portlet:renderURL var="courseDetailsURL">
                                    <portlet:param name="mvcRenderCommandName" value="/courses/view_course" />
                                    <portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
                                </portlet:renderURL>
                                <clay:link href="${courseDetailsURL}" type="button" displayType="secondary" label="back" />
                            </c:when>
                            <c:otherwise>
                                <%-- View Lectures --%>
                                <portlet:renderURL var="viewLecturesURL">
                                    <portlet:param name="mvcRenderCommandName" value="/courses/view_lectures" />
                                    <portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
                                </portlet:renderURL>
                                <clay:link href="${viewLecturesURL}" type="button" displayType="secondary" label="back" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </clay:sheet-footer>
        </clay:sheet>
    </clay:container-fluid>
</aui:form>

<aui:script>
    function <portlet:namespace />changeUrlTitle(courseNameInput) {
        const urlTitleInput = document.getElementById('<portlet:namespace />urlTitle');
        urlTitleInput.value = courseNameInput.value
                    .replace(/[^a-z0-9_-]/gi, '-')
                    .replace(/^-+/, '')
                    .replace(/--+/, '-')
                    .toLowerCase();
    }
</aui:script>