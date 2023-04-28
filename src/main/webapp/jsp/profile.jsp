<%@ page import="com.motrechko.taxservice.model.User" %><%--
  Created by IntelliJ IDEA.
  User: vovam
  Date: 22.10.2022
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&amp;display=swap">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
</head>



<body>


<nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
    <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
        <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-suitcase" style="font-size: 36px;"></i></div>
        <div class="sidebar-brand-text mx-3"><span>Tax service</span></div>
    </a>
        <header></header>
        <ul class="navbar-nav text-light" id="accordionSidebar">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/jsp/profile.jsp"><i class="fas fa-user" style="font-size: 30px;"></i><span style="margin-left: 20px;">Profile</span></a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/controller?command=Reports&page=1&recordsPerPage=5"><i class="fas fa-table" style="font-size: 30px;"></i><span style="margin-left: 20px;"><strong>Reports</strong></span></a></li>
            <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/jsp/newReport.jsp"><i class="far fa-plus-square" style="font-size: 30px;"></i><span style="margin-left: 20px;"><strong>New Report</strong></span></a></li>
            <li class="nav-item"><a class="nav-link"href="${pageContext.request.contextPath}/controller?command=Logout"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 20 20" fill="none" style="font-size: 30px;">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M3 3C2.44772 3 2 3.44772 2 4V16C2 16.5523 2.44772 17 3 17C3.55228 17 4 16.5523 4 16V4C4 3.44772 3.55228 3 3 3ZM13.2929 12.2929C12.9024 12.6834 12.9024 13.3166 13.2929 13.7071C13.6834 14.0976 14.3166 14.0976 14.7071 13.7071L17.7071 10.7071C17.8946 10.5196 18 10.2652 18 10C18 9.73478 17.8946 9.48043 17.7071 9.29289L14.7071 6.29289C14.3166 5.90237 13.6834 5.90237 13.2929 6.29289C12.9024 6.68342 12.9024 7.31658 13.2929 7.70711L14.5858 9L7 9C6.44771 9 6 9.44772 6 10C6 10.5523 6.44772 11 7 11H14.5858L13.2929 12.2929Z" fill="currentColor"></path>
            </svg><span style="margin-left: 20px;"><strong>Logout</strong></span></a></li>
        </ul>
        <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="../assets/js/bs-init.js"></script>
<script src="../assets/js/theme.js"></script>
</body>
</html>