<%@ include file="init.jsp" %>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-header>
            <%@ include file="tabs/tabs-navigaton.jsp" %>
        </clay:sheet-header>
        <clay:sheet-section>
            <c:choose>
                <c:when test="${tab eq 'my'}">
                    <%@ include file="tabs/view_my_courses.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@ include file="tabs/view_all_courses.jsp" %>
                </c:otherwise>
            </c:choose>
        </clay:sheet-section>
    </clay:sheet>
</clay:container-fluid>