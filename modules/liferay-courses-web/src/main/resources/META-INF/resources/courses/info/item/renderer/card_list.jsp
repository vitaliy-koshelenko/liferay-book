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
										<clay:badge displayType="info" label="${course.categoryNamesString}" />
										<p class="card-subtitle mt-2">${course.description}</p>
										<p class="mt-2 mb-2" style="color: #888; font-size: smaller; font-style: italic;">
											Created by <b>${course.userName}</b> on <b>${course.createDateString}</b>.
										</p>
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