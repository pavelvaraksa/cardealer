<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="text.delete.car.text" var="delete_car_text"/>
<fmt:message key="button.yes" var="yes"/>
<fmt:message key="button.no" var="no"/>
<fmt:message key="page.audi.dealer.delete.car.page" var="delete_page"/>

<html>
<head>
    <title>${delete_page}</title>
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
    <h3><u>${delete_car_text} ${param.id}?</u></h3>
    <form action=<%=request.getContextPath()%>"delete" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <a class="btn btn-outline-success btn-sm" href=<%=request.getContextPath()%>"/car/find-all"
           role="button">${no}</a>
        <input class="btn btn-outline-danger btn-sm" type="submit" value=${yes}>
    </form>
</div>
</body>
</html>
