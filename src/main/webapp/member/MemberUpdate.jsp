<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>회원정보수정</title>
	<link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/common.css">
	<link rel="stylesheet" type="text/css" href="/resources/css/member.css">
	<link rel="stylesheet" type="text/css" href="resources/css/list.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="/resources/js/include.js"></script>
	<!-- <script src="/resources/js/scripts.js" defer></script> -->
	<script src="script/member.js"></script>
</head>
<body>
<div class="header" include-html="include/header.jsp"></div>
	<div class="container">
		<div class="member_wrap">
			<h2 class="member_tit">회원정보수정</h2>
			<form action="memberUpdate.do" method="post" name="frm">
				<div class="member_box">
					<div class="box_item">
						<input type="text" name="name" class="input" size="20" value="${mdto.name}" style="color: pink" readonly>
											</div>
					<div class="box_item">
						<input type="text" name="userid" class="input" size="20" value="${mdto.userid}" style="color: pink" readonly>
					</div>
					<div class="box_item">
						<input type="password" name="pwd" class="input" placeholder="새 비밀번호" size="20" value="${mdto.pwd}">
					</div>
					<div class="box_item">
						<input type="password" name="pwd_check" class="input" placeholder="새 비밀번호 확인" size="20" value="${mdto.pwd}" >
					</div>
					<div class="box_item">
						<input type="text" name="email" class="input" placeholder="이메일" size="20" value="${mdto.email}" style="color: gray">
					</div>
					<div class="box_item">
						<input type="text" name="phone" class="input" placeholder="전화번호" size="20" value="${mdto.phone}"  style="color: gray">
					</div>
					<div class="box_item">
						<input type="text" name="nickname" class="input" size="20" value="${mdto.nickname}" style="color: pink" readonly>
					</div>
					<div class="btn_area">
						<button type="submit" class="btn btn_submit" onclick="return joinCheck();">확인</button>
						<button type="reset" class="btn btn_gray" onclick="location.href='main.do'">취소</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>