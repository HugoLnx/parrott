<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="parrot/search" method="post">
	<input type="text" name="userName" id="userName"/>
	<button id="send">send</button>
</form>
<table>
<c:forEach items="${payloadList}"  var="payload" varStatus="commit">
	
	<tr> 
		<td> ${payload.repo}</td>
		<c:forEach items="${payload.commits}"  var="index">
			<td>${index.message } </td>
			<td>${index.url}</td>	
		</c:forEach>
	</tr>
	
</c:forEach>
</table>
</body>
</html>