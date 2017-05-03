<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
<title>Airbnb!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Edit profile</h1>
            <h3>Enter first and last name</h3>
            <form method="post" action="/editProfile">
                <label>
                    First Name <input type="text" name="firstName"/>
                </label>
                <label>
                    Last Name <input type="text" name="lastName"/>
                </label>
                <button class="btn btn-success"  type="submit">Submit</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>