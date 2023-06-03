<%@ include file="init.jsp" %>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-header>
            <%@ include file="tabs/tabs-navigaton.jsp" %>
        </clay:sheet-header>
        <clay:sheet-section>
            <h2 class="sheet-title">
                <liferay-ui:message key="course" /> #${course.courseId}: "${course.name}"
            </h2>
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
            <p class="mt-4 p-2">
                ${course.description}
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