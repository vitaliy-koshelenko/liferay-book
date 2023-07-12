<%@ include file="../../../init.jsp" %>
<div class="card">
	<div class="card-item">
		<div class="card-body">
			<div class="card-row">
				<div class="autofit-col autofit-col-expand">
					<section class="autofit-section">
						<h3 class="card-title">${course.name}</h3>
						<p class="card-subtitle">${course.description}</p>
						<p class="text-right mt-2">
							<a href="${course.getDisplayPageURL(themeDisplay)}">
								<liferay-ui:message key="read-more" />
							</a>
						</p>
					</section>
				</div>
			</div>
		</div>
	</div>
</div>