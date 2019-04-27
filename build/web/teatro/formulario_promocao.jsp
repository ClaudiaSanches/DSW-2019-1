<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Promoção de Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Cadastro de Promoção</h1>
        <h2>            
            <a href="listaPromocoes">Lista de promoções</a>          
        </h2>
    </center>
     <jsp:useBean id='bean' class='br.ufscar.dc.atv1.DynamicSelectBean'/>
    <div align="center">
        <c:if test="${promocao != null}">
            <form action="atualizacaoPromocao" method="post">
            </c:if>
            <c:if test="${promocao == null}">
                <form action="insercaoPromocao" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${promocao != null}">
                                Edição
                            </c:if>
                            <c:if test="${promocao == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${promocao != null}">
                        <input type="hidden" name="id" value="<c:out value='${promocao.id}' />" />
                    </c:if>            
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="nome" size="45" required
                                   value="<c:out value='${promocao.peça}' />"
                                   />
                        </td>
                    </tr>
                <tr>
                    <th>Teatro: </th>
                    <td>
                        <select id='teatro' name='cnpj'>
                            <c:forEach items='${bean.teatros}' var='teatro'>
                                <option value='${teatro.CNPJ}'>${teatro.nome}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>
                <tr>
                <th>SITE: </th>
                    <td>
                        <select id='site' name='url'>
                            <c:forEach items='${bean.sites}' var='site'>
                                <option value='${site.url}'>${site.nome}</option>
                            </c:forEach>
                        </select>   
                    </td>
                </tr>              
                        <th>Preço: </th>
                        <td>
                            <input type="text" name="preco" size="45" required
                                   value="<c:out value='${promocao.preço}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Data: </th>
                        <td>
                            <input type="date" name="data" size="45" required />
                        </td>
                    </tr>
                    <tr>
                        <th>Horário: </th>
                        <td>
                            <input type="time" name="hora" size="45" required/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Salva" />
                        </td>
                    </tr>
                </table>
            </form>
    </div>
    <c:if test="${!empty requestScope.mensagens}">
        <ul class="erro">
            <c:forEach items="${requestScope.mensagens}" var="mensagem">
                <li>${mensagem}</li>
                </c:forEach>
        </ul>
    </c:if>
</body>
</html>
