<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.model.entity.EngineType" %>
<%@ page import="by.varaksa.cardealer.model.entity.FuelType" %>

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
    <h2><u>Engine update form</u></h2>
    <form action="update" method="post">
        <form class="row g-3">
            <div class="col-md-2">
                <input type="hidden" name="id" value="${param.id}">
            </div>
            <table style="with: 100%">
                <div class="col-md-2">
                    <label class="form-label"><u>Engine type</u>
                        <select name="engine_type">
                            <option value="FUEL">${EngineType.FUEL}</option>
                            <option value="ELEKTRIC">${EngineType.ELEKTRIC}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Fuel type</u>
                        <select name="fuel_type">
                            <option value="PETROL">${FuelType.PETROL}</option>
                            <option value="DIESEL">${FuelType.DIESEL}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationVolume" class="form-label"><u>Volume</u></label>
                    <input type="text" class="form-control" id="validationVolume" name="volume"
                           value="${param.volume}" placeholder="${param.volume}" required>
                </div>
                <div class="col-md-2">
                    <label for="validationCylindersCount" class="form-label"><u>Cylinders count</u></label>
                    <input type="text" class="form-control" id="validationCylindersCount" name="cylinders_count"
                           value="${param.cylindersCount}" placeholder="${param.cylindersCount}" required>
                </div>
        </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Update">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/engine/find-all" role="button">Return to
            previous
            page</a>
    </label>
</div>
</body>
</html>
