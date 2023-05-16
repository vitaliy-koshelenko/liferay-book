<%@ include file="/init.jsp" %>

<h2>Liferay Courses:</h2>
<ul>
	<c:forEach var="course" items="${courses}">
		<li>
			<b>${course.name}</b>
			<p>${course.description}</p>
		</li>
	</c:forEach>
</ul>