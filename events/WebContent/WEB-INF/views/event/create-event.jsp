<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="event-form">
	<form:form method="POST" modelAttribute="command">
		<c:choose>
			<c:when test="${empty command.site}">
				<div class="data-section">
					<form:label path="site">
						<s:message
							code="event.main.panel.menu.item.event.create.form.site.label" />
					</form:label>
					<form:select path="site" size="1">
						<form:options items="${sites}" itemValue="siteId"
							itemLabel="labSite" />
					</form:select>
				</div>
			</c:when>
			<c:otherwise>
				<div class="data-section">
					<form:label path="site">
						<s:message
							code="event.main.panel.menu.item.event.create.form.site.label" />
					</form:label>
					<form:input path="site.labSite" readonly="true" />
					<form:hidden path="site.siteId" />
				</div>
			</c:otherwise>
		</c:choose>

		<c:if test="${not empty locals}">
			<c:choose>
				<c:when test="${empty command.local}">
					<div class="data-section">
						<form:label path="local">
							<s:message
								code="event.main.panel.menu.item.event.create.form.local.label" />
						</form:label>

						<form:select path="local" size="1">
							<form:options items="${locals}" itemValue="idLocal"
								itemLabel="labLocal" />
						</form:select>
					</div>
				</c:when>
				<c:otherwise>
					<div class="data-section">
						<form:label path="local">
							<s:message
								code="event.main.panel.menu.item.event.create.form.local.label" />
						</form:label>
						<form:input path="local.labLocal" readonly="true" />
						<form:hidden path="local.idLocal" />
					</div>
					<div class="data-section">
						<form:label path="piece">
							<s:message
								code="event.main.panel.menu.item.event.create.form.piece.label" />
						</form:label>
						<form:select path="piece" size="1">
							<form:options items="${pieces}" itemValue="idPiece"
								itemLabel="labPiece" />
						</form:select>
					</div>
					<div class="data-section">
						<form:label path="cause">
							<s:message
								code="event.main.panel.menu.item.event.create.form.cause.label" />
						</form:label>
						<form:select path="cause" size="1">
							<form:options items="${causes}" itemValue="causeId"
								itemLabel="causeDesc" />
						</form:select>
					</div>
					<div class="data-section">
						<form:label path="responsible">
							<s:message
								code="event.main.panel.menu.item.event.create.form.responsible.label" />
						</form:label>
						<form:select path="responsible" size="1">
							<form:options items="${responsibles}" itemValue="userId"
								itemLabel="fullName" />
						</form:select>
					</div>
					<div class="data-section">
						<form:label path="subcontractorType">
							<s:message
								code="event.main.panel.menu.item.event.create.form.subcontractorType.label" />
						</form:label>
						<form:select path="subcontractorType" size="1">
							<form:options items="${subcontractorTypes}" itemValue="id" itemLabel="type" />
						</form:select>
					</div>
					<div class="data-section">
						<form:label path="eventDate">
							<s:message
								code="event.main.panel.menu.item.event.create.form.eventDate.label" />
						</form:label>

						<form:input cssClass="datepicker" path="eventDate" readonly="true" />
						<script type="text/javascript">
							 /*$(function(){
									$('#eventDate').appendDtpicker({
										'dateFormat' : 'DD/MM/YYYY hh:mm',
										'locale': 'fr'
									});
								});*/
								// http://jquerybyexample.blogspot.com/2012/05/add-timepicker-to-jquery-ui-datepicker.html
								
								    $('#eventDate').datetimepicker({
										dateFormat : "dd/mm/yy",
										timeFormat: "hh:mm tt",
										locale: 'fr'
									});
							/*$('.datepicker').datepicker({
								dateFormat : "dd/mm/yy"
							});*/
						</script>
					</div>
					<div class="data-section">
						<form:label path="description">
							<s:message
								code="event.main.panel.menu.item.event.create.form.description.label" />
						</form:label>
						<form:textarea path="description" />
						<s:message
							code="event.main.panel.menu.item.event.create.form.submit"
							var="addEvtBtn" />
						<input type="submit" value="${addEvtBtn}" class="add-btn">
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
	</form:form>
</div>

<script type="text/javascript">
	tn.tunisietelecom.Event.setUpSubmitListener();
	tn.tunisietelecom.Event.init();
	//tn.tunisietelecom.Event.setUpCreateDate();
</script>