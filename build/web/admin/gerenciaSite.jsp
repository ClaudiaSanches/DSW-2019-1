<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Promoção Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Gerenciamento de Sites</h1>
        <h2>
            <a href="admin/cadastroSite">Adicione novo Site</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Sites (acesso com login adm)</h2></caption>
            <tr>
                <th>NOME</th>
                <th>URL</th>
                <th>EMAIL</th>
                <th>TELEFONE</th>
            </tr>
            <c:forEach var="site" items="${requestScope.listaSites}">
                <tr>
                    <td><c:out value="${site.nome}" /></td>
                    <td><c:out value="${site.url}" /></td>
                    <td><c:out value="${site.email}" /></td>
                    <td><c:out value="${site.telefone}" /></td>
                    <td>
                        <a href="admin/edicaoSite?id=<c:out value='${site.url}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="admin/remocaoSite?id=<c:out value='${site.url}' />" 
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
