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
        <script src="../js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="../js/navigation.js" type="text/javascript"></script>
        <script src="../js/blogentry.js" type="text/javascript"></script>
        <title>Blog Entry</title>
    </head>
    <body>
        <jsp:include page="navigation.jsp"></jsp:include>
            <fieldset>
                <legend>${entry.title}</legend>
            <p>${entry.detail}</p>
            <p>Posted by: ${entry.created}</p>
            <p>Likes: <span id="likesnumber">${entry.likes}</span></p>
            <!--
            <c:if test="${entry.viewerlikes == false && user != null}">
                <button id="likebutton">like</button>
                
                <form action="likeblogentry" method="POST">
                    <input type="submit" value="Like"/>
                    <input type="hidden" name="beid" value="${entry.id}" />
                    <input type="hidden" name="likey" value="like"/>
                </form>
                
            </c:if>
            -->
            <input id="likestatus" type="hidden" value="${entry.viewerlikes}"/>
            <input id="beid" type="hidden" name="beid" value="${entry.id}" />
            <button id="likebutton"></button>
            <!--
            <c:if test="${entry.viewerlikes == true && user != null}">
                <button id="likebutton">like</button>
                <form action="likeblogentry" method="POST">
                    <input type="submit" value="Unlike"/>
                    <input type="hidden" name="beid" value="${entry.id}" />
                    <input type="hidden" name="likey" value="unlike}"/>
                </form>
            </c:if>
            -->
            <c:if test="${user.id == entry.userid}" >
                <form class="inline" action="../BlogEntryList/edit" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="Edit"/>
                </form>
                <form class="inline" onsubmit="return confirm('Are you sure?');" action="../BlogEntryList/delete" method="GET">
                    <input type="hidden" name="id" value="${entry.id}" />
                    <input type="submit" value="Delete"/>
                </form>
                <br/>
                <br/>
            </c:if>
            <div class="commentbox">

                <h2 class="marleft">Comment</h2>
                <c:if test="${user.id != null}" >
                    
                    <form class="marleft" method="POST">
                        <textarea name="comment" cols="150" rows="5"></textarea><br/>
                        <input type="hidden" name="id" value="${entry.id}" />
                        <input type="submit" value="Submit"/>
                    </form>
                    <br/>
                </c:if>
                <c:forEach var="comment" items="${comments}">
                    <fieldset class="commentclass">
                        <legend>${comment.username}</legend>
                        <p>${comment.comment}</p>
                        <p>Posted by: ${comment.created}</p>
                        <p>Likes: <span class="likescommentnumber">${comment.likes}</span></p>
                        <input name="commentid" type="hidden" value="${comment.id}"/>
                        <input name="likecommentstatus" type="hidden" value="${comment.viewerlikes}"/>
                        <button id="likecommentbutton">hi</button>
                        <!--
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
                        -->
                        <c:if test="${user.id == comment.userid}" >
                            <form class="inline" onsubmit="return confirm('Are you sure?');" action="/SeniorProject/BlogEntryList/comment/delete" method="POST">
                                <input type="hidden" name="commentid" value="${comment.id}" />
                                <input type="hidden" name="beid" value="${entry.id}" />
                                <input type="submit" value="Delete"/>
                            </form>
                        </c:if>
                    </fieldset>
                </c:forEach>

            </div>
        </fieldset>
    </body>
</html>
