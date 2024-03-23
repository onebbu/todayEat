<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%  request.setCharacterEncoding("UTF-8"); %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>간단매출통계</title>
<link rel="stylesheet" href="${path }/resources/css/chart.css">
    <script src="https://code.jquery.com/jquery-3.6.4.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script>    	
    	$('.tab-button').click(function(){
    		var tab_id = $(this).attr('data-tab');

    		$('.tab-button').removeClass('is_on');
    		$(this).addClass('is_on');
		})
	</script>
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
                
</body>
</html>