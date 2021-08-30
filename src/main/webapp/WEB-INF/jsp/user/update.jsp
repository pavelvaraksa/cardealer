<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="Audi_car_dealer" var="audi_car_dealer"/>
<fmt:message key="Audi_dealer_update_user_page" var="update_page"/>
<fmt:message key="Update_form" var="update_form"/>
<fmt:message key="Update" var="update"/>
<fmt:message key="Firstname" var="firstname"/>
<fmt:message key="Lastname" var="lastname"/>
<fmt:message key="Birth_date" var="birth_date"/>
<fmt:message key="Email" var="email"/>
<fmt:message key="Role" var="role"/>
<fmt:message key="Is_blocked" var="is_blocked"/>
<fmt:message key="role.user" var="user"/>
<fmt:message key="role.admin" var="admin"/>
<fmt:message key="Yes" var="yes"/>
<fmt:message key="No" var="no"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>

<fmt:message key="Firstname_help_text" var="firstname_help_text"/>
<fmt:message key="Lastname_help_text" var="lastname_help_text"/>
<fmt:message key="Email_help_text" var="email_help_text"/>

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
    <h2><u>${update_form}</u></h2>
    <form action=<%=request.getContextPath()%>"update" method="post">
        <div class="col-md-2">
            <input type="hidden" name="id" value="${param.id}">
        </div>
        <form class="row g-3">
            <div class="col-md-3">
                <label for="validationFirstname" class="form-label"><u>${firstname}</u>
                    <input type="text" class="form-control" id="validationFirstname" name="firstname"
                           value="${param.firstName}" placeholder="${param.firstName}" required>
                    <small id="firstNameHelpText" class="text-muted">${firstname_help_text}</small>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationLastname" class="form-label"><u>${lastname}</u>
                    <input type="text" class="form-control" id="validationLastname" name="lastname"
                           value="${param.lastName}" placeholder="${param.lastName}" required>
                    <small id="lastNameHelpText" class="text-muted">${lastname_help_text}</small>
                </label>
            </div>
            <div class="col-md-3">
                <label for="validationBirthdate" class="form-label"><u>${birth_date}</u>
                    <input type="date" class="form-control" id="validationBirthdate" name="birth_date"
                           value="${param.birthDate}" placeholder=${param.birthDate}>
                </label>
            </div>
            <div class="col-md-3">
                <label class="form-label"><u>${role}</u>
                    <select name="role">
                        <option value="USER">${user}</option>
                        <option value="ADMIN">${admin}</option>
                    </select>
                </label>
            </div>
            <div class="col-md-3">
                <label class="form-label"><u>${is_blocked}</u>
                    <select name="is_blocked">
                        <option value=false>${no}</option>
                        <option value=true>${yes}</option>
                    </select>
                </label>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${update}>
            </div>
            <label>
                <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/user/find-all"
                   role="button">${return_to_previous_page}</a>
            </label>
        </form>
    </form>
</div>
</body>
</html>
