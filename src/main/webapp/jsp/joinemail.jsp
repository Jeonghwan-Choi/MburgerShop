<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    	<link rel="stylesheet" href="css/joinemail.css?ver=1.1">	
</head>
<body>
    <div class = "email_main">
        <h2>이메일 인증</h2>
        <hr>
        <img src="productimg/icon/email.png" style="margin-top:60px;">
        <h1>인증 메일이 발송되었습니다</h1>
        <div class="email_main2">
            <a>메일함에서(</a><a class ="email_a">${sessionScope.JoinUser}</a><a>)인증메일을 확인 바랍니다.</a><br><br>
            <a> 이메일의 인증버튼을 선택하면 회원가입이 완료됩니다.</a>
        </div>    
        <input class = "email_button" type ="button" value ="메인으로 돌아가기" onclick= "location.href='MbergerServlet?command=index'" >
    </div>
</body>
</html>