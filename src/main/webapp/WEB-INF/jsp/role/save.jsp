<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.model.entity.Color" %>
<%@ page import="by.varaksa.cardealer.model.entity.BodyType" %>
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
    <h2><u>Body save form</u></h2>
    <form action="save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label class="form-label"><u>Color</u>
                        <select name="color">
                            <option value="BLACK">${Color.BLACK}</option>
                            <option value="WHITE">${Color.WHITE}</option>
                            <option value="RED">${Color.RED}</option>
                            <option value="GREEN">${Color.GREEN}</option>
                            <option value="BLUE">${Color.BLUE}</option>
                            <option value="YELLOW">${Color.YELLOW}</option>
                            <option value="BROWN">${Color.BROWN}</option>
                            <option value="SILVER">${Color.SILVER}</option>
                            <option value="ORANGE">${Color.ORANGE}</option>
                            <option value="GREY">${Color.GREY}</option>
                            <option value="CHAMPAGNE">${Color.CHAMPAGNE}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Body type</u>
                        <select name="body_type">
                            <option value="SEDAN">${BodyType.SEDAN}</option>
                            <option value="COUPE">${BodyType.COUPE}</option>
                            <option value="TOURING">${BodyType.TOURING}</option>
                            <option value="HATCHBACK">${BodyType.HATCHBACK}</option>
                            <option value="SUV">${BodyType.SUV}</option>
                        </select>
                    </label>
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
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/body/find-all" role="button">Return to
            previous page</a>
    </label>
</div>
</body>
</html>
