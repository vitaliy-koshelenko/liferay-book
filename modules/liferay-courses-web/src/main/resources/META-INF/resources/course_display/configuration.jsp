<%@ include file="init.jsp" %>

<% long courseId = PrefsParamUtil.getLong(portletPreferences, request, "courseId", 0); %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="<%= configurationActionURL %>" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
    <liferay-frontend:edit-form-body>
        <h2 class="text-center"><liferay-ui:message key="course-display-configuration-title" /></h2>
        <liferay-frontend:fieldset>
            <aui:input label="courses-course-id" helpMessage="course-display-configuration-select-course"
                       name="preferences--courseId--" type="text" value="<%= courseId %>" />
        </liferay-frontend:fieldset>
    </liferay-frontend:edit-form-body>
    <liferay-frontend:edit-form-footer>
        <liferay-frontend:edit-form-buttons />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>