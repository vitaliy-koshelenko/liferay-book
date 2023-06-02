<%@ include file="init.jsp" %>

<%
	LiferayCoursesAPI coursesAPI = (LiferayCoursesAPI) request.getAttribute(LiferayCoursesAPI.class.getName());
	LiferayCourse course = (LiferayCourse) request.getAttribute("course");
	Long courseId = course.getCourseId();

	PortletURL iteratorURL = PortletURLBuilder.create(renderResponse.createRenderURL())
			.setMVCRenderCommandName("/courses/view_lectures")
			.setParameter("courseId", String.valueOf(course.getCourseId()))
			.buildPortletURL();
%>

<clay:container-fluid>
	<clay:sheet size="full">
		<clay:sheet-section>
			<clay:sheet-header>
				<h2 class="sheet-title">
					<liferay-ui:message key="lectures-list" arguments="<%= new Object[]{course.getCourseId(), course.getName()} %>"  />
				</h2>
				<div class="w-100 text-right">
					<portlet:renderURL var="addLectureURL">
						<portlet:param name="mvcRenderCommandName" value="/courses/edit_lecture" />
						<portlet:param name="courseId" value="<%= String.valueOf(courseId) %>" />
					</portlet:renderURL>
					<clay:link href="${addLectureURL}" label="+" type="button" displayType="primary" />
				</div>
			</clay:sheet-header>
			<liferay-ui:search-container iteratorURL="<%= iteratorURL %>" total="<%= coursesAPI.getLecturesCount(courseId) %>"
										 delta="4" emptyResultsMessage="No Lectures Found">
				<liferay-ui:search-container-results results="<%= coursesAPI.getLectures(courseId, searchContainer.getStart(), searchContainer.getEnd())  %>"/>
				<liferay-ui:search-container-row className="com.liferaybook.courses.api.LiferayLecture" modelVar="lecture" keyProperty="lectureId">
					<liferay-ui:search-container-column-text name="lectures-lecture-id" value="${lecture.lectureId}" />
					<liferay-ui:search-container-column-text name="lectures-name" value="${lecture.name}" />
					<liferay-ui:search-container-column-text name="lectures-description" value="${lecture.description}" />
					<liferay-ui:search-container-column-text name="lectures-video-link">
						<a href="${lecture.videoLink}" target="_blank">${lecture.videoLink}</a>
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="lectures-user"
															 value="${lecture.userName}" />
					<liferay-ui:search-container-column-text name="lectures-create-date">
						<fmt:formatDate var="courseCreateDate" value="${lecture.createDate}" pattern="dd-MM-yyyy HH:mm" />
						${courseCreateDate}
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="lectures-modified-date">
						<fmt:formatDate var="courseModifiedDate" value="${lecture.modifiedDate}" pattern="dd-MM-yyyy HH:mm" />
						${courseModifiedDate}
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text>
						<liferay-ui:icon-menu direction="left-side" icon="" markupView="lexicon" message="actions" showWhenSingleIcon="<%= true %>">
							<portlet:renderURL var="editLectureURL">
								<portlet:param name="mvcRenderCommandName" value="/courses/edit_lecture" />
								<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
								<portlet:param name="lectureId" value="<%= String.valueOf(lecture.getLectureId()) %>" />
							</portlet:renderURL>
							<liferay-ui:icon message="edit" url="${editLectureURL}" />
							<portlet:actionURL name="/courses/delete_lecture" var="deleteLectureURL">
								<portlet:param name="courseId" value="<%= String.valueOf(course.getCourseId()) %>" />
								<portlet:param name="lectureId" value="<%= String.valueOf(lecture.getLectureId()) %>" />
							</portlet:actionURL>
							<liferay-ui:icon-delete message="delete" confirmation="lecture-delete-confirmation" url="${deleteLectureURL}" />
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>
				<liferay-ui:search-iterator markupView="lexicon" />
			</liferay-ui:search-container>
		</clay:sheet-section>
		<clay:sheet-footer cssClass="sheet-footer-btn-block-sm-down">
			<div class="btn-group">
				<div class="btn-group-item">
					<clay:link href="${coursesListUrl}" type="button" displayType="secondary" label="back" />
				</div>
			</div>
		</clay:sheet-footer>
	</clay:sheet>
</clay:container-fluid>