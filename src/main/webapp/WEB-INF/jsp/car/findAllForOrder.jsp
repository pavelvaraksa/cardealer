<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="list.cars.list" var="cars_list_page"/>
<fmt:message key="menu.title.cars.list" var="cars_list"/>
<fmt:message key="button.add" var="add"/>
<fmt:message key="id" var="id"/>
<fmt:message key="car.model" var="model"/>
<fmt:message key="car.issue_country" var="issue_country"/>
<fmt:message key="car.guarantee_period" var="guarantee_period"/>
<fmt:message key="car.price" var="price"/>
<fmt:message key="dealer.name" var="name"/>
<fmt:message key="engine.fuel.type" var="fuel_type"/>
<fmt:message key="engine.volume" var="volume"/>
<fmt:message key="transmission.transmission.type" var="transmission_type"/>
<fmt:message key="transmission.gears.count" var="gears_count"/>
<fmt:message key="car.color" var="color"/>
<fmt:message key="car.body.type" var="body_type"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${cars_list_page}</title>
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
    <h2><u>${cars_list}</u></h2>
    <form action=<%=request.getContextPath()%>"/car/find-all-for-order" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th></th>
                <th>${model}</th>
                <th>${issue_country}</th>
                <th>${price}</th>
                <th>${name}</th>
                <th>${fuel_type}</th>
                <th>${volume}</th>
                <th>${transmission_type}</th>
                <th>${gears_count}</th>
                <th>${color}</th>
                <th>${body_type}</th>
            </tr>
            </thead>

            <%--@elvariable id="carListForOrder" type="java.util.List"--%>
            <c:forEach var="car" items="${carListForOrder}">

                <tr>
                    <td>${car.id}</td>
                    <td>${car.model}</td>
                    <td>${car.issueCountry}</td>
                    <td>${car.price}</td>
                    <td>${car.name}</td>
                    <td>${car.fuelType}</td>
                    <td>${car.volume}</td>
                    <td>${car.transmissionType}</td>
                    <td>${car.gearsCount}</td>
                    <td>${car.color}</td>
                    <td>${car.bodyType}</td>

                    <td>
                    <form action=<%=request.getContextPath()%>"/user-order/save-user-order-for-user" method="post">
                        <input type="hidden" class="form-control" name="id" value="${car.id}">
                        <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
                    </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/user-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
