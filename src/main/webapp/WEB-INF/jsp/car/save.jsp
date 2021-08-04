<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.Model" %>
<%@ page import="by.varaksa.cardealer.entity.Country" %>
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
    <h2><u>Car save form</u></h2>
    <form action="save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label class="form-label"><u>Model</u>
                        <select name="model">
                            <option value="A1">${Model.A1}</option>
                            <option value="A3">${Model.A3}</option>
                            <option value="A5">${Model.A5}</option>
                            <option value="A7">${Model.A7}</option>
                            <option value="A8">${Model.A8}</option>
                            <option value="Q3">${Model.Q3}</option>
                            <option value="Q5">${Model.Q5}</option>
                            <option value="Q7">${Model.Q7}</option>
                            <option value="Q8">${Model.Q8}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Issue country</u>
                        <select name="issue_country">
                            <option value="GERMANY">${Country.GERMANY}</option>
                            <option value="CZECH">${Country.CZECH}</option>
                            <option value="POLAND">${Country.POLAND}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationGuaranteePeriod" class="form-label"><u>Guarantee period</u>
                        <input type="text" class="form-control" id="validationGuaranteePeriod" name="guarantee_period"
                               value="${param.guaranteePeriod}" placeholder="${param.guaranteePeriod}" required>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationPrice" class="form-label"><u>Price</u>
                        <input type="text" class="form-control" id="validationPrice" name="price"
                               value="${param.price}" placeholder="${param.price}" required>
                    </label>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Save">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/car/find-all" role="button">Return to
            previous page</a>
    </label>
</div>
</body>
</html>
