<%-- 
    Document   : login
    Created on : Jan 16, 2017, 11:32:11 AM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Login</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <form action="login" method="POST">
            <fieldset>
                <legend>Login</legend>
                Username:<br/>
                <input type="text" size="30" name="username" value="${username}"/><br/>
                Password:<br/>
                <input type="password" size="30" name="password"/><br/>
                <input type="submit"/> ${validability}
            </fieldset>
        </form>
        <form action="register" method="GET">
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>
