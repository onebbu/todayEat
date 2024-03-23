<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   isELIgnored="false"  %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%  request.setCharacterEncoding("UTF-8"); %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="${path}/resources/css/login.css" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>로그인</title>
	
	<c:choose>
	<c:when test="${result=='loginFailed' }">
	  <script>
	    window.onload=function(){
	      alert("아이디나 비밀번호가 틀립니다.다시 로그인 하세요!");
	    }
	  </script>
	</c:when>
	</c:choose> 
</head>
<body>
	<div class="login-wrapper">
	<div>
		<h2>Login</h2>
		<form method="post" action="${path}/member/login.do" id="login-form">
		<input type="text" name="userid" placeholder="ID" id="id" maxlength="20" title="회원 아이디" required="required" data-parsley-required-message="아이디를 입력해주세요">
		<input type="password" name="pwd" placeholder="Password" id="pwd" maxlength="20" title="회원 비밀번호" autocomplete="off" required="required" data-parsley-required-message="비밀번호를 입력해주세요">
		<label for="remember-check">
			<input type="checkbox" id="remember-check">아이디 저장하기
 		</label>
		<input type="submit" value="Login">
		</form>
	</div>
    
	<div>
		<ul class="login-guide-list">
			<li><a href="#">회원가입</a></li>
			<li><a href="#">아이디 찾기</a></li>
			<li><a href="#">비밀번호 찾기</a></li>
		</ul>
    
			<dl class="sns-login">
			<dt>SNS 계정으로 로그인 하기</dt>
			<dd>
				<a href="#" class="btn-sns-facebook" title="페이스북">네이버 계정으로 로그인</a>
				<a href="#" class="btn-sns-kakao" title="카카오톡">카카오톡 계정으로 로그인</a>
				<a href="#" class="btn-sns-google" title="구글">구글 계정으로 로그인</a>               
			</dd>
			</dl>
	</div>
	</div>
</body>
</html>