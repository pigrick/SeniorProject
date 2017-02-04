<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navigation">
    <ul>
        <li class="left"><a href="/SeniorProject/MainPage.jsp">Home</a></li>
        <li class="left"><a href="/SeniorProject/BlogEntryList">Blog Entry</a></li>
        <li class="dropdown left"><a href="#" class="dropbtn">Browse</a>
            <div class="dropdown-content">
                <a href="/SeniorProject/browse?page=0">All</a>
                <a href="/SeniorProject/browsefriend?page=0">Friends</a>
            </div>
        <li class="left"><a href="/SeniorProject/friendlist">Friend List</a></li>
        <li class="left">
            <form action="/SeniorProject/searchUser" method="GET">
                <label>
                    <input type="text" name="name" placeholder="Search for username" size="25"/>
                </label>
            </form>
        </li>
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
            <li class="right"><a href="/SeniorProject/CreateBlogEntry">New Entry</a></li>
            
        <li class="dropdown right"><a href="#" class="dropbtn">Messages: <span id="messageno">0</span></a>
            <div id="messages" class="dropdown-content">

            </div>
        </li>
        <li class="dropdown right"><a href="#" class="dropbtn">Notification: <span id="notificationno">0</span></a>
            <div id="notification" class="dropdown-content">

            </div>
        </li>
        </c:if>

    </ul>
</nav>