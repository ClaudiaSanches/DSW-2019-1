<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promoção de Ingressos</title>
        <script>
            var xmlHttp;

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
    <body onload="cidadeSelecionada('null')">
    <center>
        <h1>Teatros</h1>
    </center>
    <div align="center">
        <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
        <form name='form'>
            <table cellpadding="5">
            <tr>
                    <td>Cidade</td>
                    <td>
                        <select id='cidade' name='cidade' onchange='cidadeSelecionada(this.value)'>
                            <option value='null'>Todas</option>
                            <c:forEach items='${bean.cidades}' var='cidade'>
                                <option value='${cidade}'>${cidade}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>
            </table>
        
            <caption><h2>Lista de Teatros</h2></caption>
            <table border="1" cellpadding="5">
                <tr>
                    <th>Nome</th>
                    <th>CNPJ</th>
                    <th>Cidade</th>
                    <th>E-mail</th>
                </tr>
                <tbody id="infos">
                <tr>                
                    <td> </td>
                    <td> </td>
                    <td> </td>
                    <td> </td>                 
                </tr>   
            </table>
            </form>
    </div>	
</body>
</html>