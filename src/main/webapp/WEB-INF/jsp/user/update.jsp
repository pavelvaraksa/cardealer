<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.Role" %>

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
    <h2><u>User update form</u></h2>
    <form action="update" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <input type="hidden" name="id" value="${param.id}">
                </div>
                <div class="col-md-2">
                    <label for="validationFirstname" class="form-label"><u>Firstname</u>
                        <input type="text" class="form-control" id="validationFirstname" name="firstname"
                               value="${param.firstName}" placeholder="${param.firstName}" required>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationLastname" class="form-label"><u>Lastname</u>
                        <input type="text" class="form-control" id="validationLastname" name="lastname"
                               value="${param.lastName}" placeholder="${param.lastName}" required>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationBirthdate" class="form-label"><u>Birth date</u>
                        <input type="date" class="form-control" id="validationBirthdate" name="birth_date"
                               value="${param.birthDate}" placeholder=${param.birthDate}>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationEmail" class="form-label"><u>Email</u>
                        <input type="email" class="form-control" id="validationEmail" name="email"
                               value="${param.email}" placeholder="${param.email}" required>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Role</u>
                        <select name="role">
                            <option value="USER">${Role.USER}</option>
                            <option value="ADMIN">${Role.ADMIN}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Is blocked</u>
                        <select name="is_blocked">
                            <option value=false>${false}</option>
                            <option value=true>${true}</option>
                        </select>
                    </label>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Update">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/user/find-all" role="button">Return to
            previous
            page</a>
    </label>
</div>
</body>
</html>
