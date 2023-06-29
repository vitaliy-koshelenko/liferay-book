<%@ include file="../init.jsp" %>
<%
    String tab = ParamUtil.getString(request, "tab", "my");
    pageContext.setAttribute("tab", tab);
%>