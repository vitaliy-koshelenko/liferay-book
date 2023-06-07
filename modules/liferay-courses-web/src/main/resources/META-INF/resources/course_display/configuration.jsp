<%@ include file="init.jsp" %>

<% String urlTitle = PrefsParamUtil.getString(portletPreferences, request, "urlTitle"); %>
<% String displayStyle = PrefsParamUtil.getString(portletPreferences, request, "displayStyle", "table"); %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
    <liferay-frontend:edit-form-body>
        <h2 class="text-center"><liferay-ui:message key="course-display-configuration-title" /></h2>
        <liferay-frontend:fieldset>
            <aui:input label="course-display-configuration-url-title" helpMessage="course-display-configuration-url-title-help"
                       name="preferences--urlTitle--" type="text" value="<%= urlTitle %>" />
            <aui:select name="preferences--displayStyle--" label="course-display-configuration-display-style">
                <aui:option label="table" selected='<%= displayStyle.equals("table") %>' />
                <aui:option label="cards" selected='<%= displayStyle.equals("cards") %>' />
            </aui:select>
        </liferay-frontend:fieldset>
    </liferay-frontend:edit-form-body>
    <liferay-frontend:edit-form-footer>
        <liferay-frontend:edit-form-buttons />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>