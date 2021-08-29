<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :
pageContext.request.locale}" scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<fmt:message key="Audi_car_dealer" var="audi_car_dealer"/>
<fmt:message key="Company_history" var="company_history"/>
<fmt:message key="Audi_dealer_history_page" var="history_page"/>
<fmt:message key="Return_to_previous_page" var="return_to_previous_page"/>
<fmt:message key="The_Audi_brand_has_been_officially_represented_on_the_Belarusian_market_since_1994." var="item_1"/>
<fmt:message
        key="As_of_today,_there_are_6_official_Audi_dealers_in_Belarus._Audi_car_owners_can_choose_one_of_the_centers_with_the_most_convenient_location_for_them."
        var="item_2"/>
<fmt:message
        key="All_centers_are_built_according_to_the_new_corporate_concept_Audi_Terminal_and_combines_a_spacious_showroom,_which_presents_the_most_popular_models_of_the_company_from_Ingolstadt,_as_well_as_a_large_service_area."
        var="item_3"/>
<fmt:message
        key="Works_in_dealerships_are_carried_out_by_a_team_of_professionals_with_many_years_of_experience_with_the_Audi_brand_in_mapping_with_the_quality_standards_of_the_German_manufacturing_plant."
        var="item_4"/>
<fmt:message
        key="Audi_was_founded_on_July_16,_1909_by_German_engineer_August_Horch._The_company's_technological_leadership_is_stored_in_the_slogan_Superiority_of_high_technologies."
        var="item_5"/>
<fmt:message
        key="At_the_moment,_the_company_is_one_of_the_three_leading_premium_class_manufacturers._In_a_challenging_2020,_marked_by_global_restrictions_tribute_to_the_coronavirus_pandemic,_Audi_delivered_a_total_of_1,692,773_models."
        var="item_6"/>
<fmt:message
        key="Nevertheless,_the_last_quarter_was_the_most_successful_in_the_history_of_AUDI_AG_from_October_to_December,_the_company_delivered_505,583_vehicles_to_its_customers."
        var="item_7"/>
<fmt:message
        key="The_modern_range_of_Audi_cars_includes_multitude_of_passenger_models_from_the_compact_A1_hatchback_to_the_executive_sedan_A8,_the_SUV_class_models_Q2,_Q3,_Q5,_Q7_and_Q8,_as_well_as_sports_modifications_S."
        var="item_8"/>
<fmt:message key="The_AudiSport_division_is_engaged_in_the_development_and_production_of_sports_RS_models."
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
