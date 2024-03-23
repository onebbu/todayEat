<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%  request.setCharacterEncoding("UTF-8"); %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <title> 매출 요약 </title>
        <link rel="stylesheet" href="${path }/resources/css/chart.css">
        <script src="https://code.jquery.com/jquery-3.6.4.js"></script>
        <style>
            nav{
            	padding: 20px;
            }
            .show{
                background-color: #e9e9e9;
                min-height: 550px;
                padding:40px;
                padding-bottom: 60px;
                margin-top: 20px;
                border-top: 0.5px solid #5a5a5a;
            }
            .grid{
                display:grid;
                grid-template-columns:  300px 300px 300px;
                grid-template-rows: 195px 195px;
                gap: 30px;
            }
            .box {
                background-color: white;
                width:90%;
                height: 90%;
                font-size: 15px;
                border-top: 5px solid #5a5a5a;
                padding: 20px;
                line-height: 40px;
            }
            .number {
            	overflow:hidden;
            	word-wrap:break-word;
            	font-size: 24px;
                font-weight: bold;
            }
            .reset{
            	position: absolute;
        		left: 700px;
        		top: 50px;
            }
        </style>
        
    </head>
    <body>
    
    <div class="menu-title"> 간편매출통계 </div>
                <div class="menu">
                    <ul class="inner-menu">
                        <li class = "tab-button is_on" data-tab="tab-1" onClick="location.href='${path }/chart/tab-1.do'">매출요약 </li>
                        <li  class = "tab-button" data-tab="tab-2" onClick="location.href='${path }/chart/tab-2.do'">요일별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-3" onClick="location.href='${path }/chart/tab-3.do'">월별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-4" onClick="location.href='${path }/chart/tab-4.do'">분기별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-5" >상품/인구통계 </li>
                        <li  class = "tab-button" data-tab="tab-6">상품/지역 </li>
                        <li  class = "tab-button" data-tab="tab-7">배송통계 </li>
                    </ul>
                    <br> <br>
                    <div id = "tabcontent"></div>
	</div>
        <nav> <p> 검색하려는 날짜를 입력하세요.</p>
            <form method = "GET" action = "${path}/chart/tab-1/lookup.do" id="date-form">
            <input type="date" min="2020-01-01" name="date_from"/> 부터
            <input type="date" min="2020-01-01" name="date_to"/> 까지
            <input type ="submit" value ="조회하기">
            <input type="reset" >
            </form> 
            <span class="number"> ${date_from } 부터 ${date_to } 사이 구간 조회 </span>
            <button name="form_reset" class="fr">새로고침</button>
        </nav>
        <div class = "show">
            <div class="grid">
                <div class="box item1">
                    기간 내 결제 금액 <br>
                    <span class="number">
                    <fmt:formatNumber pattern="###,###,###원">${box01 }</fmt:formatNumber> </span>
                </div>
                <div class="box item2">
                    기간 내 결제건수 <br>
                    <span class="number">
                    <fmt:formatNumber pattern="###,### 건"> ${box02 }</fmt:formatNumber> </span>
                </div>
                <div class="box item3">
                    기간 내 결제 상품수량 <br>
                    <span class="number">
                    <fmt:formatNumber pattern="###,### 개"> ${box03 } </fmt:formatNumber></span>
                </div>
                
                <div class="box item4">
                    기간 내 최고 결제 금액 카테고리 <br>
                    <span class="number"> ${box04 } </span> <br>
                    <span class="number"> 
                    <fmt:formatNumber pattern="###,###,###원   |">${box04_1 }</fmt:formatNumber>
                    <fmt:formatNumber type="percent" value="${box04Per }"></fmt:formatNumber> </span>
                </div>
                <div class="box item5">
                    기간 내 최고 결제 수량 카테고리 <br>
                    <span class="number"> ${box05 } </span> <br>
                    <span class="number"> 
                    <fmt:formatNumber pattern="###,### 개  |">${box05_1 }</fmt:formatNumber>
                    <fmt:formatNumber type="percent" value="${box05Per }"></fmt:formatNumber></span>
                </div>
                <div class="box item6">
                    기간 내 최고 결제 수단 <br>
                    <span class="number"> ${box06 } </span> <br>
                    <span class="number"> 
                    <fmt:formatNumber pattern="###,### 건">${box06_1 }</fmt:formatNumber></span>
                </div>
            </div>
        </div>
    </body>
</html>