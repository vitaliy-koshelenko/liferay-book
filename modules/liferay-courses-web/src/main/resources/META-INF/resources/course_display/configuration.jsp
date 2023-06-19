<%@ include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form action="${configurationActionURL}" method="post" name="fm">
    <aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
    <aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />
    <liferay-frontend:edit-form-body>
        <h2 class="text-center"><liferay-ui:message key="course-display-configuration-title" /></h2>
        <liferay-frontend:fieldset>
            <aui:input type="hidden" name="preferences--urlTitle--" value="${configuration.urlTitle()}" />

            <div class="sheet-section">
                <div class="sheet-subtitle"><liferay-ui:message key="course" /></div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="course-preview ${course ne null ? '' : 'd-none'}">
                            <div class="card file-card card-type-asset">
                                <div class="card-item-first aspect-ratio">
                                    <div class="aspect-ratio-item aspect-ratio-item-center-middle aspect-ratio-item-fluid card-type-asset-icon">
                                        <svg class="lexicon-icon lexicon-icon-documents-and-media" role="presentation">
                                            <use xlink:href="/o/admin-theme/images/clay/icons.svg#documents-and-media"></use>
                                        </svg>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div class="card-row">
                                        <div class="autofit-col autofit-col-expand">
                                            <p class="card-title" title="${course.name}">
                                                <span class="text-truncate-inline"><span class="text-truncate">${course.name}</span></span>
                                            </p>
                                            <p class="card-subtitle" title="${course.description}">
                                                <span class="text-truncate-inline"><span class="text-truncate">${course.description}</span></span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <clay:button id='<%= liferayPortletResponse.getNamespace() + "selectCourseButton" %>'
                                     displayType="secondary" label="Select Course" />
                    </div>
                </div>
            </div>

            <div class="sheet-section">
                <div class="sheet-subtitle">
                    <liferay-ui:message key="course-display-configuration-display-style" />
                </div>
                <aui:select name="preferences--displayStyle--" label="course-display-configuration-course-display-style">
                    <aui:option label="table" selected='${configuration.displayStyle() eq "table"}' />
                    <aui:option label="cards" selected='${configuration.displayStyle() eq "cards"}' />
                </aui:select>
            </div>

        </liferay-frontend:fieldset>
    </liferay-frontend:edit-form-body>
    <liferay-frontend:edit-form-footer>
        <liferay-frontend:edit-form-buttons />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<script>
    var selectCourseButton = document.getElementById('<portlet:namespace />selectCourseButton');
    selectCourseButton.addEventListener(
        'click',
        function() {
            Liferay.Util.openSelectionModal({
                onSelect: function (event) {
                    console.log('event: ', event);
                    // Parse Course Data
                    var course = JSON.parse(event.value);
                    // Course Preview
                    var coursePreviewEl = document.getElementsByClassName('course-preview')[0];
                    coursePreviewEl.querySelector('.card-title').setAttribute('title', course.name);
                    coursePreviewEl.querySelector('.card-title .text-truncate').setHTML(course.name);
                    coursePreviewEl.querySelector('.card-subtitle').setAttribute('title', course.description);
                    coursePreviewEl.querySelector('.card-subtitle .text-truncate').setHTML(course.description);
                    coursePreviewEl.classList.remove('d-none');
                    // Course urlTitle Hidden Field
                    document.getElementById('<portlet:namespace />urlTitle').value = course.urlTitle;
                },
                selectEventName: '<portlet:namespace />selectCourse',
                title: '<liferay-ui:message key="course-display-configuration-select-course"/>',
                url: '${itemSelectorURL}'
            });
        }
    );
</script>