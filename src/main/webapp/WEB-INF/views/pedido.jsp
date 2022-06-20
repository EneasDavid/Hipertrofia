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
					<c:if test="${pedido == null}">
						<form action="editarPedido" method="post">
			        </c:if>
			        <c:if test="${pedido != null}">
						<form action="novoPedido" method="post">
			        </c:if>
				    <table border="1" cellpadding="5">
			            <caption>
			            	<h2>
			            		<c:if test="${pedido == null}">
			            			Altere as informações do produto
			            		</c:if>
			            		<c:if test="${pedido != null}">
			            			Adicione as informações do produto
			            		</c:if>
			            	</h2>
			            </caption>
			        		<c:if test="${pedido != null}">
			        			<p type="hidden" name="id" value="<c:out value='${pedido.id}'/>" /></p>
			        		</c:if>            
			            <tr>
			                <th>Referencia: </th>
			                <td>
			                	<p type="text" name="referencia"  value="<c:out value='${pedido.referencia}'/>"/><c:out value='${pedido.referencia}'/></p>
			                </td>
			            </tr>
			            <tr>
			                <th>Tamanho: </th>
			                <td>
								<p type="text" name="tamanho" value="<c:out value='${pedido.tamanho}'/>"><c:out value='${pedido.tamanho}'/></p>
			                </td>
			            </tr>
			            <tr>
			                <th>Preço: </th>
			                <td>
			                	<p type="number" name="preco" value="<c:out value='${pedido.preco}' />"><c:out value='${pedido.preco}' /></p>
			                </td>
			            </tr>
			            <tr>
			                <th>Forma de Pagamento: </th>
			                <td>
			                	<input type="number" name="numeroCartao" />
			                	<!-- ?php 
									$conn = mysqli_connect('localhost','root','123456','hipertrofia')or die(mysqli_error());
			                		mysql_set_charset($conn,'utf8'))or die ()mysqli_error($conn);
			                		$sql=mysqli_query($conn,"selecte numeroCartao from cartao where id>=1");
			                		while ($row = mysqli_fetch_assoc($sql)){
			                			echo "<option>".$row['numeroCartao']."</option>";
			                		}	
			                	?>	-->                
			                </td>
			            </tr>
			            <tr>
			            	<td colspan="2" align="center">
			            		<input type="submit" value="Salvar" />
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