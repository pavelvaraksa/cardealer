<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_engines_list" var="engines_list_page"/>
<fmt:message key="Engines_list" var="engines_list"/>
<fmt:message key="Add" var="add"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Delete" var="delete"/>
<fmt:message key="Id" var="id"/>
<fmt:message key="Fuel_type" var="fuel_type"/>
<fmt:message key="Volume" var="volume"/>
<fmt:message key="Cylinders_count" var="cylinders_count"/>
<fmt:message key="Created" var="created"/>
<fmt:message key="Changed" var="changed"/>
<fmt:message key="Car_id" var="car_id"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <title>${engines_list_page}</title>
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
    <h2><u>${engines_list}</u></h2>
    <td>
        <form action="save-engine-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
        </form>
    </td>
    <form action="engine/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${fuel_type}</th>
                <th>${volume}</th>
                <th>${cylinders_count}</th>
                <th>${created}</th>
                <th>${changed}</th>
                <th>${car_id}</th>
            </tr>
            </thead>

            <c:forEach var="engine" items="${engineList}">

                <tr>
                    <td>${engine.id}</td>
                    <td>${engine.fuelType}</td>
                    <td>${engine.volume}</td>
                    <td>${engine.cylindersCount}</td>
                    <td>${engine.created}</td>
                    <td>${engine.changed}</td>
                    <td>${engine.carId}</td>
                    <td>
                        <form action="update-engine-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${engine.id}">
                            <input type="hidden" class="form-control" name="fuelType" value="${engine.fuelType}">
                            <input type="hidden" class="form-control" name="volume" value="${engine.volume}">
                            <input type="hidden" class="form-control" name="cylindersCount"
                                   value="${engine.cylindersCount}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>
                    <td>
                        <form action="delete-engine-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${engine.id}">
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
