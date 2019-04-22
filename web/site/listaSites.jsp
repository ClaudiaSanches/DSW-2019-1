<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Promoção de Ingressos</title>
        <script src="js/ajax.js"></script>
    </head>
    <body>
    <center>
        <h1>Sites</h1>
        <h2>
            <a href="formulario_site.jsp">Cadastrar Site</a>
            &nbsp;&nbsp;&nbsp;

        </h2>
    </center>
    <div align="center">
        <caption><h2>Lista de Sites</h2></caption>
        <table border="1" cellpadding="5">
            <tr>
                <th>URL</th>
                <th>Nome</th>
                <th>E-mail</th>
            </tr>
            <tr id="infos">
            <c:forEach var="site" items="${requestScope.listaSites}">
                
                    <td><c:out value="${site.url}" /></td>
                    <td><c:out value="${site.nome}" /></td>
                    <td><c:out value="${site.email}" /></td>                    
                   <!--<td>
                        <a href="edicao?id=<>">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="remocao?id=</>" 
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a>                    	
                    </td>-->
                </tr>
            </c:forEach>
        </table>
    </div>	
</body>
</html>