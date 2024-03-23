<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %> 


<head>
   <meta charset="UTF-8">
   <title>상품 수정</title>
   <link rel="stylesheet"	href="${contextPath}/resources/css/ProductDetail.css">
   <script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
   <script type="text/javascript" >
     function backToList(obj){
	    obj.action="${contextPath}/product/listProducts.do";
	    obj.submit();
     }
     
	 function fn_modify_product(obj){
		 obj.action="${contextPath}/product/modProduct.do?prodno=${product.prodno }";
		 obj.submit();
	 }
	  
	 function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
	 }
	 
	 
 </script>
</head>
<body>
<form name="articleForm" method="post"   action="${contextPath}/product/modProduct.do"   enctype="multipart/form-data">
    <table border="0" align="center">
    	
    	<tr>
		   <td width=150 align="right" >상품번호	</td>
		   <td ><input type="text"  value="${product.prodno }" disabled /></td>
		   <td ><input type="hidden"  value="${product.prodno }" name="prodno"/></td>
	  	</tr>

     	<tr>
			<td align="right"> 상품 분류</td>
			<td colspan=2  align="left">
				<select name="product-category" class="product-category">
					<option value="${product.cat }">${product.cat }</option>
					<option value="한식">한식</option>
					<option value="양식">양식</option>
					<option value="중식">중식</option>
					<option value="일식">일식</option>
					<option value="주류">주류</option>
				</select> 
			</td>
		</tr>
	    <tr>
		   <td align="right">상품명: </td>
		   <td ><input type=text value="${product.prodname }" name="prodname" /></td>
		</tr>
		<tr>
     		<td align="right">상품 가격: </td>
     		<td colspan="2"><input type=text value="${product.price }"  name="price"/></td>
     	</tr>
	 	<tr>
			<td align="right" valign="top"><br>상품 상세 설명: </td>
			<td colspan=2><textarea name="descr" rows="10" cols="65" maxlength="4000">${product.descr }</textarea> </td>
     	</tr>
     	
   	
     	
     	<tr>
			  <td align="right">상품 이미지 :  </td>
			  
	<c:choose>
		<c:when test="${not empty product.img && product.img!='null'}">
			<td>
			<img src="${contextPath}/downloadImage.do?productNO=${product.prodno}&imageFileName=${product.img}" 
			onerror='this.src="http://www.ledshine.co.kr/app/dubu_board/docs/imgs/h/lg_h15496012553427_1.jpg"' 
			id="preview" width="300" height="200" /><br>
			</td>
		</c:when>
		<c:otherwise>
		<td><img  id="preview" src="#"   width=200 height=200/></td>
		</c:otherwise>
	</c:choose>	  
			  <td> <input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			  
	   	</tr>
	   <tr>
	      <td colspan="4"><div id="d_file"></div></td>
	   </tr>
	    <tr>
	      <td align="right"> </td>
	      <td colspan="2">
	       <input type="submit" value="상품 수정" onClick="fn_modify_article(frmArticle)"/>
	       <input type=button value="취소"onClick="backToList(this.form)" />
	      </td>
     </tr>
    </table>
  </form>
  
</body>
</html>