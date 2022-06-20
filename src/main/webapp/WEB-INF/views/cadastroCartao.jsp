<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastrar Cartão</title>
	</head>
	<body>	
		<main>
			<h2>		       	
			<a href="/Hipertrofia/">Cancelar</a>
		   </h2>		
		   <div align="center">
			<c:if test="${cartao != null}">
				<form action="editarCartao" method="post">
	        </c:if>
	        <c:if test="${cartao == null}">
				<form action="novoCartao" method="post">
	        </c:if>
		    <table border="1" cellpadding="5">
            <caption>
            	<h2>
            		<c:if test="${cartao != null}">
            			Altere as informações do metodo de pagamento
            		</c:if>
            		<c:if test="${cartao == null}">
            			Adicione as informações do metodo de pagamento
            		</c:if>
            	</h2>
            </caption>
        		<c:if test="${cartao != null}">
        			<input type="hidden" name="id" value="<c:out value='${cartao.id}' />" />
        		</c:if>            
            <tr>
                <th>Número do cartão: </th>
                <td>
                	<input type="number" name="numeroCartao"  value="<c:out value='${cartao.numeroCartao}'/>"/>
                </td>
            </tr>
            <tr>
                <th>CVC: </th>
                <td>
                	<input type="number" name="cvc" value="<c:out value='${cartao.cvc}' />"/>
                </td>
            </tr>
            <tr>
                <th>Validade: </th>
                <td>
                	<input type="number" name="validade" value="<c:out value='${cartao.dataVencimento}' />" />
                </td>
            </tr>
            <tr>
            	<td colspan="2" align="center">
            		<input type="submit" value=<c:if test="${cartao == null}">Salvar</c:if><c:if test="${cartao != null}">Editar</c:if>>
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