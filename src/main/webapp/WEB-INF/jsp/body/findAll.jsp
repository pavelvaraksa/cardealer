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
<div align="center">
    <h2><u>Bodies list</u></h2>
    <td>
        <form action="save-body-page" method="post">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Add new">
        </form>
    </td>
    <form action="body/find-all" method="get">
        <tbody>
        <table class="table table-bordered">

            <thead>
            <tr>
                <th>Id</th>
                <th>Color</th>
                <th>Body type</th>
                <th>Created</th>
                <th>Changed</th>
                <th>Car id</th>
            </tr>
            </thead>

            <c:forEach var="body" items="${bodyList}">

                <tr>
                    <td>${body.id}</td>
                    <td>${body.color}</td>
                    <td>${body.bodyType}</td>
                    <td>${body.created}</td>
                    <td>${body.changed}</td>
                    <td>${body.carId}</td>
                    <td>
                        <form action="update-body-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${body.id}">
                            <input type="hidden" class="form-control" name="color" value="${body.color}">
                            <input type="hidden" class="form-control" name="bodyType" value="${body.bodyType}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="delete-body-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${body.id}">
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
