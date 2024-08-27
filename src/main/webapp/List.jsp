<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LIST</title>
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="resources/css/list.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/resources/js/include.js"></script>
    <script src="/resources/js/scripts.js" defer></script>
    
</head>
<body>
    <!-- <div class="header" include-html="./include/header.html"></div> -->
    <div class="header" include-html="include/header.jsp"></div>
    

    
    <!-- content -->
	<div class=“container”>
		<div class="page_wrap">
			<div class="content_wrap">
				<div class=“btn_area”>
					<c:if test="${adminCheck}">
						<button type="button" class="btn btn_submit" onclick="location.href='/write.do';" style="position:relative; left:960px; margin-bottom:30px;">글쓰기</button>
					</c:if>
				</div>
				<!-- 목록 부분 -->
				<ul class="popup_list">
					<c:forEach var="item" items="${popupList}">
						<li class="list_item">
							<div class="thumb">
								<%-- <a href="${pageContext.request.contextPath}/view.do?no=${item.no}&searchField=${map.searchField}&searchWord=${map.searchWord}"> --%>
								<a href="${pageContext.request.contextPath}/view.do?no=${item.no}">
									<img src="${pageContext.request.contextPath}/Images/${item.sfile}" alt="${item.title}">
								</a>
							</div>
							<div class="content">
								<h4 class="popup_tit">
									<a href="${pageContext.request.contextPath}/view.do?no=${item.no}">${item.title}</a>
								</h4>
								<div class="popup_info">
									<p class="place">${item.location}</p>
									<p class="date">${item.sdate}~ ${item.edate}</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
<%-- 	<div class="pagination">
		<!-- 페이지네이션 HTML을 JSP에서 직접 출력 -->
		${map.pagingImg}
	</div> --%>
	
    <div class="footer" include-html="include/footer.jsp"></div>

</body>
</html>