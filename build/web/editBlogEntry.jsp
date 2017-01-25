<%-- 
    Document   : editBlogEntry
    Created on : Jan 20, 2017, 9:44:35 PM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../main.css">
        <title>Edit Blog Entry</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <form action="/SeniorProject/BlogEntryList/edit" method="POST">
            <fieldset>
                <legend>Edit</legend>
                Title: <input type="text" size="40" name="title" value="${entry.title}"/><br/>
            <textarea name="detail" cols="50" rows="25">${entry.detail}</textarea><br/>
            <input type="hidden" name="id" value="${entry.id}" />
            <input type="submit" value="Submit"/>   
            </fieldset>
        </form>
    </body>
</html>
