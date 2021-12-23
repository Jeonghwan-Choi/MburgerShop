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

public class JoinAccess implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url="MbergerServlet?command=index";
		
		HttpSession session= request.getSession();

		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String admin = request.getParameter("admin");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		
		System.out.println(email);
		System.out.println(name);
		System.out.println(gender);
		System.out.println(admin);
		System.out.println(phone);
		System.out.println(password);
		System.out.println(address);
	    
//	    MemberDAO memberDAO = MemberDAO.getInstance();
//	    memberDAO.AccessMember(email);
	    MemberDAO memberDAO = MemberDAO.getInstance();
	    memberDAO.insertMember(email,name,gender,admin,phone,password,address);
	    
	    session.setAttribute("email",request.getParameter("email"));
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
