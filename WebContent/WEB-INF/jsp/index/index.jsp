<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-34512810-1']);
	  _gaq.push(['_trackPageview', '/']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	
	</script>
	
	<%@ include file="/inc/includeHeader.jsp" %>
	<title>Parrott</title>
</head>
<body>
	<div class="container-fluid">

	<%@ include file="/inc/header.jsp" %>
		
		<div class="socialNetworks">
			<div class="fb-like" data-href="http://parrott.com.br/" data-layout="button_count" data-width="450" data-show-faces="true"></div>
			<div class="g-plus" data-action="share" data-annotation="bubble" data-href="http://parrott.com.br/"></div>
			<a href="https://twitter.com/share" class="twitter-share-button" data-url="http://parrott.com.br/" data-text="Parrott - Descubra, de maneira rápida, os commits que fizeram no GitHub!" data-lang="pt">Tweetar</a>
		</div>
		
	  	<div class="well">
		<div class="row-fluid">
		 	 <div class="span4">
				<fieldset> 
					<legend>Busca por usuário</legend>
					<form action="<c:url value="/timeline/" />">
						<p>
						
							<label for="userName">Digite um usuário do <a href="http://github.com">GitHub</a></label>
							<input type="text" name="username" id="userName" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Buscar</button>
							<a href="#" class="bspopover" rel="popover"
									data-trigger="hover"
									data-original-title="Busca por usuário"
									data-content="Digite o usuário do GitHub que você quer ver os commits">
								<i class="icon-question-sign"></i>
							</a>
						</p>
					</form>
				</fieldset>
			</div>

		  	<div class="span4">
			  	<fieldset> 
					<legend>Busca por Followings</legend>
					<form action="<c:url value="/following/" />" method="get">
						<p>
							<label for="folusername">Digite seu usuário do <a href="http://github.com">GitHub</a></label>
							<input type="text" name="username" id="folusername" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Buscar</button>
							<a href="#" class="bspopover" rel="popover"
									data-trigger="hover"
									data-original-title="Busca por followings"
									data-content="Digite seu usuário do GitHub e veja os commits de quem você está seguindo!">
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
							<label for="email">Receba email com atualizações</label>
							<input type="text" name="email" id="email" class="input-medium search-query"/>
							<button type="submit" class="btn btn-success">Enviar</button>
							<a href="#" class="bspopover" rel="popover"
									data-trigger="hover"
									data-original-title="Fique por dentro"
									data-content="Increva-se e será informado por email a cada atualização lançadas no site.">
								<i class="icon-question-sign"></i>
							</a>
							<span>${mensagem}</span>
						</p>
					</form>
				</fieldset>
			  </div>
		  	</div>
			<hr>
			<div id="events">
				<div class="progress progress-striped active">
					<div class="bar bar-success" style="width: 100%;">Carregando commits de Exemplo ...</div>
				</div>
			</div>
		</div>
		<%@ include file="/inc/footer.jsp" %>
	</div>
	
	<%@ include file="/inc/includeJs.jsp" %>
	
	<script type="text/javascript">
		$("#events").load("<c:url value="/index/events" />");
	</script>
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
	<script type="text/javascript">(function() {var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;po.src = 'https://apis.google.com/js/plusone.js'; var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);})();</script>
	<div id="fb-root"></div>
	<script>(function(d, s, id) {  var js, fjs = d.getElementsByTagName(s)[0]; if (d.getElementById(id)) return;js = d.createElement(s); js.id = id;js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";fjs.parentNode.insertBefore(js, fjs);}(document, 'script', 'facebook-jssdk'));</script>
</body>
</html>