<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
<link rel="stylesheet" type="text/css" href="/resources/css/member.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/resources/js/include.js"></script>
<!-- <script src="/resources/js/scripts.js" defer></script> -->
<script src="script/member.js"></script>
</head>
<body>
<div class="header" include-html="include/header.jsp"></div>
	<div class="container">
		<div class="member_wrap">
			<h2 class="member_tit">로그인</h2>
			<form action="login.do" method="post" name="frm">
				<div class="member_box">
					<div class="box_item">
						<input type="text" name="userid" class="input" value="${userid}" placeholder="아이디">
					</div>			
					<div class="box_item">
						<input type="password" name="pwd" class="input" placeholder="비밀번호">
					</div>
				</div>
				<div class="btn_area">
					<button type="submit" class="btn btn_submit" onclick="return loginCheck();">로그인</button>
					<a href="javascript:void(0)" type="button" class="btn btn_gray" onclick="location.href='join.do'">회원가입</a>
				</div>
				<p id="loginMessage" class="msg_login ${message != null ? "" : "hidden"}">${message}</p>
			</form>
			<script>
			document.addEventListener('DOMContentLoaded', () => {
				setTimeout(() => {
					const loginMessageElem = document.getElementById("loginMessage");
					if (loginMessageElem) {
						loginMessageElem.textContent = "";
					}
				}, 2000);
			});
			</script>
		</div>
	</div>
	<div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>