<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>~Promoção Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Promoções</h1>
        <h2>
            <a href="site/cadastroPromocao">Adicione Nova Promoção</a>
            &nbsp;&nbsp;&nbsp;
            <a href="site/listaTodasPromocoes">Lista de Todas as Promoções</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Promoções (do teatro logado)</h2></caption>
            <tr>
                <th>ID</th>
                <th>PEÇA</th>
                <th>PREÇO</th>
                <th>DATA</th>
                <th>SITE</th>
            </tr>
            <c:forEach var="promocao" items="${requestScope.listaPromocoes}">
                <tr>
                    <td><c:out value="${promocao.id}" /></td>
                    <td><c:out value="${promocao.peça}" /></td>
                    <td><c:out value="${promocao.preço}" /></td>
                    <td><c:out value="${promocao.diahorario}" /></td>
                    <td><c:out value="${promocao.site}" /></td>
                    <td>
                        <a href="site/edicaoPromocao?id=<c:out value='${promocao.id}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="site/remocaoPromocao?id=<c:out value='${promocao.id}' />" 
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a>                    	
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>