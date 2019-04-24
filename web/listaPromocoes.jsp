<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Promoção de Ingressos</title>
        <script>
            var xmlHttp;

            function teatroSelecionado(str) {
                if (typeof XMLHttpRequest !== "undefined") {
                    xmlHttp = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }

                if (xmlHttp === null) {
                    alert("Browser does not support XMLHTTP Request");
                    return;
                }

                var url = "buscaPorTeatro";
                url += "?teatro=" + str;
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
    <body onload="teatroSelecionado('null')">
    <center>
        <h1>Promoções</h1>
    </center>
    <div align="center">
        <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
        <table cellpadding="5">
            <tr>
                    <td>Teatros</td>
                    <td>
                        <select id='teatro' name='teatro' onchange='teatroSelecionado(this.value)'>
                            <option value="null">Todos</option>
                            <c:forEach items='${bean.teatros}' var='teatro'>                                
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
            <tbody id="infos">
					<td> </td>
                    <td> </td>
                    <td> </td>
                    <td> </td>   
                    <td> </td>        
            </tbody>
        </table>
    </div>	
</body>
</html>