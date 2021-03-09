<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>views/insertPro.jsp</title>
</head>
<body>
<h3>상품등록</h3>
<form:form modelAttribute="productVO" action="insertPro" method="post" name="frm">
	product_id <form:input path="product_id"/>
					<form:errors path="product_id" cssClass="error"/><br>
	product_name  <form:input path="product_name"/>
					<form:errors path="product_name" cssClass="error"/><br>
	product_price   <form:input path="product_price"/>
					<form:errors path="product_price" cssClass="error"/><br>
	product_info     <form:input path="product_info"/>
					<form:errors path="product_info" cssClass="error"/><br>
	product_date<form:input path="product_date"/><br>
	company   <form:input path="company" />
					<form:errors path="company" cssClass="error"/><br>
	manager_id 
	<form:input path="manager_id" />

	<button type="submit">등록</button>
	<button type="reset">초기화</button>
</form:form>

<a href="getSearchPro">상품목록</a>

</body>
</html>