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

<link rel="stylesheet" href="css/orderdetail.css?ver=1.1"  >
<script type="text/javascript" src = "script/main.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">




	

    <form class = "OrderDetail_form">
        <div class = "OrderDetail_form_div">
            
            <table id = "content_table1" class = "content_table">
            	<thead> 
	                <tr class = "content_table_tr" >
	                    <th class = "or_date">주문일자</th>
	                    <th class = "or_email">이메일</th>
	                    <th class = "or_address">주소</th>
	                    <th class = "or_name">상품명</th>
	                    <th class = "or_grade2">구분</th>
	                    <th class = "or_side">사이드</th>
	                </tr>
	            </thead>    
				<tbody>
	              <c:forEach items="${orderList }"  var="orderVO" varStatus="status"> 
	              	
	                  <tr>
	                      <td rowspan="3" style="width:150px" >${orderVO.date }</td>
	                      <td rowspan="3" style="width:190px">${orderVO.email }</td>
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
	                  
	              </c:forEach> 


				</tbody>
            </table>

            <input class = "order_button" type = "button" value = "주문취소" onclick="location.href='#pop01'">
            <input class = "order_button" type = "button" value = "조회로 돌아가기">

        </div>
        
        <div id="pop01" class="overlay">
        
    <div class="popup10" style=" text-align :center;" >

      <a href="" class="close">&times;</a>
      <h4 id ="login_logo" style="color: red; text-align :center; ">주문취소</h4>
      <p style=" text-align :center;" >주문을 취소 하시겠습니까?</p>
      <br><br>
                  <input  type = "button" value = "취소" onclick="location.href='MbergerServlet?command=deleteorder'" >

    
  
    </div>
  </div>
        
        
    </form>
    
    </body>
  
  
</html>