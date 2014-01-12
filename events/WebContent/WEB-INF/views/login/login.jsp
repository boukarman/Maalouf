<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="UTF-8"%>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title><s:message code="common.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href='<c:url value="/resources/images/telecom-logo.png"/>' type="image/x-icon" />

<!-- CSS -->
<link rel='stylesheet'
	href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
<link rel="stylesheet" href="<c:url value="/resources/css/login/reset.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/login/supersized.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/login/style.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/css/login/login.css"/>">

<!-- jquery -->
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery/jquery-1.9.1.js"/>"></script>

<!-- 
	Extjs default blue theme 
-->	
<script type="text/javascript"
	src="<c:url value="/resources/js/extjs/ext-all-debug.js"/>"></script>

<!-- create names space -->
<script type="text/javascript">
	Ext.ns("tn.tunisietelecom");
	tn.tunisietelecom.Constants = {};
	
	Ext.apply(tn.tunisietelecom.Constants, {
		backgrounds_login_1 : '<c:url value="/resources/images/1.jpg"/>',
		backgrounds_login_2 : '<c:url value="/resources/images/2.jpg"/>',
		backgrounds_login_3 : '<c:url value="/resources/images/3.jpg"/>'
	});
</script>

</head>

<body>

	<div class="page-container">
		<c:url value="/j_spring_security_check" var="formUrl"/>
		<h1><s:message code="login.form.title" /></h1>
		<s:message code="login.field.username.placeholder" var="usernameHolder"/>
		<s:message code="login.field.password.placeholder" var="passwordHolder"/>
		<form method="post" action="${formUrl}">
			<input type="text" name="j_username" class="username" placeholder="${usernameHolder}"/>
			<input type="password" name="j_password" class="password" placeholder="${passwordHolder}" />
			<button type="submit"><s:message code="login.button.submit"/></button>
			<div class="error"><span>+</span></div>
		</form>
		<c:choose>
			<c:when test="${error}">
				<div class="login-error">
					<s:message code="login.action.error" />
				</div>
			</c:when>
		</c:choose>
	</div>

	<!-- Javascript -->
	<script src="<c:url value="/resources/js/jquery/supersized.3.2.7.min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery/supersized-init.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery/scripts.js"/>"></script>
	<script type="text/javascript">
		$('input[name=j_username]').focus();
	</script>
</body>

</html>