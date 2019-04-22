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
            <a href="cadastro">Cadastrar Teatro</a>
            &nbsp;&nbsp;&nbsp;
            <a href="lista">Lista de teatros</a>

        </h2>
    </center>
    <div align="center">
        
           <jsp:useBean id='teatros' class='br.ufscar.dc.atv1.Lista'/>

            <caption><h2>Lista de Teatros</h2></caption>
        <table border="1" cellpadding="5">
            <tr>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Cidade</th>
                <th>E-mail</th>
                <th></th>
            </tr>
            
                <c:forEach items='${teatros.teatros}' var='teatro'>
              <tr id='infos'>  
                    <td>${teatro.nome}</td>
                        <td>${teatro.CNPJ}</td>
                    <td>${teatro.cidade}</td>
                    <td>${teatro.email}</td>                    
                   <td>
                        <a href="edicaoTeatro?id=<>">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="remocaoTeatro?id=</>" 
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a>                    	
                    </td></tr>
           </c:forEach>
                
        </table>
    </div>	
</body>
</html>