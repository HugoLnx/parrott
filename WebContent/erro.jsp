<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-34512810-1']);
	  _gaq.push(['_trackPageview', '/erro']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	
	<%@ include file="/inc/includeHeader.jsp" %>
	
	<title>Parrott - Erro</title>
</head>
<body>
	<div class="container-fluid">
		<%@ include file="/inc/header.jsp" %>
		
		<div class="well">
			<h4>Ops... Um erro ocorreu, desculpe a inconveniência.</h4>
			<p>Nossos desenvolvedores já estão sendo notificados quanto ao problema.</p>
		</div>
		
		<%@ include file="/inc/footer.jsp" %>
	</div>
	
	<%@ include file="/inc/includeJs.jsp" %>
</body>
</html>