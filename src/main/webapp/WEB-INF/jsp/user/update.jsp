<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--@elvariable id="language" type=""--%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="page.audi.dealer.update.user.page" var="update_page"/>
<fmt:message key="form.update.form" var="update_form"/>
<fmt:message key="button.update" var="update"/>
<fmt:message key="user.firstname" var="firstname"/>
<fmt:message key="user.lastname" var="lastname"/>
<fmt:message key="user.birth_date" var="birth_date"/>
<fmt:message key="user.phone_number" var="phone_number"/>
<fmt:message key="user.email" var="email"/>
<fmt:message key="user.role" var="role"/>
<fmt:message key="user.is.blocked" var="is_blocked"/>
<fmt:message key="role.user" var="user"/>
<fmt:message key="role.admin" var="admin"/>
<fmt:message key="button.yes" var="yes"/>
<fmt:message key="button.no" var="no"/>
<fmt:message key="text.firstname.help.text" var="firstname_help_text"/>
<fmt:message key="text.lastname.help.text" var="lastname_help_text"/>
<fmt:message key="text.email.help.text" var="email_help_text"/>
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
                <label for="validationPhoneNumber" class="form-label"><u>${phone_number}</u>
                    <input type="tel" class="form-control" id="validationPhoneNumber" name="phone_number"
                           value="${param.phoneNumber}" placeholder=${param.phoneNumber}>
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
