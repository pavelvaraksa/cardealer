<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="AUDI_CAR_DEALER" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_user_orders_list" var="user_orders_list_page"/>
<fmt:message key="User_orders_list" var="user_orders_list"/>
<fmt:message key="Add" var="add"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Delete" var="delete"/>
<fmt:message key="Id" var="id"/>
<fmt:message key="Order_name" var="order_name"/>
<fmt:message key="Created" var="created"/>
<fmt:message key="Changed" var="changed"/>
<fmt:message key="User_id" var="user_id"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

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
    <h2><u>${user_orders_list}</u></h2>
    <td>
        <form action="save-user-order-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value=${add}>
        </form>
    </td>
    <form action="user-order/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${id}</th>
                <th>${order_name}</th>
                <th>${created}</th>
                <th>${changed}</th>
                <th>${user_id}</th>
            </tr>
            </thead>

            <c:forEach var="user_order" items="${userOrderList}">

                <tr>
                    <td>${user_order.id}</td>
                    <td>${user_order.orderName}</td>
                    <td>${user_order.created}</td>
                    <td>${user_order.changed}</td>
                    <td>${user_order.userId}</td>
                    <td>
                        <form action="update-user-order-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user_order.id}">
                            <input type="hidden" class="form-control" name="orderName" value="${user_order.orderName}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value=${update}>
                        </form>
                    </td>
                    <td>
                        <form action="delete-user-order-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user_order.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value=${delete}>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href="http://localhost:8080/main-menu"
               role="button">${return_to_previous_page}</a>
        </label>
    </form>
</div>
</body>
</html>
