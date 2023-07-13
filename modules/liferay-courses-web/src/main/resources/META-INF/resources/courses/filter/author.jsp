<%@ include file="../init.jsp" %>

<aui:select name="courseAuthor" value="${filterAuthor}" label="filter-author-label"
			wrapperCssClass="form-group-sm" onChange="AuthorFilter.setFilterValue(this)">
	<aui:option label="filter-author-placeholder" value="" />
	<c:forEach var="author" items="${authors}">
		<aui:option label="${author}" value="${author}" />
	</c:forEach>
</aui:select>

<script type="text/javascript">
	var AuthorFilter = {
		setFilterValue: function (event) {
			const url = new URL(window.location.href);
			var author = event.value;
			if (author) {
				url.searchParams.set('${filterName}', author);
			} else {
				url.searchParams.delete('${filterName}');
			}
			window.location.href = url.toString();
		}
	};
</script>