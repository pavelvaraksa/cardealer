<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <h2><u>Users list</u></h2>
    <form action="user/find-all" method="get">
        <tbody>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Birth date</th>
                <th>Login</th>
                <th>Email</th>
                <th>Role</th>
                <th>Is blocked</th>
                <th>Created</th>
                <th>Changed</th>
            </tr>
            </thead>

            <c:forEach var="user" items="${userList}">

                <tr>
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.blocked}</td>
                    <td>${user.created}</td>
                    <td>${user.changed}</td>
                    <td>
                        <form action="update-user-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user.id}">
                            <input type="hidden" class="form-control" name="firstName" value="${user.firstName}">
                            <input type="hidden" class="form-control" name="lastName" value="${user.lastName}">
                            <input type="hidden" class="form-control" name="birthDate" value="${user.birthDate}">
                            <input type="hidden" class="form-control" name="email" value="${user.email}">
                            <input type="hidden" class="form-control" name="role" value="${user.role}">
                            <input type="hidden" class="form-control" name="blocked" value="${user.blocked}">
                            <input class="btn btn-outline-warning btn-sm" type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="delete-user-page" method="post">
                            <input type="hidden" class="form-control" name="id" value="${user.id}">
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
