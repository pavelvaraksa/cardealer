<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #ff6347">

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/main-menu/find-all" class="nav-link">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>

        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Birth date</th>
                <th>Login</th>
                <th>Role</th>
                <th>Is blocked</th>
                <th>Created</th>
                <th>Changed</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td>
                        <c:out value="${user.id}"/>
                    </td>
                    <td>
                        <c:out value="${user.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${user.lastName}"/>
                    </td>
                    <td>
                        <c:out value="${user.birthDate}"/>
                    </td>
                    <td>
                        <c:out value="${user.login}"/>
                    </td>
                    <td>
                        <c:out value="${user.role}"/>
                    </td>
                    <td>
                        <c:out value="${user.blocked}"/>
                    </td>
                    <td>
                        <c:out value="${user.created}"/>
                    </td>
                    <td>
                        <c:out value="${user.changed}"/>
                    </td>

                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>

</html>


<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"--%>
<%--          rel="stylesheet"--%>
<%--          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"--%>
<%--          crossorigin="anonymous">--%>

<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"--%>
<%--            crossorigin="anonymous">--%>
<%--    </script>--%>
<%--</head>--%>

<%--<body>--%>
<%--<div align="center">--%>
<%--    <h2>Users list</h2>--%>
<%--&lt;%&ndash;    <form action="<%=request.getContextPath()%>/findAll" method="get">&ndash;%&gt;--%>
<%--        <table class="table table-bordered">--%>
<%--            <thead>--%>
<%--            <tr>--%>
<%--                <th>ID</th>--%>
<%--                <th>Firstname</th>--%>
<%--                <th>Lastname</th>--%>
<%--                <th>Birth date</th>--%>
<%--                <th>Login</th>--%>
<%--                <th>Role</th>--%>
<%--                &lt;%&ndash;                <th>Is Blocked</th>&ndash;%&gt;--%>
<%--                <th>Created</th>--%>
<%--                <th>Changed</th>--%>
<%--            </tr>--%>
<%--            <thead>--%>
<%--            <tbody>--%>
<%--            <c:forEach var="user" items="${listUser}">--%>
<%--                <tr>--%>
<%--                    <td>${user.id}</td>--%>
<%--                    <td>${user.firstName}</td>--%>
<%--                    <td>${user.lastName}</td>--%>
<%--                    <td>${user.birthDate}</td>--%>
<%--                    <td>${user.login}</td>--%>
<%--                    <td>${user.role}</td>--%>
<%--                        &lt;%&ndash;                    <td>${user.isBlocked}/></td>&ndash;%&gt;--%>
<%--                    <td>${user.created}</td>--%>
<%--                    <td>${user.changed}</td>--%>
<%--                    <td><a href="edit?id=<c:out value='${user.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a--%>
<%--                            href="delete?id=<c:out value='${user.id}' />">Delete</a></td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--            </tbody>--%>
<%--        </table>--%>


<%--        <table style="with: 100%">--%>
<%--            <form class="row g-3">--%>
<%--                <div class="container">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col" >--%>
<%--                            id--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box"></div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col" >--%>
<%--                            firstname--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            lastname--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            birth date--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            login--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            role--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            is blocked--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            created--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col">--%>
<%--                            changed--%>
<%--                            <div class="row">--%>
<%--                                <div class="col mini-box">1</div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--        </table>--%>
</form>
<label>
    <input class="btn btn-outline-info btn-sm" onclick="history.back();" value="Return to previous page"/>
</label>
</div>
</body>
</html>
