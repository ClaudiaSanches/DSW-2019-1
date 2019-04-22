<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoções</title>
    </head>
    <body>
        <h1>Cadastro de sites de venda de ingressos</h1>
        <hr>
        <c:if test="${!empty mensagem}">
            ${mensagem}
            <hr>
        </c:if>
        <p>Escolha o que deseja fazer:</p>
        <a href="site/listaSites.jsp">Sites</a><br/>
        <a href="teatro/listaTeatro.jsp">Teatros</a><br/>
        <a href="promocao/listaPromocoes.jsp">Promoções</a><br/>
    </body>
</html>