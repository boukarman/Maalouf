<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="piece-form">
	<c:url value="/telecom/piece/create" var="pieceFormUrl"></c:url>
	<form:form method="POST" action="${pieceFormUrl}">
		<c:choose>
			<c:when test="${empty command.site}">
				<div class="data-section">
					<form:label path="site">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.site.label" />
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
							code="event.main.panel.menu.item.piece.create.form.site.label" />
					</form:label>
					<form:input path="site.labSite" readonly="true" />
					<form:hidden path="site.siteId" />
				</div>
				<div class="data-section">
					<form:label path="idLocal">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.local.label" />
					</form:label>
					<form:select path="idLocal" size="1">
						<form:options items="${locals}" itemValue="idLocal"
							itemLabel="labLocal" />
					</form:select>
				</div>
				<div class="data-section">
					<form:label path="labPiece">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.labPiece.label" />
					</form:label>
					<form:input path="labPiece" />
				</div>
				<div class="data-section">
					<form:label path="establishementDate">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.establishementDate.label" />
					</form:label>
					<form:input path="establishementDate" />
					<script type="text/javascript">
					    $('#establishementDate').datetimepicker({
							dateFormat : "dd/mm/yy",
							timeFormat: "hh:mm tt",
							locale: 'fr'
						});
					</script>
				</div>
				<div class="data-section">
					<form:label path="status">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.status.label" />
					</form:label>
					<form:input path="status" />
				</div>
				<div class="data-section">
					<form:label path="mark">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.mark.label" />
					</form:label>
					<form:input path="mark" />
				</div>
				<div class="data-section">
					<form:label path="supplier">
						<s:message
							code="event.main.panel.menu.item.piece.create.form.supplier.label" />
					</form:label>
					<form:input path="supplier" />
					<s:message
						code="event.main.panel.menu.item.piece.create.form.submit"
						var="addPieceBtn" />
					<input type="submit" value="${addPieceBtn}" class="add-btn">
				</div>
			</c:otherwise>
		</c:choose>
	</form:form>
</div>

<script type="text/javascript">
	tn.tunisietelecom.Piece.init();
</script>