<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="info.contact.us" var="contact_us"/>
<fmt:message key="page.audi.dealer.contact.page" var="contact_page"/>
<fmt:message key="audi.center.minsk" var="minsk"/>
<fmt:message key="audi.center.grodno" var="grodno"/>
<fmt:message key="audi.center.vitebsk" var="vitebsk"/>
<fmt:message key="audi.center.mogilev" var="mogilev"/>
<fmt:message key="audi.center.gomel" var="gomel"/>
<fmt:message key="audi.center.brest" var="brest"/>
<fmt:message key="user.email" var="email"/>
<fmt:message key="info.contact.number" var="contact_number"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${contact_page}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
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
        <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
