<%@ include file="init.jsp" %>

<% String urlTitle = PrefsParamUtil.getString(portletPreferences, request, "urlTitle", StringPool.BLANK); %>

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
        </liferay-frontend:fieldset>
    </liferay-frontend:edit-form-body>
    <liferay-frontend:edit-form-footer>
        <liferay-frontend:edit-form-buttons />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>