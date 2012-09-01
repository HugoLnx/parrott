<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TESTE</title>
</head>
<body>
	<form method="POST" action='<c:url value="/teste/pagamento/criar"></c:url>'>
		<input type="radio" name="tipoXML" value="semRepresentante" /> XML sem representante
		<input type="radio" name="tipoXML" value="parcelado" /> XML com pagamento parcelado
		<input type="radio" name="tipoXML" value="comRepresentanteMeioPreenchido" /> XML com representante meio preenchido
		<input type="submit" />
	</form>
	
</body>
</html>