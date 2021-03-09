<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/getSearchPro.jsp</title>
</head>
<body>
<h2>상품 목록</h2>
	
		<c:forEach var="pro" items="${list }">
			${pro.product_id } ${pro.product_name } ${pro.product_price }
			${pro.product_info }
			${pro.product_date} ${pro.company}
			 ${pro.manager_id}
			<br>
		</c:forEach>

	<a href="insertPro">상품등록</a>
</body>
</html>