<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tag" uri="customtags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="menu.title.main.menu" var="main_menu"/>
<fmt:message key="menu.title.log.out" var="log_out"/>
<fmt:message key="menu.title.users.list" var="users_list"/>
<fmt:message key="menu.title.user.orders.list" var="user_orders_list"/>
<fmt:message key="menu.title.cars.list" var="cars_list"/>
<fmt:message key="menu.title.bodies.list" var="bodies_list"/>
<fmt:message key="menu.title.transmissions.list" var="transmissions_list"/>
<fmt:message key="menu.title.engines.list" var="engines_list"/>
<fmt:message key="menu.title.dealers.list" var="dealers_list"/>

<html>
<head>
    <title>${main_menu}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
        <body style="background-color:antiquewhite"></body>
        <div class="collapse navbar-collapse" id="navbarsExample03">
            <ul class="navbar-nav me-auto mb-2 mb-sm-0">
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/logout">${log_out}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/user/find-all">${users_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/user-order/find-all">${user_orders_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/dealer/find-all">${dealers_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/car/find-all">${cars_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/body/find-all">${bodies_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/engine/find-all">${engines_list}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href=<%=request.getContextPath()%>"/transmission/find-all">${transmissions_list}</a>
                </li>
            </ul>
        </div>
        <form>
            <label for="language"></label>
            <select id="language" name="language" onchange="submit()">
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>english</option>
                <option value="de" ${language == 'de' ? 'selected' : ''}>deutsche</option>
            </select>
        </form>
    </div>
</nav>
<div>
    <div style="position: absolute;color: white; padding-left: 30px; font-size: large"><tag:welcome/></div>
    <img src="${pageContext.request.contextPath}/image/center.jpg" width="100%" height="auto">
</div>
</body>
</html>



