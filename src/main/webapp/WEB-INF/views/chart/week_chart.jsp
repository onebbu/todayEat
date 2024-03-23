<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet" href="${path }/resources/css/chart.css">
<style>
nav{  	padding: 20px;     }
.number {
            	overflow:hidden;
            	word-wrap:break-word;
            	font-size: 24px;
                font-weight: bold;
            }
</style>
</head>
<body>
<div class="menu-title"> 간편매출통계 </div>
                <div class="menu">
                    <ul class="inner-menu">
                        <li class = "tab-button" data-tab="tab-1" onClick="location.href='${path }/chart/tab-1.do'">매출요약 </li>
                        <li  class = "tab-button is_on" data-tab="tab-2" onClick="location.href='${path }/chart/tab-2.do'">요일별 통계 </li>
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
            <form method = "GET" action = "${path}/chart/tab-2/lookup.do" id="date-form">
            <input type="date" min="2020-01-01" name="date_from"/> 부터
            <input type="date" min="2020-01-01" name="date_to"/> 까지
            <input type ="submit" value ="조회하기">
            <input type="reset" >
            </form> 
            <span class="number"> ${date_from } 부터 ${date_to } 사이 구간 조회 </span>
            <button name="form_reset" class="fr">새로고침</button>
        </nav>
                    <canvas id="hist" width="300" height="100"></canvas>
                    <script>
                        const ctx = document.getElementById('hist');
                        new Chart(ctx, {
                            type: 'bar',
                            data: {
                                labels: ['Mon', 'Tue','Wed','Thur','Fri','Sat','Sun'],
                                datasets: [{
                                    labael: '매출',
                                    backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f", "#FF6347", "#c45850","#ffd700","#ff69b4"],
                                    data: [${weeklist[2] },${weeklist[3] }, ${weeklist[4] }, ${weeklist[5] }, ${weeklist[6] }, ${weeklist[7] }, ${weeklist[1] }],
                                    <%-- 2:월 3:화 4:수 5:목 6:금 7:토 1:일--%>
                                    borderWidth: 1
                                }]
                            },
                            options: {
                                legend: {  display: false  },
                                title: {
                                    display: true,
                                    text: 'Order volume by day of the week'},
                                scales: { y: { beginAtZero: true } }
                            }
                        });
                    </script>
</body>
</html>