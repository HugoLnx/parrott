<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	</head>
	
	<body>
		<div id="carrinho">
			<h1>Carrinho:</h1>
			<ul>
			<c:forEach var="item" items="${pagamento.itens}"> 
				<li class="item">
					<ul>
						<li class="sigla">${item.sigla}</li>
						<li class="descricao">${item.descricao}</li>
						<li class="preco">${item.preco}</li>
					</ul>
				</li>
			</c:forEach>
			</ul>
			<p class="total"><span>Total:</span> ${pagamento.total} (Juros não inclusos)</p>
		</div>
	
		<p>Pedido confirmado com sucesso!</p>
		
		<c:if test="${pagamento.forma.boleto}">
			<a href="${uriRedirecionamento}">Visualizar Boleto</a>
		</c:if>

		<c:if test="${pagamento.forma.debito}">
			<a href="${uriRedirecionamento}">Fazer debito no ${pagamento.forma.nome}</a>
		</c:if>
	</body>
</html>