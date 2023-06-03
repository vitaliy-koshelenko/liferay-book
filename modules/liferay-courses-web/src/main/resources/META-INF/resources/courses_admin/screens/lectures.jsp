<%@ include file="../init.jsp" %>

<%
    long courseId = ParamUtil.getLong(renderRequest, "courseId");
    pageContext.setAttribute("courseId", courseId);
%>

<c:if test="${courseId eq 0}">
    <clay:container-fluid>
        <clay:sheet size="full">
            <clay:sheet-section>
                <div class="portlet-msg-info">
                    Select a course on the "Courses" tab to view the course lectures list.
                </div>
            </clay:sheet-section>
        </clay:sheet>
    </clay:container-fluid>
</c:if>