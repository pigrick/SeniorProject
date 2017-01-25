<%-- 
    Document   : blogEntry
    Created on : Jan 21, 2017, 10:42:55 AM
    Author     : yeerick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="../main.css">
        <title>Blog Entry</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
            <fieldset>
                <legend>${entry.title}</legend>
            <p>${entry.detail}</p>
            <p>${entry.created}</p>
            <p>Likes: ${entry.likes}</p>
            <c:if test="${entry.viewerlikes == false && user != null}">
                <form action="likeblogentry" method="POST">
                    <input type="submit" value="Like"/>
                    <input type="hidden" name="beid" value="${entry.id}" />
                    <input type="hidden" name="likey" value="like"/>
                </form>
            </c:if>
            <c:if test="${entry.viewerlikes == true && user != null}">
                <form action="likeblogentry" method="POST">
                    <input type="submit" value="Unlike"/>
                    <input type="hidden" name="beid" value="${entry.id}" />
                    <input type="hidden" name="likey" value="unlike}"/>
                </form>
            </c:if>
            <c:if test="${user.id == entry.userid}" >
                <form action="../BlogEntryList/edit" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="Edit"/>
                </form>
                <form onsubmit="return confirm('Are you sure?');" action="../BlogEntryList/delete" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="Delete"/>
                </form>
            </c:if>
            <fieldset>
                <legend>Comment</legend>
                <c:if test="${user.id != null}" >
                    <form method="POST">
                        <textarea name="comment" cols="50" rows="5"></textarea><br/>
                        <input type="hidden" name="id" value="${entry.id}" />
                        <input type="submit" value="Submit"/>
                    </form>
                </c:if>
                <c:forEach var="comment" items="${comments}">
                    <fieldset>
                        <legend>${comment.username}</legend>
                        <p>${comment.comment}</p>
                        <p>${comment.created}</p>
                        <p>Likes: ${comment.likes}</p>
                        <c:if test="${comment.viewerlikes == false && user != null}">
                            <form action="likecomment" method="POST">
                                <input type="submit" value="Like"/>
                                <input type="hidden" name="beid" value="${entry.id}" />
                                <input type="hidden" name="commentid" value="${comment.id}"/>
                                <input type="hidden" name="likey" value="like"/>
                            </form>
                        </c:if>
                        <c:if test="${comment.viewerlikes == true && user != null}">
                            <form action="likecomment" method="POST">
                                <input type="submit" value="Unlike"/>
                                <input type="hidden" name="beid" value="${entry.id}" />
                                <input type="hidden" name="commentid" value="${comment.id}"/>
                                <input type="hidden" name="likey" value="unlike}"/>
                            </form>
                        </c:if>
                        <c:if test="${user.id == comment.userid}" >
                            <form onsubmit="return confirm('Are you sure?');" action="/SeniorProject/BlogEntryList/comment/delete" method="POST">
                                <input type="hidden" name="commentid" value="${comment.id}" />
                                <input type="hidden" name="beid" value="${entry.id}" />
                                <input type="submit" value="Delete"/>
                            </form>
                        </c:if>
                    </fieldset>
                </c:forEach>
            </fieldset>
        </fieldset>
    </body>
</html>
