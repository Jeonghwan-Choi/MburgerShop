<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="CSS/subpage.css" rel="stylesheet">
<style type="text/css">
	body{   
	  background-color:white;

      background-color: rgb(238, 238, 238);
	}
	#wrap{     
	  margin: 0 20px;
	}

	input[type=button], input[type=submit] {
	  float: right;
	}
</style>
<script type="text/javascript">
	function idok(){
		opener.formm.id.value="${id}"; 
		opener.formm.reid.value="${id}";
		self.close();
	}
</script>
</head>
<body>
	<div id="wrap" style="">
        <div style="text-align: center; background-color:rgb(210, 210, 210); width: 100%;" >
            <a style="color:red; font-size: 22px; font-weight: 700;  ">MBurgerShop</a>
            &nbsp;&nbsp;
            <br>
            
            <a style="color:rgb(114, 114, 114); font-size: 17px;" > Email 중복확인</a>
        </div>
        <hr>
  		<form method="post" name="formm" action="MbergerServlet?command=id_check_form" style="margin-right:0 ">
  			<a style="font-size: 17px;" >User Email : <a></a> <input type="text" name="id" id="id" value="${id }" style=" padding:3px; border-radius: 3px; border:1px solid rgb(210, 210, 210);"> 
                    <input type=submit value="검색" class="submit"><br> 
            <div style="margin-top: 20px">
            	<script>
            		
            	</script>
            	 <c:if test="${message == 1 }">
            	 	${id }는 이미 사용중인 아이디 입니다.

            	 </c:if> 
            	 <c:if test="${message == -1 }">
            	 	${id }는 사용가능한 아이디 입니다.

            	 	<input type="button" value="사용" class="cancel" onclick="idok()">
            	 </c:if>           
            </div>
  		</form>
    </div>
</body>
<script type="text/javascript" >
function idok(){
	opener.formm.email.value = document.formm.id.value;

	
	self.close();
}
</script>
</html>





















