<%-- 
    Document   : message
    Created on : Feb 2, 2017, 11:15:33 AM
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
        <script src="js/message.js" type="text/javascript"></script>
        <title>Message</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
        <h1>Message Box for ${target}</h1>
        <div class="chatbox">
            <input id="sendname" type="hidden" value="${user.username}"/>
            <input id="targetname" type="hidden" value="${target}"/>
            <input class="currentid" type="hidden" name="currentid" value="0"/> 
            <c:forEach var="message" items="${messages}">
                <input class="currentid" type="hidden" name="currentid" value="${message.id}"/>            
                
                <c:if test="${message.sendid == user.id}">
                    <div class="textright"><span class="messagename">${message.sendname}</span><br/>${message.details}</div>
                <br/>    
                </c:if>
                    <c:if test="${message.receiveid == user.id}">
                    <div class="textleft"><span class="messagename">${message.sendname}</span><br/>${message.details}</div>
                    <br/>
                    </c:if>
                </c:forEach>
        </div>
        <br/>
        <textarea id="sendmessage" name="sendmessage" cols="53" rows="5"></textarea>
    </body>
</html>
