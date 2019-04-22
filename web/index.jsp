<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Cadastro de sites de venda de ingressos</h1>
        <p>Escolha o que deseja fazer:</p>
        <a href="site/formulario_site.jsp">Sites</a><br/>
        <a href="teatro/formulario_teatro.jsp">Cadastrar um teatro</a><br/>
        <a href="promocao/formulario_promocao.jsp">Cadastrar uma promocao</a><br/>
    </body>
</html>
