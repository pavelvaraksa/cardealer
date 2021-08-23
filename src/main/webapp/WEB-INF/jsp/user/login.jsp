<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="Audi_car_dealer" var="audi_car_dealer"/>
<fmt:message key="Authentication_form" var="authentication_form"/>
<fmt:message key="Audi_dealer_auth_page" var="authentication_page"/>
<fmt:message key="Login" var="login"/>
<fmt:message key="Password" var="password"/>
<fmt:message key="Enter" var="enter"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<fmt:message key="Login_help_text" var="login_help_text"/>
<fmt:message key="Password_help_text" var="password_help_text"/>

<html>
<head>
    <title>${authentication_page}</title>
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
    <h2><u>${authentication_form}</u></h2>
    <form action="login" method="post">
        <div class="col-md-3">
            <label for="login">${login}</label>
            <input type="text" id="login" class="form-control" name="login" required>
            <small id="loginHelpText" class="text-muted">${login_help_text}</small>
        </div>
        <div class="col-md-3">
            <label for="password">${password}</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <small id="passwordHelpText" class="text-muted">${password_help_text}</small>
        </div>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${enter}>
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
