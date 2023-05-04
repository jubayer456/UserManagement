<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <title>User Management</title>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-lg ">
                <div class="container-fluid">
                    <a class="navbar-brand" href="<%=request.getContextPath()%>/list">User Management Application</a>
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active"
                                href="<%=request.getContextPath()%>/list">View
                                All</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="container">
            <h3 class="text-center">All User</h3>
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add User</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Email</th>
                        <th scope="col">Country</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${AllUserList}">
                    <tr>
                        <th>
                            <c:out value="${user.getId()}" />
                        </th>
                        <th>
                            <c:out value="${user.getName()}" />
                        </th>
                        <th>
                            <c:out value="${user.getEmail()}" />
                        </th>
                        <th>
                            <c:out value="${user.getCountry()}" />
                        </th>

                        <td><a href="edit?id=<c:out value="${user.getId()}" />" class="btn btn-warning
                            m-2">Update</a>
                            <a href="delete?id=<c:out value="${user.getId()}" />"
                            class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    </body>

</html>