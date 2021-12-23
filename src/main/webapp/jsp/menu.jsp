<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.text.SimpleDateFormat"%>
<%@ page import ="java.util.Date" %>
<%@ page import ="com.mbergershop.dao.CartDAO" %>
<%@ page import ="com.mbergershop.dto.CartVO" %>
<%@ page import ="com.mbergershop.dao.OrderDAO2" %>
<%@ page import ="com.mbergershop.dto.OrderVO" %>
<%@ page import = "java.util.ArrayList" %>    
<%@ page import = "javax.servlet.http.HttpServletRequest" %>
<%@ page import = "javax.servlet.http.HttpServletResponse" %>   
<%@ page import = "javax.servlet.http.HttpSession" %>   


<%@ include file = "header.jsp"%>

<link rel="stylesheet" href="css/menu.css?ver=1.1"  >
<script type="text/javascript" src = "script/main.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
<script>

		function deleteCart() {
			
			console.log(event.srcElement.id);

 		 	document.formmm.action = "MbergerServlet?command=delete_Cart&CartNum="+ event.srcElement.id;
 		    document.formmm.submit(); 

		}

		var textarea = document.getElementById("messageWindow");
		
		var chatdiv = document.getElementById("ChatBox");
		
		var webSocket = new WebSocket('ws://localhost:8080/MBugerShop/broadcasting');
		var inputMessage = document.getElementById('inputMessage');
		var WOrdernum = document.getElementById('new_order_num');
		
        let today = new Date();   

        let years = today.getFullYear();
        let months = today.getMonth();
        let days = today.getDay();
        let hours = today.getHours(); // 시
        let minutes = today.getMinutes();  // 분
        let seconds = today.getSeconds();  // 초

        console.log();
		
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
/* 		textarea.value += "<a>상대 : " + event.data + "\n</a>";
		document.getElementById("ChatBox").innerHTML += 
		      "<h3>"+ event.data+"</h3>";
		var edata = event.data;
		var onM = edata.split(',');
		
		if(onM[1]==1){
		$("#ChatTable tr:eq(1)").before("<tr>"+
		    "<td>"+onM[0]+"</td>"+
		    "<td>"+onM[1]+"</td>"+
		    "<td>"+onM[2]+"</td>"+
		    "/tr>");
		}; */
		}
		
		function onOpen(event) {


		}
		
		function onError(event) {
		alert(event.data);
		}
		
		function send() {
	
		
		}
		




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

function requestPay() {
		
    var IMP = window.IMP; 
    IMP.init('imp96463033'); 
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
    		
    	
    	
      pg : 'kakaopay',
      pay_method : 'card', //생략 가능
      merchant_uid: document.getElementById("new_order_num").value, // 상점에서 관리하는 주문 번호
      name : '주문명:결제테스트',
      amount : document.getElementById("order_total").value,
      buyer_email : 'iamport@siot.do',
      buyer_addr :  document.getElementById("member_post").value +" " + document.getElementById("member_addr").value,
    }, function (rsp) { // callback
      if (rsp.success) { // 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
        // jQuery로 HTTP 요청
        
/*         
        jQuery.ajax({
            url: "{서버의 결제 정보를 받는 endpoint}", // 예: https://www.myservice.com/payments/complete
            method: "POST",
            headers: { "Content-Type": "application/json" },
            data: {
                imp_uid: rsp.imp_uid,
                merchant_uid: rsp.merchant_uid
            }
 
        }).done(function (data) { */
        	alert("결제에 성공하였습니다.");
        	
        
        	document.formm.action = "MbergerServlet?command=insert_order";
		    document.formm.submit();
		    
			webSocket.send("1,1,"+document.getElementById('new_order_num').value+","+years+"-"+months+"-"+days+"/"+hours+":"+minutes+":"+seconds+","+"00:00");

/*         }) */
      } else {
        alert("결제에 실패하였습니다. 에러 내용: " +  rsp.error_msg);
      }
    });
  }

