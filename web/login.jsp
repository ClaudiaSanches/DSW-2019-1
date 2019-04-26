<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoções</title>
    </head>
    <body>
        <hr>
        <c:if test="${!empty mensagem}">
            ${mensagem}
        <hr>
        </c:if>
        <h1>Cadastro de sites de venda de ingressos 
         <%=request.getUserPrincipal().getName().toString()%></h1>
        <p>Escolha o que deseja fazer:</p>
        
        <sec:authorize access="hasRole('ADMIN')">
            <a href="admin/listaSites">Gerenciar Sites</a><br/>
            <a href="admin/listaTeatros">Gerenciar Teatros</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
        <sec:authorize access="hasRole('TEATRO')">            
            <a href="teatro/listaPromocoesTeatro">Gerenciar Promoções</a><br/> 
            <a href="logout">Logout</a>
        </sec:authorize>
        <sec:authorize access="hasRole('SITE')">
            <a href="site/listaPromocoesSite">Promoções do Site</a><br/>         
            <a href="logout">Logout</a>
        </sec:authorize>
        
    </body>
</html>
