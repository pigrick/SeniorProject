<%-- 
    Document   : blogentry
    Created on : Jan 17, 2017, 10:33:01 AM
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
        <title>Blog Entry List</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <p>${apple}</p>
        <c:forEach var="entry" items="${entries}">
            <fieldset>
                <a class="marleft" href="BlogEntryList/view?id=${entry.id}"><c:out value="${entry.title}" /></a>
                <p>Posted by: ${entry.created}</p>
                <p>Likes: ${entry.likes}</p>               
                
            </fieldset>
                
        </c:forEach>
        
        <form action="CreateBlogEntry" method="GET">
            <input type="submit" value="Create New"/>
        </form>
    </body>
</html>
