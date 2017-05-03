<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
<title>Airbnb!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Login page</h1>
            <h3>Enter login and password</h3>
            <c:import url="template/login.jsp"/>
            <a href="/restorePassword">Forgot password? Restore!</a>
        </div>
    </div>
</div>

</body>
</html>