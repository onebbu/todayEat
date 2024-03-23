<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%  request.setCharacterEncoding("UTF-8");  %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
  .login{
    float: right;
    font-size: 12px; font-weight:bold;
    margin-right: 100px;
    color:white;
     display: inline-block; width: 200px;
}

  .btn{
  color: white;
  font-size: 12px; font-weight:bold;
    background-color: black;
    float: right;
    padding: 2px;
}

  </style>
<title>헤더</title>
</head>
<body>
	오늘뭐먹지 스마트스토어센터
	<div class="login">
       <c:choose>
          <c:when test="${isLogOn == true  && member != null}">
            <a style="color:white;" href="${contextPath}/member/logout.do">환영합니다. ${member.username }님!     로그아웃</a>
          </c:when>
          <c:otherwise>      
	        <button class = "btn" onclick = "location.href='${contextPath}/main.do'">로그인</button>
	      </c:otherwise>
	   </c:choose>
	</div>
</body>
</html>