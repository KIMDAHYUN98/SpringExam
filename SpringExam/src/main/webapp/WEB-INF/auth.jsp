<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>auth.jsp</title>
</head>
<body>
<a href="auth">사용자 인증</a>
<a href="callback?code=GpifZLJuI2K1wh5Pb4u6sTvchZ232D">토큰발급</a>
<a href="userinfo">사용자 정보</a>
<div>
    access_token:${access_token}
</div>
</body>
</html>