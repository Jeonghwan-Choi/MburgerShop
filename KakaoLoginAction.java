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
            sb.append("&client_id=rest api"); //수정 할것 //rest api
            sb.append("&redirect_uri=http://localhost:8080/MBugerShop/MbergerServlet?command=indexKakao"); //자신이 등록한 url
            sb.append("&client_secret=시크릿코드"); //수정 할것 //시크릿 코드
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
            ;
            
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
        	    

        	    
        	   
        	    System.out.println("카카오 로그인 성공");
    	    	

        	    
        	    
   
    		    
        	    
        	    
        	    
            
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