</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<form name = "formm" method ="post" action="MbergerServlet?command=insert_order"  >



 <div id="menu_form" >   
    <div id="left-menu" >

        <ul>
            <li style="background-color: rgb(122, 122, 122); " id = "menu_li_top" ><div >일반메뉴</div></li>
            <li><a href="MbergerServlet?command=menu_recommand" name = "recommand" >추천메뉴</a></li>
            <li><a href="MbergerServlet?command=menu_burger" name = "burger" >버거 & 세트</a></li>
            <li><a href="MbergerServlet?command=menu_side" name = "side"   >스낵 & 사이드</a></li>
            <li><a href="MbergerServlet?command=menu_drink" name = "drink"  >음료</a></li>
            
        </ul>
    </div>
	
	<input id="new_order_num" type = "text" name="new_order_num" value ="${sessionScope.newordernum}" style="display:none;" >
    <div id="menu_front_menu">
        <h2 style="color : rgb(95, 95, 95);"> ${productVO.grade}</h2>     
        <div id="bestitem">         
			<c:forEach items="${gradeProductList }"  var="productVO">
	            <div id="item">
	              <c:choose>
					   <c:when test="${empty sessionScope.loginUser}">
							<a id = "product_name" href="#"  onclick="showPopup(false)"  >
					  </c:when>
					  <c:otherwise>  					  	
					  	<a id = "product_name" href="MbergerServlet?command=product_detail&code=${productVO.code}"  >				          
					  </c:otherwise>       
				    </c:choose>
	                <img src="productimg/${productVO.grade}/${productVO.name}.png" />
	                <p >${productVO.name}</p>    
	                <p style="color : rgb(55, 126, 34);" >₩${productVO.price}</p>
	              </a>    
	            </div>	
			</c:forEach>
			
			
           
        </div>





    </div>  

    <div>
      <div class = "right_menu">
        <div style="padding-top: 10px; background-color: rgb(235, 235, 235); padding-bottom: 10px">내 주문목록</div>

        <div style="text-align: left;">
          <div >
            <table class = "order_table">
              <tr>
                <td class = "topic_td" >
                	<a>배달 주소 :</a>
                	
                </td>
                <td class = "content_td" >
                	<input class="content_td_address" id = "member_post" name ="member_post" type = "text" value ="${sessionScope.member_post }" readonly>
                	<input class="content_td_address" id = "member_addr" name ="member_addr"type = "text" value ="${sessionScope.member_addr }" readonly>
                	<input class="content_td_address" name = "member_addr_1"type="text" placeholder="상세주소" value="${sessionScope.member_addr_detail }">
                </td>
              </tr>
              <tr>
                <td class = "change_td" >
                  <a class = "change_td" href = # onclick="findAddr()">변경</a>
                </td>
              </tr>
            </table>
          </div>    
          <hr>
          <div>
            <table class = "order_table">
              <tr>
                <td class = "topic_td" >
                  <a>총 주문 합계</a>
                </td>
                <td class = "content_td" style ="color : rgb(88,140,42); font-size: 25px;" >₩ 
                 	<input id="order_total" class = "order_total" name = "order_total" type = "text" value = "${sessionScope.Total}">                
                 </td>
                
              </tr>
              <tr>
                <td class = "order_button_td"  colspan="2">
                	<c:choose>
					   <c:when test="${empty sessionScope.loginUser}">
						<input class = "order_button" type="button" value = "결제" onclick="showPopup(false)"  >	
					  </c:when>
					  <c:otherwise>  					  	
					  	<input class = "order_button" type="button" value = "결제" onclick="requestPay()" >	          
					  </c:otherwise>       
				    </c:choose>
                  
                </td>
                
              </tr> 
            </table>
          </div>

        </div>
        <div style="text-align: left;padding-left: 10px; padding-bottom: 8px;background-color: rgb(235, 235, 235); padding-top: 10px;">
          주문 세부사항
        </div>


	        	<c:choose>
		       <c:when test="${empty sessionScope.loginUser}">

			        
			
			        
				        <table class = "order_detail_table">

				        </table>

		       </c:when>
		      
		        <c:otherwise>
		        <form name = "formmm" method ="post" action="MbergerServlet?command=delete_Cart&"  >
			        <c:forEach items="${CartList }"  var="cartVO" varStatus="status">
			        
			
			        
				        <table class = "order_detail_table">
						<input name = "id_${status.index+1 }" type="hidden" value = "${cartVO.id }">
				          <tr>
				            <td class = "order_detail_table_td_num"><input type = "text" class = "order_count" name = "order_count" value = "${status.index+1}" readonly ></td>
				            <td class = "order_detail_table_td"><img class = "order_detail_table_td_img" src="productimg/${cartVO.grade }/${cartVO.name }.png"></td>
				            <td class = "order_detail_table_td_side">
				            	<input  name = "grade2_${status.index+1 }" class = "order_td_content" type="text"  value = "•${cartVO.grade2 } " readonly  >
				            </td>
				          </tr>
				          <tr>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td_side">
				            	<input  name = "name_${status.index+1 }" class = "order_td_content" type="text"  value = "• 단품 - ${cartVO.name } " readonly  >
				            </td>
				          </tr>
				          <tr>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td_side">
				            	<input type = "hidden" class = "order_td_content" name = "side_${status.index+1}_1" value = "${cartVO.side1 }" readonly>
				            	<a class = "order_td_content" >${cartVO.side1 }</a>
				            </td>
				          </tr>
				          <tr>
				            <td class = "order_detail_table_td"></td>	
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td_side">
				             	<input type = "text" class = "order_td_content" name = "side_${status.index+1}_2" value = "${cartVO.side2 }" readonly>
				             </td>
				          </tr>
				          <tr>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td_side">
				             	<input type = "text" class = "order_td_content" name = "side_${status.index+1}_3" value = "${cartVO.side3 }" readonly>
				             </td>
				          </tr>
				          <tr>
				            <td class = "order_detail_table_td"></td>
				            <td class = "order_detail_table_td">
				              <input  id="${cartVO.id }"  class = "order_detail_button" type="button" value = "삭제" onclick="deleteCart()">
				            </td>
				            <td class = "order_detail_table_td_price">₩ 
								<input type = "text" class = "order_td_content_price" name = "price_${status.index+1}" value = "${cartVO.price }">
							</td>
				          </tr>
				        </table>
				    </c:forEach>
				    </form>
		       </c:otherwise>       
		       
    
		     </c:choose>
			
        
        



        

      </div>


    </div>

    
</div>   
</form>
</body>

</html>