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
		 	 <div class="span4">
				<fieldset> 
					<legend>Busque usuário</legend>
					<form action="<c:url value="/timeline/" />">
						<p>
							<label for="userName">Digite o usuário do <a href="http://github.com">GitHub</a> que você que ver os commits</label>
							<input type="text" name="username" id="userName" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Buscar</button>
							<a href="#">
								<i class="icon-question-sign"></i>
							</a>
						</p>
					</form>
				</fieldset>
			</div>

		  	<div class="span4">
			  	<fieldset> 
					<legend>Busque seguidores</legend>
					<form action="<c:url value="/following/" />" method="get">
						<p>
							<label for="folusername">Digite seu usuário do <a href="http://github.com">GitHub</a> e veja os commits dos seus seguidores</label>
							<input type="text" name="username" id="folusername" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Buscar</button>
							<a href="#">
								<i class="icon-question-sign"></i>
							</a>
						</p>
					</form>
				</fieldset>
			 </div>
			 <div class="span4">
			  	<fieldset> 
					<legend>Fique por dentro</legend>
					<form action="<c:url value="/subscribe/" />" method="post">
						<p>
							<label for="email">Inscreva-se e receba os email com atualizações</label>
							<input type="text" name="email" id="email" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Enviar</button>
							<a href="#">
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
		
		<div class="socialNetworks">
			<div class="fb-like" data-href="http://startupers_de_primeira_viagem.webbynode.us/" data-layout="button_count" data-width="450" data-show-faces="true"></div>
			<div class="g-plus" data-action="share" data-annotation="bubble" data-href="http://startupers_de_primeira_viagem.webbynode.us/"></div>
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://startupers_de_primeira_viagem.webbynode.us/" data-text="Parrot - Descubra, de maneira rápida, os commits que fizeram no GitHub! http://startupers_de_primeira_viagem.webbynode.us/" data-lang="pt">Tweetar</a>
		</div>
		
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
	<script type="text/javascript">(function() {var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;po.src = 'https://apis.google.com/js/plusone.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);})();</script>
	<div id="fb-root"></div>
	<script>(function(d, s, id) {  var js, fjs = d.getElementsByTagName(s)[0]; if (d.getElementById(id)) return;js = d.createElement(s); js.id = id;js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";fjs.parentNode.insertBefore(js, fjs);}(document, 'script', 'facebook-jssdk'));</script>
</body>
</html>