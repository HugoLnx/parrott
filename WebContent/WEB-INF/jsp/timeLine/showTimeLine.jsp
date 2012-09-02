<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-34512810-1']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="<c:url value="/css/bootstrap.css"/>" rel="stylesheet">
	<link href="<c:url value="/css/bootstrap-responsive.css"/>" rel="stylesheet">
	<link href="<c:url value="/css/parrot.css"/>" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/img/parrot16.ico"/>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parrot</title>
</head>
<body>
	<div class="container-fluid">
		<div class="hero-unit">
			<h1 class="header">
				<a href="<c:url value="/" />" title="Início"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrot</span></a>
				<small> - Descubra, de maneira rápida, os commits que fizeram no <a href="http://github.com">GitHub</a>! =D</small>
			</h1> 
		</div>
		
		<div class="container">
			<c:if test="${!empty username}"><h3>Commits do usuário ${username}</h3></c:if>
			<c:forEach items="${payloads}" var="payload">
				<c:if test="${empty username}"><h3>Commits do usuário ${payload.login}</h3></c:if>
				<c:forEach items="${payload.commits}" var="commit">
					<div class="push">
					<fieldset class="commit">
						<legend>${commit.message}</legend>
						<c:forEach items="${commit.commitFiles}" var="commitFile">
							<div class="commitfile">
							<span class="filename"><strong>Arquivo:</strong> ${commitFile.fileName}</span>
							<table class="file">
								<tbody>
									<c:forEach items="${commitFile.lines}" var="line" varStatus="index">
									<tr class="line ${line.status}">
										<td class="line-index">${index.count}</td>
										<td class="line-sinal">${line.status.sinal}</td>
										<td class="line-content"><c:out value="${line.content}"/></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
						</c:forEach>
					</fieldset>
					</div>
				</c:forEach>
			</c:forEach>
			 
		</div>
		 
		<hr>
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
</body>
</html>