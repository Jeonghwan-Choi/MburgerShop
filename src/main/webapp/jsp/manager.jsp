<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.mbergershop.dao.OrderDAO2" %>
<%@ page import ="com.mbergershop.dto.OrderVO" %>
<%@ page import = "java.util.ArrayList" %>    
<%@ include file = "header.jsp"%>

<link rel="stylesheet" href="css/manager.css?ver=1.1"  >
<script type="text/javascript" src = "script/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<form method ="post" name="formm" action="MbergerServlet?command=managerDetail" >
<input id ="textField" type = "hidden" name = "detailNum">






	    <div class = "main_form">
	        <fieldset style="display:none;" >
        <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
        <br/>
        <input id="inputMessage" type="text"/>
        <input type="button" value="send1" onclick="send()" />
    </fieldset>
        <div class = "left_information">
            <div class = "order_state">
                <div>
                    <a>주문상태</a>
                </div>
                
                <c:forEach items="${orderCountList }"  var="orderCountVO" varStatus="status">   
                 	<c:choose>
					   	<c:when test="${orderCountVO.state == 1}">
					        <table style="background-color: rgb(114, 114, 114);" >
								<tr>
									<td  >대기중</td>
                        			<td id="order_state_td_1"  class = "order_state_td"  >${orderCountVO.id }</td>
                   				</tr>
              				</table> 
						</c:when>
							
						<c:when test="${orderCountVO.state == 2}">
						  	<table style="background-color: rgb(222, 154, 72);" >
			                    <tr>
			                        <td  >준비중</td>
			                        <td id="order_state_td_2"  class = "order_state_td"  >${orderCountVO.id }</td>
			                    </tr>
			                </table >
						</c:when>
						
						<c:when test="${orderCountVO.state == 3}">
						  <table style="background-color: rgb(92, 157, 82);">
			                    <tr>
			                        <td >제조완료</td>
			                        <td id="order_state_td_3"  class = "order_state_td">${orderCountVO.id }</td>
			                    </tr>
			                </table>
						</c:when>
						
						<c:when test="${orderCountVO.state == 4}">
						  <table style="background-color: rgb(0, 150, 209);">
			                    <tr>
			                        <td >전달완료</td>
			                        <td id="order_state_td_4" class = "order_state_td">${orderCountVO.id }</td>
			                    </tr>
		                </table>
						</c:when>
						
					</c:choose>	  
                </c:forEach>
                

                
                
                
                
            </div>
        </div>

        <div class = "main_information">
            <div class = "main_information_title">
                <p>주문현황</p>
            </div>
            
            <div class = "main_information_content" >
                <table class = "main_information_content_table" cellspacing="0" cellpadding="0" >
                    <tr>
                        <th class = "th_title">현재상태</th>
                        <th class = "th_state">주문번호</th>
                        <th class = "th_ordertime">주문시간</th>
                        <th class = "th_overtime">경과시간</th>
                        <th class = "th_content">상세보기</th>
                    </tr>
                <c:forEach items="${orderNumList }"  var="orderNumVO" varStatus="status">    
                    <tr >
                    <c:choose>
					   	<c:when test="${orderNumVO.state == 1}">
					        	<td class = "td_state"  >
					        		<div style="color:white; background-color: rgb(114, 114, 114); line-height:30px; border-radius:6px; border:none; " ><span>대기중</span></div>
					        	</td>
						  </c:when>
						   <c:when test="${orderNumVO.state == 2}">
					        	<td class = "td_state" >
					        		<div style="color:white; background-color: rgb(222, 154, 72); line-height:30px; border-radius:6px; border:none; " ><span>준비중</span></div>
						  		</td>
						  </c:when> 
						   <c:when test="${orderNumVO.state == 3}">
					        	<td class = "td_state" >
					        		<div style="color:white; background-color: rgb(92, 157, 82); line-height:30px; border-radius:6px; border:none; " ><span>제조완료</span></div>
						 		</td>
						  </c:when> 	
						   <c:when test="${orderNumVO.state == 4}">
					        	<td class = "td_state" >
					        		<div style="color:white; background-color: rgb(0, 150, 209); line-height:30px; border-radius:6px; border:none; " ><span>전달완료</span></div>
						 		</td>
						  </c:when> 				       
					 </c:choose> 
                        <td style="color: rgb(0, 150, 209); padding-left:4px;padding-right:5px;">${orderNumVO.ordernum}</td>
                        
                       	
                        <td>${orderNumVO.date}</td>
                        <td>${orderNumVO.elapsedtime}분</td>
                        <td><input id="${orderNumVO.ordernum}" class = "checkBtn" type = button  value = "상세보기" onclick="orderdetailform()"></td>
                    </tr>
				</c:forEach>
				
					
                    


                </table>
            </div>
        </div>
    </div>

