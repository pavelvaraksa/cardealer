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
<body style="background-color:antiquewhite"></body>
<div align="center">
    <form action="verify" method="post">
        <div>
            <h2><u>Verify page</u></h2>
            <h5>Please, check your email and enter the verification code</h5>
        </div>
        <div class="col-md-1">
            <label for="validationCode"></label>
            <input type="text" class="form-control" id="validationCode" name="authCode" required>
        </div>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Confirm">
        </div>
        <label>
            <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/register-page" role="button">Return
                to
                previous page</a>
        </label>
    </form>
</div>
</body>
</html>
