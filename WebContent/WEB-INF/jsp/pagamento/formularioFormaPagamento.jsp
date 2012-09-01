<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	</head>

	<body>
		<h1>Escolha Forma de Pagamento</h1>
		
		<c:if test="${errors[0].category == 'pagamento.forma'}">
			<p>${errors[0].category}: ${errors[0].message}</p>
		</c:if>
		
		<form action="<c:url value="/pagamento/forma" />" method="POST">
			<input type="hidden" name="servicoUUID" value="${servicoUUID}"/>
			 
			<p>	
				<input type="radio" id="cartao" name="forma" value="" />Cartão de Crédito
				<div id="divCartao" style="display:none">
					<input type="radio" name="forma" value="AMEEXP" <c:if test="${pagamento.forma eq 'AMEEXP'}">checked</c:if>/>American Express
					<input type="radio" name="forma" value="DINERS" <c:if test="${pagamento.forma eq 'DINERS'}">checked</c:if>/>Diners
					<input type="radio" name="forma" value="HIPERC"  <c:if test="${pagamento.forma eq 'HIPERC'}">checked</c:if>/>Hipercard
					<input type="radio" name="forma" value="MASTER"  <c:if test="${pagamento.forma eq 'MASTER'}">checked</c:if>/>Mastercard
					<input type="radio" name="forma" value="VISA" <c:if test="${pagamento.forma eq 'VISA'}">checked</c:if>/>  Visa
				</div>
			</p>
			
			<p>
				<input type="radio" id="debito" name="forma" value="" />Débito em Conta Bancária
				<div id="divDebito" style="display:none">
					<input type="radio" name="forma" value="BRADES"/>Bradesco
					<input type="radio" name="forma" value="BRASIL"/>Banco do Brasil
					<input type="radio" name="forma" value="BANRIS"/>Banrisul
					<input type="radio" name="forma" value="ITAU"/>  Itaú
				</div>
			</p>
			
			<p id="pBoleto">
				<input type="radio" id="boleto" name="forma" value="BOLETO"/>Boleto Bancário
			</p>
			<div id="infoCartao" <c:if test="${cartao == null || cartao.vazio}"> style="display:none" </c:if>>
				<p>Preencha as informações do cartão:</p>
				
				<c:if test="${errors[0].category != 'pagamento.forma'}">
				<ul id="erros">
				<c:forEach items="${errors}" var="error">
					<li>${error.category}: ${error.message}</li>
				</c:forEach>
				</ul>
				</c:if>
				
				<p>
					<label>Parcelas:</label>
					<select name="cartao.parcelas">
						<option value="0">Selecione o número de parcelas</option>
						<c:forEach var="parcela" items="${pagamento.opcoesNumeroParcelas}"> 
							<option value="${parcela}">${parcela}</option>
						</c:forEach>
					</select>
				</p>	
			
				<p>
					<label>Número do cartão:</label>
					<input name="cartao.numero" value="${cartao.numero}" type="text" size="15" maxlength="19"/>		
				</p>
				
				<p>
					<label>Validade:</label>
					<input name="cartao.validade" value="${cartao.validade}" type="text" maxlength="5" size="4"/>	
				</p>
	
				<p>
					<label>Código de segurança:</label>
					<input name="cartao.codigo" value="${cartao.codigo}" type="text" size="3" maxlength="4"/>		
				</p>
				
				<p>
					<label>Nome (Idêntico ao nome exibido no cartão):</label>
					<input name="cartao.nomePortador" value="${cartao.nomePortador}" type="text" size="25"maxlength="45"/>		
				</p>
				
				<p>
					<label>Data de Nascimento:</label>
					<input name="cartao.nascimentoPortador" value="${cartao.nascimentoPortador}" type="text" size="8" maxlength="10"/>
				</p>
				
				<p>
					<label>Telefone:</label>
					<input name="cartao.telefonePortador" value="${cartao.telefonePortador}" type="text" size="10" maxlength="13"/>		
				</p>
				
				<p>
					<label>CPF do titular do cartão:</label>
					<input name="cartao.cpfTitular" value="${cartao.cpfTitular}" type="text" size="11" maxlength="14"/>
				</p>
			</div>
			
			<button type="submit">Enviar</button>
		</form>
		<script src="<c:url value="/js/mascaras.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/js/mascarasPagamento.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/js/forma.js"/>" type="text/javascript"></script>
	</body>
</html>