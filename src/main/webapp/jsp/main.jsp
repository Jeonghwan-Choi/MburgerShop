<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



    <div id = "form">

        

        <div class="slider"  >
            <input type="radio" name="slide" id="slide1" checked>
            <input type="radio" name="slide" id="slide2">
            <input type="radio" name="slide" id="slide3">
            <ul id="imgholder" class="imgs">
                <li><img src="productimg/adimage/adimage1.jpeg" ></li>
                <li><img src="productimg/adimage/adimage2.jpeg" ></li>
                <li><img src="productimg/adimage/adimage3.jpeg" ></li>
            </ul>
            <div class="bullets">
                <label for="slide1">&nbsp;</label>
                <label for="slide2">&nbsp;</label>
                <label for="slide3">&nbsp;</label>

            </div>

        </div>

        <hr>

        <div id="front-menu">
            <h2 style="color : rgb(95, 95, 95);"> 추 천 메 뉴</h2>     
            <div id="bestitem">      
            
 
               
				<c:forEach items="${bestProductList }"  var="productVO">
	                <div id="item">
	                <c:choose>
					   <c:when test="${empty sessionScope.loginUser}">
							<a id = "product_name" href="#"  onclick="showPopup(false)"  >
					  </c:when>
					  <c:otherwise>  					  	
					  	<a id = "product_name" href="MbergerServlet?command=product_detail&code=${productVO.code}"  >				          
					  </c:otherwise>       
				    </c:choose>
	                    <img src="productimg/burger/${productVO.name}.png" />
	                    <p >${productVO.name}</p>    
	                    <p style="color : rgb(55, 126, 34);" >₩${productVO.price}</p>
	                  </a>    
	                </div>
				</c:forEach>  

      
    
            </div>


        </div>
    </div>    

</body>
</html>