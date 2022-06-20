<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Hipertrofia - Moda esportiva</title>
	</head>
	<body>	
		<main>
			<h2>
		      	<a href="novoFormularioCartao">Adicione um novo cartão</a>
		       	&nbsp;&nbsp;&nbsp;
				<a href="ListarProduto">Faça um pedido</a>
		       	&nbsp;&nbsp;&nbsp;
		        <a href="novoFormularioProduto">Adicione um novo produto</a>
				&nbsp;&nbsp;&nbsp;
		        <a href="ListarPedidos">liste todos os pedidos</a>
		   </h2>
			<div align="center">
	        <table border="1" cellpadding="5">
	            <caption><h2>Lista de cartões</h2></caption>
	            <tr>
	                <th>ID</th>
	                <th>Número</th>
	                <th>CVC</th>
	                <th>Validade</th>
	                <th>Ação</th>
	                
	            </tr>
	            <c:forEach var="cartao" items="${cartoes}">
	                <tr>
	                    <td><c:out value="${cartao.id}" /></td>
	                    <td><c:out value="${cartao.numeroCartao}" /></td>
	                    <td><c:out value="${cartao.cvc}" /></td>
	                    <td><c:out value="${cartao.dataVencimento}" /></td>
	                    <td>
	                    	<a href="FormularioEditarCartao?id=<c:out value='${cartao.id}' />">Editar</a>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;
	                    	<a href="excluirCartao?id=<c:out value='${cartao.id}' />">Deletar</a>                    	
	                    </td>
	                </tr>
	            </c:forEach>
	        </table>
	    </div>			
		</main>
		<footer>
		</footer>
	</body>
</html>	