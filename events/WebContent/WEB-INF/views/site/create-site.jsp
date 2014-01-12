<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="site-form">
	<form:form method="POST" modelAttribute="command">
		<div class="data-section">
			<form:label path="labSite">
				<s:message
					code="event.main.panel.menu.item.site.create.form.site.label" />
			</form:label>
			<form:input path="labSite" />
		</div>
		<div class="data-section">
			<form:label path="address">
				<s:message
					code="event.main.panel.menu.item.site.create.form.address.label" />
			</form:label>
			<form:input path="address" />
			<s:message code="event.main.panel.menu.item.site.create.form.submit"
				var="addSiteBtn" />
			<input type="submit" value="${addSiteBtn}" class="add-btn">
		</div>
	</form:form>
</div>

<script type="text/javascript">
	tn.tunisietelecom.Site.setUpSubmitListener();
</script>