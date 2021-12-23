function showPopup(hasFilter) {
	const popup = document.querySelector('#popup');
  
  if (hasFilter) {
  	popup.classList.add('has-filter');
  } else {
  	popup.classList.remove('has-filter');
  }
  
  popup.classList.remove('hide');
}

function closePopup() {
	const popup = document.querySelector('#popup');
  popup.classList.add('hide');
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

		
	function order_table(){
	    const rowCnt = 5;
	    const columnCnt = 2;
	        document.writeln("");
	        document.writeln('<table border="1" style="margin:100px;">');
	        for (let i = 0; i < rowCnt; i++) {
	        document.writeln('<tr>');
	        for (let j = 0; j < columnCnt; j++)  {
	            document.writeln('<td>');
	            document.writeln(i + ", " + j);
	            document.writeln('</td>');
	        }
	        document.writeln('</tr>')
	        }
	        document.writeln('</table>');
	}
	
	function order_text(){
	
	
	    var 템플릿 = '<p>안녕하세요</p>'
	    $('login').html(템플릿);
	
	}
	
	
				




}


function showPopup1(hasFilter) {
	const popup = document.querySelector('#popup_mypage');
  
  if (hasFilter) {
  	popup.classList.add('has-filter');
  } else {
  	popup.classList.remove('has-filter');
  }
  
  popup.classList.remove('hide');
}

function closePopup1() {
	const popup = document.querySelector('#popup_mypage');
  popup.classList.add('hide');
}
