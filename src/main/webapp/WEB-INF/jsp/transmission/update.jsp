<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.update.transmission.page" var="update_page"/>
<fmt:message key="form.update.form" var="update_form"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="transmission.transmission.type" var="transmission_type"/>
<fmt:message key="transmission.gears.count" var="gears_count"/>
<fmt:message key="transmission.weight" var="weight"/>
<fmt:message key="transmission.type.automatic" var="automatic"/>
<fmt:message key="transmission.type.mechanic" var="mechanic"/>
<fmt:message key="transmission.type.robotic" var="robotic"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${update_page}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
    </div>
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h2><u>${update_form}</u></h2>
    <form action=<%=request.getContextPath()%>"update" method="post">
        <form class="row g-3">
            <div class="col-md-2">
                <input type="hidden" name="id" value="${param.id}">
            </div>
            <div class="col-md-3">
                <label class="form-label"><u>${transmission_type}</u>
                    <select name="transmission_type">
                        <option value="AUTOMATIC">${automatic}</option>
                        <option value="MECHANIC">${mechanic}</option>
                        <option value="ROBOTIC">${robotic}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationGearsCount" class="form-label"><u>${gears_count}</u></label>
                <input type="text" class="form-control" id="validationGearsCount" name="gears_count"
                       value="${param.gearsCount}" placeholder="${param.gearsCount}" required>
            </div>
            <div class="col-md-3">
                <label for="validationWeight" class="form-label"><u>${weight}</u></label>
                <input type="text" class="form-control" id="validationWeight" name="weight"
                       value="${param.weight}" placeholder="${param.weight}" required>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${update}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/transmission/find-all"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
