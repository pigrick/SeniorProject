<%-- 
    Document   : searchuser
    Created on : Jan 27, 2017, 11:40:52 AM
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
        <title>Search user</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <h1>Search of user</h1>
        <p>Result for ${searchname}:</p>
        <c:forEach var="thisuser" items="${users}">
            <fieldset>                
                <legend><a href="/SeniorProject/user?name=${thisuser.username}">${thisuser.username}</a></legend>
                <img class="left" src="${thisuser.picurl}" height="200" width="200"/>
                <p>Email: ${thisuser.email}<br/><br/> Description: <br/> ${thisuser.description} </p>
                
            </fieldset>
        </c:forEach>
        <p>${noresult}</p>
    </body>
</html>
