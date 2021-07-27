<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title> GAME WEB </title>

    <!-- Custom fonts for this template-->
    <link href="../../resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../../resources/css/sb-admin-2.min.css" rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js" type="text/javascript"></script>
	
	<script type=text/javascript>
		$(document).ready(function(){
			$("#btnIdCheck").click(userIDCheck);
			$("#btnSignUp").click(userSignUp);
			$("#userName").focus();
		});
		
		userIDCheck = function() {
			console.log('아이디 체크');
		}
		
		userSignUp = function() {
			var frm = document.signUp_frm;
			var userName = frm.userName;
			var userID = frm.userID;
			var userPW = frm.userPW;
			var userRepeatPW = frm.userRepeatPW;
			
			if(chkInputSpace(userID.value) > -1) {
				$("#spanMSG").css("font-size", "12px").html("아이디에 공백문자를 넣을 수 없습니다.");
				userID.focus();
			} else if(chkInputSpace(userPW.value) > -1) {
				$("#spanMSG").css("font-size", "12px").html("비밀번호에 공백문자를 넣을 수 없습니다.");
				userPW.focus();
			} else if(userName.value.length < 1 || userID.value.length < 1 || userPW.value.length < 1 || userRepeatPW.value.length < 1) {
				$("#spanMSG").css("font-size", "12px").html("입력되지 않은 항목이 있습니다.");
			} else if(userPW.value != userRepeatPW.value) {
				$("#spanMSG").css("font-size", "12px").html("비밀번호와 비밀번호 확인이 일치하지 않습니다");
				userPW.focus();
			} else if(checkPassword(userPW)) {
				alert('회원가입 진행!!');
			}
		}
		
		chkInputSpace = function(text) {
			var pattern = /\s/;
			return text.search(pattern);
		}
		
		checkPassword = function(pw) {
			if(!/^[a-zA-Z0-9]{8,20}$/.test(pw.value)) {
				$("#spanMSG").css("font-size", "12px").html("비밀번호는 숫자와 영문자 조합으로 8~20자리로 설정할 수 있습니다.");
				userPW.focus();
				return false;
			}
			
			var chk_num = pw.value.search(/[0-9]/g);
			var chk_eng = pw.value.search(/[a-z]/ig);
			if(chk_num < 0 || chk_eng < 0) {
				$("#spanMSG").css("font-size", "12px").html("비밀번호는 숫자와 영문자를 혼용하여 사용하여야 합니다.");
				userPW.focus();
				return false;
			}
			return true;
		}
	</script>
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                        	<div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form method="post" class="user" name="signUp_frm" onsubmit="return false">
                               	<div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="userName"
                                        placeholder="닉네임">
                                </div>
                                <div class="form-group row">
                                	<div class="col-sm-8 mb-3 mb-sm-0">
                                		<input type="text" class="form-control form-control-user" id="userID"
                                        placeholder="아이디">
                                	</div>
                                	<div class="col-sm-4">
                                		<a class="btn btn-secondary btn-user btn-block" id="btnIdCheck"> 중복 확인 </a>
                                	</div>
                                </div>
                                <div class="form-group">
                                	<input type="password" class="form-control form-control-user"
                                            id="userPW" placeholder="비밀번호">
                                </div>
                                <div class="form-group">
                                	<input type="password" class="form-control form-control-user"
                                            id="userRepeatPW" placeholder="비밀번호 확인">
                                </div>
                                <div class="form-group text-center">
                                	<span class="text-danger" id="spanMSG"></span>
                                </div>
                                <button type="submit" class="btn btn-primary btn-user btn-block" id="btnSignUp">
                                	회원가입
                                </button>
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="/login/login.do"> 이미 계정이 있나요? </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../../resources/js/sb-admin-2.min.js"></script>

</body>

</html>