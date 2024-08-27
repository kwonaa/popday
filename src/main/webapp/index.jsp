<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PopDay</title>
    <link rel="stylesheet" type="text/css" href="resources/css/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/css/common.css">
    <link rel="stylesheet" type="text/css" href="resources/css/list.css">
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script src="resources/js/include.js"></script>
    <script src="resources/js/scripts.js" defer></script>
</head>
<body>
    <div class="header" include-html="include/header.jsp"></div>

    <div class="container">
	    <div class="main_wrap">
	        <div class="swiper_wrap">
	            <div class="swiper" onclick="location.href='/list.do';">
	                <div class="swiper-wrapper">
	                  <div class="swiper-slide">
	                      <img src="../resources/images/temp/4.png" >
	                  </div>    
	                  <div class="swiper-slide">
	                      <img src="resources/images/temp/2.jpg">
	                   </div>
	                  <div class="swiper-slide">
	                      <img src="resources/images/temp/25.jpg">
	                  </div>
	                  <div class="swiper-slide">
	                      <img src="resources/images/temp/16.jpg">
	                  </div>  
	                </div>
	                <!-- <div class="swiper-button-next"></div>
	                <div class="swiper-button-prev"></div> -->
	                <div class="swiper-pagination"></div>
	          </div>
	        </div>
	        
	                     
	               
	            
	    </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const swiper = new Swiper('.swiper', {
                loop: true,
                slidesPerView: 1,
                pagination: {
                    el: '.swiper-pagination',
                },
                autoplay: {
                    delay: 2500,
                    disableOnInteraction: false,
                },
                navigation: {
                    nextEl: '.swiper-button-next',
                    prevEl: '.swiper-button-prev',
                },
                scrollbar: {
                    el: '.swiper-scrollbar',
                },
            });
        });
    </script>
    <div class="footer" include-html="include/footer.jsp"></div>
</body>
</html>