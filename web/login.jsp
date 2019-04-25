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
            <a href="listaSites">Gerenciar Sites</a><br/>
            <a href="listaTeatros">Gerenciar Teatros</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
        <sec:authorize access="hasRole('TEATRO')">            
            <a href="listaPromocoesTeatro">Gerenciar Promoções</a><br/> 
            <a href="logout">Logout</a>
        </sec:authorize>
        <sec:authorize access="hasRole('SITE')">
            <a href="listaPromocoesSite">Gerenciar Promoções</a><br/>         
            <a href="logout">Logout</a>
        </sec:authorize>
        
    </body>
</html>
