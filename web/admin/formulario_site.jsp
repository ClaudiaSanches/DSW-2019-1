<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ADMIN')">
    <sec:authorize url="/insercaoSite" access="hasRole('ADMIN')">
        <sec:authorize method="post" access="hasRole('ADMIN')">
<html>
    <head>
        <title>Promoção de Ingressos</title>
    </head>
    <body>
    <center>
        <h1>Cadastro do site de vendas</h1>
        <h2>
            <a href="admin/listaSites">Lista de sites</a>

        </h2>
    </center>
    <div align="center">
        <c:if test="${site != null}">
            <form action="admin/atualizacaoSite" method="post">
            </c:if>
            <c:if test="${site == null}">
                <form action="admin/insercaoSite" method="post">
                </c:if>
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>
                            <c:if test="${site != null}">
                                Edição
                            </c:if>
                            <c:if test="${site == null}">
                                Cadastro
                            </c:if>
                        </h2>
                    </caption>
                    <c:if test="${site != null}">
                    <tr>
                        <th>URL: </th>
                        <td>
                            <input type="text" name="url" size="45" required
                                   readonly="true" value="<c:out value='${site.url}' />"
                                   />
                        </td>
                    </tr>
                    <tr>
                        <th>E-mail: </th>
                        <td>
                            <input type="text" name="email" size="45" required
                                   readonly="true" value="<c:out value='${site.email}' />"
                                   />
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${site == null}">
                    <tr>
                        <th>URL: </th>
                        <td>
                            <input type="text" name="url" size="45" required/>
                                   
                        </td>
                    </tr>
                    <tr>
                        <th>E-mail: </th>
                        <td>
                            <input type="text" name="email" size="45" required />
                        </td>
                    </tr>
                    </c:if>
                    
                    <tr>
                        <th>Nome: </th>
                        <td>
                            <input type="text" name="nome" size="45" required
                                   value="<c:out value='${site.nome}' />"
                                   />
                        </td>
                    </tr>
                   
                    <tr>
                        <th>Telefone: </th>
                        <td>
                            <input type="text" name="telefone" size="45" required
                                   value="<c:out value='${site.telefone}' />"
                                   />
                        </td>
                    </tr>  
                    <tr>
                        <th>Senha: </th>
                        <td>
                            <input type="password" name="senha" size="45" required
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
</sec:authorize>
</sec:authorize>
</sec:authorize>
