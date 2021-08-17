<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Register_form" var="register_form"/>
<fmt:message key="Firstname" var="firstname"/>
<fmt:message key="Lastname" var="lastname"/>
<fmt:message key="Birth_date" var="birth_date"/>
<fmt:message key="Login" var="login"/>
<fmt:message key="Password" var="password"/>
<fmt:message key="Email" var="email"/>
<fmt:message key="Required_field" var="required_field"/>
<fmt:message key="Register" var="register"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous">
    </script>
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
    <h2><u>${register_form}</u></h2>
    <form action="user/save" method="post">
        <form class="row g-3">
            <div class="col-md-2">
                <label for="validationFirstname" class="form-label"><u>${firstname}*</u></label>
                <input type="text" class="form-control" id="validationFirstname" name="firstname" required>
            </div>
            <div class="col-md-2">
                <label for="validationLastname" class="form-label"><u>${lastname}*</u></label>
                <input type="text" class="form-control" id="validationLastname" name="lastname" required>
            </div>
            <div class="col-md-2">
                <label for="validationBirthDate" class="form-label"><u>${birth_date}</u></label>
                <input type="date" class="form-control" id="validationBirthDate" name="birth_date">
            </div>
            <div class="col-md-2">
                <label for="validationLogin" class="form-label"><u>${login}*</u></label>
                <input type="text" class="form-control" id="validationLogin" name="login" required>
            </div>
            <div class="col-md-2">
                <label for="validationPassword" class="form-label"><u>${password}*</u></label>
                <input type="password" class="form-control" id="validationPassword" name="password" required>
            </div>
            <div class="col-md-2">
                <label for="validationEmail" class="form-label"><u>${email}*</u></label>
                <input type="email" class="form-control" id="validationEmail" name="email" required>
            </div>
            <p class="text-dark">* - ${required_field}</p>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${register}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
