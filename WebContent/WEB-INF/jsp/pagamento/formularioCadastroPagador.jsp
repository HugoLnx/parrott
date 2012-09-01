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
			<p class="total"><span>Total:</span> ${pagamento.total}</p>
		</div>
	
		<ul id="erros">
		<c:forEach items="${errors}" var="error">
			<li>${error.category}: ${error.message}</li>
		</c:forEach>		
		</ul>
		
		<form action="<c:url value="/pagamento/pagador" />" method="POST">
			<input type="hidden" name="pagamentoUUID" value="${pagamento.uuid}" />
			<input name="representanteFormBean.endereco.pais" value="BRA" type="hidden"/>		
			
			<p>Entre com as seguintes informações:</p>
			
			<p>	
				<label>Nome:</label>
				<input name="representanteFormBean.nome" value="${representanteFormBean.nome}" type="text" size="25"/>	
			</p>
			
			<p>	
				<label>E-mail:</label>
				<input name="representanteFormBean.email" value="${representanteFormBean.email}" type="text" size="25" maxlength="45"/>	
			</p>
			
			<p>	
				<label>CPF:</label>
				<input name="representanteFormBean.cpf" value="${representanteFormBean.cpf}" type="text" size="11" maxlength="14"/>
			</p>
		
			<p>	
				<label>Estado:</label>
				<select name="representanteFormBean.endereco.estado">
					<option value="AC">Acre</option>
					<option value="AL">Alagoas</option>
					<option value="AP">Amapá</option>
					<option value="AM">Amazonas</option>
					<option value="BA">Bahia</option>
					<option value="CE">Ceará</option>
					<option value="DF">Distrito Federal</option>
					<option value="ES">Espírito Santo</option>
					<option value="GO">Goiás</option>
					<option value="MA">Maranhão</option>
					<option value="MT">Mato Grosso</option>
					<option value="MS">Mato Grosso do Sul</option>
					<option value="MG">Minas Gerais</option>
					<option value="PA">Pará</option>
					<option value="PB">Paraíba</option>
					<option value="PR">Paraná</option>
					<option value="PE">Pernambuco</option>
					<option value="PI">Piauí</option>
					<option value="RJ">Rio de Janeiro</option>
					<option value="RN">Rio Grande do Norte</option>
					<option value="RS">Rio Grande do Sul</option>
					<option value="RO">Rondônia</option>
					<option value="RR">Roraima</option>
					<option value="SC">Santa Catarina</option>
					<option value="SP">São Paulo</option>
					<option value="SE">Sergipe</option>
					<option value="TO">Tocantins</option>
				</select>
			</p>
			
			<p>	
				<label>Cidade:</label>
				<input name="representanteFormBean.endereco.cidade" value="${representanteFormBean.endereco.cidade}" type="text" size="25"maxlength="32"/>
			</p>
		
			<p>	
				<label>Cep:</label>
				<input name="representanteFormBean.endereco.cep" value="${representanteFormBean.endereco.cep}" type="text" size="7" maxlength="9"/>
			</p>
		
			<p>	
				<label>Bairro:</label>
				<input name="representanteFormBean.endereco.bairro" value="${representanteFormBean.endereco.bairro}" type="text" size="25" maxlength="45"/>	
			</p>
							
			<p>	
				<label>Logradouro:</label>
				<input name="representanteFormBean.endereco.logradouro" value="${representanteFormBean.endereco.logradouro}" type="text" size="25" maxlength="45"/>
			</p>
			
			<p>	
				<label>Número:</label>
				<input name="representanteFormBean.endereco.numero" value="${representanteFormBean.endereco.numero}" type="text" size="4" maxlength="5"/>
			</p>
			
			<p>	
				<label>Complemento:</label>
				<input name="representanteFormBean.endereco.complemento" value="${representanteFormBean.endereco.complemento}" type="text" size="10" maxlength="45"/>
			</p>
				
			<p>	
				<label>Telefone:</label>
				<input name="representanteFormBean.telefone" value="${representanteFormBean.telefone}" type="text" size="10" maxlength="13"/>
			</p>
			
			<button type="submit" name="_method" value="PUT">Enviar</button>
		</form>
		<script src="<c:url value="/js/mascaras.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/js/mascarasPagador.js"/>" type="text/javascript"></script>
	</body>
</html>