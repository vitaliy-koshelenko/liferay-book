<%@ include file="init.jsp" %>

<%
    LiferayLecture lecture = (LiferayLecture) request.getAttribute("lecture");
    Long courseId = (Long) request.getAttribute("courseId");
    System.out.println("lecture=" + lecture);
    System.out.println("courseId=" + courseId);
%>

<portlet:actionURL name="/courses/edit_lecture" var="editLectureURL" />

<aui:form action="${editLectureURL}" method="post" name="fm">
    <aui:input name="courseId" type="hidden" value="${courseId}" />
    <aui:input name="lectureId" type="hidden" value="${lecture.lectureId}" />
    <clay:container-fluid>
        <clay:sheet size="full">
            <clay:sheet-header>
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
                <aui:input name="name" label="lectures-name" value="${lecture.name}" />
                <aui:input type="textarea" name="description" label="lectures-description" value="${lecture.description}" />
                <aui:input name="videoLink" label="lectures-video-link" value="${lecture.videoLink}" />
            </clay:sheet-section>
            <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
                <div class="btn-group">
                    <div class="btn-group-item">
                        <clay:button displayType="primary" label="Save" type="submit" />
                        <%-- View Lectures --%>
                        <portlet:renderURL var="viewLecturesURL">
                            <portlet:param name="mvcRenderCommandName" value="/courses/view_lectures" />
                            <portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
                        </portlet:renderURL>
                        <clay:link href="${viewLecturesURL}" type="button" displayType="secondary" label="back" />
                    </div>
                </div>
            </clay:sheet-footer>
        </clay:sheet>
    </clay:container-fluid>
</aui:form>