<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/order.css "  >
    <script type="text/javascript" src = "script/main.js"></script>
    <title>Document</title>
</head>
<body>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
  <script type="text/javascript">

        function oneCheckbox(a){
    
            var obj = document.getElementsByName("toggle");
    
            for(var i=0; i<obj.length; i++){
    
                if(obj[i] != a){
    
                    obj[i].checked = false;
    
                }
    
            }
    
        }
    
    </script>



    <form id = "order_form" method="post" name="formm" action="MbergerServlet?command=insert_cart">
    
    <input type="checkbox" id="expand-toggle1" name ="toggle" onclick="oneCheckbox(this)" value = "1" />
    <input type="checkbox" id="expand-toggle2" name ="toggle" onclick="oneCheckbox(this)" value = "2"/>
    <input type="checkbox" id="expand-toggle3" name ="toggle" onclick="oneCheckbox(this)" value = "3"/> 
    
    

    
        <input type = "hidden" name ="email" value = "${sessionScope.loginUser}">
   <c:forEach items="${gradeProductList }"  var="productVO"  varStatus="status" begin="1" end="1">
   
    <input type = "hidden" name ="order_code" value = "${productVO.code}">
    <input type = "hidden" name ="order_name" value = "${productVO.name}">
    <input type = "hidden" name ="order_grade"  value = "${productVO.grade}">
   </c:forEach>
   

    
    <div class="main_form" >

            
            <div class="head">
                <div class="container">
                <c:forEach items="${gradeProductList }"  var="productVO"  varStatus="status"  begin="1" end="1">
                        <ul>
                        <li style="padding-right:30px">
                            <div class="count">
                                <span class="add_text">0</span>
                                <br> 
                                <span class="count_text">수량</span>
                            </div>
                        </li>
                        <li><div ><img class="pic" src="productimg/${productVO.grade}/${productVO.name}.png" alt=""></div></li> 
                        <li  ><h1 class="item_name" >${productVO.name}</h1></li>
                        <li class="close_li"  ><button class="close_button" onclick="closePopup()">
                            <img src="productimg/icon/cancel.png" ><img>
                        </button></li>   
                        </ul>
                 </c:forEach>
   
                </div>
            </div>    
        </div>

        <div class="order_form" >
            <div class="order_list">

                <div>
                    <table class = "order_table">
          
                        <tr class = "order_tr">
                            <th class = "order_th"  style="float: left; "  ><h3>1단계 : 메뉴를 선택하세요</h3></th>
                            <th></th>
                            <th></th>
                            <th class = "order_th" style="text-align: right;" >가격</th>
                            <th class = "order_th" style="text-align: right;">kcal</th>
                        </tr>
                     <c:forEach items="${gradeProductList }"  var="productVO" varStatus="status">
                        <tr>
                                <td  class="order_td_column" style="width: 200px; text-align: center; "  >
                                    <label for="expand-toggle${status.index+1}"id="expand-btn">선택</label>

                                </td>
                                <td class ="order_td_pic" style="width: 100px; ">
                                    <img class="td_pic" src="productimg/set/set2.png" alt="">
                                </td>
                                <td ><h4>${productVO.grade2 }</h4></td>
                                <td  style="text-align: right; color: rgb(170, 170, 170); " >₩ 
                                <input class = "order_text" name="order_price${status.index+1}" type= "text" value = "${productVO.price}">
                                </td>
                                <td  style="text-align: right; color: rgb(170, 170, 170); "><a>${productVO.kcal } kcal</a></td>
                            
                        </tr>
                    </c:forEach>    


                    </table>
                    <h3 class = "second_text" style = "padding:14px">2단계 : 메뉴를 항목을 선택하세요</h3>
                    <table class = "order_table2" >
        
                        <tr class = "expandable1" style="border-top : 1px rgb(182, 182, 182) solid;">
                            <td  style="width: 30px; "><img src="productimg/icon/cancel.png" ><img></td>
                            <td  style="width: 120px; border-right: 1px rgb(182, 182, 182) solid; ">
							 <input type="text" class = "order_table2_text" name = "order_grade2_1" value = "라지세트" readonly>							
							</td>
                            <c:forEach items="${gradeProductList }"  var="productVO"  varStatus="status"  begin="1" end="1">
                            <td  style="width: 450px; padding-left: 30px;  border-right: 1px rgb(182, 182, 182) solid;" >• 단품 - ${productVO.name}</td>
							</c:forEach>                           
                            <td  style=" width:150px; height: 80px; ">
                                <div style="width: 150px;">
                                    <input type="text" class = "order_table2_text" name = "side_1_1" value = "• 품절 시 맥너겟/치즈스틱으로 배송될 수 있습니다." readonly>
                                </div>
                            </td>
                            <td style="width: 30px;"> </td>
                        </tr>
                        <tr class = "expandable1">
                            <td></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;"></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td>
                                 <input type="text" class = "order_table2_text" name = "side_1_2" value = "• 코카-콜라 - 미디엄" readonly>
                            </td>
                            <td style="width: 30px;"> </td>
                        </tr> 
                        <tr class = "expandable1">
                            <td ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;"></td>
                            <td>
								<input type="text" class = "order_table2_text" name = "side_1_3" value = "• 선택안함" readonly>
                            </td>
                            <td style="width: 30px;">   </td>
                        </tr>     
                        </table>

                        <table class = "order_table2" >
                        <tr class = "expandable2" style="border-top : 1px rgb(182, 182, 182) solid;">
                            <td  style="width: 30px; "><img src="productimg/icon/cancel.png" ><img></td>
                            <td  style="width: 120px; border-right: 1px rgb(182, 182, 182) solid; ">
								<input type="text" class = "order_table2_text" name = "order_grade2_2" value = "세트" readonly>	
							</td>
                           <c:forEach items="${gradeProductList }"  var="productVO"  varStatus="status"  begin="1" end="1">
                            <td  style="width: 450px; padding-left: 30px;  border-right: 1px rgb(182, 182, 182) solid;" >• 단품 - ${productVO.name}</td>
							</c:forEach>
                            <td  style=" width:150px; height: 80px; ">
                                <div style="width: 150px;">
                                    <input type="text" class = "order_table2_text" name = "side_2_1" value = "• 품절 시 맥너겟/치즈스틱으로 배송될 수 있습니다." readonly>
                                </div>
                            </td>
                            <td style="width: 30px;">  </td>
                        </tr>
                        <tr  class = "expandable2">
                            <td></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;"></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td  >
                                 <input type="text" class = "order_table2_text" name = "side_2_2" value = "• 코카-콜라 - 미디엄" readonly>
                            </td>
                            <td style="width: 30px;">  </td>
                        </tr> 
                        <tr  class = "expandable2">
                            <td ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;"></td>
                            <td>
                                 <input type="text" class = "order_table2_text" name = "side_2_3" value = "• 선택 안 함 " readonly>
                            </td>
                            <td style="width: 30px;">   </td>
                        </tr> 
                         
                    </table>
                    
                    <table class = "order_table2" >
                        <tr class = "expandable3" style="border-top : 1px rgb(182, 182, 182) solid;">
                            <td  style="width: 30px; "><img src="productimg/icon/cancel.png" ><img></td>
                            <td  style="width: 120px; border-right: 1px rgb(182, 182, 182) solid; ">
								<input type="text" class = "order_table2_text" name = "order_grade2_3" value = "단품" readonly>	
							</td>
                            <c:forEach items="${gradeProductList }"  var="productVO"  varStatus="status"  begin="1" end="1">
                            <td  style="width: 450px; padding-left: 30px;  border-right: 1px rgb(182, 182, 182) solid;" >• 단품 - ${productVO.name}</td>
							</c:forEach>
                            <td  style=" width:150px; height: 80px;  ">
                                <div style="width: 150px;">
                                     <input type="text" class = "order_table2_text" name = "side_3_1" value = "• 선택 안 함 " readonly>
                                </div>
                            </td>
                            <td style="width: 30px;"> </td>
                        </tr>
                        <tr  class = "expandable3">
                            <td></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td ></td>
                            <td >  </td>
                        </tr> 
                        <tr  class = "expandable3">
                            <td ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;" ></td>
                            <td style="border-right: 1px rgb(182, 182, 182)solid;"></td>
                            <td>
                            </td>
                            <td style="width: 30px;"> <a class ="change_text" href="#" style="float:right ;"></a>  </td>
                        </tr> 
                         
                    </table>



                </div>
            </div>

        </div>

        <div class="footer_form" >
            
            <div class="head">
                <div class="container">
                        <ul>
                        <li class="menu_li">
                            <div>
                                <span style = "color :red;" >《</span>
                                <a href="MbergerServlet?command=menu" class="menu_text">메뉴로 돌아가기</a>

                            </div>
                        </li>

                        <li class = "cart_li" >
                        <input class = "cart_button" type = "button" value = "카트에 담기" onclick="login()">  
                        </ul>


                    
                </div>
            </div>    
        </div>

            

    </form>
</body>
<script>
		function login(){
			
		    

		    
		
		    if($("#expand-toggle1").prop("checked") == false && $("#expand-toggle2").prop("checked") == false && $("#expand-toggle3").prop("checked") == false){
		    	alert('메뉴를 선택해 주시기 바랍니다.');
		    }else {
		    	
		/* 	    	 if (document.formm.email.value == document.formm.emailchk.value) {
		 		    alert('이메일이 일치하지 않습니다.');
		 		    document.formm.password.focus();
		    	 }else{ */
		    		document.formm.action = "MbergerServlet?command=insert_cart";
		 		    document.formm.submit();
		/* 	    	 } */
		    	
			    
		 	}
		
		    
		}

</script>
</html>