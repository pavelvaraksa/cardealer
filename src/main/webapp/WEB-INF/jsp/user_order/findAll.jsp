<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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
        <a class="navbar-brand" href="http://localhost:8080">CAR DEALER APPLICATION</a>
    </div>
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h2><u>User orders list</u></h2>
    <td>
        <form action="save-user-order-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Add new">
        </form>
    </td>
    <form action="user-order/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Order name</th>
                <th>Created</th>
                <th>Changed</th>
                <th>User id</th>
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
                            <input class="btn btn-outline-warning btn-sm" type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="delete-user-order-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user_order.id}">
                            <input class="btn btn-outline-danger btn-sm" type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </tbody>
        <label>
            <a class="btn btn-outline-primary" href="http://localhost:8080/main-menu" role="button">Return to main
                menu</a>
        </label>
    </form>
</div>
</body>
</html>
