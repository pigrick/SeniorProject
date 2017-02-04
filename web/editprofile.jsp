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
        <script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="js/navigation.js" type="text/javascript"></script>
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
                Describe your self:<br/>
                <textarea name="description" cols="50" rows="10">${description}</textarea><br/>
                Pictures:<br/>
                <input type="text" size="30" name="picurl" value="${picurl}"/><br/>
                <br/>
                <input type="submit" value="Update"/> ${validability}
            </fieldset>
        </form>
    </body>
</html>
