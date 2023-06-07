<%@ include file="init.jsp" %>

<c:choose>
    <c:when test="${course ne null}">
        <clay:container-fluid>
            <clay:sheet size="full">
                <clay:sheet-section>
                    <div class="text-center">
                        <h1 class="sheet-title">${course.name}</h1>
                        <p>${course.description}</p>
                        <p class="list-group-subtitle small mt-1 mb-4">${course.userName}, ${course.modifiedDateString}</p>
                    </div>
                    <c:choose>
                        <c:when test="${not empty course.getLectures()}">
                            <h4><liferay-ui:message key="lectures" /></h4>
                            <c:choose>
                                <c:when test="${displayStyle eq 'cards'}">
                                    <div class="container lfr-search-container-wrapper">
                                        <div class="row">
                                            <c:forEach var="lecture" items="${course.getLectures()}">
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
                                </c:when>
                                <c:otherwise>
                                    <table class="table lfr-search-container-wrapper">
                                        <thead>
                                        <tr>
                                            <th>#</th>
                                            <th><liferay-ui:message key="lectures-name" /></th>
                                            <th><liferay-ui:message key="lectures-description" /></th>
                                            <th><liferay-ui:message key="lectures-video-link" /></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="lecture" items="${course.getLectures()}" varStatus="lectureCounter">
                                            <tr>
                                                <td>${lectureCounter.count}.</td>
                                                <td>${lecture.name}</td>
                                                <td>${lecture.description}</td>
                                                <td>
                                                    <a href="${lecture.videoLink}" target="_blank" class="nav-link">
                                                            ${lecture.videoLink} <clay:icon symbol="shortcut" />
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <div class="portlet-msg-info">
                                <liferay-ui:message key="lectures-empty-list" />
                            </div>
                        </c:otherwise>
                    </c:choose>
                </clay:sheet-section>
            </clay:sheet>
        </clay:container-fluid>
    </c:when>
    <c:otherwise>
        <clay:container-fluid>
            <clay:sheet size="full">
                <clay:sheet-section>
                    <div class="portlet-msg-alert">
                        <liferay-ui:message key="course-display-configuration-alert" />
                    </div>
                </clay:sheet-section>
            </clay:sheet>
        </clay:container-fluid>
    </c:otherwise>
</c:choose>