<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-34512810-1']);
	  _gaq.push(['_trackPageview', '/sobre']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	
	<%@ include file="/inc/includeHeader.jsp" %>
	
	<title>Sobre</title>
</head>
<body>
	<div class="container-fluid">
	
		<%@ include file="/inc/header.jsp" %>
		
		<div class="container">

		<div class="well">
			<div class="row-fluid">
				<p>
					Você já sonhou com um Facebook/Twitter de código?  Uma página onde você só vê código? Sem imagens do tipo "Veja isso ou você ama o diabo" ou
					"Veja! Meu gatinho na caixa de areia!", só um bom e velho código. Páginas e páginas de código escrito pelos seus maiores idolos da
					programação!
				</p>
				<p>
					Se você quer aprender com <a href="<c:url value="/timeline/tenderlove"/>">Tender Love</a>
					, <a href="<c:url value="/timeline/unclebob"/>">Uncle Bob</a>
					, <a href="<c:url value="/timeline/akitaonrails"/>">Fábio Akita</a>
					, <a href="<c:url value="/timeline/guilhermesilveira"/>">Guilherme Silveira</a>
					, <a href="<c:url value="/timeline/fnando"/>">Nando Vieira</a> e muitos outros!
					Não perca a oportunidade de ver os últimos commits que essas feras escreveram!
				</p>
			</div>
		</div>
		 
		<%@ include file="/inc/footer.jsp" %>
	</div>
	
	<%@ include file="/inc/includeJs.jsp" %>
</body>
</html>