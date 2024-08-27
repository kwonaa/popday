<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원가입</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/member.css">
	<link rel="stylesheet" type="text/css" href="resources/css/list.css">
	
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/js/include.js"></script>
	<!-- <script src="/resources/js/scripts.js" defer></script> -->
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	<script src="script/member.js"></script>
</head>
<body>
<div class="header" include-html="include/header.jsp"></div>
<div class="container">
	<div class="member_wrap">
		<h2 class="member_tit">회원가입</h2>	
		<form action="/join.do" method="post" name="frm">
		 	<div class="member_box">
		 		<div class="box_item">
		 			<input type="text" name="name" class="input" placeholder="이름" size="20">
		 		</div>
				<div class="re_check">
		        	<div class="box_item">
		            	<input type="text" name="userid" class="input" placeholder="아이디" size="20" id="userid">
		                <input type="hidden" name="reid" size="20">
		        	</div>
		        	<button type="button" class="btn_check" onclick="idCheck()">중복확인</button>
				</div>
		        <div class="box_item">
		        	<input type="password" name="pwd" class="input" placeholder="비밀번호" size="20">
		        </div>
		        <div class="box_item">
		             <input type="password" name="pwd_check" class="input" placeholder="비밀번호 확인" size="20">
		        </div>
		        <div class="box_item">
		             <input type="text" name="email" class="input" placeholder="이메일" size="20">
		        </div>
		        <div class="box_item">
		            <input type="text" name="phone" class="input" placeholder="전화번호" maxlength="13">
		        </div>
		        <div class="re_check">
		        	<div class="box_item">
		        		<input type="text" name="nickname" class="input" placeholder="닉네임" size="20" id="nickname">
		        		<input type="hidden" name="renickname" size="20">
		        	</div>
		        	<button type="button" class="btn_check" onclick="nicknameCheck()">중복확인</button>
		        </div>
		        <div class="btn_area">
		        	<button type="submit" class="btn btn_submit" onclick="return joinCheck()">가입</button>	        	
		        	<button type="reset" class="btn btn_gray"  onclick="location.href='main.do'" >취소</button>
		        </div>
		        <p id="loginMessage" class="msg_login ${message != null ? "" : "hidden"}">${message}</p>
		       </div>
		    </form>
	</div>
</div>
<div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>			