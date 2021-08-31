<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="audi.car.dealer" var="audi_car_dealer"/>
<fmt:message key="info.company.history" var="company_history"/>
<fmt:message key="info.audi.dealer.history.page" var="history_page"/>
<fmt:message key="button.return.to.previous.page" var="return_to_previous_page"/>
<fmt:message key="the.audi.brand.has.been.officially.represented.on.the.belarusian.market.since.1994." var="item_1"/>
<fmt:message
        key="as.of.today.there.are.6.official.audi.dealers.in.belarus.audi.car.owners.can.choose.one.of.the.centers.with.the.most.convenient.location.for.them."
        var="item_2"/>
<fmt:message
        key="all.centers.are.built.according.to.the.new.corporate.concept.audi.terminal.and.combines.a.spacious.showroom.which.presents.the.most.popular.models.of.the.company.from.ingolstadt.as.well.as.a.large.service.area."
        var="item_3"/>
<fmt:message
        key="works.in.dealerships.are.carried.out.by.a.team.of.professionals.with.many.years.of.experience.with.the.audi.brand.in.mapping.with.the.quality.standards.of.the.german.manufacturing.plant."
        var="item_4"/>
<fmt:message
        key="audi.was.founded.on.july.16.1909.by.german.engineer.august.horch.the.company's.technological.leadership.is.stored.in.the.slogan.superiority.of.high.technologies."
        var="item_5"/>
<fmt:message
        key="at.the.moment.the.company.is.one.of.the.three.leading.premium.class.manufacturers.in.a.challenging.2020.marked.by.global.restrictions.tribute.to.the.coronavirus.pandemic.audi.delivered.a.total.of.1.692.773.models."
        var="item_6"/>
<fmt:message
        key="nevertheless.the.last.quarter.was.the.most.successful.in.the.history.of.audi.ag.from.october.to.december.the.company.delivered.505.583.vehicles.to.its.customers."
        var="item_7"/>
<fmt:message
        key="the.modern.range.of.audi.cars.includes.multitude.of.passenger.models.from.the.compact.a1.hatchback.to.the.executive.sedan.a8.the.suv.class.models.q2.q3.q5.q7.and.q8.as.well.as.sports.modifications.s."
        var="item_8"/>
<fmt:message key="the.audi.sport.division.is.engaged.in.the.development.and.production.of.sports.rs.models."
             var="item_9"/>

<html>
<head>
    <title>${history_page}</title>
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
    <div>
        <h2><u>${company_history}</u></h2>
    </div>
    <p class="text-lg-center">-${item_1}</p>
    <p class="text-lg-center">-${item_2}</p>
    <p class="text-lg-center">-${item_3}</p>
    <p class="text-lg-center">-${item_4}</p>
    <p class="text-lg-center">-${item_5}</p>
    <p class="text-lg-center">-${item_6}</p>
    <p class="text-lg-center">-${item_7}</p>
    <p class="text-lg-center">-${item_8}</p>
    <p class="text-lg-center">-${item_9}</p>

    <label>
        <a class="btn btn-outline-primary" href=<%=request.getContextPath()%>"/"
           role="button">${return_to_previous_page}</a>
    </label>
</div>
</body>
</html>
