<%-- 
    Document   : friendlist
    Created on : Jan 29, 2017, 9:33:29 PM
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
        <title>Friend List</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
            <br/>
            <div id="friendbox">
                <h2>Friends</h2>
            <c:forEach var="friend" items="${friends}">
                <c:if test="${friend.viewerfriend == 'FRIEND' && user.id != friend.id}">
                    <fieldset>                
                        <legend><a href="/SeniorProject/user?name=${friend.username}">${friend.username}</a></legend>                    
                        <img class="left" src="${friend.picurl}" height="200" width="200" alt=""/>
                        <form method="POST">
                            <input type="hidden" name="currentname" value="${friend.username}"/>
                            <input type="hidden" name="currentid" value="${friend.id}"/>
                            <input type="hidden" name="status" value="UNFRIEND"/>
                            <input type="submit" value="Unfriend" />
                        </form>
                        <form action="/SeniorProject/message" method="GET">
                            <input type="hidden" name="user" value="${friend.username}"/>
                            <input type="submit" value="Message" />
                        </form>

                        <p>Email: ${friend.email}</p>
                    </fieldset>
                </c:if>
            </c:forEach>
        </div>
        <br/>
        <div id="requestingbox">
            <h2>Waiting for Response</h2>
            <c:forEach var="requester" items="${requesters}">

                <fieldset>                
                    <legend><a href="/SeniorProject/user?name=${requester.username}">${requester.username}</a></legend>                    
                    <img class="left" src="${requester.picurl}" height="200" width="200" alt=""/>
                    <form method="POST">
                        <input type="hidden" name="currentname" value="${requester.username}"/>
                        <input type="hidden" name="currentid" value="${requester.id}"/>
                        <input type="hidden" name="status" value="WAITING"/>
                        <input type="submit" value="Waiting for Response" />
                    </form>
                    <p>Email: ${requester.email}</p>
                </fieldset>

            </c:forEach>
        </div>
        <br/>
        <div id="acceptingbox">
            <h2>Responding to requests</h2>
            <c:forEach var="accepter" items="${accepters}">

                <fieldset>                
                    <legend><a href="/SeniorProject/user?name=${accepter.username}">${accepter.username}</a></legend>                    
                    <img class="left" src="${accepter.picurl}" height="200" width="200" alt=""/>
                    <form method="POST">
                        <input type="hidden" name="currentname" value="${accepter.username}"/>
                        <input type="hidden" name="currentid" value="${accepter.id}"/>
                        <input type="hidden" name="status" value="RESPONSE"/>
                        <input type="submit" value="Accept Friend Request" />
                    </form>
                    <p>Email: ${accepter.email}</p>
                </fieldset>


            </c:forEach>
        </div>
    </body>
</html>
