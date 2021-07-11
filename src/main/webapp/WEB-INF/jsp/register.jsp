<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<div align="center">
    <h2>Register form</h2>
    <form action="<%=request.getContextPath()%>/save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-4">
                    <label for="validationName" class="form-label">Name</label>
                    <input type="text" class="form-control" id="validationName" name="name" required>
                </div>
                <div class="col-md-4">
                    <label for="validationSurname" class="form-label">Surname</label>
                    <input type="text" class="form-control" id="validationSurname" name="surname" required>
                </div>
                <div class="col-md-4">
                    <label for="validationBirthDate" class="form-label">Birth date</label>
                    <input type="date" class="form-control" id="validationBirthDate" name="birth_date">
                </div>
                <div class="col-md-4">
                    <label for="validationLogin" class="form-label">Login</label>
                    <input type="text" class="form-control" id="validationLogin" name="login" required>
                </div>
                <div class="col-md-4">
                    <label for="validationPassword" class="form-label">Password</label>
                    <input type="password" class="form-control" id="validationPassword" name="password" required>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-primary btn-sm" type="submit" value="Register">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-info btn-sm" href="http://localhost:8080" role="button">Return to previous page</a>
    </label>
</div>
</body>
</html>
