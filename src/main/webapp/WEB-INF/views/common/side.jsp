<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%   request.setCharacterEncoding("UTF-8"); %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>

<html>
<head>
 <style>
 

 *, :before, :after{ box-sizing: border-box; }

.search-select {
    margin: 0 auto;
    width: 120px;
    padding: 5px 35px 5px 5px;
    outline: 0;
    background-color: #2f3844;
    color: white;
    font-size: 9px;
    font-weight: lighter;
    border: 0.5px solid rgb(168, 168, 168);
    
}
.search-bar {
    margin-top: 10px;
    display: block;
    width: 115px;
    height: 20px;
    background-color: #dd171700;
    border: 0px;
    border-bottom: 1px solid #CCCCCC;
}
.search-bar .input-search {
    background-color: #ec020200;
    border: 0px;
    display:block;
    float:left;
    width: 80px;
    font-size: 9px;
    color: white;
    padding: 2px 0 -2px 3px;
}
.search-bar .input-search:focus {
    outline: 0;
}
.search-bar .input-search-submit {
    display:inline;
    float:right;
    vertical-align: center;
    border: 0px;
    margin-left: -1px;
}
.unstyled{ list-style: none; padding: 0; margin: 0;
  a{ text-decoration: none; }
}

.main-nav{
  
  ul{  border-top: solid 1px #3C3735;  }
  li{  border-bottom: solid 1px #3C3735; }
  a{
    padding: 1.1em 0;
    color: white;
    font: 400 1.125em;
    text:{
      align: center;
      transform: lowercase;
    }
    &:hover{ color: #fff;  }
  }
}

.list-hover-slide{
  
  li{
    position: relative;
    overflow: hidden;
  }
  
  a{
    display: block;
    position: relative;
    z-index: 1;
    transition: .35s ease color;
    
    &:before{
      content: '';
      display: block;
      z-index: -1;
      position: absolute;
      left: -100%; top: 0;
      width: 100%; height: 100%;
      border-right: solid 5px #0a239f;
      background: #a8adc8c5;
      transition: .35s ease left;
    }
    &.is-current,
    &:hover{  &:before{  left: 0;  }   }
  }
}
 </style>
  <meta charset="UTF-8">
  <title>사이드 메뉴</title>
</head>
<body>
	<div>
		<form action="#">
   			<select name="search-select" class="search-select">
				<option value="week">주문번호</option>
				<option value="month">상품주문번호</option>
				<option value="quarter">상품번호</option>
				<option value="time">운송장번호</option>
				<option value="customer">구매자명</option>
				<option value="customerID">구매자ID</option>
				<option value="customercontact">구매자연락처</option>
			</select>
  		</form>
	</div>
	<div class="search-bar">
		<form>
			<input type="text" class="input-search">
    		<input type="image" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png"  class="input-search-submit" width="12px">
		</form>
	</div>
	<nav class="main-nav" role="navigation">
			<ul class="unstyled list-hover-slide">
				<li><a href="${contextPath}/product/listProducts.do">상품목록</a><li>
				<li><a href="${contextPath}/product/registProduct.do">상품등록</a><li>
				<li><a href="#">주문관리</a><li>
				<li><a href="#">리뷰관리</a><li>
				<li><a href="${contextPath}/chart/simplechart.do">간편매출통계</a><li>
				<li><a href="#">상품매출통계</a><li>
			</ul>
	</nav>
</body>
</html>