<%@ include file="init.jsp" %>

<% Course course = (Course) request.getAttribute("course"); %>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-section>
            <h2 class="sheet-title">
                <liferay-ui:message key="course" /> #${course.courseId}: "${course.name}"
            </h2>
            <p>
                ${course.description}
            </p>
            <div class="text-right">
                <p>
                    <b><liferay-ui:message key="courses-user" /> :</b> ${course.userName}
                </p>
                <p>
                    <b><liferay-ui:message key="courses-create-date" /> :</b>
                    <fmt:formatDate var="courseCreateDate" value="${course.createDate}" pattern="dd-MM-yyyy HH:mm" />
                        ${courseCreateDate}
                </p>
                <p>
                    <b><liferay-ui:message key="courses-modified-date" /> :</b>
                    <fmt:formatDate var="courseModifiedDate" value="${course.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
                        ${courseModifiedDate}
                </p>
                <p>
                    <b><liferay-ui:message key="courses-view-count" /> :</b>
                    ${course.viewCount}
                </p>
            </div>
            <c:if test="<%= CoursePermission.contains(permissionChecker, course.getCourseId(), CoursesActionKeys.ADD_LECTURE) %>">
                <div class="w-100 mb-2 text-right">
                    <portlet:renderURL var="addLectureURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/edit_lecture" />
                        <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                    </portlet:renderURL>
                    <clay:link href="${addLectureURL}" label="+" type="button" displayType="primary" />
                </div>
            </c:if>
            <c:choose>
                <c:when test="${not empty course.getLectures()}">
                    <table class="table table-autofit table-heading-nowrap table-list lfr-search-container-wrapper">
                        <thead>
                            <tr>
                                <th><liferay-ui:message key="lectures-lecture-id" /></th>
                                <th><liferay-ui:message key="lectures-name" /></th>
                                <th><liferay-ui:message key="lectures-description" /></th>
                                <th><liferay-ui:message key="lectures-user" /></th>
                                <th><liferay-ui:message key="lectures-create-date" /></th>
                                <th><liferay-ui:message key="lectures-modified-date" /></th>
                                <th><%-- Actions --%></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lecture" items="${course.getLectures()}">
                                <%
                                    Lecture lecture = (Lecture) pageContext.getAttribute("lecture");
                                    boolean hasViewPermission = LecturePermission.contains(permissionChecker, lecture.getLectureId(), ActionKeys.VIEW);
                                %>
                                <tr>
                                    <td>
                                        <portlet:renderURL var="lectureDetailsURL">
                                            <portlet:param name="mvcRenderCommandName" value="/courses/view_lecture" />
                                            <portlet:param name="urlTitle" value="${lecture.urlTitle}" />
                                        </portlet:renderURL>
                                        <c:choose>
                                            <c:when test="<%= hasViewPermission %>">
                                                <a href="${lectureDetailsURL}">${lecture.lectureId}</a>
                                            </c:when>
                                            <c:otherwise>
                                                ${lecture.lectureId}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${lecture.name}</td>
                                    <td>${lecture.description}</td>
                                    <td>${lecture.userName}</td>
                                    <td>
                                        <fmt:formatDate var="courseCreateDate" value="${lecture.createDate}" pattern="dd-MM-yyyy HH:mm" />
                                        ${courseCreateDate}
                                    </td>
                                    <td>
                                        <fmt:formatDate var="courseModifiedDate" value="${lecture.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
                                        ${courseModifiedDate}
                                    </td>
                                    <td>
                                        <liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>"
                                                              markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
                                            <c:if test="<%= hasViewPermission %>">
                                                <liferay-ui:icon message="details" url="${lectureDetailsURL}" />
                                            </c:if>
                                            <c:if test="<%= LecturePermission.contains(permissionChecker, lecture.getLectureId(), ActionKeys.UPDATE) %>">
                                                <portlet:renderURL var="editLectureURL">
                                                    <portlet:param name="mvcRenderCommandName" value="/courses/edit_lecture" />
                                                    <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                                                    <portlet:param name="lectureId" value="<%= String.valueOf(lecture.getLectureId()) %>" />
                                                </portlet:renderURL>
                                                <liferay-ui:icon message="edit" url="${editLectureURL}" />
                                            </c:if>
                                            <c:if test="<%= LecturePermission.contains(permissionChecker, lecture.getLectureId(), ActionKeys.PERMISSIONS) %>">
                                                <liferay-security:permissionsURL
                                                        modelResource="<%= Lecture.class.getName() %>"
                                                        modelResourceDescription="<%= lecture.getName() %>"
                                                        resourcePrimKey="<%= String.valueOf(lecture.getLectureId()) %>"
                                                        var="lecturePermissionsURL"
                                                        windowState="<%= LiferayWindowState.POP_UP.toString() %>"
                                                />
                                                <liferay-ui:icon message="permissions" method="get" url="${lecturePermissionsURL}" useDialog="<%= true %>" />
                                            </c:if>
                                            <c:if test="<%= LecturePermission.contains(permissionChecker, lecture.getLectureId(), ActionKeys.DELETE) %>">
                                                <portlet:actionURL name="/courses/delete_lecture" var="deleteLectureURL">
                                                    <portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
                                                    <portlet:param name="lectureId" value="<%= String.valueOf(lecture.getLectureId()) %>" />
                                                </portlet:actionURL>
                                                <liferay-ui:icon-delete message="delete" confirmation="lecture-delete-confirmation" url="${deleteLectureURL}" />
                                            </c:if>
                                        </liferay-ui:icon-menu>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="6">
                            <div class="portlet-msg-info">
                                <liferay-ui:message key="lectures-empty-list" />
                            </div>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            <p>
                <liferay-asset:asset-links
                        className="<%= Course.class.getName() %>"
                        classPK="${course.courseId}"
                />
            </p>
            <p>
                <liferay-ratings:ratings type="thumbs"
                        className="<%= Course.class.getName() %>"
                        classPK="${course.courseId}"
                />
            </p>
            <p>
                <liferay-comment:discussion
                        className="<%= Course.class.getName() %>"
                        classPK="${course.courseId}"
                        discussion="${courseContext.discussion}"
                        formName="fm2"
                        ratingsEnabled="<%= true %>"
                        userId="${course.userId}"
                />
            </p>
        </clay:sheet-section>
        <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
            <div class="btn-group">
                <div class="btn-group-item">
                    <clay:link href="${coursesListUrl}" type="button" displayType="secondary" label="back" />
                </div>
            </div>
        </clay:sheet-footer>
    </clay:sheet>
</clay:container-fluid>