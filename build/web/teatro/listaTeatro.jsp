<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoção de Ingressos</title>
        <script>
            function cidadeSelecionada(str) {
    if (typeof XMLHttpRequest !== "undefined") {
        xmlHttp = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    if (xmlHttp === null) {
        alert("Browser does not support XMLHTTP Request");
        return;
    }

    var url = "buscaPorCidade";
    url += "?cidade=" + str;
    xmlHttp.onreadystatechange = atualizaInformacoes;
    xmlHttp.open("GET", url, true);
    xmlHttp.send(null);
}
function atualizaInformacoes() {
    if (xmlHttp.readyState === 4 || xmlHttp.readyState === "complete") {
        document.getElementById("infos").innerHTML = xmlHttp.responseText;
    }
}
        </script>
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
        <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
        <form name='form'>
        <table cellpadding="5">
            <tr>
                    <td>Cidade</td>
                    <td>
                        <select id='cidade' name='cidade' onchange='cidadeSelecionada(this.value)'>
                            <option value="null">Todas</option>
                            <c:forEach items='${bean.cidades}' var='cidade'>
                                <option value='${cidade}'>${cidade}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>
        </table>
        </form>
            <caption><h2>Lista de Teatros</h2></caption>
        <table border="1" cellpadding="5">
            <tr>
                <th>Nome</th>
                <th>CNPJ</th>
                <th>Cidade</th>
                <th>E-mail</th>
            </tr>
            <tr id='infos'>
           
                
                    <td></td>
                        <td></td>
                    <td></td>
                    <td></td>                    
                   <!--<td>
                        <a href="edicao?id=<>">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="remocao?id=</>" 
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a>                    	
                    </td>-->
                </tr>
        </table>
    </div>	
</body>
</html>