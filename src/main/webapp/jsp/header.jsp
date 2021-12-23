<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>


<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	<link rel="stylesheet" href="css/header.css?ver=1.0">
	<link rel="stylesheet" href="css/main.css "  >
	
	<script type="text/javascript" src = "script/main.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
</head>



<body>



<form  method="post" action="MbergerServlet?command=login" name="formmm" >
 <div id = "form1" >
 
    <div>
        <ul id = "login">

        	
        	
        	<c:choose>
		       <c:when test="${empty sessionScope.loginUser}">
		        <li id = "login-image">
	                <a >
	                    <img src="https://www.mcdelivery.co.kr/kr//static/1629420806939/assets/00/img/icon_profile_gray.png" alt="Profile"  width="20" class="profile-grey-avator">
	                </a>
           		 </li >	
           		<li >
               	 	<a href="#" onclick="showPopup(false);" >로그인</a>
            	</li>	   
   
		       </c:when>
		        <c:otherwise>
			     <li>
	        		<a>
	        		<input type = "hidden" name ="email" value = "${sessionScope.loginUser}">
	        			${sessionScope.loginUser}
	        		</a>
	        	</li>
		       <li><a href="MbergerServlet?command=logout">로그아웃</a></li>
		       	 <li >
	                <a href="#"  onclick="location='MbergerServlet?command=mypage'" >주문조회</a>
	            </li> 
		       </c:otherwise>       
		     </c:choose>

        </ul>
    </div>

    <ul id="menu">
        <li id="menu-logo" >
            <a href="MbergerServlet?command=index" id="menu-logo-a"  >M BergerShop</a>
        </li>
        <li>
            <a href="#" id="menu-content" onclick="location='MbergerServlet?command=menu'">메뉴</a>
        </li>
	<c:choose>
	   <c:when test="${empty sessionScope.loginUser}">
        <li>
        	<a href="#" id="menu-content" onclick="showPopup(false)" >마이페이지</a>
        </li> 
	  </c:when>
	  <c:otherwise>  
	  	<li>
            <a href="#" id="menu-content" onclick="location='MbergerServlet?command=mypage'" >마이페이지</a>
        </li>  
	  </c:otherwise>       
    </c:choose>  

      </ul>
      
    </div>   

    <div id="popup" class="hide" >
        <div class="content"  style="text-align: center;" >
            <a class="close_button" style="float:right;" onclick="location='MbergerServlet?command=index'">
                <img src="productimg/icon/cancel.png" ><img>
            </a>

                    
<!--           <div class = "toggle_div">
 -->		       
 				   

<!--          </div> -->
            		
            		

                
                  <h2 id ="login_logo" style="color: red; text-align :center; margin-left: 40px; ">M BergerShop</h2>
                  <p style="font-size: 19px;">주문을 하시려면 로그인하시기 바랍니다.</p>
                  <ul>
                    <li>
                    	<div>
                  	 	  <input  id="expand-toggle1" type="checkbox" name="toggle" value="1" onclick="oneCheckbox(this)">
    					  <label class = "header_login" for="expand-toggle1" onclick="oneCheckbox(this)">로그인</label>
    					  <a  style="color:rgb(212, 209, 209); font-size: 19px;" >|</a>
    					  <input id="expand-toggle2" type="checkbox" name="toggle" value="2" onclick="oneCheckbox(this)">
                      	  <label id = "login2" class = "header_login" for="expand-toggle2" onclick="oneCheckbox(this)" >관리자 로그인</label>
                      	</div>
                    </li>
                    <li>
                        
                    </li>
                    <li>

                    </li>
                    
                  </ul>
                  
       
                    <input class ="id" type = "text" name = "id" placeholder='아이디'>
                    <br>
                    <input class ="password" type = "password" name ="pwd" placeholder='비밀번호' >
                    <br>
                    <br>
                    <input class ="login_button" type = "button" value = "로그인" onclick="login()" >

                    <hr>
                    <input class ="join_button" type = "button" value = "회원가입"
							onclick="location='MbergerServlet?command=join_form'"><br>
					<ul style="margin-right=200px;">
