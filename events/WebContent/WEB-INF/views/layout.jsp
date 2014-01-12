<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href='<c:url value="/resources/images/telecom-logo.png"/>' type="image/x-icon" />
<title><s:message code="common.title" /></title>
<tiles:insertAttribute name="header" />
</head>
<body>
	<div id="header-style" class="header-style"><tiles:insertAttribute name="telecom-header"/></div>
	<div id="body-style" class="body-style"><tiles:insertAttribute name="body"/></div>
	<div id="footer-style" class="footer-style"><tiles:insertAttribute name="footer"/></div>
</body>
</html>
