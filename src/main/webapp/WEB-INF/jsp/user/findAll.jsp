<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.Role" %>
<%@ page import="by.varaksa.cardealer.model.entity.User" %>

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
<fmt:message key="button.delete" var="delete"/>
<fmt:message key="role.admin" var="admin"/>
<fmt:message key="role.user" var="user"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${users_list_page}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
        <form>
            <label for="language"></label>
            <select id="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>english</option>
                <option value="de" ${language == 'de' ? 'selected' : ''}>deutsche</option>
            </select>
        </form>
    </div>
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h2><u>${users_list}</u></h2>
    <form action=<%=request.getContextPath()%>"/user/find-all" method="get">
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

            <%--@elvariable id="userList" type="java.util.List"--%>
            <c:forEach var="user" items="${userList}">
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

                    <td>
                        <form action=<%=request.getContextPath()%>"update-user" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user.id}">
                            <input type="hidden" class="form-control" name="firstName" value="${user.firstName}">
                            <input type="hidden" class="form-control" name="lastName" value="${user.lastName}">
                            <input type="hidden" class="form-control" name="birthDate" value="${user.birthDate}">
                            <input type="hidden" class="form-control" name="phoneNumber" value="${user.phoneNumber}">
                            <input type="hidden" class="form-control" name="role" value="${user.role}">
                            <input type="hidden" class="form-control" name="blocked" value="${user.blocked}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>

                    <td>
                        <form action=<%=request.getContextPath()%>"delete-user" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value=${delete}>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
<%--        <c:choose>--%>
<%--            <c:when test="${role == Role.ADMIN}">${admin}</c:when>--%>
<%--            <c:when test="${role == Role.USER}">${user}</c:when>--%>
<%--        </c:choose>--%>
        </body>
        <label>
            <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/admin-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
