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
<nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080">CAR DEALER APPLICATION</a>
    </div>
</nav>
<div align="center">
    <h2><u>Update form</u></h2>

    <form action="update" method="post">
        <input type="hidden" name="id" value="${param.id}">
        <label>
            <input type="text" name="firstname" value="${param.firstName}" placeholder=${param.firstName}>
        <label>
            <input type="text" name="lastname" value="${param.lastName}" placeholder=${param.lastName}>
        </label>
        <label>
            <input type="date" name="birth_date" value="${param.birthDate}" placeholder=${param.birthDate} >
        </label>
        <label>
            <input type="email" name="email" value="${param.email}" placeholder=${param.email}>
        </label>
        <label>
            <input type="text" name="role" value="${param.role}" placeholder=${param.role}>
            <select class="col-md-3" id="exampleFormControlRole" name="role">
                <option>USER</option>
                <option>MANAGER</option>
                <option>ADMIN</option>
            </select>
        </label>
        <label>
            <input type="text" name="is_blocked" value="${param.blocked}" placeholder=${param.blocked}>
            <select class="col-md-3" id="exampleFormControlBlocked" name="is_blocked">
                <option>false</option>
                <option>true</option>
            </select>
        </label>
        <input class="btn btn-outline-success btn-sm" type="submit" value="Update">
    </form>
    <label>
        <a class="btn btn-outline-primary btn-sm" href="http://localhost:8080/find-all" role="button">Return to previous
            page</a>
    </label>
</div>
</body>
</html>
