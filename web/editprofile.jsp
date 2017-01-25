<%-- 
    Document   : editprofile
    Created on : Jan 24, 2017, 10:11:56 PM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="main.css">
        <title>Edit Profile</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <form method="POST">
            <fieldset>
                <legend>Edit Profile</legend>
                Username:<br/>
                <input type="text" size="30" name="username" value="${username}"/><br/>
                Old Password:<br/>
                <input type="password" size="30" name="oldpassword"/><br/>
                New Password:<br/>
                <input type="password" size="30" name="newpassword"/><br/>
                Email:<br/>
                <input type="text" size="30" name="email" value="${email}"/><br/>
                <input type="submit" value="Update"/> ${validability}
            </fieldset>
        </form>
    </body>
</html>
