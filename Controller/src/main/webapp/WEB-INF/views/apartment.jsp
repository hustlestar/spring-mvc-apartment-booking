<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
<title>Airbnb!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">${apartment.title}</h1>
            <jsp:useBean id="apartment" class="com.hustlestar.airbnb.domain.Apartment" scope="request"/>
            ${apartment.id}<br>
            ${apartment.title}<br>
            ${apartment.address}<br>
            ${apartment.country}<br>
            ${apartment.city}<br>
            ${apartment.guests}
        </div>
    </div>
</div>

</body>
</html>