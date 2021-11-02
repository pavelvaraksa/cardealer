<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="user" type="java.util.List"--%>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="list.users.list" var="users_list_page"/>
<fmt:message key="menu.title.users.list" var="users_list"/>
<fmt:message key="id" var="id"/>
<fmt:message key="user.firstname" var="firstname"/>
<fmt:message key="user.lastname" var="lastname"/>
<fmt:message key="user.birth_date" var="birth_date"/>
<fmt:message key="user.phone_number" var="phone_number"/>
<fmt:message key="user.login" var="login"/>
<fmt:message key="user.email" var="email"/>
<fmt:message key="user.role" var="role"/>
<fmt:message key="user.is.blocked" var="is_blocked"/>
<fmt:message key="info.created" var="created"/>
<fmt:message key="info.changed" var="changed"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="role.admin" var="admin"/>
<fmt:message key="role.user" var="user"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
    </div>
</nav>

<body style="background-color:antiquewhite"></body>
<div align="center">
    <body>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>${id}</th>
            <th>${firstname}</th>
            <th>${lastname}</th>
            <th>${birth_date}</th>
            <th>${phone_number}</th>
            <th>${login}</th>
            <th>${email}</th>
            <th>${role}</th>
            <th>${is_blocked}</th>
            <th>${created}</th>
            <th>${changed}</th>
        </tr>
        </thead>

        <c:forEach var="user" items="${user}">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.birthDate}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.login}</td>
                <td>${user.email}</td>
                <td>${user.role}</td>
                <td>${user.blocked}</td>
                <td>${user.created}</td>
                <td>${user.changed}</td>
            </tr>
        </c:forEach>
    </table>
    </body>
    <label>
        <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/user-menu"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
