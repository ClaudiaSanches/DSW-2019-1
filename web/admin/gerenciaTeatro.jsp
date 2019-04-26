<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoção de Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Teatros</h1>
        <h2>
            <a href="admin/cadastroTeatro">Cadastrar Teatro</a>
            &nbsp;&nbsp;&nbsp;
            <a href="../listaTodosTeatros">Lista de todos os Teatros</a>

        </h2>
    </center>
    <div align="center">
        
            <caption><h2>Lista de Teatros</h2></caption>
        <table border="1" cellpadding="5">
            <tr>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Cidade</th>
                <th>E-mail</th>
                <th></th>
            </tr>
            <c:forEach var="teatro" items="${requestScope.listaTeatros}">
              <tr>  
                    <td><c:out value="${teatro.nome}" /></td>
                        <td><c:out value="${teatro.CNPJ}"/></td>
                    <td><c:out value="${teatro.cidade}"/></td>
                    <td><c:out value="${teatro.email}"/></td>                    
                   <td>
                        <a href="admin/edicaoTeatro?id=<c:out value='${teatro.CNPJ}' />">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="admin/remocaoTeatro?id=<c:out value='${teatro.CNPJ}' />" 
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