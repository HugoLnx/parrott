<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<p class="total">
				<span>Total:</span>
				<span id="valorTotal">
					<c:if test="${not pagamento.forma.cartaoCredito || cartao.parcelas eq 1}">${pagamento.total}</c:if>
				</span>
			</p>
		</div>
		
		<c:if test="${pagamento.forma.boleto}">
			<p>Pagamento será efetuado através de boleto</p>
		</c:if>
		
		<c:if test="${pagamento.forma.debito}">
			<p>Pagamento será debitado diretamente de uma Conta Bancária</p>
		</c:if>

		<c:if test="${pagamento.forma.cartaoCredito}">
			<p>Confirme os dados do cartão:</p>
			
			<p>
				<span>Parcelas:</span>
				<span id="valorParcelas">${cartao.parcelas}</span>
			</p>	
		
			<p>
				<span>Número do cartão:</span>
				${cartao.numero}		
			</p>
			
			<p>
				<span>Validade:</span>
				${cartao.validade}		
			</p>
			
			<p>
				<span>Código de segurança:</span>
				${cartao.codigo}		
			</p>
			
			<p>
				<span>Nome (Idêntico ao nome exibido no cartão):</span>
				${cartao.nomePortador}		
			</p>
			
			<p>
				<span>Data de Nascimento:</span>
				${cartao.nascimentoPortador}
			</p>
			
			<p>
				<span>Telefone:</span>
				${cartao.telefonePortador}	
			</p>
			
			<p>
				<span>CPF do titular do cartão:</span>
				${cartao.cpfTitular}
			</p>
		</c:if>
		<button type="submit" id="finalizarCompra">Finalizar compra</button>
		<button type="submit" id="voltarCompra">Voltar</button>
		
		<form action='<c:url value="/pagamento/confirmacao"></c:url>' method="POST" id="confirmarPagamento" style="display:none">
			<input type="hidden" name="servicoUUID" value="${moip.uuid}"/>
		</form>
		
		<form action='<c:url value="/pagamento/formularioFormaPagamentoErroMoip"></c:url>' method="POST" id="formErroMoip" style="display:none">
		</form>
		<form action='<c:url value="/pagamento/formularioVoltarFormaPagamento"></c:url>' method="POST" id="formVoltarPagamento" style="display:none">
		</form>
		
		<div id="MoipWidget"
				data-token="${moip.token}"
				callback-method-success="Caelum.PagPag.moipCallback.funcaoSucesso"
				callback-method-error="Caelum.PagPag.moipCallback.funcaoFalha"></div>
				
		<div id="PagPagData"
			data-formaPagamento="${formaPagamento}"
			data-servicoUUID="${moip.uuid}"
			<c:choose>
				<c:when test="${formaPagamento == 'CartaoCredito'}">
					data-bandeira="${bandeiraCartao}"
					data-parcelas="${cartao.parcelas}"
					data-numeroCartao="${cartao.numero}"
					data-validadeCartao="${cartao.validade}"
					data-codigoCartao="${cartao.codigo}"
					data-nomePortador="${cartao.nomePortador}"
					data-nascimentoPortador="${cartao.nascimentoPortador}"
					data-telefonePortador="${cartao.telefonePortador}"
					data-cpfTitular="${cartao.cpfTitular}"
				</c:when>
				
				<c:when test="${formaPagamento == 'DebitoBancario'}">
					data-banco="${bancoDebito}"
				</c:when>
		    </c:choose>
		></div>
		<script type='text/javascript' src='${moipjs}' charset='ISO-8859-1'></script>
		<script src="<c:url value="/js/pagpag.js"/>" type="text/javascript"></script>
	</body>
</html>