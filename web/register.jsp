<%-- 
    Document   : register
    Created on : Jan 18, 2017, 2:39:18 PM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Register</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <form action="register" method="POST">
            <fieldset>
                <legend>Register</legend>
                Username:<br/>
                <input type="text" size="30" name="username" value="${username}"/><br/>
                Password:<br/>
                <input type="password" size="30" name="password" value="${password}"/><br/>
                Email:<br/>
                <input type="text" size="30" name="email" value="${email}"/><br/>
                <input type="submit"/> ${validability}
            </fieldset>
        </form>
    </body>
</html>
