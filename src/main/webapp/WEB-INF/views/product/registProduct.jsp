<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<% request.setCharacterEncoding("UTF-8"); %> 

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<script type="text/javascript">
function readURL(input) {
    if (input.files && input.files[0]) {
	      var reader = new FileReader();
	      reader.onload = function (e) {
	        $('#preview').attr('src', e.target.result);
        }
       reader.readAsDataURL(input.files[0]);
    }
}   
  function backToList(obj){
    obj.action="${contextPath}/product/listProducts.do";
    obj.submit();
  }
  
  var cnt=1;
  function fn_addFile(){
	  $("#d_file").append("<br>"+"<input type='file' name='file"+cnt+"' />");
	  cnt++;
  }  

</script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>상품 등록창</title>
</head>
<body>
<form name="articleForm" method="post"   action="${contextPath}/product/addNewProduct.do"   enctype="multipart/form-data">
    <table border="0" align="center">
     	<tr>
			<td align="right"> 상품 분류</td>
			<td colspan=2  align="left">
				<select name="product-category" class="product-category">
					<option value="">미분류</option>
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
		   <td colspan="2"><input type="text" size="67"  maxlength="500" name="prodname" data-parsley-required-message="상품 이름을 입력해주세요."/></td>
		</tr>
		<tr>
     		<td align="right">상품 가격: </td>
     		<td colspan="2"><input type="text" size="67"  maxlength="500" name="price" data-parsley-required-message="상품 가격을 입력해주세요." /></td>
     	</tr>
	 	<tr>
			<td align="right" valign="top"><br>상품 상세 설명: </td>
			<td colspan=2><textarea name="descr" rows="10" cols="65" maxlength="4000"></textarea> </td>
     	</tr>
     	
     	<tr>
			  <td align="right">상품 이미지 파일 첨부:  </td>
			  <td><input type="file" name="imageFileName"  onchange="readURL(this);" /></td>
			  <td><img  id="preview" src="#"   width=200 height=200/></td>
			  
			  <td align="right">이미지 파일 추가 첨부</td>
			  <td align="left"> <input type="button" value="파일 추가" onClick="fn_addFile()"/></td>
	   	</tr>
	   <tr>
	      <td colspan="4"><div id="d_file"></div></td>
	   </tr>
	    <tr>
	      <td align="right"> </td>
	      <td colspan="2">
	       <input type="submit" value="등록" />
	       <input type=button value="등록취소"onClick="backToList(this.form)" />
	      </td>
     </tr>
    </table>
  </form>
 
</body>
</html>