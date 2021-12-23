<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.mbergershop.dao.CartDAO" %>
<%@ page import ="com.mbergershop.dto.CartVO" %>
<%@ page import = "java.util.ArrayList" %>    
<%@ include file = "header.jsp"%>

<link rel="stylesheet" href="css/managerdetail.css?ver=1.0"  >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

<script type="text/javascript" src = "script/main.js"></script>
<script>
function findAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
        	console.log(data);
        	
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var jibunAddr = data.jibunAddress; // 지번 주소 변수
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('member_post').value = data.zonecode;
            if(roadAddr !== ''){
                document.getElementById("member_addr").value = roadAddr;
            } 
            else if(jibunAddr !== ''){
                document.getElementById("member_addr").value = jibunAddr;
            }
        }
    }).open();
}
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


    <fieldset style="display:none;" >
        <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
        <br/>
        <input id="inputMessage" type="text"/>
        <input type="button" value="send1" onclick="send()" />
    </fieldset>
    <form class = "OrderDetail_form" method ="post" name="formm" action="MbergerServlet?command=managerDetail">
        <div class = "OrderDetail_form_div">
            
            <table id = "content_table1" class = "content_table">
	            <thead>
	                <tr class = "content_table_tr" >
	                    <th class = "or_date">주문일자</th>
	                     <th class = "or_date">주문번호</th>
	                    <th class = "or_email">이메일</th>
	                    <th class = "or_address">주소</th>
	                    <th class = "or_name">상품명</th>
	                    <th class = "or_grade2">구분</th>
	                    <th class = "or_side">사이드</th>
	                </tr>
				</thead>
				<tbody>
	             	<c:forEach items="${orderList }"  var="orderVO" varStatus="status"> 
	             	<input id="ordnum" type="text" value="${orderVO.ordernum }">
	                  <tr>
	                 	  <td rowspan="3" >${orderVO.date }</td>
	                      <td rowspan="3" >${orderVO.ordernum }</td>
	                      <td rowspan="3" >${orderVO.email }</td>
	                      <td rowspan="3">${orderVO.address }</td>
	                      <td rowspan="3">${orderVO.name }</td>
	                      <td rowspan="3">${orderVO.grade2 }</td>                                
	                      <td >${orderVO.side1 }</td>                             
	                  </tr>
	                  <tr>
	
	                      <td>${orderVO.side2 }</td>
	                  </tr>
	                  <tr>
	                      <td>${orderVO.side3 }</td>
	                  </tr>
	                  <input type="text">
	              </c:forEach> 
	
	
				</tbody>
            </table>

            <input  class = "state_button1" type = "button" value = "준비중" onclick="send1()" >
            <input  class = "state_button2" type = "button" value = "제조완료" onclick="send2()">
            <input  class = "state_button3" type = "button" value = "전달완료" onclick="send3()">

        </div>
    </form>
</body>
<script>

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
 
			
			}
			
			function onOpen(event) {
			  $("#login li:eq(0)").before("<a class = 'server_state_on'>● Live Service On</a>")
			}
			
			function onError(event) {
				 $("#login li:eq(0)").before("<a class = 'server_state_off'>● Live Service Off</a>")
			}
			
			function send1() {
		/* 		console.log(document.getElementById("ordnum").value); */
				
			webSocket.send("2,2,"+document.getElementById("ordnum").value);
				document.formm.action = "MbergerServlet?command=manager_Detail_Up1&ordnum="+document.getElementById("ordnum").value;
	 		    document.formm.submit();  
			}
			function send2() {
			webSocket.send("2,3,"+document.getElementById("ordnum").value);
				document.formm.action = "MbergerServlet?command=manager_Detail_Up2&ordnum="+document.getElementById("ordnum").value;
	 		    document.formm.submit(); 
			}
			function send3() {
			webSocket.send("2,4,"+document.getElementById("ordnum").value);
			 	document.formm.action = "MbergerServlet?command=manager_Detail_Up3&ordnum="+document.getElementById("ordnum").value;
	 		    document.formm.submit(); 
			}
			
			function orderdetailform(){

                console.log(event.srcElement.id);

	    	
		    
		 	}

</script>
</html>