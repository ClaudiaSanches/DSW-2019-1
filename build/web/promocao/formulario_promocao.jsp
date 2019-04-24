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
                                   value="<c:out value='${promocao.nome}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>URL: </th>
                        <td>
                            <input type="text" name="url" size="45" required
                                   value="<c:out value='${promocao.url}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>CNPJ: </th>
                        <td>
                            <input type="text" name="cnpj" size="45" required
                                   value="<c:out value='${promocao.cnpj}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Preço: </th>
                        <td>
                            <input type="text" name="preco" size="45" required
                                   value="<c:out value='${promocao.preco}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Data e horário: </th>
                        <td>
                            <input type="text" name="email" size="45" required
                                   value="<c:out value='${promocao.email}' />"
                                   />
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
