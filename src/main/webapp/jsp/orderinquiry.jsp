<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.mbergershop.dao.CartDAO" %>
<%@ page import ="com.mbergershop.dto.CartVO" %>

<%@ page import ="com.mbergershop.dao.OrderDAO" %>
<%@ page import ="com.mbergershop.dao.OrderDAO2" %>
<%@ page import ="com.mbergershop.dto.OrderVO" %>
<%@ page import = "java.util.ArrayList" %>    
<%@ page import = "javax.servlet.http.HttpSession" %>    
<%@ include file = "header.jsp"%>

<link rel="stylesheet" href="css/orderinquiry.css?ver=1"  >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script type="text/javascript" src = "script/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>




	

 <form class = "main_form"  method="post" action="MbergerServlet?command=mypageDetail"  >
 		<div id="ex1_Result1"></div>
 		<!-- <input id="textField"  name = "detailNum" type = "text" value = "" onchange="location='MbergerServlet?command=mypage#pop01'"> -->
 		
		  <input id="loginid" name = "loginid" type="hidden" value="${sessionScope.loginUser}"  >
		  <input id="textField" name = "detailNum" type="hidden" value="" onchange="" >
		 
  		 

        <div class = "mymenu" >
            <ul>
                <li class = "li_title">
                    <a>마이페이지</a>
                </li>
                <ul>
                    <li class = "li_content">
                        <a href= "#">주문조회</a>
                    </li>
                </ul>
            </ul>

        </div>
        <div class = "mycontent">
            <div ></div>
                <p style="font-size: 18px; font-weight:500;  ">나의 주문내역</p>

            <div class = "mycontent_content">
                <div class = "mycontent_information_ordernum">
                    <table  id="example-table-1" class = "mypage_table" border="1" style="width:100%; border: 1px rgb(170, 170, 170) solid;">
	                     <thead>  
	                        <tr>
	                        	<th>현재상태</th>
	                            <th style ="color:red;" >주문번호</th>
	                            <th>이메일</th>
	                            <th>주소</th>
	                            <th>금액</th>
	                            <th>상세정보</th>
	                        </tr>
	                      </thead>  
                     <c:forEach items="${orderNumList }"  var="orderNumVO" varStatus="status">
                      <tbody>
                        <tr>
                        <c:choose>
					   	<c:when test="${orderNumVO.state == 1}">
					        	<td id="${orderNumVO.ordernum }" class = "td_state" style="color:white; background-color: rgb(114, 114, 114);" >대기중</td>
						  </c:when>
						   <c:when test="${orderNumVO.state == 2}">
					        	<td id="${orderNumVO.ordernum }"  class = "td_state" style="color:white; background-color: rgb(222, 154, 72);" >준비중</td>
						  </c:when> 
						   <c:when test="${orderNumVO.state == 3}">
					        	<td id="${orderNumVO.ordernum }"  class = "td_state" style="color:white; background-color: rgb(92, 157, 82);" >제조완료</td>
						  </c:when> 	
						   <c:when test="${orderNumVO.state == 4}">
					        	<td id="${orderNumVO.ordernum }"  class = "td_state" style="color:white; background-color: rgb(0, 150, 209);" >전달완료</td>
						  </c:when> 				       
					    </c:choose> 
                            <td class ="mypage_content_td_ordernum" >${orderNumVO.ordernum}</td>
                            <td class ="mypage_content_td_email">${orderNumVO.email}</td>
                            <td class ="mypage_content_td_address">${orderNumVO.address}</td>
                            <td class ="mypage_content_td_price" style = "color : rgb(88,140,42);">₩ ${orderNumVO.price}</td>
                            <td class ="mypage_content_td_information">
                            <input type="submit" onclick="location.href='MbergerServlet?command=mypageDetail'" class = "checkBtn" value = "상세정보" >

                            </td>
                        </tr>
                       <tbody>
                     </c:forEach>
                    </table>
                </div>
                

                <div class = "mycontent_order_information">
                    
                </div>

            </div>
        </div>



    </form>
    
    
  <div id="pop01" class="overlay">
    <div class="popup">
      <a href="" class="close">&times;</a>
      <h2 id ="login_logo" style="color: red; text-align :center; margin-left: 40px; ">상세정보</h2>
                      
                      <table id = "content_table1" class = "content_table" border="1">
                          <tr class = "content_table_tr" >
                              
                              <th>이메일</th>
                              <th>주소</th>
                              <th>상품명</th>
                              <th>구분</th>
                              <th>사이드</th>
                          </tr>
    
    					<c:forEach items="${orderList }"  var="orderVO" varStatus="status">
                            <tr>
                                <td rowspan="3" >${orderVO.email }</td>
                                <td rowspan="3">${orderVO.address }</td>
                                <td rowspan="3">${orderVO.name }</td>
                                <td rowspan="3">${orderVO.grade2 }</td>                                
                                <td >${orderVO.side1}</td>                             
                            </tr>
                            <tr>
    
                                <td>${orderVO.side2}</td>
                            </tr>
                            <tr>
                                <td>${orderVO.side3}</td>
                            </tr>
    					</c:forEach>

    
    
                      </table>
    </div>
  </div>

  
    
