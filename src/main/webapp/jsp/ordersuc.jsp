<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "header.jsp"%>
<link rel="stylesheet" href="css/ordersuc.css?ver=1.1"  >


     <form class = "ordersuc_main_form">
        <div class = "ordersuc_form_title" >
            <p class = "ordersuc_form_title_text">주문완료</p>
        </div>

        <div class = "ordersuc_form_content" style="text-align:center;">
        
        
                <div class = "ordersuc_form_content_title">


                    <div class = "ordersuc_form_content_main">
                        <a class = "ordersuc_order_text" >고객님 </a> 
                        <a style="color :red;">주문이 완료 </a>
                        <a class = "ordersuc_order_text">되었습니다.</a>
                    </div>
                    <div>
                        <a class = "ordersuc_order_text">고객님이 주문하신 주문번호는</a><br>
                        <a class = "ordersuc_order_number">${sessionScope.orderNum}</a>&nbsp;
                        <a class = "ordersuc_order_text">입니다</a>

                    </div>
                    
                   
                </div>
					 <input class = "order_button" type="button" value = "메뉴로 돌아가기" >


        </div>


    </form>



    
</body>
</html>