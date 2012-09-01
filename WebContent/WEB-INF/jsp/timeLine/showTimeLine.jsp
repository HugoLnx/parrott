<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
	<link href="<c:url value="/css/bootstrap-responsive.css"/>" rel="stylesheet">
	<link href="<c:url value="/css/parrot.css"/>" rel="stylesheet">
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parrot</title>
</head>
<body>
	<div class="container-fluid">
		<div class="hero-unit">
			<h1 class="header">
				<a href="/" title="Início"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrot</span></a>
				<small> - Descubra, de maneira rápida, os commits que fizeram <a href="http://github.com">GitHub</a>! =D</small>
			</h1> 
		</div>
		
		<div class="container">
			<h3>Commits do Parrot</h3>
			
			<c:forEach items="${payloads}" var="payload">
			<c:forEach items="${payload.commits}" var="commit">
			<c:forEach items="${commit.commitFiles}" var="commitFile">
			<fieldset> <legend>${commit.message}</legend>
				<span><strong>Head:</strong>${payload.head}</span><br />
				<span><strong>Arquivo:</strong> ${commitFile.fileName}</span> <br />
				<span><strong>URL:</strong>${commit.url}</span> <br /> 
				<table class="table table-bordered table-hover">
					<tbody>
						<c:forEach items="${commitFile.lines}" var="line" varStatus="index">
						<tr class="${line.status}">
							<td>${index.count}</td><td><span>${line.status.sinal}</span></td> <td><span>${line.content}</span></td>
						</tr>
						</c:forEach>
					</tbody>
					
				</table>
			</fieldset>
			</c:forEach>
			</c:forEach>
			</c:forEach>
			 
		</div>
		 
		<hr>
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
</html>