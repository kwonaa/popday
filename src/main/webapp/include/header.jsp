<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="left_tab">
        <h1 class="">
            <a href="/" class="logo">
                <img src="resources/images/temp/logo.png">
            </a>
        </h1>
<!--         <div class="main_nav">
            <ul class="main_menu">
                <li><a href="#" class="menu">CHARACTER</a></li>
                <li><a href="/list.do" class="menu">CHARACTER</a></li>
                <li><a href="/slist.do" class="menu">STYLE</a></li>
                <li><a href="/olist.do" class="menu">OTHER</a></li>
            </ul>
        </div> -->
        
        <div class="filter_area">
            <div class="filter_group">
                <!-- <p class="filter_tit">월별</p> -->
                <ul class="filter_tab">
                    <li class="list_item">
                        <button type="button" onclick="location.href='/list.do';">전체</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=01';">1월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=02';">2월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=03';">3월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=04';">4월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=05';">5월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=06';">6월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=07';">7월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=08';">8월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=09';">9월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=10';">10월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=11';">11월</button>
                    </li>
                    <li>
                        <button type="button" onclick="location.href='list.do?searchWord=12';">12월</button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    
<!--     <div class="right_tab">
        <div class="search_area">
            <form>
                <div class="search_input_box">
                    <input type="search" name="search" class="search_input" maxlength="150">
                </div>
                <button type="submit" class="btn_search" onclick="">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </form>
        </div> -->
        
        <div class="sign_area">
       
        	<c:if test="${empty userid}">
            <a href="main.do" class="btn_sign_up">
                <i class="fa-solid fa-user-plus"></i>
            </a>
            </c:if>
            <c:if test="${not empty userid}">
            <a href="main.do" class="btn_sign_up">
                <i class="fa-solid fa-user"></i>
            </a>
            </c:if>
        </div>
    </div>