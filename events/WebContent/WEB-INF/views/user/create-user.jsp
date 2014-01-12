<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="user-form">
	<form:form method="POST" modelAttribute="command">
		<div class="data-section">
			<form:label path="login">
				<s:message
					code="event.main.panel.menu.item.user.create.form.login.label" />
			</form:label>
			<form:input path="login" />
		</div>
		<div class="data-section">
			<form:label path="password">
				<s:message
					code="event.main.panel.menu.item.user.create.form.password.label" />
			</form:label>
			<form:input path="password" />
		</div>
		<div class="data-section">
			<form:label path="lastName">
				<s:message
					code="event.main.panel.menu.item.user.create.form.lastName.label" />
			</form:label>
			<form:input path="lastName" />
		</div>
		<div class="data-section">
			<form:label path="firstName">
				<s:message
					code="event.main.panel.menu.item.user.create.form.firstName.label" />
			</form:label>
			<form:input path="firstName" />
		</div>
		<div class="data-section">
			<form:label path="address">
				<s:message
					code="event.main.panel.menu.item.user.create.form.address.label" />
			</form:label>
			<form:input path="address" />
		</div>
		<div class="data-section">
			<form:label path="email">
				<s:message
					code="event.main.panel.menu.item.user.create.form.email.label" />
			</form:label>
			<form:input path="email" />
		</div>
		<div class="data-section">
			<form:label path="telNumber">
				<s:message
					code="event.main.panel.menu.item.user.create.form.telNumber.label" />
			</form:label>
			<form:input path="telNumber" />
		</div>
		<div class="data-section">
			<form:label path="formation">
				<s:message
					code="event.main.panel.menu.item.user.create.form.formation.label" />
			</form:label>
			<form:input path="formation" />
		</div>
		<div class="data-section">
			<form:label path="idRole">
				<s:message
					code="event.main.panel.menu.item.user.create.form.role.label" />
			</form:label>
			<form:select path="idRole" size="1">
				<form:options items="${roles}" itemValue="idRole" itemLabel="label" />
			</form:select>
		</div>
		<div class="data-section hidden">
			<form:label path="subcontractorTypeId">
				<s:message
					code="event.main.panel.menu.item.user.create.form.subcontractorType.label" />
			</form:label>
			<form:select path="subcontractorTypeId" size="1">
				<form:options items="${subcontractorTypes}" itemValue="id"
					itemLabel="type" />
			</form:select>
		</div>
		<div class="data-section">
		<s:message code="event.main.panel.menu.item.event.create.form.submit"
				var="addEvtBtn" />
			<input type="submit" value="${addEvtBtn}" class="add-btn">
		</div>
	</form:form>
</div>

<script type="text/javascript">
	tn.tunisietelecom.User.init();
</script>