<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
<title>Airbnb!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Password update</h1>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
            <c:if test="${not empty sessionScope.newPassword}">
                Your new password is ${sessionScope.newPassword}
                ${sessionScope.remove('newPassword')}
            </c:if>
            <c:choose>
                <c:when test="${sessionScope.result}">
                    You successfully changed your password.
                    ${sessionScope.remove('result')}
                </c:when>
                <c:otherwise>
                    You did not change your password.
                    ${sessionScope.remove('result')}
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

</body>
</html>