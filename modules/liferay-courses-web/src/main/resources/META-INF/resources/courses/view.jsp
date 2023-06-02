<%@ include file="init.jsp" %>

<%
    String tab = ParamUtil.getString(request, "tab", "my");
    pageContext.setAttribute("tab", tab);
%>

<clay:container-fluid>
    <clay:sheet size="full">
        <clay:sheet-section>
            <clay:navigation-bar navigationItems='<%=
                new JSPNavigationItemList(pageContext) {
                    {
                        add(
                            navigationItem -> {
                                navigationItem.setActive("my".equals(tab));
                                navigationItem.setHref(renderResponse.createRenderURL());
                                navigationItem.setLabel("My Courses");
                            });
                        add(
                            navigationItem -> {
                                navigationItem.setActive("all".equals(tab));
                                navigationItem.setHref(renderResponse.createRenderURL(), "tab", "all");
                                navigationItem.setLabel("All Courses");
                            });
                    }
                }
            %>'
            />
            <c:choose>
                <c:when test="${tab eq 'my'}">
                    <%@ include file="view_my_courses.jsp" %>
                </c:when>
                <c:otherwise>
                    <%@ include file="view_all_courses.jsp" %>
                </c:otherwise>
            </c:choose>
        </clay:sheet-section>
    </clay:sheet>
</clay:container-fluid>