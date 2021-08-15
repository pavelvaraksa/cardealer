<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="Log_in" var="buttonValue1"/>
<fmt:message key="Password" var="buttonValue2"/>

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
    <h2><u>Authentication form</u></h2>
    <form action="login" method="post">
        <table style="with: 100%">
            <div class="col-md-2">
<%--                <label for="validationLogin" class="form-label"><u>Login</u></label>--%>
                <input type="text" id="login1" class="form-control" name="login" required>
                <label for="login1">${buttonValue1}</label>
            </div>
            <div class="col-md-2">
<%--                <label for="validationPassword" class="form-label"><u>Password</u></label>--%>
                <input type="password" class="form-control" id="password" name="password" required>
                <label for="password">${buttonValue2}</label>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value="${buttonValue1}">
            </div>
            <label>
                <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080" role="button">Return to
                    previous
                    page</a>
            </label>
        </table>
    </form>
</div>
<form>
    <label for="language"></label><select id="language" name="language" onchange="submit()">
    <option value="en" ${language == 'en' ? 'selected' : ''}>Eng</option>
    <option value="ru" ${language == 'ru' ? 'selected' : ''}>Рус</option>
</select>
</form>
</body>
</html>
