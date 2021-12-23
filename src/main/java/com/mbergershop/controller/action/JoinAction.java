package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.MemberDAO;
import com.mbergershop.dto.MemberVO;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="jsp/joinemail.jsp";
		
		HttpSession session= request.getSession();
		
//		MemberVO memberVO = new MemberVO();
//		
//		memberVO.setName(request.getParameter("name"));
//	    memberVO.setGender(request.getParameter("gender"));
//	    memberVO.setAdmin(request.getParameter("admin"));
//		memberVO.setPassword(request.getParameter("password"));
//	    memberVO.setEmail(request.getParameter("email"));
//	    memberVO.setPhone(request.getParameter("phone"));  
//	    memberVO.setAddress(request.getParameter("address"));

	    

	    
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String admin = request.getParameter("admin");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");  
		String address = request.getParameter("member_post")+"/"+request.getParameter("member_addr")+"/"+request.getParameter("member_addr_1");
		
		String str = address;
		String result = str.replaceAll("\\s+","_");
		System.out.println(result);
		
		
		System.out.println("1: "+email);
		System.out.println(username);
		System.out.println(gender);
		System.out.println(admin);
		System.out.println(phone);
		System.out.println(password);
		System.out.println(address);
		
		
	   	//generate a 4 digit integer 1000 <10000
        int randomPIN = (int)(Math.random()*9000)+1000;

      
    	
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.naver.com");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "587");
 
        Authenticator auth = new MyAuthentication();
        Session session1 = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session1);
 
        try {
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            
            from = new InternetAddress("MBurgerShop<yzon007@naver.com>");
            msg.setFrom(from);
 
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(Message.RecipientType.TO, to);
 
            msg.setSubject("MBurgerShop 회원가입 인증주소를 보내드립니다."
            		, "UTF-8");
            msg.setText(username+"님 반갑습니다 인증주소는"+"<br>"
            			+"<a href=http://localhost:8080/MBugerShop/MbergerServlet?command=join_access&email="+email+"&name="+username+"&password="+password+"&gender="+gender+"&admin="+admin+"&phone="+phone+"&address="+result+" >여기를 클릭하시면 인증과 함께 회원가입이 완료됩니다.</a>", "UTF-8");
            msg.setHeader("content-Type", "text/html");
 
            javax.mail.Transport.send(msg);
        } catch (AddressException addr_e){
            addr_e.printStackTrace();
        } catch (MessagingException msg_e){
            msg_e.printStackTrace();
        }
		
	    
        session.setAttribute("JoinUser", email);
	    
	    
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
