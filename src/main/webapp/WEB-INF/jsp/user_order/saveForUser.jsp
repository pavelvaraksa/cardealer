<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.save.user.order.page" var="save_page"/>
<fmt:message key="button.save" var="save"/>
<fmt:message key="button.yes" var="yes"/>
<fmt:message key="button.no" var="no"/>
<fmt:message key="text.add.user.order.text" var="add_to_order"/>
<fmt:message key="user.user.id" var="user_id"/>
<fmt:message key="car.car.id" var="car_id"/>
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
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h3><u>${add_to_order}?</u></h3>
    <form action=<%=request.getContextPath()%>"/user-order-for-user/save-for-user" method="post">
        <form class="row g-3">
            <div class="col-md-3">
                <input type="hidden" name="id" value="${param.id}">
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${save}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/car-for-user/find-all-for-user"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
