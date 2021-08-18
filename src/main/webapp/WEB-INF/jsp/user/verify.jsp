<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_verify_page" var="verify_page"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>
<fmt:message key="Confirm" var="confirm"/>
<fmt:message key="Confirmation_page" var="confirmation_page"/>
<fmt:message key="Indication_text" var="indication_text"/>

<html>
<head>
    <title>${verify_page}</title>
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
    <form action="verify" method="post">
        <div>
            <h2><u>${confirmation_page}</u></h2>
            <h5>${indication_text}</h5>
        </div>
        <div class="col-md-1">
            <label for="validationCode"></label>
            <input type="text" class="form-control" id="validationCode" name="authCode" required>
        </div>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${confirm}>
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/register-page"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
