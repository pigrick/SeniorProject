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
        <title>Create Blog Entry</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <form action="CreateBlogEntry" method="POST">
            
            Title: <input type="text" size="40" name="title"/><br/>
            <textarea name="detail" cols="50" rows="25"></textarea><br/>
            <input type="submit" value="Create Blog Entry"/>            
        </form>
    </body>
</html>
