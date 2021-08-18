<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Main_menu" var="main_menu"/>
<fmt:message key="Log_out" var="log_out"/>
<fmt:message key="Users_list" var="users_list"/>
<fmt:message key="User_orders_list" var="user_orders_list"/>
<fmt:message key="Cars_list" var="cars_list"/>
<fmt:message key="Bodies_list" var="bodies_list"/>
<fmt:message key="Transmissions_list" var="transmissions_list"/>
<fmt:message key="Engines_list" var="engines_list"/>
<fmt:message key="Dealers_list" var="dealers_list"/>

<html>
<head>
    <title>${main_menu}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080">${audi_car_dealer}</a>
        <body style="background-color:antiquewhite"></body>
        <div class="collapse navbar-collapse" id="navbarsExample03">
            <ul class="navbar-nav me-auto mb-2 mb-sm-0">
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/logout">${log_out}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/user/find-all">${users_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/user-order/find-all">${user_orders_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/car/find-all">${cars_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/body/find-all">${bodies_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/engine/find-all">${engines_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/transmission/find-all">${transmissions_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="http://localhost:8080/dealer/find-all">${dealers_list}</a>
                </li>
            </ul>
        </div>
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
</body>
</html>