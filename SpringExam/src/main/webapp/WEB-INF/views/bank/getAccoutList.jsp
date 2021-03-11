<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>bank/getAccoutList.jsp</title>
</head>
<body>

<table border="1">
<c:forEach var="bank" items="${list.res_list }">
	<tr><td>${bank.bank_name }</td>
	<td>${bank.account_alias }</td>
	<td>${bank.account_num_masked }</td>
	<td><a href="#" onclick="getBalance1('${bank.fintech_use_num}')">${bank.fintech_use_num}</a></td>
	<td><a href="#" onclick="getBalance2('${bank.fintech_use_num}')">${bank.fintech_use_num}</a></td>
	</tr>
</c:forEach>
</table>

<div id="result">
</div>
	<script>
			// ajax 호출
			// ajaxGetBalace
			//fintech_use_num
			//location.href="getBalance?fintech_use_num=" + fin;
			function getBalance1(fin){
			      //ajax호출
			      $.ajax({
			         url:"getBalance",
			         data:{fintech_use_num:fin},
			         // 생략하면 html 형식
			         success:function(response){
			            console.log(response);
			            $('#result').html(response);
			         },
			         error:function(result){
			             console.log('에러: ' + result.statusText);
			         }
			      });
			      //ajacGetBalance
			      //fineetechUseNum = "+fin"
			   }
			function getBalance2(fin){
			      //ajax호출
			      $.ajax({
			         url:"ajaxGetBalance",
			         data:{fintech_use_num:fin},
			         dataType : "json",
			         success:function(response){
			            console.log(response);
			            $('#result').html(response);
			            $("#result").empty();
			            $('#result').append("bankname : " + response.bank_name + "<br>")
			            			.append("product_name : " + response.product_name + "<br>")
			            			.append("balance_amt : " + response.balance_amt + "<br>")
			            			.append("available_amt : " + response.available_amt);
			         },
			         error:function(result){
			             console.log('에러: ' + result.statusText);
			         }
			      });
			   }
		
	</script>

	
</body>
</html>