<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.City" %>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.save.dealer.page" var="save_page"/>
<fmt:message key="form.save.form" var="save_form"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="dealer.name" var="name"/>
<fmt:message key="dealer.address" var="address"/>
<fmt:message key="dealer.foundation.date" var="foundation_date"/>
<fmt:message key="dealer.city" var="city"/>
<fmt:message key="car.car.id" var="car_id"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

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
                <label for="validationName" class="form-label"><u>${name}</u></label>
                <input type="text" class="form-control" id="validationName" name="name" required>
            </div>
            <div class="col-md-2">
                <label for="validationAddress" class="form-label"><u>${address}</u></label>
                <input type="text" class="form-control" id="validationAddress" name="address" required>
            </div>
            <div class="col-md-2">
                <label for="validationFoundationDate" class="form-label"><u>${foundation_date}</u></label>
                <input type="date" class="form-control" id="validationFoundationDate" name="foundation_date" required>
            </div>
            <div class="col-md-2">
                <label class="form-label"><u>${city}</u>
                    <select name="city">
                        <option value="MINSK">${City.MINSK}</option>
                        <option value="BREST">${City.BREST}</option>
                        <option value="MOGILEV">${City.MOGILEV}</option>
                        <option value="GOMEL">${City.GOMEL}</option>
                        <option value="VITEBSK">${City.VITEBSK}</option>
                        <option value="GRODNO">${City.GRODNO}</option>
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
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/dealer/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