<!-- 						<li>
		                 	<button type="button" class="naver_button" name="naver_button" id="naver_id_login">
						        <img class="naver_button_img" src="productimg/icon/naverbtnG.png" >
						    </button>
					    </li>
					    <li >
						    <button type="button" class="kakao_button" name="kakao_button" id="kakao">
						        <img class="kakao_button_img" src="productimg/icon/kakobtnL.png" >
						    </button>
					    </li> -->
					    <li style="margin-left:30px; width:200px;" >
					    	<button type="submit" class="btn btn-primary"></button>
							<a class="kakao_button" href="https://kauth.kakao.com/oauth/authorize?client_id=74367f5ed86a607b43fc2ddbd5a8b756&redirect_uri=http://localhost:8080/MBugerShop/MbergerServlet?command=indexKakao&response_type=code"><img class="kakao_button_img" src="productimg/icon/kakobtnL.png" /></a>
					    <li>
					    <li>
					    <%
					    String clientId = "qMh2DeQgQyJSxYChS9ba";//애플리케이션 클라이언트 아이디값";
					    String redirectURI = URLEncoder.encode("http://localhost:8080/MBugerShop/MbergerServlet?command=indexNaver", "UTF-8");
					    SecureRandom random = new SecureRandom();
					    String state = new BigInteger(130, random).toString();
					    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
					    apiURL += "&client_id=" + clientId;
					    apiURL += "&redirect_uri=" + redirectURI;
					    apiURL += "&state=" + state;
					    session.setAttribute("state", state);
					 %>
					  <a href="<%=apiURL%>"><img class="naver_button_img" src="productimg/icon/naverbtnG.png"/></a>
					    </li>
					</ul>
					
					  
                    


        </div>
      </div>
</form>
<script type="text/javascript">

	$(function(){
		document.getElementById("expand-toggle1").checked = false;
        document.getElementById("expand-toggle2").checked = false;
	});
	
	$(function loginbtn(){
		document.getElementById("expand-toggle1").checked = false;
        document.getElementById("expand-toggle2").checked = false;
	});
	
	
	

	function login(){
	
	    
	    console.log($("#expand-toggle1").prop("checked"));
	    console.log($("#expand-toggle2").prop("checked"));
	    
	
	    if($("#expand-toggle1").prop("checked") == false && $("#expand-toggle2").prop("checked") == false){
	    	alert('로그인 방식을 선택해 주시기 바랍니다.');
	    }else {
	    	
/* 	    	 if (document.formm.email.value == document.formm.emailchk.value) {
	 		    alert('이메일이 일치하지 않습니다.');
	 		    document.formm.password.focus();
	    	 }else{ */
	    		document.formmm.action = "MbergerServlet?command=login";
	 		    document.formmm.submit();
/* 	    	 } */
	    	
		    
	 	}
	
	    
	}


	
	function navercall(){
		
		var naver_id_login = new naver_id_login("qMh2DeQgQyJSxYChS9ba", "http://localhost:8080/MBugerShop/MbergerServlet?command=index");
		var state = naver_id_login.getUniqState();
		naver_id_login.setButton("green", 10,40);
		naver_id_login.setDomain("http://localhost:8080/MBugerShop/MbergerServlet?command=index");
		
		naver_id_login.setPopup();
		naver_id_login.init_naver_id_login();
		
		
		console.log(naver_id_login.oauthParams.access_token);
		
		
		
		/* naver_id_login.get_naver_userprofile("naverSignInCallback()"); */
	}






	function admincheck() {
		  if (document.formmm.email.value == document.formmm.emailchk.value) {
		    alert('이메일이 일치하지 않습니다.');
		    document.formmm.password.focus();
		  }
	}	  


    function oneCheckbox(a){

        var obj = document.getElementsByName("toggle");

        for(var i=0; i<obj.length; i++){

            if(obj[i] != a){

                obj[i].checked = false;

            }

        }

    }
    
/*     var div2 = document.getElementsByClassName("header_login");

    function handleClick(event) {
      console.log(event.target);
      // console.log(this);
      // 콘솔창을 보면 둘다 동일한 값이 나온다

      console.log(event.target.classList);

      if (event.target.classList[1] === "clicked") {
        event.target.classList.remove("clicked");
      } else {
        for (var i = 0; i < div2.length; i++) {
          div2[i].classList.remove("clicked");
        }

        event.target.classList.add("clicked");
      }
    }

    function init() {
      for (var i = 0; i < div2.length; i++) {
        div2[i].addEventListener("click", handleClick);
      }
    }

    init();
     */


</script>
      
      