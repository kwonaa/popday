<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>PopDay - VIEW</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="resources/css/common.css">
<link rel="stylesheet" type="text/css" href="resources/css/list.css">
<link rel="stylesheet" type="text/css" href="resources/css/write.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css" />
<link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
<script src="/resources/js/include.js"></script>
<script src="/resources/js/scripts.js" defer></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdng.net/dmaps/map_js_init/roughmapLoader.js"></script>
</head>
<body>
    <div class="header" include-html="include/header.jsp"></div>
    
    <div class="container">
        <!-- content -->
        <div class="page_wrap">
            <div class="content_wrap">
                <form name="viewFrm" method="post" action="/view.do" enctype="multipart/form-data">
					<%-- <input type="hidden" name="admin" value="${mdto.admin}"> --%>
                    <div class="create_info_area">
                    
                        <div class="image_area">
                            <div class="input_image">
                                <img src="${pageContext.request.contextPath}/Images/${dto.sfile}" alt="${dto.title}" class="img-thumbnail">
                            </div>
                        </div>
                        
                        <div class="create_info">
                            <div class="section_tit">
                                <h3 class="tit">상세보기</h3>
                            </div>
                            <div class="section_con">
                                <ul class="create_input">
                                    <li>
                                        <p class="input_tit">팝업명</p>
                                        <p class="input_area">${dto.title}</p>
                                    </li>
                                    <li>
                                        <p class="input_tit">팝업기간</p>
                                        <p class="input_area">
											<span class="input_txt date" name="sdate">${dto.sdate}</span>
											<span class="">~</span> 
											<span class="input_txt date"name="edate">${dto.edate}</span>
										</p>
                                    </li>
                                    <li>
                                        <p class="input_tit">팝업장소</p>
                                        <p class="input_area">${dto.location}</p>
                                    </li>
                                    <li>
                                        <p class="input_tit">내용</p>
                                        <p class="input_area" style="height:150px;">${dto.content}</p>
                                    </li>
                                </ul>
                                <div class="btn_area">
                                	<c:if test="${adminCheck}">
                                    	<%-- <button type="button" class="btn_reset" onclick="/edit.do?no=${param.no}">수정</button> --%>
                                    	<button type="button" class="btn_reset" onclick="location.href='/edit.do?no=${param.no}'">수정</button>
                                    </c:if>
                                    <c:if test="${adminCheck}">
                                    	<button type="button" class="btn_submit" onclick="location.href='/delete.do?no=${param.no}'">삭제</button>
                                    </c:if>
                                    <button type="button" class="btn_submit" onclick="location.href='/list.do'">목록</button>
                                </div>
                            </div>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                </form>
            </div>
        </div>
        <!-- //content -->
    </div>
    <div class="footer" include-html="include/footer.jsp"></div>

</body>
</html>
