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
        <title>Blog Entry List</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <p>${apple}</p>
        <c:forEach var="entry" items="${entries}">
            <fieldset>
                <c:out value="${entry.title}" />
                <p>${entry.created}</p>
                <form action="BlogEntryList/view" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="View"/>
                </form>                
                
            </fieldset>
        </c:forEach>
        <form action="CreateBlogEntry" method="GET">
            <input type="submit" value="Create New"/>
        </form>
    </body>
</html>
