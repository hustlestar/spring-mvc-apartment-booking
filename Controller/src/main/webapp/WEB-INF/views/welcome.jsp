<%--
  Created by IntelliJ IDEA.
  User: Yauheni_Malashchytsk
  Date: 3/21/2017
  Time: 3:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="com.epam.contactbook.domain.User" scope="request"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Welcome <c:out value="${user.toString()}"/>!
    <a href="/contacts?login=${user.login}">My Contacts</a>
</body>
</html>
