<%@ page import="by.varaksa.cardealer.entity.User" %>
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
    <h2>Authentication form</h2>
    <form action="<%=request.getContextPath()%>/login" method="post">
        <table style="with: 100%">
            <div class="col-md-3">
                <label for="validationLogin" class="form-label">Login</label>
                <input type="text" class="form-control" id="validationLogin" name="login" required>
            </div>
            <div class="col-md-3">
                <label for="validationPassword" class="form-label">Password</label>
                <input type="password" class="form-control" id="validationPassword" name="password" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-primary btn-sm" type="submit" value="Log in">
            </div>
            <label>
                <a class="btn btn-outline-info btn-sm" href="http://localhost:8080" role="button">Return to
                    previous
                    page</a>
            </label>
        </table>
    </form>
</div>
</body>
</html>
