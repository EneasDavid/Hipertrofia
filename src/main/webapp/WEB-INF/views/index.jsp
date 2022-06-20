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
				<a href="ListarCartao">liste todos os cartões</a>
		       	&nbsp;&nbsp;&nbsp;
		        <a href="novoFormularioProduto">Adicione um novo produto</a>
				&nbsp;&nbsp;&nbsp;
		        <a href="ListarPedidos">liste todos os pedidos</a>
		   </h2>
			<div align="center">
	        <table border="1" cellpadding="5">
	            <caption><h2>Lista de produtos</h2></caption>
	            <tr>
	                <th>ID</th>
	                <th>Referencia</th>
	                <th>tamanho</th>
	                <th>Preço</th>
	                <th>Ação</th>
	                
	            </tr>
	            <c:forEach var="produto" items="${estoque}">
	                <tr>
	                    <td><a href="novoFormularioPedido?id=<c:out value="${produto.id}" />"><c:out value="${produto.id}"/></a></td>
	                    <td><c:out value="${produto.referencia}" /></td>
	                    <td><c:out value="${produto.tamanho}" /></td>
	                    <td><c:out value="${produto.preco}" /></td>
	                    <td>
	                    	<a href="FormularioEditarProduto?id=<c:out value='${produto.id}' />">Editar</a>
	                    	&nbsp;&nbsp;&nbsp;&nbsp;
	                    	<a href="excluirProduto?id=<c:out value='${produto.id}' />">Deletar</a>                    	
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