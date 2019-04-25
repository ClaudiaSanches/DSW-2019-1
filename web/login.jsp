<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoções de ingressos</title>
    </head>
    <body>
        <h2>Bemvindo
            <%=request.getUserPrincipal().getName().toString()%>
        </h2>
        
        <sec:authorize access="hasRole('ADMIN')">
            <a href="gerenciaSite">Gerenciar Sites</a><br/>
            <a href="gerenciaTeatro">Gerenciar Teatros</a><br/>
            <a href="logout">Logout</a>
        </sec:authorize>
            
        <sec:authorize access="hasRole('TEATRO')">            
            <a href="formulario_promocao">Gerenciar Promoções</a><br/> 
            <a href="logout">Logout</a>
        </sec:authorize>
            
        <sec:authorize access="hasRole('SITE')">
            <a href="gerenciaPromocao">Gerenciar Promoções</a><br/>         
            <a href="logout">Logout</a>
        </sec:authorize>
        
    </body>
</html>
