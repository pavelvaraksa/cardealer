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
<fmt:message key="button.update" var="update"/>
<fmt:message key="button.delete" var="delete"/>
<fmt:message key="id" var="id"/>
<fmt:message key="car.model" var="model"/>
<fmt:message key="car.issue_country" var="issue_country"/>
<fmt:message key="car.guarantee_period" var="guarantee_period"/>
<fmt:message key="car.price" var="price"/>
<fmt:message key="info.created" var="created"/>
<fmt:message key="info.changed" var="changed"/>
<fmt:message key="order.user.order.id" var="order_id"/>
<fmt:message key="dealer.id" var="dealer_id"/>
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
    <td>
        <form action=<%=request.getContextPath()%>"/car/save-car" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
        </form>
    </td>
    <form action=<%=request.getContextPath()%>"/car/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${model}</th>
                <th>${issue_country}</th>
                <th>${guarantee_period}</th>
                <th>${price}</th>
                <th>${created}</th>
                <th>${changed}</th>
                <th>${order_id}</th>
                <th>${dealer_id}</th>
            </tr>
            </thead>

            <%--@elvariable id="carList" type="java.util.List"--%>
            <c:forEach var="car" items="${carList}">

                <tr>
                    <td>${car.id}</td>
                    <td>${car.model}</td>
                    <td>${car.issueCountry}</td>
                    <td>${car.guaranteePeriod}</td>
                    <td>${car.price}</td>
                    <td>${car.created}</td>
                    <td>${car.changed}</td>
                    <td>${car.userOrderId}</td>
                    <td>${car.dealerId}</td>
                    <td>
                        <form action=<%=request.getContextPath()%>"update-car" method="post">
                            <input type="hidden" class="form-control" name="id" value="${car.id}">
                            <input type="hidden" class="form-control" name="model" value="${car.model}">
                            <input type="hidden" class="form-control" name="guaranteePeriod"
                                   value="${car.guaranteePeriod}">
                            <input type="hidden" class="form-control" name="price" value="${car.price}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>
                    <td>
                        <form action=<%=request.getContextPath()%>"delete-car" method="post">
                            <input type="hidden" class="form-control" name="id" value="${car.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value=${delete}>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/admin-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
