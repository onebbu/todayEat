<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 상세페이지</title>
<link rel="stylesheet"	href="${contextPath }/resources/css/ProductDetail.css">
	 <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js"></script>
<script>
	//상품 별점 표시
	$(document).ready(function () {
            $(function () {
                var rating = $('.review .rating');
                rating.each(function () {
                    var targetScore = $(this).attr('data-rate');
                    $(this).find('svg:nth-child(-n+' + targetScore + ')').css({color: '#FFCC00'});
                });
            });
        });
</script>
</head>
<body>
	<div class="nav">
		<span class="prodtitle">[${product.prodname }] 상세페이지 </span>
		<div id="model">
			<button id="btn-modify" onClick="location.href='${contextPath }/product/modifyProduct.do?prodno=${product.prodno }'" formmethod="post">수정하기</button>
		<form action="${contextPath }/product/listProducts/delete.do" method="POST">
			<input type="hidden" name="prodno" value="${product.prodno }">
			<button id="btn-delete" type="submit">삭제하기</button>
		</form>	
		</div>
	</div>
	<div id="fullscreen">
		<form id="item-form" class="inputItem" method="get"	enctype="multipart/form-data">
			<!-- 분할1 -->
			<div id="screen1">
				<div id="item-information">
					<div id="item-info" class="item-info">
						<div>
							<h4>상품ID</h4>
							<textarea rows = "1" cols="37" disabled>${product.prodno }</textarea>
						</div>
						<div>
							<h4>상품명</h4>
							<textarea rows = "1" cols="37" disabled>${product.prodname } </textarea>
						</div>
						<div>
							<h4>가격</h4>
							<textarea rows = "1" cols="37" disabled>${product.price }</textarea>
						</div>
						<div>
							<h4>상세정보</h4>
							<textarea rows = "20" cols="37" disabled>${product.descr }</textarea>
						</div>
					</div>
				</div>
			</div>
			<!-- 분할2 -->
			<div id="screen2">
				<div id="item-info" class="item-info">
					<div>
						<h4>카테고리</h4>
						<select id="item-select">
							<option value="none">${product.cat }</option>
						</select>
					</div>
					<div>
						<h4>상품 사진</h4>						
					</div>
					<div class="addImage" id="item-image">
							<c:choose>
								<c:when test="${not empty product.img && product.img!='null'}">
									<td>
									<img src="${contextPath}/downloadImage.do?productNO=${product.prodno}&imageFileName=${product.img}" 
									onerror='this.src="http://www.ledshine.co.kr/app/dubu_board/docs/imgs/h/lg_h15496012553427_1.jpg"' 
									id="preview" width=100% height=100%/><br>
									</td>
								</c:when>
								<c:otherwise>
								<td><img  id="preview" src="#"   width=200 height=200/></td>
								</c:otherwise>
							</c:choose>	 
					</div>
				</div>
			</div>
			<!-- 분할3 -->
			<div id="screen3">
				<h3>
					<a href="${contextPath }/review/viewReview.do?prodno=${product.prodno }">리뷰</a>  ${product.avgRating }
				</h3>
				
				<div class="review"> <!--  별이 다섯 개~ -->
                	<div class="rating" data-rate=${product.avgRating }>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
						<i class="fas fa-star"></i>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>