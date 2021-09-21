<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="form.register.form" var="register_form"/>
<fmt:message key="page.audi.dealer.register.page" var="register_page"/>
<fmt:message key="user.firstname" var="firstname"/>
<fmt:message key="user.lastname" var="lastname"/>
<fmt:message key="user.birth_date" var="birth_date"/>
<fmt:message key="user.login" var="login"/>
<fmt:message key="user.password" var="password"/>
<fmt:message key="user.email" var="email"/>
<fmt:message key="info.required.field" var="required_field"/>
<fmt:message key="button.register" var="register"/>
<fmt:message key="info.birthdate.field" var="birth_date_field"/>
<fmt:message key="text.firstname.help.text" var="firstname_help_text"/>
<fmt:message key="text.lastname.help.text" var="lastname_help_text"/>
<fmt:message key="text.login.help.text" var="login_help_text"/>
<fmt:message key="text.password.help.text" var="password_help_text"/>
<fmt:message key="text.email.help.text" var="email_help_text"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>

<html>
<head>
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
    <h2><u>${register_form}</u></h2>
    <p class="text-dark">* - ${required_field}</p>
    <form action=<%=request.getContextPath()%>"/user/save" method="post">
        <form class="row g-3">
            <div class="col-md-3">
                <label for="validationFirstname" class="form-label"><u>${firstname}*</u></label>
                <input type="text" class="form-control" id="validationFirstname" name="firstname" required>
                <small id="firstNameHelpText" class="text-muted">${firstname_help_text}</small>
            </div>
            <div class="col-md-3">
                <label for="validationLastname" class="form-label"><u>${lastname}*</u></label>
                <input type="text" class="form-control" id="validationLastname" name="lastname" required>
                <small id="lastNameHelpText" class="text-muted">${lastname_help_text}</small>
            </div>
            <div class="col-md-3">
                <label for="validationBirthDate" class="form-label"><u>${birth_date}</u></label>
                <input type="date" class="form-control" id="validationBirthDate" name="birth_date">
            </div>
            <div class="col-md-3">
                <label for="validationLogin" class="form-label"><u>${login}*</u></label>
                <input type="text" class="form-control" id="validationLogin" name="login" required>
                <small id="loginHelpText" class="text-muted">${login_help_text}</small>
            </div>
            <div class="col-md-3">
                <label for="validationPassword" class="form-label"><u>${password}*</u></label>
                <input type="password" class="form-control" id="validationPassword" name="password" required>
                <small id="passwordHelpText" class="text-muted">${password_help_text}</small>
            </div>
            <div class="col-md-3">
                <label for="validationEmail" class="form-label"><u>${email}*</u></label>
                <input type="email" class="form-control" id="validationEmail" name="email" required>
                <small id="emailHelpText" class="text-muted">${email_help_text}</small>
            </div>
            <div class="col-12">
                <input class="btn btn-outline-success btn-sm" type="submit" value=${register}>
            </div>
        </form>
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href=<%=request.getContextPath()%>"/"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
