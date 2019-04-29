<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Promoção de Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Cadastro de Teatro</h1>
        <h2>
            <a href="admin/listaTeatros">Lista de teatros</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${teatro != null}">
            <form action="admin/atualizacaoTeatro" method="post">
            </c:if>
            <c:if test="${teatro == null}">
                <form action="admin/insercaoTeatro" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${teatro != null}">
                                Edição
                            </c:if>
                            <c:if test="${teatro == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${teatro != null}">
                        <input type="hidden" name="cnpj" value="<c:out value='${teatro.cnpj}' />" />
                                
                    <tr>
                        <th>E-mail: </th>
                        <td>
                            <input type="text" name="email" size="45" required
                                   readonly="true" value="<c:out value='${teatro.email}' />"
                                   />
                        </td>
                    </tr>
                    </c:if>                    
                    <c:if test="${teatro == null}">
                    <tr>
                        <th>E-mail: </th>
                        <td>
                            <input type="text" name="email" size="45" required/>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="nome" size="45" required
                                   value="<c:out value='${teatro.nome}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>Cidade: </th>
                        <td>
                            <input type="text" name="cidade" size="45" required
                                   value="<c:out value='${teatro.cidade}' />"
                                   />
                        </td>
                    </tr>
                    
                    <tr>
                        <th>Senha: </th>
                        <td>
                            <input type="password" name="password" size="45" required 
                                   value="<c:out value='${senha}' />"
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
