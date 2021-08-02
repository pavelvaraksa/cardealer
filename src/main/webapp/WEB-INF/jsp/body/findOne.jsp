<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div align="center">
    <h2>User by id</h2>
    <form action="<%=request.getContextPath()%>/find-by-id" method="get">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Id</th>
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
            <c:forEach var="user" items="${oneUser}">

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
                    <td>
                        <a class="btn btn-outline-success btn-sm"
                           href="http://localhost:8080/update"
                           role="button">Find</a>
                    </td>
                </tr>

                <a class="btn btn-outline-info btn-sm"
                   href="find-by-id?id=<c:out value='${user.id}' />"
                   role="button">Return to
                    previous
                    page</a>

            </c:forEach>
            </tbody>
        </table>

        <label>
            <a class="btn btn-outline-info" href="http://localhost:8080/main-menu" role="button">Return to main menu</a>
        </label>
    </form>
</div>
</body>
</html>
