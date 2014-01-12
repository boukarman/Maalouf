<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="local-form">
	<form:form method="POST" modelAttribute="command">
		<div class="data-section">
			<form:label path="labLocal">
				<s:message
					code="event.main.panel.menu.item.local.create.form.labLocal.label" />
			</form:label>
			<form:input path="labLocal" />
		</div>
		<div class="data-section">
			<form:label path="site">
				<s:message
					code="event.main.panel.menu.item.local.create.form.site.label" />
			</form:label>
			<form:select path="site" size="1">
				<form:options items="${sites}" itemValue="siteId"
					itemLabel="labSite" />
			</form:select>
		</div>
		<div class="data-section">
			<form:label path="position">
				<s:message
					code="event.main.panel.menu.item.local.create.form.position.label" />
			</form:label>
			<form:input path="position" />
			<s:message code="event.main.panel.menu.item.local.create.form.submit"
				var="addLocalBtn" />
			<input type="submit" value="${addLocalBtn}" class="add-btn">
		</div>
	</form:form>
</div>

<script type="text/javascript">
	tn.tunisietelecom.Local.setUpSubmitListener();
</script>