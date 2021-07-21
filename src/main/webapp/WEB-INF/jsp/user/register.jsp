<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h2><u>Register form</u></h2>
    <form action="<%=request.getContextPath()%>/save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label for="validationName" class="form-label"><u>Firstname <span
                            class="badge rounded-pill bg-danger">required field</span></u></label>
                    <input type="text" class="form-control" id="validationName" name="firstname" required>
                </div>
                <div class="col-md-2">
                    <label for="validationSurname" class="form-label"><u>Lastname <span
                            class="badge rounded-pill bg-danger">required field</span></u></label>
                    <input type="text" class="form-control" id="validationSurname" name="lastname" required>
                </div>
                <div class="col-md-2">
                    <label for="validationBirthDate" class="form-label"><u>Birth date</u></label>
                    <input type="date" class="form-control" id="validationBirthDate" name="birth_date">
                </div>
                <div class="col-md-2">
                    <label for="validationLogin" class="form-label"><u>Login <span class="badge rounded-pill bg-danger">
                        required field</span></u></label>
                    <input type="text" class="form-control" id="validationLogin" name="login" required>
                </div>
                <div class="col-md-2">
                    <label for="validationPassword" class="form-label"><u>Password <span
                            class="badge rounded-pill bg-danger">required field</span></u></label>
                    <input type="password" class="form-control" id="validationPassword" name="password" required>
                </div>
                <div class="col-md-2">
                    <label for="validationEmail" class="form-label"><u>Email <span class="badge rounded-pill bg-danger">
                        required field</span></u></label>
                    <input type="email" class="form-control" id="validationEmail" name="email" required>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Register">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080" role="button">Return to previous page</a>
    </label>
</div>
</body>
</html>
