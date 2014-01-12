<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Image converter</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
    </head>
    <body>
        <h1>Please upload a file</h1>
        <c:url value="/image-converter/convert" var="convertFormUrl"></c:url>
        <form:form method="POST" action="${convertFormUrl}" enctype="multipart/form-data">
            <input type="file" name="uploadedImage"/>
            <input type="submit"/>
        </form:form>
    </body>
</html>