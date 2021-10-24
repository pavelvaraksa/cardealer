<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.save.engine.page" var="save_page"/>
<fmt:message key="form.save.form" var="save_form"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="engine.fuel.type" var="fuel_type"/>
<fmt:message key="engine.volume" var="volume"/>
<fmt:message key="engine.cylinders.count" var="cylinders_count"/>
<fmt:message key="car.car.id" var="car_id"/>
<fmt:message key="fuel.type.diesel" var="diesel"/>
<fmt:message key="fuel.type.petrol" var="petrol"/>
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
    <h2><u>${save_form}</u></h2>
    <form action=<%=request.getContextPath()%>"/engine/save" method="post">
        <form class="row g-3">
            <div class="col-md-3">
                <label class="form-label"><u>${fuel_type}</u>
                    <select name="fuel_type">
                        <option value="PETROL">${petrol}</option>
                        <option value="DIESEL">${diesel}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationVolume" class="form-label"><u>${volume}</u></label>
                <input type="text" class="form-control" id="validationVolume" name="volume" required>
            </div>
            <div class="col-md-3">
                <label for="validationCylindersCount" class="form-label"><u>${cylinders_count}</u></label>
                <input type="text" class="form-control" id="validationCylindersCount" name="cylinders_count"
                       required>
            </div>
            <div class="col-md-3">
                <label for="validationCarId" class="form-label"><u>${car_id}</u></label>
                <input type="text" class="form-control" id="validationCarId" name="car_id" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${save}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/engine/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
