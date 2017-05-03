<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<form:form method="post" commandName="userDto" action="register" cssclass="">
    <fieldset>
        <form:label path="login">Login:</form:label>
        <form:input path="login"/>
        <form:label path="email">Email:</form:label>
        <form:input path="email"/>
        <form:label path="password">Password:</form:label>
        <form:input path="password"/>
        <form:label path="password2">Repeat password:</form:label>
        <form:input path="password2"/>
        <button class="btn btn-success" type="submit">Submit</button>
    </fieldset>
</form:form>


