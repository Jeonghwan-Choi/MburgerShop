package com.mbergershop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.MemberVO;
import com.mbergershop.dao.MemberDAO;
import com.mbergershop.dto.ProductVO;

//import com.cos.blog.action.Action;
//import com.cos.blog.dto.KakaoProfile;
//import com.cos.blog.model.OAuthToken;
//import com.cos.blog.model.Users;
//import com.cos.blog.repository.UsersRepository;
//import com.cos.blog.util.Script;
//import com.google.gson.Gson;

public class KakaoLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=74367f5ed86a607b43fc2ddbd5a8b756"); //수정 할것
            sb.append("&redirect_uri=http://localhost:8080/MBugerShop/MbergerServlet?command=indexKakao"); //수정 할것
            sb.append("&client_secret=OnGNmCDGFyzQNvsxG3ViOnK4fCOgMc41"); //수정 할것
            sb.append("&code=" + request.getParameter("code"));
            bw.write(sb.toString());
            bw.flush();
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
       
//            access_Token = element.getAsJsonObject().get("access_token").getAsString();
//            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
    		//카카오 토큰값으로 조회한 문자열 자르기 
    	    String str = result;
    	    String[] array = str.split(",");
    	    		    
    	    //출력				
    	    for(int i=0;i<array.length;i++) {
    	    System.out.println(array[i]);
    	    }
            
    	    System.out.println(array[0]);
    	    
    	    String Ktoken = array[0].replaceAll("\\\"","");
    	    
    	    String Ktokenstr = Ktoken;
    	    String[] Ktokenarray = Ktokenstr.split(":");
    	    
    	    

            System.out.println("access_token : " + Ktokenarray[1]);
            access_Token = Ktokenarray[1];
            
            br.close();
            
         
            

            //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
//            HashMap<String, Object> userInfo = new HashMap<String, Object>();
            String reqURL1 = "https://kapi.kakao.com/v2/user/me";
            try {
                URL url1 = new URL(reqURL1);
                HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
                conn1.setRequestMethod("GET");

                //    요청에 필요한 Header에 포함될 내용
                conn1.setRequestProperty("Authorization", "Bearer " + access_Token);

                int responseCode1 = conn1.getResponseCode();
                System.out.println("responseCode : " + responseCode1);

                BufferedReader br1 = new BufferedReader(new InputStreamReader(conn1.getInputStream()));



                while ((line = br1.readLine()) != null) {
                    result += line;
                }
                System.out.println("response body : " + result);
                
        	    String str1 = result;
        	    String[] array1 = str1.split(",");
        	    		    
        	    //출력				
        	    for(int i=0;i<array1.length;i++) {
        	    System.out.println(array1[i]);
        	    }
        	    
        	    System.out.println(array1[11]);
        	    
        	    String email = array1[11].replaceAll("\\\"","");
        	    
        	    String emailstr = email;
        	    String[] emailarray = emailstr.split(":");
        	    
        	    email = emailarray[1];
        	    System.out.println(email);
        	    
        	   
        	    System.out.println("카카오 로그인 성공");
    	    	
//        		String url2 = "/index.jsp";
//        		

//        		session.removeAttribute("id");
//                session.setAttribute("loginUser", email);
////                session.setAttribute("loginPhone", phone);
//        		
//        		ProductDAO productDAO = ProductDAO.getInstance();
//
//        		ArrayList<ProductVO> bestProductList = productDAO.listBestProduct();
//        		
//
//        		request.setAttribute("bestProductList", bestProductList);
//        		
//        		RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
//        	    dispatcher.forward(request, response);
        	    
        	    
        		HttpSession session=request.getSession();
        	    MemberDAO memberDAO = MemberDAO.getInstance();
    		    MemberVO KakaoLogin = memberDAO.NaverLogin(email);
    		    
    			   
    		    
    		    int Kcount =  Integer.parseInt(KakaoLogin.getCount());
    		    
    		    System.out.println("갯수: " + Kcount);
    		    
        	    
        	    
        	    
        	    if(Kcount == 0) {
    		    	
    		    	String url2 = "jsp/join.jsp";

    		    	session.setAttribute("joinEmail", email);
    		    	
    		    	
    				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
    			    dispatcher.forward(request, response);
    		    	
    		    }else if(Kcount == 1)  {
    		    	
    				String url2 = "/index.jsp";
    				
    				
    				session.removeAttribute("id");
    		        session.setAttribute("loginUser", email);
    				
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
    				
    				
    				RequestDispatcher dispatcher = request.getRequestDispatcher(url2);
    			    dispatcher.forward(request, response);
    		    }
    		    
        	    
        	    
        	    
            
    	        } catch (IOException e) {
    	            // TODO Auto-generated catch block
    	            e.printStackTrace();
    	        }

            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
       
        


       
		
	}

}
