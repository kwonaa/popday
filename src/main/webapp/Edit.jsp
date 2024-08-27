<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>PopDay - EDIT</title>
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
<script type="text/javascript">
    function validateForm(form) {
        if (form.title.value == "") {
            alert("제목을 입력하세요");
            form.title.focus();
            return false;
        }
        if (form.sdate.value == "") {
            alert("시작 날짜를 입력하세요");
            form.sdate.focus();
            return false;
        }
        if (form.edate.value == "") {
            alert("종료 날짜를 입력하세요");
            form.edate.focus();
            return false;
        }
        if (form.location.value == "") {
            alert("팝업장소를 입력하세요");
            form.location.focus();
            return false;
        }
        if (form.content.value == "") {
            alert("내용을 입력하세요");
            form.content.focus();
            return false;
        }
        if (form.cno.value == "") {
            alert("카테고리 번호를 입력하세요");
            form.cno.focus();
            return false;
        }
        // 파일 크기 체크 (10MB 이하)
        var inputFile = document.getElementById("file");
        var files = inputFile.files;
        if (files.length > 0 && files[0].size > 10 * 1024 * 1024) {
            alert("파일 크기는 10MB를 초과할 수 없습니다.");
            return false;
        }
    }

    function loadFiles(input) {
        const file = input.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById('imageView').style.backgroundImage = 'url(' + e.target.result + ')';
            };
            reader.readAsDataURL(file);
        }
    }
</script>
</head>
<body>
    <div class="header" include-html="include/header.jsp"></div>
    
    <div class="container">
        <!-- content -->
        <div class="page_wrap">
            <div class="content_wrap">
                <form name="writeFrm" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this);">
					<input type="hidden" name="no" value="${dto.no}">
					<input type="hidden" name="prevOfile" value="${dto.ofile}"> 
					<input type="hidden" name="prevSfile" value="${dto.sfile}">
					
					<div class="create_info_area">
                        <div class="image_area">
                            <div class="input_image">
                                <div class="add_image" id="imageView" style="background-image: url('/path/to/default/image.jpg');"></div>
                                <input type="hidden" name="no" value="${dto.no}">
                                <input type="file" id="file" name="ofile" class="file_upload" accept="image/*" onchange="loadFiles(this)">
                            </div>
                        </div>
                        
                        <div class="create_info">
                            <div class="section_tit">
                                <h3 class="tit">팝업 정보 입력</h3>
                            </div>
                            <div class="section_con">
                                <ul class="create_input">                              
                                    <li>
                                        <p class="input_tit">팝업명</p>
                                        <p class="input_area">
                                            <input type="text" name="title" class="input_txt" title="팝업명" value="${dto.title}" placeholder="팝업명을 입력해주세요">
                                        </p>
                                    </li>
                                    <li>
                                        <p class="input_tit">팝업기간</p>
                                        <p class="input_area">
                                            <input type="date" class="input_txt date" name="sdate" value="${dto.sdate}" placeholder="시작 날짜">
                                            <span class="">~</span>
                                            <input type="date" class="input_txt date" name="edate" value="${dto.edate}" placeholder="종료 날짜">
                                        </p>
                                    </li>
                                    <li>
                                        <p class="input_tit">팝업장소</p>
                                        <p class="input_area">
                                            <input type="text" name="location" class="input_txt" title="팝업장소" value="${dto.location}" placeholder="팝업장소를 입력해주세요">
                                        </p>
                                    </li>
                                    <li>
                                        <div class="text_area">
                                            <textarea class="textarea input_txt" name="content" placeholder="팝업 설명을 입력해주세요">${dto.content}</textarea>
                                            <p class="byte">0/2000</p>
                                        </div>
                                    </li>
                                    <li>
                                        <p class="input_tit">카테고리</p>
                                        <p class="input_area">
                                            <input type="number" name="cno" min="1" max="36" class="input_txt" title="카테고리번호" value="${dto.cno}" placeholder="카테고리번호를 입력해주세요">
                                        </p>
                                    </li> 
                                </ul>
                                <div class="btn_area">
                                    <button type="submit" class="btn_submit">완료</button>
                                    <button type="reset" class="btn_reset">취소</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- //content -->
    </div>
    <div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>
