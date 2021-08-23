<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.FuelType" %>

<fmt:message key="Audi_car_dealer" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_update_engine_page" var="update_page"/>
<fmt:message key="Update_form" var="update_form"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Fuel_type" var="fuel_type"/>
<fmt:message key="Volume" var="volume"/>
<fmt:message key="Cylinders_count" var="cylinders_count"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <title>${update_page}</title>
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
    <h2><u>${update_form}</u></h2>
    <form action="update" method="post">
        <form class="row g-3">
            <div class="col-md-2">
                <input type="hidden" name="id" value="${param.id}">
            </div>
            <div class="col-md-2">
                <label class="form-label"><u>${fuel_type}</u>
                    <select name="fuel_type">
                        <option value="PETROL">${FuelType.PETROL}</option>
                        <option value="DIESEL">${FuelType.DIESEL}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label for="validationVolume" class="form-label"><u>${volume}</u></label>
                <input type="text" class="form-control" id="validationVolume" name="volume"
                       value="${param.volume}" placeholder="${param.volume}" required>
            </div>
            <div class="col-md-2">
                <label for="validationCylindersCount" class="form-label"><u>${cylinders_count}</u></label>
                <input type="text" class="form-control" id="validationCylindersCount" name="cylinders_count"
                       value="${param.cylindersCount}" placeholder="${param.cylindersCount}" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${update}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/engine/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
