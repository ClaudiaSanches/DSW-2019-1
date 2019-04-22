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
        <h1>Promoções</h1>
        <h2>
            <!--<a href="cadastro">Cadastrar Promoções</a>-->
            &nbsp;&nbsp;&nbsp;
            <a href="lista">Lista de Promoções</a>

        </h2>
    </center>
    <div align="center">
        <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
        <table cellpadding="5">
            <tr>
                    <td>Teatros</td>
                    <td>
                        <select id='teatro' name='teatro' onchange='teatroSelecionado(this.value)'>
                            <option value='--'>--</option>
                            <c:forEach items='${bean.teatros}' var='teatro'>
                                <option value="null">Todos</option>
                                <option value='${teatro.CNPJ}'>${teatro.nome}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>
        </table>
            <caption><h2>Lista de Promoções</h2></caption>
        <table border="1" cellpadding="5">
            <tr>
                <th>Peça</th>
                <th>Preço</th>
                <th>Data</th>
                <th>Site</th>
                <th>Teatro</th>
            </tr>
            <tr id="infos">
            <c:forEach var="teatro" items="${requestScope.listaPromocoes}">
                
                    <td><c:out value="${promocao.peça}" /></td>
                    <td><c:out value="${promocao.preço}" /></td>
                    <td><c:out value="${promocao.diahorario}" /></td>
                    <td><c:out value="${promocao.site}" /></td>                    
                    <td><c:out value="${promocao.teatro}" /></td>                    
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