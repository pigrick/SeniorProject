<%-- 
    Document   : MainPage
    Created on : Jan 16, 2017, 11:28:41 AM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="js/navigation.js" type="text/javascript"></script>
        <title>Pigrick's Amazing Blogger UTOPIA</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <c:if test="${user != null}">
            <h1> Welcome, ${user.username} </h1>
            <img src='${user.picurl}' alt="${user.username}'s picture" height="200" width="200"/>
        </c:if>
        <c:if test="${user == null}">
            <h1>WELCOME to Pigrick's Amazing Blogger UTOPIA!</h1>
            <p>Please login or register to make use this awesome web application!</p>
        </c:if>
    </body>
</html>
