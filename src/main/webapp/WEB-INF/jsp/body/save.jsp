<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.Color" %>
<%@ page import="by.varaksa.cardealer.model.entity.BodyType" %>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_save_body_page" var="save_page"/>
<fmt:message key="Save_form" var="save_form"/>
<fmt:message key="Save" var="save"/>
<fmt:message key="Color" var="color"/>
<fmt:message key="Body_type" var="body_type"/>
<fmt:message key="Car_id" var="car_id"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <title>${save_page}</title>
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
    <h2><u>${save_form}</u></h2>
    <form action="save" method="post">
        <form class="row g-3">
            <div class="col-md-2">
                <label class="form-label"><u>${color}</u>
                    <select name="color">
                        <option value="BLACK">${Color.BLACK}</option>
                        <option value="WHITE">${Color.WHITE}</option>
                        <option value="RED">${Color.RED}</option>
                        <option value="GREEN">${Color.GREEN}</option>
                        <option value="BLUE">${Color.BLUE}</option>
                        <option value="YELLOW">${Color.YELLOW}</option>
                        <option value="BROWN">${Color.BROWN}</option>
                        <option value="SILVER">${Color.SILVER}</option>
                        <option value="ORANGE">${Color.ORANGE}</option>
                        <option value="GREY">${Color.GREY}</option>
                        <option value="CHAMPAGNE">${Color.CHAMPAGNE}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label class="form-label"><u>${body_type}</u>
                    <select name="body_type">
                        <option value="SEDAN">${BodyType.SEDAN}</option>
                        <option value="COUPE">${BodyType.COUPE}</option>
                        <option value="TOURING">${BodyType.TOURING}</option>
                        <option value="HATCHBACK">${BodyType.HATCHBACK}</option>
                        <option value="SUV">${BodyType.SUV}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label for="validationCarId" class="form-label"><u>${car_id}</u></label>
                <input type="text" class="form-control" id="validationCarId" name="car_id" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${save}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/body/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
