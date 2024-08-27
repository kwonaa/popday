<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/member.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="js/include.js"></script>
    <!-- <script src="js/scripts.js" defer></script> -->
<script src="script/member.js"></script>
</head>
<body>
	<div class="recheck_popup">
        <h2 class="popup_tit">닉네임 중복확인</h2>
	<form action="nicknameCheck.do" method="get" name="frm">
            <div class="recheck_box">
            	<input type="text" name="nickname" class="" value="${nickname}">
            	<button type="submit" class="btn btn_gray">확인</button>
            </div>
		<c:if test="${result==1}">
		<script>
			opener.document.frm.nickname.value=""; //부모창(opener) 폼의 nickname 초기화
		</script> 
            <p class="msg_login">${nickname}은 이미 사용중인 닉네임입니다.</p>
		</c:if>
		<c:if test="${result==-1}">
        	<p class="msg_login">${nickname}는 사용 가능한 닉네임입니다.</p>
            <div class="btn_area">
                <button type="submit" class="btn btn_submit" onclick="nicknameok('${nickname}')">사용</button>
            </div>
		</c:if>
	</form>
    </div>
</body>
</html>