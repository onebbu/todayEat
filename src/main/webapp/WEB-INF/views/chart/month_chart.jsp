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
                        <li  class = "tab-button" data-tab="tab-2" onClick="location.href='${path }/chart/tab-2.do'">요일별 통계 </li>
                        <li  class = "tab-button  is_on" data-tab="tab-3" onClick="location.href='${path }/chart/tab-3.do'">월별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-4" onClick="location.href='${path }/chart/tab-4.do'">분기별 통계 </li>
                        <li  class = "tab-button" data-tab="tab-5" >상품/인구통계 </li>
                        <li  class = "tab-button" data-tab="tab-6">상품/지역 </li>
                        <li  class = "tab-button" data-tab="tab-7">배송통계 </li>
                    </ul>
                    <br> <br>
                  </div>
                    
		<nav> <p> 검색하려는 날짜를 입력하세요. 선택하신 날짜까지 6개월의 데이터.</p>
            <form method = "GET" action = "${path }/chart/tab-3/lookup.do" id="date-form">
            	<input type="date" min="2020-01" max="2024-04" name="input_date"/> 까지 
            	<input type ="submit" value ="조회하기">
            </form> 
            <span class="number"> ${date_from } 부터 ${date_to } 사이 구간 조회 </span>
        </nav>
                    <canvas id = "MonthChart" width="300" height="100"></canvas>
                    <script>
                        new Chart(
                            document.getElementById('MonthChart'),
                            {
                                type: 'bar',
                                data: {
                                    labels: ["${mList[1] }", "${mList[2] }","${mList[3] }","${mList[4] }","${mList[5] }", "${mList[6] }" ],
                                    datasets: [
                                        {
                                            label: '한식',
                                            data: [${monthlist_han[1] },${monthlist_han[2] },${monthlist_han[3] },${monthlist_han[4] },${monthlist_han[5] },${monthlist_han[6] } ]
                                        },
                                        {
                                            label: '중식',
                                            data: [${monthlist_joong[1] },${monthlist_joong[2] },${monthlist_joong[3] },${monthlist_joong[4] },${monthlist_joong[5] },${monthlist_joong[6] }]
                                        },
                                        {
                                            label: '양식',
                                            data: [${mL_yang[1] },${mL_yang[2] },${mL_yang[3] },${mL_yang[4] },${mL_yang[5] },${mL_yang[6] } ]
                                        },
                                        {
                                            label: '분식',
                                            data: [${mL_B[1] },${mL_B[2] },${mL_B[3] },${mL_B[4] },${mL_B[5] },${mL_B[6] } ]
                                        },
                                        {
                                        	label: '한식 주문 건수',
                                        	data: [${cntH[1] },${cntH[2] },${cntH[3] },${cntH[4] },${cntH[5] },${cntH[6] } ],
                                        	type: 'line'
                                        },
                                        {
                                        	label: '양식 주문 건수',
                                        	data: [${cntY[1] },${cntY[2] },${cntY[3] },${cntY[4] },${cntY[5] },${cntY[6] } ],
                                        	type: 'line'
                                        }
                                    ]
                        
                                }, 
                                options : {
                                    scales: {  x: {  stacked: true  } , y:{stacked:true} }
                             
                                }});
                    </script>
</body>
</html>