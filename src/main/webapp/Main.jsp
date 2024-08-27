<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 로그인 유무 확인 -->
<c:if test="${empty loginUser}">
	<jsp:forward page="/login.do"></jsp:forward>
</c:if>
<!-- 로그인 유무 확인.end -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
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
	<h2 class="member_tit">회원전용 페이지</h2>
	<form action="logout.do">
		<div class="member_box">
			<div class="box_item">
				<input type="text" name="nickname" class="input" value="${loginUser}님♥" >
			</div>	
			<div class="btn_area">
				<button type="submit" class="btn btn_gray" style="margin-bottom:10px;">로그아웃</button>
				<button type="button" class="btn btn_submit" onclick="location.href='memberUpdate.do?userid=${param.userid}'">회원정보변경</button>
				<!-- <button type="button" class="btn btn_gray" onclick="location.href='/list.do'">목록</button> -->
			</div>
		</div>
	</form>
	</div>
	</div>
<div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>		
		<%-- <table>
			<tr>
				<td>${loginUser}님 ^^</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그아웃">
					<input type="button" value="회원정보변경" onclick="location.href='memberUpdate.do?userid=${param.userid}'">
					<input type="button" value="목록" onclick="location.href='/list.do'">
				</td>
			</tr>
		</table>
	</form>
	<div class="footer" include-html="include/footer.jsp"></div>
</body>
</html> --%>