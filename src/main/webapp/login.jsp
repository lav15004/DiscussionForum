<%-- 
    Document   : login
    Created on : Mar 1, 2017, 3:38:06 PM
    Author     : JL5372
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when  test="${sessionScope.username != null}">
        <c:redirect url="index.jsp" />
    </c:when>
</c:choose>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Login Page</title>
    </head>
    <body>
        <form method="POST" action="LoginServlet">
        <h1 style="text-align: center">Please enter your credentials below</h1>
        <br />
        <br />
        <table align="center">
            <tr>
                <td><label for="username">User Name:  </label></td>
                <td><input type="text" name="username" id ="username"></td>
            </tr>            
            <tr>
                <td><label for="password">Password:  </label></td>
                <td><input type="text" name="password" id ="password"></td>
            </tr>
            <tr>
                <td colspan="2"><button type="submit">Login</button></td>
            </tr>
        </table>
        </form>
    </body>
</html>
