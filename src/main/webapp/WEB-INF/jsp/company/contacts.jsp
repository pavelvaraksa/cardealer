<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Contact_us" var="contact_us"/>
<fmt:message key="Audi_center_Minsk" var="minsk"/>
<fmt:message key="Audi_center_Grodno" var="grodno"/>
<fmt:message key="Audi_center_Vitebsk" var="vitebsk"/>
<fmt:message key="Audi_center_Mogilev" var="mogilev"/>
<fmt:message key="Audi_center_Gomel" var="gomel"/>
<fmt:message key="Audi_center_Brest" var="brest"/>
<fmt:message key="Email" var="email"/>
<fmt:message key="Contact_number" var="contact_number"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <h2><u>${contact_us}</u></h2>
    <h5>${minsk}</h5>
    <p class="text-center">${email}:audicenterminsk@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 111 11 11</p>
    <h5>${grodno}</h5>
    <p class="text-center">${email}:audicentergrodno@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 222 22 22</p>
    <h5>${vitebsk}</h5>
    <p class="text-center">${email}:audicentervitebsk@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 333 33 33</p>
    <h5>${mogilev}</h5>
    <p class="text-center">${email}:audicentermogilev@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 444 44 44</p>
    <h5>${gomel}</h5>
    <p class="text-center">${email}:audicentergomel@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 555 55 55</p>
    <h5>${brest}</h5>
    <p class="text-center">${email}:audicenterbrest@gmail.com</p>
    <p class="text-center">${contact_number}:+375 17 666 66 66</p>

    <label>
        <a class="btn btn-outline-primary" href="http://localhost:8080" role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
