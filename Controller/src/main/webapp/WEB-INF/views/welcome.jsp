<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
    <title>Welcome, ${sessionScope.get('user').login}!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Your profile</h1>
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="https://cdn2.iconfinder.com/data/icons/metro-ui-dock/512/User_No-Frame.png"
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>${sessionScope.get('user').login}</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-9 placeholder">
                    <h2>Welcome, ${sessionScope.get('user').login}!</h2>
                    <p>Email: ${sessionScope.get('user').email}</p>
                    <p>First name: ${sessionScope.get('user').firstName}</p>
                    <p>Last name: ${sessionScope.get('user').lastName}</p>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
