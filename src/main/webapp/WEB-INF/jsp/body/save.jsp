<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.save.body.page" var="save_page"/>
<fmt:message key="form.save.form" var="save_form"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="car.color" var="color"/>
<fmt:message key="car.body.type" var="body_type"/>
<fmt:message key="car.car.id" var="car_id"/>
<fmt:message key="body.type.coupe" var="coupe"/>
<fmt:message key="body.type.sedan" var="sedan"/>
<fmt:message key="body.type.touring" var="touring"/>
<fmt:message key="body.type.hatchback" var="hatchback"/>
<fmt:message key="body.type.suv" var="suv"/>
<fmt:message key="color.black" var="black"/>
<fmt:message key="color.white" var="white"/>
<fmt:message key="color.red" var="red"/>
<fmt:message key="color.blue" var="blue"/>
<fmt:message key="color.brown" var="brown"/>
<fmt:message key="color.green" var="green"/>
<fmt:message key="color.grey" var="grey"/>
<fmt:message key="color.yellow" var="yellow"/>
<fmt:message key="color.orange" var="orange"/>
<fmt:message key="color.silver" var="silver"/>
<fmt:message key="color.champagne" var="champagne"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${save_page}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
        <form>
            <label for="language"></label>
            <select id="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>??????????????</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>english</option>
                <option value="de" ${language == 'de' ? 'selected' : ''}>deutsche</option>
            </select>
        </form>
    </div>
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h2><u>${save_form}</u></h2>
    <form action=<%=request.getContextPath()%>"/body/save" method="post">
        <form class="row g-3">
            <div class="col-md-3">
                <label class="form-label"><u>${color}</u>
                    <select name="color">
                        <option value="BLACK">${black}</option>
                        <option value="WHITE">${white}</option>
                        <option value="RED">${red}</option>
                        <option value="GREEN">${green}</option>
                        <option value="BLUE">${blue}</option>
                        <option value="YELLOW">${yellow}</option>
                        <option value="BROWN">${brown}</option>
                        <option value="SILVER">${silver}</option>
                        <option value="ORANGE">${orange}</option>
                        <option value="GREY">${grey}</option>
                        <option value="CHAMPAGNE">${champagne}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label class="form-label"><u>${body_type}</u>
                    <select name="body_type">
                        <option value="SEDAN">${sedan}</option>
                        <option value="COUPE">${coupe}</option>
                        <option value="TOURING">${touring}</option>
                        <option value="HATCHBACK">${hatchback}</option>
                        <option value="SUV">${suv}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationCarId" class="form-label"><u>${car_id}</u></label>
                <input type="text" class="form-control" id="validationCarId" name="car_id" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${save}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/body/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