</form>
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
		  textarea.value += event.data + "\n";

		  var edata = event.data;
		  var onM = edata.split(',');
		  var ostd1 = parseInt($("#order_state_td_1").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd2 = parseInt($("#order_state_td_2").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd3 = parseInt($("#order_state_td_3").val());    //문자를 정수형 숫자로 변환해줌    
		  var ostd4 = parseInt($("#order_state_td_4").val());    //문자를 정수형 숫자로 변환해줌    
		  

		  
		
		  if(onM[0]==1){
			  if(onM[1]==1){	
					  $(".main_information_content_table tr:eq(1)").before("<tr>"+							  
					      "<td class = 'td_state' ><div style='color:white; background-color: rgb(114, 114, 114); line-height:30px; border-radius:6px; border:none; ' ><span>대기중</span></div></td>"+
					      "<td id='blink' style='color: rgb(0, 150, 209); padding-left:4px;padding-right:5px;''>"+onM[2]+"</td>"+
					      "<td>"+onM[3]+"</td>"+
					      "<td>1분</td>"+
					      "<td><input id="+onM[2]+" class = "+onM[2]+" type = button  value = '상세보기' onclick='orderdetailform()'></td>"+
					      "/tr>"+
					      ""
					          
					  )
					  $("#order_state_td_1").val(ostd1+1);   
			}else if(onM[1]==2){
					  $(".main_information_content_table tr:eq(1)").before("<tr>"+							  
					      "<td class = 'td_state'><div style='color:white; background-color: rgb(222, 154, 72); line-height:30px; border-radius:6px; border:none; ' ><span>대기중</span></div></td>"+
					      "<td style='color: rgb(0, 150, 209); padding-left:4px;padding-right:5px;''>"+onM[2]+"</td>"+
					      "<td>"+onM[3]+"</td>"+
					      "<td>1분</td>"+
					      "<td><input id="+onM[2]+" class = 'checkBtn' type = button  value = '상세보기' onclick='orderdetailform()'></td>"+
					      "/tr>"+
					      ""
					              
					  )
					  $("#order_state_td_2").val(ostd2+1);
			}else if(onM[1]==3){
					 $(".main_information_content_table tr:eq(1)").before("<tr>"+							  
					      "<td class = 'td_state'><div style='color:white; background-color: rgb(92, 157, 82); line-height:30px; border-radius:6px; border:none; ' ><span>대기중</span></div></td>"+
					      "<td style='color: rgb(0, 150, 209); padding-left:4px;padding-right:5px;''>"+onM[2]+"</td>"+
					      "<td>"+onM[3]+"</td>"+
					      "<td>1분</td>"+
					      "<td><input id="+onM[2]+" class = 'checkBtn' type = button  value = '상세보기' onclick='orderdetailform()'></td>"+
					      "/tr>"+
					      ""
					              
					 )
					 $("#order_state_td_3").val(ostd3+1); 
			}else if(onM[1]==4){
					$(".main_information_content_table tr:eq(1)").before("<tr>"+							  
					      "<td class = 'td_state'><div style='color:white; background-color: rgb(0, 150, 209); line-height:30px; border-radius:6px; border:none; ' ><span>대기중</span></div></td>"+
					      "<td style='color: rgb(0, 150, 209); padding-left:4px;padding-right:5px;''>"+onM[2]+"</td>"+
					      "<td>"+onM[3]+"</td>"+
					      "<td>1분</td>"+
					      "<td><input id="+onM[2]+" class = 'checkBtn' type = button  value = '상세보기' onclick='orderdetailform()'></td>"+
					      "/tr>"+
					      ""
					               
					 )
					 $("#order_state_td_4").val(ostd4+1);
			}
		  }	      
		  
	        var times = 10;
	        var loop = setInterval(repeat, 1100);
	        function repeat() {
	            times--;
	            if (times === 0) {
	                clearInterval(loop);
	            }
	            var audio = document.createElement("audio");
	            audio.src = "audio/notyfi.mp3";
	            audio.play();
	        }
	        repeat();    
		
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
		

		
		
		function orderdetailform(){

                console.log(event.srcElement.id);

		    		document.formm.action = "MbergerServlet?command=managerDetail&ordernum="+ event.srcElement.id;
		 		    document.formm.submit();	    	
		    
		 	}
			    

  
  
  </script>

</html>