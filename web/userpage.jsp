<%-- 
    Document   : userpage
    Created on : Jan 26, 2017, 11:23:36 PM
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
        <title>${target.username}'s page</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include> 
        <h1>${target.username}'s page</h1>
        <img class="left" src='${target.picurl}' alt="${target.username}'s picture" height="200" width="200"/>
        <c:if test="${target.viewerfriend == 'FRIEND' && user.id != target.id}">
            <form action="friend" method="POST">
                <input type="hidden" name="currentname" value="${target.username}"/>
                <input type="hidden" name="currentid" value="${target.id}"/>
                <input type="hidden" name="status" value="UNFRIEND"/>
                <input type="submit" value="Already Friend - Unfriend" />
            </form>
        </c:if>
        <c:if test="${target.viewerfriend == 'NOTFRIEND' && user.id != target.id}">
            <form action="friend" method="POST">
                <input type="hidden" name="currentname" value="${target.username}"/>
                <input type="hidden" name="currentid" value="${target.id}"/>
                <input type="hidden" name="status" value="REQUEST"/>
                <input type="submit" value="Request Friend" />
            </form>
        </c:if>
        <c:if test="${target.viewerfriend == 'ACCEPTING' && user.id != target.id}">
            <form action="friend" method="POST">
                <input type="hidden" name="currentname" value="${target.username}"/>
                <input type="hidden" name="currentid" value="${target.id}"/>
                <input type="hidden" name="status" value="RESPONSE"/>
                <input type="submit" value="Accept Friend Request" />
            </form>
        </c:if>
        <c:if test="${target.viewerfriend == 'REQUESTING' && user.id != target.id}">
            <form action="friend" method="POST">
                <input type="hidden" name="currentname" value="${target.username}"/>
                <input type="hidden" name="currentid" value="${target.id}"/>
                <input type="hidden" name="status" value="WAITING"/>
                <input type="submit" value="Waiting for Response" />
            </form>
        </c:if>
        <c:if test="${user.id != target.id}">            
                <form action="/SeniorProject/message" method="GET">
                    <input type="hidden" name="user" value="${target.username}"/>
                    <input type="submit" value="Message" />
                </form>
            
        </c:if>
        <p>Email: ${target.email}<br/><br/> Description: <br/> ${target.description} </p>
        <div class="clear entrybox">

            <h2>Entries</h2>
            <c:forEach var="entry" items="${entries}">
                <fieldset>
                    <legend>${entry.title}</legend>
                    <p>${entry.created}</p>
                    <p>Likes: ${entry.likes}</p>
                    <form action="BlogEntryList/view" method="GET">
                        <input type="hidden" name="id" value="${entry.id}" />
                        <input type="submit" value="View"/>
                    </form>
                </fieldset>
            </c:forEach>

        </div>
    </body>
</html>
