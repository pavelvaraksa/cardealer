<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.City" %>

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
    <h2><u>Dealer update form</u></h2>
    <form action="update" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <input type="hidden" name="id" value="${param.id}">
                </div>
                <div class="col-md-2">
                    <label for="validationName" class="form-label"><u>Name</u></label>
                    <input type="text" class="form-control" id="validationName" name="name"
                           value="${param.name}" placeholder="${param.name}" required>
                </div>
                <div class="col-md-2">
                    <label for="validationAddress" class="form-label"><u>Address</u></label>
                    <input type="text" class="form-control" id="validationAddress" name="address"
                           value="${param.address}" placeholder="${param.address}" required>
                </div>
                <div class="col-md-2">
                    <label for="validationFoundationDate" class="form-label"><u>Foundation date</u></label>
                    <input type="date" class="form-control" id="validationFoundationDate" name="foundation_date"
                           value="${param.foundationDate}" placeholder="${param.foundationDate}" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>City</u>
                        <select name="city">
                            <option value="MINSK">${City.MINSK}</option>
                            <option value="BREST">${City.BREST}</option>
                            <option value="MOGILEV">${City.MOGILEV}</option>
                            <option value="GOMEL">${City.GOMEL}</option>
                            <option value="VITEBSK">${City.VITEBSK}</option>
                            <option value="GRODNO">${City.GRODNO}</option>
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
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/dealer/find-all" role="button">Return to
            previous
            page</a>
    </label>
</div>
</body>
</html>
