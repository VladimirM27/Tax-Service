<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vovam
  Date: 28.10.2022
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Table - Brand</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i&amp;display=swap">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/Articles-Cards-images.css">
</head>

<body id="page-top">
<div id="wrapper">
    <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
        <div class="container-fluid d-flex flex-column p-0"><a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
            <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-suitcase" style="font-size: 36px;"></i></div>
            <div class="sidebar-brand-text mx-3"><span>Tax service<br>Inspector<br></span></div>
        </a>
            <header></header>
            <ul class="navbar-nav text-light" id="accordionSidebar">
                <li class="nav-item"></li>
                <li class="nav-item"><a class="nav-link active" href="table.html"><i class="fas fa-table" style="font-size: 30px;"></i><span style="margin-left: 20px;"><strong>Reports</strong></span></a></li>
                <li class="nav-item"><a class="nav-link" href="login.html"><i class="icon ion-stats-bars" style="font-size: 30px;"></i><span style="margin-left: 20px;"><strong>Stats</strong><br></span></a></li>
                <li class="nav-item"><a class="nav-link" href="register.html"><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 20 20" fill="none" style="font-size: 30px;">
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M3 3C2.44772 3 2 3.44772 2 4V16C2 16.5523 2.44772 17 3 17C3.55228 17 4 16.5523 4 16V4C4 3.44772 3.55228 3 3 3ZM13.2929 12.2929C12.9024 12.6834 12.9024 13.3166 13.2929 13.7071C13.6834 14.0976 14.3166 14.0976 14.7071 13.7071L17.7071 10.7071C17.8946 10.5196 18 10.2652 18 10C18 9.73478 17.8946 9.48043 17.7071 9.29289L14.7071 6.29289C14.3166 5.90237 13.6834 5.90237 13.2929 6.29289C12.9024 6.68342 12.9024 7.31658 13.2929 7.70711L14.5858 9L7 9C6.44771 9 6 9.44772 6 10C6 10.5523 6.44772 11 7 11H14.5858L13.2929 12.2929Z" fill="currentColor"></path>
                </svg><span style="margin-left: 20px;"><strong>Logout</strong></span></a></li>
            </ul>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>
    <div class="d-flex flex-column" id="content-wrapper">
        <div id="content">
            <div class="container-fluid">
                <h3 class="text-dark mb-4">Reports</h3>
                <div class="card shadow">
                    <div class="card-header py-3">
                        <p class="text-primary m-0 fw-bold">Report Info</p>
                    </div>
                    <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">&nbsp;Reprot type: ${UnverifiedReportsView.type}&nbsp;</h2>
                    <div class="card-body">
                        <hr>
                        <div class="row">
                            <div class="col">
                                <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">&nbsp;User first name: ${UnverifiedReportsView.firstName}</h2>
                            </div>
                            <div class="col">
                                <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">   User Last Name: ${UnverifiedReportsView.lastName}</h2>
                            </div>
                            <div class="col">
                                <p class="font-monospace fs-1 text-center d-xxl-flex">&nbsp;User email: ${UnverifiedReportsView.email}</p>
                            </div>
                            <div class="col">
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;User TIN: ${UnverifiedReportsView.TIN}</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">&nbsp;City: ${UnverifiedReportsView.city}</h2>
                            </div>
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">Street: ${UnverifiedReportsView.street}</h2>
                            </div>
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace text-capitalize fs-1 fw-normal text-center d-xxl-flex">Number of Building: ${UnverifiedReportsView.numberOfBuilding}</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;Date of report : ${UnverifiedReportsView.date}</h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;Profit Sum: ${UnverifiedReportsView.profitSum}</h2>
                                <hr>
                            </div>
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;Tax sum: ${UnverifiedReportsView.taxSum}</h2>
                                <hr>
                            </div>
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;Fine: ${UnverifiedReportsView.fine}</h2>
                                <hr>
                            </div>
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;penny: ${UnverifiedReportsView.penny}</h2>
                                <hr>
                            </div>
                        </div>



                        <div class="row">
                            <div class="col">
                                <hr>
                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;User comment: ${UnverifiedReportsView.userComment} <br></h2>
                                <hr>
                                <div class="row">
                                    <div class="col">
                                        <form action="${pageContext.request.contextPath}/controller" method="post">
                                            <input id="POST-command" type="hidden" name="command" value="AcceptReport" >
                                            <input id="idReport" type="hidden" name="idReport" value="${param.idReport}" >
                                            <div class="col">
                                                <h2 class="font-monospace fs-1 fw-normal text-center d-xxl-flex">&nbsp;Inspector comments:&nbsp;</h2>
                                            </div>
                                            <div class="col">
                                                <textarea class="form-control" name="inspectorComment"></textarea>
                                            </div>


                                    </div>
                                    <div class="row">
                                        <div class="col mt-4" align="center">
                                            <button value="true" name="accepted" class="btn btn-primary" type="submit" >Accept Report</button>
                                        </div>

                                        <div class="col mt-4">
                                            <button value="false" name="accepted" class="btn btn-primary" type="submit">Submit for editing</button>
                                        </div>

                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="bg-white sticky-footer">
        <div class="container my-auto">
            <div class="text-center my-auto copyright"></div>
        </div>
    </footer>
</div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/bs-init.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/theme.js"></script>
</body>

</html>
