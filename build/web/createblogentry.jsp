<%-- 
    Document   : createblogentry
    Created on : Jan 17, 2017, 11:13:50 AM
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
        <title>Create Blog Entry</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <fieldset>
            <legend>Create a blog entry</legend>
        <form action="CreateBlogEntry" method="POST">
            
            Title: <input type="text" size="40" name="title"/><br/><br/>
            <textarea name="detail" cols="50" rows="25"></textarea><br/>
            <input type="submit" value="Create Blog Entry"/>            
        </form>
        </fieldset>
    </body>
</html>
