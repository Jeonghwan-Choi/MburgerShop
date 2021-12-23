package com.mbergershop.controller.action;

import java.io.IOException;
import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.MemberDAO;
import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.MemberVO;
import com.mbergershop.dto.ProductVO;

public class NaverLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String clientId = "qMh2DeQgQyJSxYChS9ba";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "4EId9V3clC";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://localhost:8080/MBugerShop/MbergerServlet?command=index", "UTF-8");
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    String access_token = "";
	    String refresh_token = "";
	    System.out.println("apiURL="+apiURL);
	    System.out.println("clientId="+clientId);
	    System.out.println("clientSecret="+clientSecret);
	    System.out.println("code="+code);
	    System.out.println("state="+state);
	    System.out.println("redirectURI="+state);
	    
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      System.out.println("responseCode="+responseCode);
	      if(responseCode==200) { // 정상 호출
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  // 에러 발생
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      String inputLine;
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode==200) {
	        System.out.println(res.toString());//여기가 토큰값
	        
	        String str1 = res.toString();
	        String[] array1 = str1.split("\\\"");
	        String Ntoken = array1[3];
	        
	        System.out.println("토큰값 조회 : " + Ntoken );
	       
	        
	        
		    String token = Ntoken; // 네이버 로그인 접근 토큰;
		    String header = "Bearer " + token; // Bearer 다음에 공백 추가
		
		
		    String apiURL1 = "https://openapi.naver.com/v1/nid/me";
		
		
		    Map<String, String> requestHeaders = new HashMap<>();
		    requestHeaders.put("Authorization", header);
		    String responseBody = get(apiURL1,requestHeaders);
		
		
		    System.out.println(responseBody);
		    
		    
		    

		    
		    
		    
			//네이버 토큰값으로 조회한 문자열 자르기 
		    String str = responseBody;
		    String[] array = str.split(",");
		    		    
		    //출력				
		    for(int i=0;i<array.length;i++) {
		    System.out.println(array[i]);
		    }
		    
		    System.out.println(array[4]);
		    System.out.println(array[5]);
		    
		    String NID = array[2].replaceAll("\\\"","");
		    String email = array[4].replaceAll("\\\"","");
		    String phone = array[5].replaceAll("\\\"","");
		    String name = array[7].replaceAll("\\\"","");
		    
		    String NIDstr = NID;
		    String[] NIDarray = NIDstr.split(":");
		    
		    String emailstr = email;
		    String[] emailarray = emailstr.split(":");
		    
		    String phonestr = phone;
		    String[] phonearray = phonestr.split(":");
		    
		    String namestr = name;
		    String[] namearray = namestr.split(":");
		    
		    
		    NID = NIDarray[2];
		    email = emailarray[1];
		    phone = phonearray[1];
		    
		    name = namearray[1];
		    
		    
		    //유니코드로 만들어진 이름 변환 
		    StringBuffer sb = new StringBuffer();
			 for (int i = 0; i < name.length(); i++) {
				 if ('\\' == name.charAt(i) && 'u' == name.charAt(i + 1)) {
					 Character r = (char) Integer.parseInt(name.substring(i + 2, i + 6), 16);
					 sb.append(r);
					 i += 5;
				 } else {
					 sb.append(name.charAt(i));
				 }
			 }
			 
			 System.out.println(sb.toString());

		    name = sb.toString();
		    
		    

		    
		    System.out.println("NID: "+NID);
		    System.out.println("EMail: "+email);
		    System.out.println("Phone :"+phone);
		    System.out.println("Name :"+name);
		    
		    
		    System.out.println("네이버 로그인 성공");
		    
		    
		    MemberDAO memberDAO = MemberDAO.getInstance();
		    MemberVO NaverLogin = memberDAO.NaverLogin(email);
		    
		   
		    
		    int Ncount =  Integer.parseInt(NaverLogin.getCount());
		    
		    System.out.println("갯수: " + Ncount);
		    HttpSession session=request.getSession();
		    
		    if(Ncount == 0) {
		    	
		    	String url1 = "jsp/join.jsp";
		    	session.setAttribute("naverID", NID);
		    	session.setAttribute("joinName", name);	    	
		    	session.setAttribute("joinPhone", phone);
		    	session.setAttribute("joinEmail", email);
		    	
		    	
				RequestDispatcher dispatcher = request.getRequestDispatcher(url1);
			    dispatcher.forward(request, response);
		    	
		    }else if(Ncount == 1)  {
		    	
				String url1 = "/index.jsp";
				
				
				session.removeAttribute("id");
		        session.setAttribute("loginUser", email);
		        session.setAttribute("loginPhone", phone);
				
				ProductDAO productDAO = ProductDAO.getInstance();

				ArrayList<ProductVO> bestProductList = productDAO.listBestProduct();
				request.setAttribute("bestProductList", bestProductList);
				
				
				
				MemberVO NMember = memberDAO.getMember(email);
				
				System.out.println("이메일 : " + NMember.getEmail());
				System.out.println("핸드폰 : " + NMember.getPhone());
				System.out.println("이름 : " + NMember.getName());
				System.out.println("주소 : " + NMember.getAddress());
				
				String[] AddSplit = NMember.getAddress().split("/");
				
				
				
				request.setAttribute("NMember",NMember);
				
				session.setAttribute("member_post", AddSplit[0]);
				session.setAttribute("member_addr", AddSplit[1]);
				session.setAttribute("member_addr_detail", AddSplit[2]);
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(url1);
			    dispatcher.forward(request, response);
		    }
		    
		    	

			
			
	        
	      }
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	
	
	
	
	
	

	    

	}
	
	 private static String get(String apiUrl, Map<String, String> requestHeaders){
	        HttpURLConnection con = connect(apiUrl);
	        try {
	            con.setRequestMethod("GET");
	            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
	                con.setRequestProperty(header.getKey(), header.getValue());
	            }


	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
	                return readBody(con.getInputStream());
	            } else { // 에러 발생
	                return readBody(con.getErrorStream());
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("API 요청과 응답 실패", e);
	        } finally {
	            con.disconnect();
	        }
	    }
	 
	 private static HttpURLConnection connect(String apiUrl){
	        try {
	            URL url = new URL(apiUrl);
	            return (HttpURLConnection)url.openConnection();
	        } catch (MalformedURLException e) {
	            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
	        } catch (IOException e) {
	            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
	        }
	    }


	    private static String readBody(InputStream body){
	        InputStreamReader streamReader = new InputStreamReader(body);


	        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
	            StringBuilder responseBody = new StringBuilder();


	            String line;
	            while ((line = lineReader.readLine()) != null) {
	                responseBody.append(line);
	            }


	            return responseBody.toString();
	        } catch (IOException e) {
	            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
	        }
	    }

}
