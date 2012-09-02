<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<meta charset="utf-8">
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
				<a href="<c:url value="/" />" title="Iní­cio"><img alt="parrot-logo" src="<c:url value="/img/parrot64.png" />" class="logoImg"><span class="logo">Parrot</span></a>
				<small> - Descubra, de maneira rápida, os commits que fizeram no <a href="http://github.com">GitHub</a>! =D</small>
			</h1> 
		</div>
		
	  		<div class="well">
		<div class="row-fluid">
		 	 <div class="span6">
				<fieldset> 
					<legend>Busque os Commits:</legend>
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
					<form action="<c:url value="/subscribe/" />" method="post">
						<p>
							<label for="email">Increva-se e receba os email com atualizações: </label>
							<input type="text" name="email" id="email" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Enviar</button>
							<a href="#" target="blank">
								<i class="icon-question-sign"></i>
							</a>
							<span>${mensagem}</span>
						</p>
					</form>
				</fieldset>
			  </div>
		</div>
	  		</div>
		
		<hr>
		<!-- AddThis Button BEGIN -->
		<div class="addthis_toolbox addthis_default_style ">
			<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
			<a class="addthis_button_tweet"></a>
			<a class="addthis_counter addthis_pill_style"></a>
		</div>
		<script type="text/javascript">var addthis_config = {"data_track_addressbar":true};</script>
		<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=ra-5042a8441d125eea"></script>
		<!-- AddThis Button END -->
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
</body>
</html>