<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="list.dealers.list" var="dealers_list_page"/>
<fmt:message key="menu.title.dealers.list" var="dealers_list"/>
<fmt:message key="button.add" var="add"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="button.delete" var="delete"/>
<fmt:message key="id" var="id"/>
<fmt:message key="dealer.name" var="name"/>
<fmt:message key="dealer.address" var="adress"/>
<fmt:message key="dealer.foundation.date" var="foundation_date"/>
<fmt:message key="dealer.city" var="city"/>
<fmt:message key="info.created" var="created"/>
<fmt:message key="info.changed" var="changed"/>
<fmt:message key="car.car.id" var="car_id"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${dealers_list_page}</title>
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
    <h2><u>${dealers_list}</u></h2>
    <td>
        <form action="save-dealer-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
        </form>
    </td>
    <form action="dealer/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${name}</th>
                <th>${adress}</th>
                <th>${foundation_date}</th>
                <th>${city}</th>
                <th>${created}</th>
                <th>${changed}</th>
                <th>${car_id}</th>
            </tr>
            </thead>

            <c:forEach var="dealer" items="${dealerList}">

                <tr>
                    <td>${dealer.id}</td>
                    <td>${dealer.name}</td>
                    <td>${dealer.address}</td>
                    <td>${dealer.foundationDate}</td>
                    <td>${dealer.city}</td>
                    <td>${dealer.created}</td>
                    <td>${dealer.changed}</td>
                    <td>${dealer.carId}</td>
                    <td>
                        <form action="update-dealer-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${dealer.id}">
                            <input type="hidden" class="form-control" name="name" value="${dealer.name}">
                            <input type="hidden" class="form-control" name="address" value="${dealer.address}">
                            <input type="hidden" class="form-control" name="foundationDate" value="${dealer.foundationDate}">
                            <input type="hidden" class="form-control" name="city" value="${dealer.city}">
                            <input type="hidden" class="form-control" name="carId" value="${dealer.carId}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>
                    <td>
                        <form action="delete-dealer-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${dealer.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value=${delete}>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href="http://localhost:8080/admin-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
