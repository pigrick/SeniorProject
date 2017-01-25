<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navigation">
    <ul>
        <li class="left"><a href="/SeniorProject/MainPage.jsp">Home</a></li>
        <li class="left"><a href="/SeniorProject/BlogEntryList">Blog Entry</a></li>
        <li class="left"><a href="/SeniorProject/browse">Browse</a></li>
        <c:if test="${user.id == null}">
            <li class="right"><a href="/SeniorProject/login">Login</a></li>
            <li class="right"><a href="/SeniorProject/register">Register</a></li>
        </c:if>
        <c:if test="${user.id != null}">
        <li class="dropdown right"><a href="#" class="dropbtn">Profile</a>
            <div class="dropdown-content">
                <a href="/SeniorProject/editProfile">Edit Profile</a>
                <a href="/SeniorProject/logout">Logout</a>
            </div>
        </li>
        </c:if>
        
    </ul>
</nav>