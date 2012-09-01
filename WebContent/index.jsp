<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
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
				<a href="/" title="Iní­cio"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrot</span></a>
				<small> - Descubra, de maneira rápida, os commits que fizeram no <a href="http://github.com">GitHub</a>! =D</small>
			</h1> 
		</div>
		
	  		<div class="well">
		<div class="row-fluid">
		 	 <div class="span6">
				<fieldset> 
					<legend>Busque os Commts:</legend>
					<form action="<c:url value="/timeline/" />">
						<p>
							<label for="userName">Digite o usuário do <a href="http://github.com">GitHub</a>:</label>
							<input type="text" name="username" id="userName" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Buscar</button>
							<a href="#" target="blank">
								<i class="icon-question-sign"></i>
							</a>
						</p>
					</form>
				</fieldset>
			</div>
		  
			  <div class="span6">
			  	<fieldset> 
					<legend>Fique por dentro:</legend>
					<form action="#">
						<p>
							<label for="email">Increva-se e receba os email com atualizações: </label>
							<input type="text" name="email" id="email" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Enviar</button>
							<a href="#" target="blank">
								<i class="icon-question-sign"></i>
							</a>
						</p>
					</form>
				</fieldset>
			  </div>
		</div>
	  		</div>
		
		<hr>
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
</body>
</html>