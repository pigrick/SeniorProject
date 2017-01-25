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
        <title>Browse</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <c:forEach var="entry" items="${entries}">
            <fieldset>
                <legend>${entry.username}</legend>
                <c:out value="${entry.title}" />
                <p>${entry.created}</p>
                <form action="/SeniorProject/BlogEntryList/view" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="View"/>
                </form>                
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
