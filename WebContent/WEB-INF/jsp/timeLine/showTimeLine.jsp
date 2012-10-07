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
	  _gaq.push(['_trackPageview', '/timeline']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	
	<%@ include file="/inc/includeHeader.jsp" %>
	
	<title>Parrott - TimeLine</title>
</head>
<body>
	<div class="container-fluid">
	
		<%@ include file="/inc/header.jsp" %>
		
		<div class="container">
			<c:forEach items="${payloads}" var="payload">
				<div class="payload-header">
					<a href="<c:url value="/timeline/${payload.login}" />"><img src="<c:url value="${payload.avatarUri}"/>" class="img-polaroid" ></a>
					<div class="payload-info">
						<p class="payload-login"><a href="<c:url value="/timeline/${payload.login}" />">${payload.login}</a></p>
						<p>
							Enviou um push no dia: <fmt:formatDate value="${payload.createdAt.time}" type="date" dateStyle="medium" />
						</p>
					</div>
				</div>
				<c:forEach items="${payload.commits}" var="commit">
					<div class="push">
					<fieldset class="commit">
						<legend>${commit.message}</legend>
						<span class="small commit-date">Comitou dia: <fmt:formatDate value="${commit.date.time}" type="date" dateStyle="medium" /></span>
						
						<c:forEach items="${commit.commitFiles}" var="commitFile">
							<div class="commitfile">
							<span class="filename"><strong>Arquivo:</strong> <a href="${commitFile.blobUri}">${commitFile.fileName}</a></span>
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
		 
		<%@ include file="/inc/footer.jsp" %>
	</div>
	
	<%@ include file="/inc/includeJs.jsp" %>
</body>
</html>