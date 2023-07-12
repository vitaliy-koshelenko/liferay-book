<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container lfr-search-container-wrapper">
	<div class="row">
		<c:forEach var="lecture" items="${lectures}">
			<div class="col-md-4">
				<div class="card">
					<div class="card-item">
						<div class="text-center p-1 pt-3">
							<iframe width="350" height="200" src="${lecture.embedVideoLink}">
							</iframe>
						</div>
						<div class="card-body">
							<div class="card-row">
								<div class="autofit-col autofit-col-expand">
									<section class="autofit-section">
										<h3 class="card-title">${lecture.name}</h3>
										<p class="card-subtitle">${lecture.description}</p>
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