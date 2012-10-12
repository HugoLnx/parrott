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
	  _gaq.push(['_trackPageview', '/following']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	
	<%@ include file="/inc/includeHeader.jsp" %>
	
	<title>Following: ${username} - Parrott</title>
</head>
<body>
	<div class="container-fluid">
	
		<%@ include file="/inc/header.jsp" %>
		
		<div class="container">

		<%@ include file="/inc/events.jspf" %>
			 
			<ul class="pager">
			<c:if test="${page ne 1}">
			  <li class="previous">
			    <a href="<c:url value="/following/${username}/${page-1}"/>">&larr; Anterior</a>
			  </li>
			</c:if>
			<c:if test="${page eq 1}">
			  <li class="previous disabled"><a>&larr; Anterior</a></li>
			</c:if>
			  <li class="next">
			    <a href="<c:url value="/following/${username}/${page+1}"/>">Próxima &rarr;</a>
			  </li>
			</ul>
			
		</div>
		 
		<%@ include file="/inc/footer.jsp" %>
	</div>
	
	<%@ include file="/inc/includeJs.jsp" %>
</body>
</html>