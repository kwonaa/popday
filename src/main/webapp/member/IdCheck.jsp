<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 중복체크</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/member.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="js/include.js"></script>
    <script src="js/scripts.js" defer></script>
	<script src="script/member.js"></script>
</head>
<body>
	<div class="recheck_popup">
        <h2 class="popup_tit">아이디 중복확인</h2>
		<form action="idCheck.do" method="get" name="frm">
				<div class="recheck_box">
					<input type="text" name="userid" value="${userid}">
					<button type="submit" class="btn btn_gray">확인</button>
				</div>
			<c:if test="${result==1}">
			<script>
				opener.document.frm.userid.value=""; //부모창(opener) 폼의 userid 초기화
			</script> 
				<p class="msg_login">${userid}는 이미 사용중인 아이디입니다.</p>
			</c:if>
			<c:if test="${result==-1}">
				<p class="msg_login">${userid}는 사용 가능한 아이디입니다.</p>
				<div class="btn_area">
					<button type="submit" class="btn btn_submit" onclick="idok('${userid}')">사용</button>
				</div>
			</c:if>
		</form>
    </div>
</body>
</html>