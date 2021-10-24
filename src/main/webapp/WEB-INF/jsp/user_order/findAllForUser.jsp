<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="userOrderList" type="java.util.List"--%>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="list.user.orders.list" var="user_orders_list_page"/>
<fmt:message key="menu.title.user.orders.list" var="user_orders_list"/>
<fmt:message key="car.model" var="model"/>
<fmt:message key="car.price" var="price"/>
<fmt:message key="dealer.name" var="name"/>
<fmt:message key="engine.fuel.type" var="fuel_type"/>
<fmt:message key="info.created" var="created"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${user_orders_list_page}</title>
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
    <h2><u>${user_orders_list}</u></h2>
    <form action=<%=request.getContextPath()%>"/user-order-for-user/find-all-for-user" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${model}</th>
                <th>${price}</th>
                <th>${name}</th>
                <th>${fuel_type}</th>
                <th>${created}</th>
            </thead>

            <c:forEach var="user_order" items="${userOrderList}">

                <tr>
                    <td>${user_order.model}</td>
                    <td>${user_order.price}</td>
                    <td>${user_order.name}</td>
                    <td>${user_order.fuelType}</td>
                    <td>${user_order.created}</td>
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
