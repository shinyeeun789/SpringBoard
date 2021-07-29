<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> GAME WEB </title>

    <!-- Custom fonts for this template-->
    <link href="../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../resources/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">
		
		<jsp:include page="/WEB-INF/views/include/sideBar.jsp"/>	<!-- SideBar include -->
		
        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

<<<<<<< HEAD
                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->

                        <c:choose>
                         	<c:when test="${not empty userInfo}">
                         		<c:set var="name" value="${sessionScope.userInfo.user_name}"/>
		                        <li class="nav-item dropdown no-arrow">
		                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
		                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${name}</span>
		                                <img class="img-profile rounded-circle"
		                                    src="../resources/img/undraw_profile.svg">
		                            </a>
		                            <!-- Dropdown - User Information -->
		                            
		                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
		                                aria-labelledby="userDropdown">
		                                <a class="dropdown-item" href="#">
		                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
		                                    Profile
		                                </a>
		                                <div class="dropdown-divider"></div>
		                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
		                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
		                                    Logout
		                                </a>
		                            </div>
		                        </li>
	                        </c:when>
                 		</c:choose>

                    </ul>

                </nav>
                <!-- End of Topbar -->
=======
                <jsp:include page="/WEB-INF/views/include/topBar.jsp"/>  <!-- TopBar include -->
>>>>>>> 041f6fc5cbaec3f214e87e1147a9ef5911da1a3e

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800"> HOME </h1>
                    </div>
                    
                    <!-- Illustrations -->
                    <c:if test="${empty userInfo}">
                    	<div class="card shadow mb-4">
	                        <div class="card-header py-3">
	                            <h6 class="m-0 font-weight-bold text-primary"> INFO </h6>
	                        </div>
	                        <div class="card-body">
	                            <div class="text-center">
	                                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 25rem;"
	                                    src="../resources/img/undraw_posting_photo.svg" alt="...">
	                            </div>
	                            <p> 게임에 참여하려면 로그인 해주세요. </p>
	                            <a rel="nofollow" href="/login/login.do"> 로그인하기 &rarr;</a>
	                        </div>
	                    </div>
                    </c:if>
                    <c:if test="${not empty userInfo}">
                    	<div class="card shadow mb-4">
	                        <div class="card-header py-3">
	                            <h6 class="m-0 font-weight-bold text-primary"> GAME </h6>
	                        </div>
	                        <div class="card-body">
	                            <div class="text-center">
	                                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 20rem;"
	                                    src="../resources/img/online-game.svg" alt="...">
	                            </div>
	                            <p> 이제 게임에 참여할 수 있습니다! </p>
	                            <a rel="nofollow" href="#"> 게임하기 &rarr;</a>
	                        </div>
	                    </div>
                    </c:if>
                    
                    <!-- Illustrations -->
                    <div class="card shadow mb-4" id="howToPlay">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary"> 게임 설명 </h6>
                        </div>
                        <div class="card-body">
                            <div class="text-center">
                                <img class="img-fluid px-3 px-sm-4 mt-3 mb-4" style="width: 15rem;"
                                    src="../resources/img/arcade-machine.svg" alt="...">
                            </div>
                            <h5 font-family="IM_Hyemin-Bold"> 오목이란? </h5>
                            <p> 바둑판에 두 사람이 번갈아 돌을 놓아 가로나 세로, 대각선으로 다섯 개의 연속된 돌을 놓으면 이기는 놀이 </p><br>
                            <h5> 기본 규칙 </h5>
                            <ul>
                            	<li> 오목은 바둑판과 알을 이용하여 진행하는 게임으로 흑돌과 백돌 중 먼저 5개의 돌을 일렬로 세우는 사용자가 승리합니다. </li>
                            	<li> 육목 이상을 두어도 승패와는 전혀 상관이 없습니다. </li>
                            </ul>
                        </div>
                    </div>
                 </div>

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Game Web 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"> 로그아웃 </h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body"> 정말 로그아웃하시겠습니까? </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="/login/logout.do">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="../resources/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../resources/js/demo/chart-area-demo.js"></script>
    <script src="../resources/js/demo/chart-pie-demo.js"></script>

</body>

</html>