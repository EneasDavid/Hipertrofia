<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Produto</title>
	</head>
	<body>	
		<main>
			<h2>		       	
			<a href="/Hipertrofia/">Cancelar</a>
		   </h2>		
		   <div align="center">
					<c:if test="${produto != null}">
						<form action="editarProduto" method="post">
			        </c:if>
			        <c:if test="${produto == null}">
						<form action="novoProduto" method="post">
			        </c:if>
				    <table border="1" cellpadding="5">
			            <caption>
			            	<h2>
			            		<c:if test="${produto != null}">
			            			Altere as informações do produto
			            		</c:if>
			            		<c:if test="${produto == null}">
			            			Adicione as informações do produto
			            		</c:if>
			            	</h2>
			            </caption>
			        		<c:if test="${produto != null}">
			        			<input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
			        		</c:if>            
			            <tr>
			                <th>Referencia: </th>
			                <td>
			                	<input type="text" name="referencia"  value="<c:out value='${produto.referencia}'/>"/>
			                </td>
			            </tr>
			            <tr>
			                <th>Tamanho: </th>
			                <td>
								<select id="tamanho" name="tamanho" value="<c:out value='${produto.tamanho}' />">
									<c:if test="${produto == null}">
			            				<option selected disabled value="">Selecione</options>
			            			</c:if>
			            			<option>PP</option>
									<option>P</option>
									<option>M</option>
			 						<option>G</option>
									<option>GG</option>
								</select>
			                </td>
			            </tr>
			            <tr>
			                <th>Preço: </th>
			                <td>
			                	<input type="number" step="0.010" name="preco" value="<c:out value='${produto.preco}' />" />
			                </td>
			            </tr>
			            <tr>
			            	<td colspan="2" align="center">
			            		<input type="submit" value=value=<c:if test="${produto == null}">Salvar</c:if><c:if test="${produto != null}">Editar</c:if>>
			            	</td>
			            </tr>
			        </table>
		        </form>
		    </div>
		</main>
		<footer>
		</footer>
	</body>
</html>	