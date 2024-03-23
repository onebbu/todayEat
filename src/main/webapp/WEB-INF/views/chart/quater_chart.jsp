<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${path }/resources/css/chart.css">
<title>Insert title here</title>
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
                        <li  class = "tab-button" data-tab="tab-2" onClick="location.href='${path }/chart/tab-2.do'">요일별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-3" onClick="location.href='${path }/chart/tab-3.do'">월별 통계 </li>
                        <li  class = "tab-button  is_on" data-tab="tab-4" onClick="location.href='${path }/chart/tab-4.do'">분기별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-5" >상품/인구통계 </li>
                        <li  class = "tab-button" data-tab="tab-6">상품/지역 </li>
                        <li  class = "tab-button" data-tab="tab-7">배송통계 </li>
                    </ul>
                    <br> <br>
                    <div id = "tabcontent"></div>
</div>
<nav> <p> 검색하려는 날짜를 입력하세요.</p>
            <form method = "post" action = "#">
            <input type="date" min="2020-01-01" name="date_from"/> 부터
            <input type="date" min="2020-01-01" name="date_to"/> 까지
            <input type ="submit" value ="조회하기">
            </form> 
        </nav>
                    <canvas id="line-chart" width="300" height="100"></canvas>
                    <script>
                        new Chart(document.getElementById("line-chart"), {
                            type: 'line',
                            data: {
                                labels: ["1분기", "2분기", "3분기", "4분기"],
                                datasets: [{
                                    data: [86,14,106,106],
                                    label: "한식",
                                    borderColor: "#3e95cd",
                                    borderWidth: 3,
                                    fill: false,
                                    lineTension : 0.2
                                }, {
                                    data: [282,35,411,502],
                                    label: "중식",
                                    borderColor: "#8e5ea2",
                                    borderWidth: 3,
                                    fill: false,
                                    lineTension : 0.2
                                }, { 
                                    data: [190,203,26,408],
                                    label: "일식",
                                    borderColor: "#3cba9f",
                                    borderWidth: 3,
                                    fill: false,
                                    lineTension : 0.2
                                }, { 
                                    data: [40,20,10,16],
                                    label: "양식",
                                    borderColor: "#ffc952",
                                    borderWidth: 3,
                                    fill: false,
                                    lineTension : 0.2
                                }]
                            },
                            options: {
                                title: {
                                    display: true,
                                    text: '분기별 카테고리 주문량'
                                }
                            }
                        });
                    </script>
</body>
</html>