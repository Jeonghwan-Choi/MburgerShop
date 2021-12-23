
<link rel="stylesheet" href="css/join.css?ver=1.1"  >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.2.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
    <form id ="join_form" method="post" action="MbergerServlet?command=join" name = "formm" >
        <div>
        <input type="text" value="${sessionScope.naverID}" style="display:none;">
            <a id = "main_text">회원가입</a>
            <br>
            <br>
            <div>*필수 항목</div>
            <br>
            <div>
                <div>* 성함 :</div>
                <input type = "text" id = "name" name = "name" value="${sessionScope.joinName}" >

                <div>* 성별 :</div>
                <select name="gender" id="gender" style="height:50px; width:622px;" >
                    <option value = "남자">남자</option>
                    <option value = "여자">여자</option>
                </select>
                
                <div>* 권한 :</div>
                <select name="admin" id="admin" style="height:50px; width:622px;" >
                    <option value = "2">일반회원</option>
                    <option value = "1">관리자</option>    
                </select>


                <div>* 휴대전화 번호 :</div>
                <input type = "text" id = "phone" name = "phone" value="${sessionScope.joinPhone}" >
                <div>* 주소 :</div>
                <input style="width:234px;" class="content_td_address" id = "member_post" name ="member_post" type = "text" value ="" readonly>
                <input style="width:234px;" class="content_td_address" id = "member_addr" name ="member_addr"type = "text" value ="" readonly>
				<input class="addr_button" type="button" value="주소검색" onclick="findAddr()" ><br>
                <input class="content_td_address" name = "member_addr_1"type="text" placeholder="상세주소">
                
                <br>
                <br>

                <a id = "main_text">계정정보</a>
                <br>
                <br>
                <div>* 이메일 :<br>
                    <input type = "text" id = "email" name = "email" value="${sessionScope.joinEmail}" >
                    <input class="addr_button" type="button" value="중복확인" onclick="emailcheck()" ><br>
                    <p id = "email-t">이메일 주소는 고객님의 안전한 거래와 회원정보 보호를 위해 정확하게 입력해 주세요.</p>
                </div>
                
                
                <div>* 이메일 확인 :</div>
                <input type = "text" id = "email-chk" name = "emailchk" >
                

                <div>* 비밀번호 :</div>
                <input type = "password" id = "password" name = "password"  onkeyup="checkPwd()" >
                <div>* 비밀번호 확인 :</div>
                <input type = "password" id = "password_chk" name = "passwordchk" onkeyup="checkPwd()" >
				<div id="checkPwd" style="margin-left:15px; font-size:13px;"> 암호를 입력하세요.</div>
                <br>
                <br>

<!--                 <a id = "main_text">이용약관</a>
                <br>
                <br>
                <div>
                    <input id="chk" type= "checkbox">
                    <a  >전체동의</a>&nbsp;<a style="color :rgb(204, 204, 204); font-size: 13px;  " >(개별 선택 가능)</a>
                </div>
                <div>
                    <input id="chk" type= "checkbox">
                    <a href =# style="color:rgb(99, 181, 243); text-decoration: none;">이용약관</a>
                    <a>을 확인했으며, 이에 동의합니다. 
                    </a><a style="color :rgb(204, 204, 204); font-size: 13px;  " >(필수)</a>
                </div> -->
                
                
            </div>
            <input class = "join_button" type = "button" value = "계정생성" onclick="idcheck()">
        </div>
    </form>
    
    <script>
    
    function checkPwd(){
    	  var f1 = document.forms[0];
    	  var pw1 = formm.password.value;
    	  var pw2 = formm.password_chk.value;
    	  if(pw1!=pw2){
    	   document.getElementById('checkPwd').style.color = "red";
    	   document.getElementById('checkPwd').innerHTML = "동일한 암호를 입력하세요.";
    	  }else{
    	   document.getElementById('checkPwd').style.color = "black";
    	   document.getElementById('checkPwd').innerHTML = "암호가 확인 되었습니다.";
    	   
    	  }
    	  
   }
    
    function idcheck() {
    	  if (document.formm.email.value != document.formm.emailchk.value) {
    	    alert('이메일이 일치하지 않습니다.');
    	    document.formm.password.focus();
    	  }else if (document.formm.password.value != document.formm.passwordchk.value) {
    	    alert('비밀번호가 일치하지 않습니다.');
    	    document.formm.password.focus();
    	  }else {
    		    document.formm.action = "MbergerServlet?command=join";
    		    document.formm.submit();
    	  }
    }	  
    
    function emailcheck() {
    	  if (document.formm.email.value == "") {
    	    alert('이메일을 입력하여 주십시오.');
    	    document.formm.email.focus();
    	    return;
    	  }
    	  var url = "MbergerServlet?command=id_check_form&id=" 
    	+ document.formm.email.value;
    	  window.open( url, "_blank_1",
    	"toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=400, height=250");
    }
    
    
 
    </script>
    
</body>
</html>