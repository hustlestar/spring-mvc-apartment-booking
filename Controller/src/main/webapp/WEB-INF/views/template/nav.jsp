<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">AIRBNB</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.get('user') == null}">
                    <li class="sign-up">
                        <a href="/register">
                            <span class="glyphicon glyphicon-user"></span>
                            Register</a>
                    </li>
                    <li><a href="/login">
                        <span class="glyphicon glyphicon-log-in"></span>
                        Sign in</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.get('user') != null}">
                    <li class="sign-up">
                        <a href="/welcome">
                            <span class="glyphicon glyphicon-user"></span> ${sessionScope.get('user').login}</a>
                    </li>
                    <li><a href="/logout">
                        <span class="glyphicon glyphicon-log-out"></span> Sign out</a>
                    </li>
                </c:if>
            </ul>
            <form class="navbar-form navbar-right" action="/apartments" method="post">
                <input name="title" class="form-control" type="text" placeholder="Apartment title...">
                <button class="btn btn-success" type="submit">Submit</button>
            </form>
        </div>
    </div>
</nav>