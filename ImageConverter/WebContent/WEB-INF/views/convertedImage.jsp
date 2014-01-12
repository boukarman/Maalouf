<%@page import="javax.servlet.*,javax.servlet.http.*,java.io.*,java.util.*"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%
	byte[] image = (byte[])request.getAttribute("binaryResult");
	response.setContentType("image/jpeg");

	try {
		ServletOutputStream view = response.getOutputStream();
		view.write(image);
	} catch (IOException e) {
		e.printStackTrace();
	}

	try {
		response.getOutputStream().close();
	} catch (IOException e) {
		e.printStackTrace();
	}
%>