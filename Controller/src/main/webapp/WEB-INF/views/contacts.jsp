<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="2px">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone number</th>
        <th>City</th>
        <th>Email</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="contact" items="${requestScope.contacts}">
        <tr>
            <td>${contact.firstName}</td>
            <td>${contact.lastName}</td>
            <td>${contact.phoneNumber}</td>
            <td>${contact.city}</td>
            <td>${contact.email}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>