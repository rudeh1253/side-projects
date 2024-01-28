<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%
String dtoAttributeKey = super.getServletContext().getInitParameter("dto-key");
%>>
	<%= request.getAttribute(dtoAttributeKey) %>>
</body>
</html>