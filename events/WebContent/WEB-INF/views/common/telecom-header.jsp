<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="body2">
	<div class="main">
		<!-- header -->
		<header>
			<div class="wrapper">
				<img class="telecom-logo" width="218" height="100"
					src="<c:url value="/resources/images/telecom-logo2.jpg" />">
				<div class="right">
					<nav>
						<ul id="top_nav">
							<div class="conn-data">
								<div class="conn-data-icon"></div>
								<div class="conn-data-usr">
									<div class="conn-data-info">
										<sec:authorize access="isAuthenticated()">
											<sec:authentication property="principal.firstName" />
											<sec:authentication property="principal.lastName" />
										</sec:authorize>
									</div>
									<div class="conn-data-action">
										<c:url value="/logout" var="logoutUrl" />
										<a href="${logoutUrl}"><s:message code="logout.action" />
										</a>
									</div>
								</div>
							</div>
						</ul>
					</nav>
				</div>
			</div>
		</header>
	</div>
</div>