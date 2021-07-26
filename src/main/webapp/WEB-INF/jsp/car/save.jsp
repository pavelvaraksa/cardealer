<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.varaksa.cardealer.entity.Brand" %>
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
<div align="center">
    <h2><u>Car save form</u></h2>
    <form action="save" method="post">
        <table style="with: 100%">
            <form class="row g-3">
                <div class="col-md-2">
                    <label class="form-label"><u>Brand</u>
                        <select name="brand">
                            <option value="MERCEDES">${Brand.MERCEDES}</option>
                            <option value="BMW">${Brand.BMW}</option>
                            <option value="AUDI">${Brand.AUDI}</option>
                            <option value="OPEL">${Brand.OPEL}</option>
                            <option value="SUBARU">${Brand.SUBARU}</option>
                            <option value="FORD">${Brand.FORD}</option>
                            <option value="HONDA">${Brand.HONDA}</option>
                            <option value="HYUNDAI">${Brand.HYUNDAI}</option>
                            <option value="SKODA">${Brand.SKODA}</option>
                            <option value="MITSUBISHI">${Brand.MITSUBISHI}</option>
                            <option value="CITROEN">${Brand.CITROEN}</option>
                            <option value="TOYOTA">${Brand.TOYOTA}</option>
                            <option value="FIAT">${Brand.FIAT}</option>
                            <option value="KIA">${Brand.KIA}</option>
                            <option value="MAZDA">${Brand.MAZDA}</option>
                            <option value="RENAULT">${Brand.RENAULT}</option>
                            <option value="VOLVO">${Brand.VOLVO}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Model</u>
                        <select name="model">
                            <option value="A200">${Model.A200}</option>
                            <option value="A250">${Model.A250}</option>
                            <option value="SERIES_3">${Model.SERIES_3}</option>
                            <option value="SERIES_5">${Model.SERIES_5}</option>
                            <option value="A3">${Model.A3}</option>
                            <option value="A5">${Model.A5}</option>
                            <option value="INSIGNIA">${Model.INSIGNIA}</option>
                            <option value="IMPREZA">${Model.IMPREZA}</option>
                            <option value="MONDEO">${Model.MONDEO}</option>
                            <option value="CIVIC">${Model.CIVIC}</option>
                            <option value="I30">${Model.I30}</option>
                            <option value="I40">${Model.I40}</option>
                            <option value="OCTAVIA">${Model.OCTAVIA}</option>
                            <option value="SUPERB">${Model.SUPERB}</option>
                            <option value="LANCER">${Model.LANCER}</option>
                            <option value="C4">${Model.C4}</option>
                            <option value="RAV_4">${Model.RAV_4}</option>
                            <option value="CAMRY">${Model.CAMRY}</option>
                            <option value="TORO">${Model.TORO}</option>
                            <option value="OPTIMA">${Model.OPTIMA}</option>
                            <option value="RIO">${Model.RIO}</option>
                            <option value="CX_9">${Model.CX_9}</option>
                            <option value="TALISMAN">${Model.TALISMAN}</option>
                            <option value="XC_40">${Model.XC_40}</option>
                        </select>
                    </label>
                </div>
                <div class="col-md-2">
                    <label class="form-label"><u>Issue country</u>
                        <select name="issue_country">
                            <option value="GERMANY">${Country.GERMANY}</option>
                            <option value="FRANCE">${Country.FRANCE}</option>
                            <option value="ITALY">${Country.ITALY}</option>
                            <option value="USA">${Country.USA}</option>
                            <option value="JAPAN">${Country.JAPAN}</option>
                            <option value="SOUTH_KOREA">${Country.SOUTH_KOREA}</option>
                            <option value="CZECH">${Country.CZECH}</option>
                            <option value="SWEDEN">${Country.SWEDEN}</option>
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
