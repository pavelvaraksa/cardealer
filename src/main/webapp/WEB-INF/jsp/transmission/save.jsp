<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.TransmissionType" %>
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
    <h2><u>Body save form</u></h2>
    <form action="save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label class="form-label"><u>Transmission type</u>
                        <select name="transmission_type">
                            <option value="AUTOMATIC">${TransmissionType.AUTOMATIC}</option>
                            <option value="MECHANIC">${TransmissionType.MECHANIC}</option>
                            <option value="ROBOTIC">${TransmissionType.ROBOTIC}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label for="validationGearsCount" class="form-label"><u>Gears count</u></label>
                    <input type="text" class="form-control" id="validationGearsCount" name="gears_count" required>
                </div>
                <div class="col-md-2">
                    <label for="validationWeight" class="form-label"><u>Weight</u></label>
                    <input type="text" class="form-control" id="validationWeight" name="weight" required>
                </div>
                <div class="col-md-2">
                    <label for="validationCarId" class="form-label"><u>Car id </u></label>
                    <input type="text" class="form-control" id="validationCarId" name="car_id" required>
                </div>
            </form>
        </table>
        <div class="col-12">
            <input class="btn btn-outline-success btn-sm" type="submit" value="Save">
        </div>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/transmission/find-all" role="button">Return to
            previous page</a>
    </label>
</div>
</body>
</html>
