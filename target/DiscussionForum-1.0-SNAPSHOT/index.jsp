<%-- 
    Document   : index.jsp
    Created on : Mar 3, 2017, 11:28:49 PM
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
        <form method="POST" action="CreatePost">
            <h1>Discussion Post Form</h1>
            <br />
            <label for="newpost">New discussion post: </label>
            <br />
            <textarea  name="newpost" id="newpost" rows="5" cols="100"></textarea>
            <br />
            <br />
            <button type="submit" name="submit">POST</button>
            <br />
            <br />
            <a href="LoadPosts">Go to existing posts.</a>
        </form>
    </body>
</html>
