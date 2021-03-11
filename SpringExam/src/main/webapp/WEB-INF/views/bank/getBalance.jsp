<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>bank/getBalance.jsp</title>

</head>
<body>
<table border="1">
<tr>
<td>잔액</td>
<td>${balance.balance_amt }</td>
</tr>
<tr>
<td>출금가능금액</td>
<td>${balance["available_amt"] }</td>
</tr>
<tr>
<td>은행</td>
<td>${balance["bank_name"] }</td>
</tr>
<tr>
<td>계좌명</td>
<td>${balance["product_name"] }</td>
</tr>
</table>

<p>=========================================================================================</p>
${balance }

</body>
</html>