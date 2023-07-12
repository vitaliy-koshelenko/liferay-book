<%@ include file="../../../init.jsp" %>

<div class="container lfr-search-container-wrapper">
	<div class="row">
		<c:forEach var="course" items="${courses}">
			<div class="col-md-4">
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
			</div>
		</c:forEach>
	</div>
</div>