</body>

  <script type="text/javascript" >
  $(".checkBtn").click(function(){ 
	  var str = ""
			var tdArr = new Array();	// 배열 선언
			var checkBtn = $(this);
			
			// checkBtn.parent() : checkBtn의 부모는 <td>이다.
			// checkBtn.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			
			var no = td.eq(1).text();
			var userid = td.eq(1).text();
			var name = td.eq(2).text();
			var email = td.eq(3).text();
			 var text = document.getElementById("textField");
			
			
			// 반복문을 이용해서 배열에 값을 담아 사용할 수 도 있다.
			td.each(function(i){	
				tdArr.push(td.eq(i).text());
			});
			
			text.value = no; 
			

	  
	  
	});

  
		
		  var textarea = document.getElementById("messageWindow");
		
		  var chatdiv = document.getElementById("ChatBox");
		
		  var webSocket = new WebSocket('ws://localhost:8080/MBugerShop/broadcasting');
		  var inputMessage = document.getElementById('inputMessage');
		webSocket.onerror = function(event) {
		onError(event)
		};
		
		webSocket.onopen = function(event) {
		onOpen(event)
		};
		
		webSocket.onmessage = function(event) {
		
		onMessage(event)
		};

		function onMessage(event) {
		
		  var edata = event.data;
		  var onM = edata.split(',');
		  var ostd1 = parseInt($("#order_state_td_1").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd2 = parseInt($("#order_state_td_2").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd3 = parseInt($("#order_state_td_3").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd4 = parseInt($("#order_state_td_4").val());    //문자를 정수형 숫자로 변환해줌    
		  
		  console.log('메세지 도착');
		  
		
		  if(onM[0]==2){
			if(onM[1]==2){	
				$("#"+onM[2]).css("background-color","rgb(222, 154, 72)");
				$("#"+onM[2]).text("준비중");
			}else if(onM[1]==3){	
				$("#"+onM[2]).css("background-color","rgb(92, 157, 82)");   
				$("#"+onM[2]).text("제조완료");
			}else if(onM[1]==4){	
				$("#"+onM[2]).css("background-color","rgb(0, 150, 209)");     
				$("#"+onM[2]).text("전달완료");
			}
			
		  }	      
		  
 
		
		}
		
		function onOpen(event) {
		    $("#login li:eq(0)").before("<a class = 'server_state_on'>● Live Service On</a>")
		}
		
		function onError(event) {
			 $("#login li:eq(0)").before("<a class = 'server_state_off'>● Live Service Off</a>")
		}
		
		function send() {
		  textarea.value += "나 : " + inputMessage.value + "\n";
		  webSocket.send(inputMessage.value);
		  inputMessage.value = "";
		}




  
  </script>
  
  
</html>