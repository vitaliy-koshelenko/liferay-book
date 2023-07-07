<%@ include file="init.jsp" %>

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
                    <b>View Count:</b>
                    ${course.getAssetEntry().getViewCount()}
                </p>
            </div>
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
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="lecture" items="${course.getLectures()}">
                                <tr>
                                    <td>
                                        <portlet:renderURL var="lectureDetailsURL">
                                            <portlet:param name="mvcRenderCommandName" value="/courses/view_lecture" />
                                            <portlet:param name="urlTitle" value="${lecture.urlTitle}" />
                                        </portlet:renderURL>
                                        <a href="${lectureDetailsURL}">${lecture.lectureId}</a>
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
                    <button class="btn btn-secondary" onclick="history.back();">
                        <liferay-ui:message key="back" />
                    </button>
                </div>
            </div>
        </clay:sheet-footer>
    </clay:sheet>
</clay:container-fluid>