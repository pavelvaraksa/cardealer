<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="menu.title.log.in" var="login"/>
<fmt:message key="button.register" var="register"/>
<fmt:message key="info.about.us" var="about_us"/>
<fmt:message key="info.contacts" var="contacts"/>
<fmt:message key="homepage" var="homepage"/>

<html>
<head>
    <title>${homepage}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href=<%=request.getContextPath()%>"/">${audi_car_dealer}</a>
        <div class="collapse navbar-collapse" id="navbarsExample03">
            <ul class="navbar-nav me-auto mb-2 mb-sm-0">
                <li class="nav-item">
                    <a class="nav-link" id="login" href="login-auth">${login}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="register" href="register">${register}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="about" href="company">${about_us}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="contacts" href="request-information">${contacts}</a>
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
    <img src="${pageContext.request.contextPath}/image/showRoom.jpg" width="100%" height="auto">
</div>
</body>
</html>




