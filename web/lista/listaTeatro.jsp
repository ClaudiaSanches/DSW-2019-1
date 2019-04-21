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
        <h1>Gerenciamento de Teatros</h1>
        <h2>
            <a href="cadastro">Cadastrar Teatro</a>
            &nbsp;&nbsp;&nbsp;
            <a href="lista">Lista de teatros</a>

        </h2>
    </center>
    <div align="center">
        <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
        <table border="1" cellpadding="5">
            <tr>
                    <td>Cidade</td>
                    <td>
                        <select id = 'cidade' name='cidade' onchange='cidadeSelecionada(this.value)'>
                            <option value='--'>--</option>
                            <c:forEach items='${bean.teatro}' var='estado'>
                                <option value='${teatro.cidade}'>${teatro.cidade}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>
            <caption><h2>Lista de Teatros</h2></caption>
            <tr>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Cidade</th>
                <th>E-mail</th>
            </tr>
            <c:forEach var="teatro" items="${requestScope.listaTeatros}">
                <tr>
                    <td><c:out value="${teatro.nome}" /></td>
                    <td><c:out value="${teatro.cnpj}" /></td>
                    <td><c:out value="${teatro.cidade}" /></td>
                    <td><c:out value="${teatro.email}" /></td>                    
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