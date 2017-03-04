<%-- 
    Document   : discussion.jsp
    Created on : Mar 3, 2017, 11:31:27 PM
    Author     : JL5372
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when  test="${sessionScope.username != null}" />
    <c:otherwise>
        <c:redirect url="login.jsp" />
    </c:otherwise>
</c:choose>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when  test="${sessionScope.allposts != null}">
                <br />
                <table align="center">
                    <tr>
                        <th>Post Author</th>
                        <th>Post Date and Time</th>
                        <th>Post Message</th>
                    </tr>
                <c:forEach items="${allposts}" var="post">
                    <tr>
                    <td><c:out value="${post[2]}" /></td>
                    <td><c:out value="${post[0]}" /></td>
                    <td><c:out value="${post[1]}" /></td>
                    </tr>
                </c:forEach>
                </table>
            </c:when>
        </c:choose>

    </body>
</html>
