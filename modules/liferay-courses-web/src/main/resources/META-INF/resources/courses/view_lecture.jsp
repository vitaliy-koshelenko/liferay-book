<%@ include file="init.jsp" %>

<clay:container-fluid>
    <clay:sheet>
        <clay:sheet-header>
            <h2 class="sheet-title">
                ${lecture.name}
            </h2>
        </clay:sheet-header>
        <clay:sheet-section>
            <p>
                <b><liferay-ui:message key="lectures-user" /> :</b> ${lecture.userName}
            </p>
            <p>
                <b><liferay-ui:message key="lectures-create-date" /> :</b>
                <fmt:formatDate var="lectureCreateDate" value="${lecture.createDate}" pattern="dd-MM-yyyy HH:mm" />
                ${lectureCreateDate}
            </p>
            <p>
                <b><liferay-ui:message key="lectures-modified-date" /> :</b>
                <fmt:formatDate var="lectureModifiedDate" value="${lecture.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
                ${lectureModifiedDate}
            </p>
            <p class="mt-4 p-2">
                ${lecture.description}
            </p>
            <p class="mt-4 p-2">
                <iframe width="750" height="400" src="${lecture.embedVideoLink}" title=""
                        frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen>
                </iframe>
            </p>
        </clay:sheet-section>
        <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
            <div class="btn-group">
                <div class="btn-group-item">
                    <portlet:renderURL var="courseLecturesURL">
                        <portlet:param name="mvcRenderCommandName" value="/courses/view_lectures" />
                        <portlet:param name="courseId" value="${lecture.courseId}" />
                    </portlet:renderURL>
                    <clay:link href="${courseLecturesURL}" type="button" displayType="secondary" label="back" />
                </div>
            </div>
        </clay:sheet-footer>
    </clay:sheet>
</clay:container-fluid>