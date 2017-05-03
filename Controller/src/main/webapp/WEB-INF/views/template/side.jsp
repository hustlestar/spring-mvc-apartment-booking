<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="col-sm-3 col-md-2 sidebar">
    <c:if test="${not empty sessionScope.get('user')}">
        <ul class="nav nav-sidebar">
            <li class="active"><a href="/welcome">My profile <span class="sr-only">(current)</span></a></li>
            <li><a href="/editProfile">Edit profile</a></li>
            <li><a href="/changePassword">Change password</a></li>
        </ul>
    </c:if>
    <ul class="nav nav-sidebar">
        <li><a href="/apartments">View apartments <span class="sr-only">(current)</span></a></li>
    </ul>
</div>