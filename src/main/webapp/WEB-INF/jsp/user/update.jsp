<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page import="by.varaksa.cardealer.model.entity.Role" %>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_update_user_page" var="update_page"/>
<fmt:message key="Update_form" var="update_form"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Firstname" var="firstname"/>
<fmt:message key="Lastname" var="lastname"/>
<fmt:message key="Birth_date" var="birth_date"/>
<fmt:message key="Email" var="email"/>
<fmt:message key="Role" var="role"/>
<fmt:message key="Is_blocked" var="is_blocked"/>

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
                <label for="validationFirstname" class="form-label"><u>${firstname}</u>
                    <input type="text" class="form-control" id="validationFirstname" name="firstname"
                           value="${param.firstName}" placeholder="${param.firstName}" required>
                </label>
            </div>
            <div class="col-md-2">
                <label for="validationLastname" class="form-label"><u>${lastname}</u>
                    <input type="text" class="form-control" id="validationLastname" name="lastname"
                           value="${param.lastName}" placeholder="${param.lastName}" required>
                </label>
            </div>
            <div class="col-md-2">
                <label for="validationBirthdate" class="form-label"><u>${birth_date}</u>
                    <input type="date" class="form-control" id="validationBirthdate" name="birth_date"
                           value="${param.birthDate}" placeholder=${param.birthDate}>
                </label>
            </div>
            <div class="col-md-2">
                <label for="validationEmail" class="form-label"><u>${email}</u>
                    <input type="email" class="form-control" id="validationEmail" name="email"
                           value="${param.email}" placeholder="${param.email}" required>
                </label>
            </div>
            <div class="col-md-2">
                <label class="form-label"><u>${role}</u>
                    <select name="role">
                        <option value="USER">${Role.USER}</option>
                        <option value="ADMIN">${Role.ADMIN}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-2">
                <label class="form-label"><u>${is_blocked}</u>
                    <select name="is_blocked">
                        <option value=false>${false}</option>
                        <option value=true>${true}</option>
                    </select>
                </label>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${update}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/user/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
