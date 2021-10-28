<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="user" type="java.util.List"--%>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="user.firstname" var="firstname"/>
<fmt:message key="user.lastname" var="lastname"/>
<fmt:message key="user.birth_date" var="birth_date"/>
<fmt:message key="user.phone_number" var="phone_number"/>
<fmt:message key="user.password" var="password"/>
<fmt:message key="user.login" var="login"/>
<fmt:message key="user.email" var="email"/>
<fmt:message key="info.created" var="created"/>
<fmt:message key="info.changed" var="changed"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="menu.title.user.profile" var="user_profile"/>
<fmt:message key="page.audi.dealer.profile.page" var="user_profile_page"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
    <title>${user_profile_page}</title>
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
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>русский</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>english</option>
                <option value="de" ${language == 'de' ? 'selected' : ''}>deutsche</option>
            </select>
        </form>
    </div>
</nav>
<body style="background-color:antiquewhite"></body>
<div align="center">
    <h2><u>${user_profile}</u></h2>
    <form action=<%=request.getContextPath()%>"/user-info/find-user" method="get">
        <body>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>${firstname}</th>
                <th>${lastname}</th>
                <th>${birth_date}</th>
                <th>${phone_number}</th>
                <th>${login}</th>
                <th>${email}</th>
                <th>${created}</th>
                <th>${changed}</th>
            </tr>
            </thead>

            <c:forEach var="user" items="${user}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.birthDate}</td>
                    <td>${user.phoneNumber}</td>
                    <td>${user.login}</td>
                    <td>${user.email}</td>
                    <td>${user.created}</td>
                    <td>${user.changed}</td>
                </tr>
            </c:forEach>
        </table>
        </body>
        <label>
            <a class="btn btn-outline-success" href=<%=request.getContextPath()%>"/user-info/update-user-by-login"
               role="button">${update}</a>
        </label>
    </form>
    <label>
    <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/user-menu"
       role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
