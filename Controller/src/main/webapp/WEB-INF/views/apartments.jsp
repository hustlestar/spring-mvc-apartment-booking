<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="template/head.jsp"/>
<title>Airbnb!</title>
<c:import url="template/nav.jsp"/>
<div class="container-fluid">
    <div class="row">
        <c:import url="template/side.jsp"/>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Apartments for you</h1>
            <form action="/apartments/intercept/">
                <label>
                    Title
                    <input type="text" name="title"/>
                </label>
                <label>
                    Country
                    <select name="country">
                        <option disabled selected value> Select country</option>
                        <c:forEach var="country" items="${requestScope.countries}">
                            <option value="${country.id}">${country.title}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    City
                    <select name="city">
                        <option disabled selected value> Select city</option>
                        <c:forEach var="city" items="${requestScope.cities}">
                            <option value="${city.id}">${city.title}</option>
                        </c:forEach>
                    </select>
                </label>
                <label>
                    Number of guests
                    <input type="number" name="guests"/>
                </label>
                <button class="btn btn-success">Submit</button>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Country</th>
                    <th>City</th>
                    <th>N of guests</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="apartment" items="${requestScope.apartments}">
                    <tr>
                        <td>${apartment.id}</td>
                        <td><a href="apartments/${apartment.id}">${apartment.title}</a></td>
                        <td>${apartment.country}</td>
                        <td>${apartment.city}</td>
                        <td>${apartment.guests}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>