<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.Color" %>
<%@ page import="by.varaksa.cardealer.entity.BodyType" %>
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
    <h2><u>User order save form</u></h2>
    <form action="save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label for="validationOrderName" class="form-label"><u>Order name</u></label>
                    <input type="text" class="form-control" id="validationOrderName" name="order_name" required>
                </div>
                <div class="col-md-2">
                    <label for="validationUserId" class="form-label"><u>User id</u></label>
                    <input type="text" class="form-control" id="validationUserId" name="user_id" required>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Save">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/user-order/find-all" role="button">Return
            to
            previous page</a>
    </label>
</div>
</body>
</html>
