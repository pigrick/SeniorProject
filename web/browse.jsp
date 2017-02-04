<%-- 
    Document   : allblogEntry
    Created on : Jan 23, 2017, 2:36:56 PM
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
        <title>Browse</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <h1>Browsing</h1>
        <c:forEach var="entry" items="${entries}">
            <fieldset>
                <legend><a class="noline" href="user?name=${entry.username}"><c:out value="${entry.username}" /></a></legend>
                <a class="marleft" href="BlogEntryList/view?id=${entry.id}"><c:out value="${entry.title}" /></a>
                <p>Posted by: ${entry.created}</p>
                <p>Likes: ${entry.likes}</p>            
            </fieldset>
        </c:forEach>
        <c:forEach var="page" items="${pages}">
            <c:if test="${currentpage == page}">
                <span>${page+1}</span>
                </c:if>
                <c:if test="${currentpage != page}">
                <span><a href="/SeniorProject/browse?page=${page}">${page+1}</a></span>
                </c:if>
            </c:forEach>
    </body>
</html>
