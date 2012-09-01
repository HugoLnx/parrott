<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<style type="text/css">
		.table tbody tr.ADDED td {
  			background-color: #dff0d8;
		}

		.table tbody tr.REMOVED td {
  			background-color: #f2dede;
		}
	
	</style>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Parrot</title>
</head>
<body>
	<div class="container-fluid">
		<div class="hero-unit">
			<h2>Parrot <small> - Descubra de maneira rápida os commits de seu cara favorito! =D</small> </h2> 
		</div>
		
		<div class="container">
			<h3>Commits do Parrot</h3>
			
			<c:forEach items="${payloads}" var="payload">
			<c:forEach items="${payload.commits}" var="commit">
			<c:forEach items="${commit.commitFiles}" var="commitFile">
			<fieldset> <legend>${commit.message}</legend>
				<span><strong>head:</strong>${payload.head}</span><br />
				<span><strong>Arquivo:</strong> ${commitFile.fileName}</span> <br />
				<span><strong>URL:</strong>${commit.url}</span> <br /> 
				<table class="table table-bordered table-hover">
					<tbody>
						<c:forEach items="${commitFile.lines}" var="line">
						<tr class="${line.status}">
							<td>1</td><td><span>${line.status.sinal}</span></td> <td><span>${line.content}</span></td>
						</tr>
						</c:forEach>
						<tr>
							<td>5</td><td></td><td>   head 'https://github.com/mongodb/mongo-php-driver.git'</td>
						</tr>
						<tr>
							<td>6</td><td></td><td></td>
						</tr>
						<tr>
							<td>7</td><td></td><td>   depends_on 'autoconf' =&gt; :build</td>
						</tr>
					</tbody>
					
				</table>
			</fieldset>
			</c:forEach>
			</c:forEach>
			</c:forEach>
			 
			<fieldset> <legend>Mudando os arquivos</legend>
				<span><strong>Arquivo:</strong> Formula/php53-mongo.rb</span>
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td>1</td><td></td> <td>   homepage 'http://pecl.php.net/package/mongo'</td>
						</tr>
						<tr>
							<td>2</td><td></td><td>   url 'http://pecl.php.net/get/mongo-1.2.12.tgz'</td>
						</tr>
						<tr class="error">
							<td>3</td><td>-</td><td>  md5 '4a6e9d71ec266365c591284950d29167'</td>
						</tr>
						<tr class="success">
							<td>4</td><td>+</td><td >  md5 'b8f2b50c818c28ae2674c46e639203ab'</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
			
			<fieldset> <legend>Refazendo o Teste</legend>
				<span><strong>Arquivo:</strong> Formula/php53-mongo.rb</span>
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td>1</td><td></td> <td>   homepage 'http://pecl.php.net/package/mongo'</td>
						</tr>
						<tr>
							<td>2</td><td></td><td>   url 'http://pecl.php.net/get/mongo-1.2.12.tgz'</td>
						</tr>
						<tr class="error">
							<td>3</td><td>-</td><td>  md5 '4a6e9d71ec266365c591284950d29167'</td>
						</tr>
						<tr class="success">
							<td>4</td><td>+</td><td >  md5 'b8f2b50c818c28ae2674c46e639203ab'</td>
						</tr>
						<tr>
							<td>5</td><td></td><td>   head 'https://github.com/mongodb/mongo-php-driver.git'</td>
						</tr>
						<tr class="error">
							<td>6</td><td>-</td><td>  md5 '4a6e9d71ec266365c591284950d29167'</td>
						</tr>
						<tr class="success">
							<td>7</td><td>+</td><td >  md5 'b8f2b50c818c28ae2674c46e639203ab'</td>
						</tr>
						<tr class="success">
							<td>8</td><td>+</td><td >  md5 'b8f2b50c818c28ae2674c46e639203ab'</td>
						</tr>
						<tr>
							<td>9</td><td></td><td></td>
						</tr>
						<tr>
							<td>10</td><td></td><td>   depends_on 'autoconf' =&gt; :build</td>
						</tr>
					</tbody>
				</table>
		</div>
		 
		<hr>
		<footer>
			<p>&copy; Startupers de Primeira Viagem 2012<small> - Anny, Jean, Hugo e Wallace</small></p>
		</footer>
	</div>
</html>