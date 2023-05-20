<%@ include file="init.jsp" %>

<clay:container-fluid>
    <clay:sheet>
        <clay:sheet-header>
            <h2 class="sheet-title">
                <liferay-ui:message key="course" /> #${course.courseId}: "${course.name}"
            </h2>
        </clay:sheet-header>
        <clay:sheet-section>
            ${course.description}
        </clay:sheet-section>
        <clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
            <div class="btn-group">
                <div class="btn-group-item">
                    <clay:link href="${coursesListUrl}" type="button"
                               displayType="secondary" label="back" />
                </div>
            </div>
        </clay:sheet-footer>
    </clay:sheet>
</clay:container-fluid>