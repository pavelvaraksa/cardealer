<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_bodies_list" var="bodies_list_page"/>
<fmt:message key="Bodies_list" var="bodies_list"/>
<fmt:message key="Add" var="add"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Delete" var="delete"/>
<fmt:message key="Id" var="id"/>
<fmt:message key="Color" var="color"/>
<fmt:message key="Body_type" var="body_type"/>
<fmt:message key="Car_id" var="car_id"/>
<fmt:message key="Created" var="created"/>
<fmt:message key="Changed" var="changed"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <title>${bodies_list_page}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080">${audi_car_dealer}</a>
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
    <h2><u>${bodies_list}</u></h2>
    <td>
        <form action="save-body-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
        </form>
    </td>
    <form action="body/find-all" method="get">
        <tbody>
        <table class="table table-bordered">

            <thead>
            <tr>
                <th>${id}</th>
                <th>${color}</th>
                <th>${body_type}</th>
                <th>${created}</th>
                <th>${changed}</th>
                <th>${car_id}</th>
            </tr>
            </thead>

            <c:forEach var="body" items="${bodyList}">

                <tr>
                    <td>${body.id}</td>
                    <td>${body.color}</td>
                    <td>${body.bodyType}</td>
                    <td>${body.created}</td>
                    <td>${body.changed}</td>
                    <td>${body.carId}</td>
                    <td>
                        <form action="update-body-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${body.id}">
                            <input type="hidden" class="form-control" name="color" value="${body.color}">
                            <input type="hidden" class="form-control" name="bodyType" value="${body.bodyType}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>
                    <td>
                        <form action="delete-body-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${body.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value=${delete}>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href="http://localhost:8080/main-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
