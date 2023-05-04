<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
                crossorigin="anonymous">
            <title>User Form</title>
        </head>

        <body>
            <header>
                <nav class="navbar navbar-expand-lg ">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/list">User Management Application</a>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" href="<%=request.getContextPath()%>/list">View
                                    All</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            </br>
             <div>
                            <div class="card w-50 mx-auto">
                                <div class="card-body">
                                    <c:if test="${user!=null}">
                                        <form action="update" method="post">
                                    </c:if>
                                    <c:if test="${user==null}">
                                        <form action="insert" method="post">
                                    </c:if>
                                    <caption>
                                        <h2>
                                            <c:if test="${user!=null}">
                                                Edit user
                                            </c:if>
                                            <c:if test="${user==null}">
                                                Add New User
                                            </c:if>
                                        </h2>
                                    </caption>
                                    <c:if test="${user!=null}">
                                        <div class="mb-3">
                                            <label for="id" class="form-label fw-semibold">Id:</label>
                                            <input type="text" class="form-control" name="id" value="${user.getId()}" readonly>
                                        </div>
                                    </c:if>

                                    <div class="mb-3">
                                        <label for="name" class="form-label fw-semibold">Name:</label>
                                        <input type="text" class="form-control" name="name" value="${user.getName()}"
                                            required="required">
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label fw-semibold">Email:</label>
                                        <input type="email" class="form-control" name="email" value="${user.getEmail()}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="country" class="form-label fw-semibold">Country:</label>
                                        <input type="text" class="form-control" id="text" name="country" value="${user.getCountry()}">
                                    </div>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                    </form>
                                </div>
                            </div>

                        </div>
        </body>

        </html>