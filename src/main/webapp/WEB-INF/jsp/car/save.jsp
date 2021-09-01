<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.Model" %>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.save.car.page" var="save_page"/>
<fmt:message key="form.save.form" var="save_form"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="id" var="id"/>
<fmt:message key="car.model" var="model"/>
<fmt:message key="car.issue_country" var="issue_country"/>
<fmt:message key="car.guarantee_period" var="guarantee_period"/>
<fmt:message key="car.price" var="price"/>
<fmt:message key="country.germany" var="germany"/>
<fmt:message key="country.poland" var="poland"/>
<fmt:message key="country.czech" var="czech"/>
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
            <div class="col-md-3">
                <label class="form-label"><u>${model}</u>
                    <select name="model">
                        <option value="A1">${Model.A1}</option>
                        <option value="A3">${Model.A3}</option>
                        <option value="A5">${Model.A5}</option>
                        <option value="A7">${Model.A7}</option>
                        <option value="A8">${Model.A8}</option>
                        <option value="Q3">${Model.Q3}</option>
                        <option value="Q5">${Model.Q5}</option>
                        <option value="Q7">${Model.Q7}</option>
                        <option value="Q8">${Model.Q8}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label class="form-label"><u>${issue_country}</u>
                    <select name="issue_country">
                        <option value="GERMANY">${germany}</option>
                        <option value="CZECH">${czech}</option>
                        <option value="POLAND">${poland}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationGuaranteePeriod" class="form-label"><u>${guarantee_period}</u>
                    <input type="text" class="form-control" id="validationGuaranteePeriod" name="guarantee_period"
                           value="${param.guaranteePeriod}" placeholder="${param.guaranteePeriod}" required>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationPrice" class="form-label"><u>${price}</u>
                    <input type="text" class="form-control" id="validationPrice" name="price"
                           value="${param.price}" placeholder="${param.price}" required>
                </label>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${save}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/car/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
