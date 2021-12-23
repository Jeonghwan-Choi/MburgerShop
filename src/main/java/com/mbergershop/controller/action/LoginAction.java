package com.mbergershop.controller.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.MemberDAO;
import com.mbergershop.dto.MemberVO;

public class LoginAction implements Action {

	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    String url="jsp/login.jsp";
	    HttpSession session=request.getSession();
	    
	    String[] toggle = request.getParameterValues("toggle");
		System.out.println(Arrays.toString(toggle));
		
		
		String tonum = Arrays.toString(toggle);
		System.out.println(tonum);
	  
	    String id=request.getParameter("id");
	    String pwd=request.getParameter("pwd");
	    
	    MemberDAO memberDAO=MemberDAO.getInstance();
	      
	    
	    System.out.println(id);
	    System.out.println(pwd);
	    

	    
		    	if(tonum.equals("[1]")) {
		    		System.out.println("토글 1 작동");
		    		MemberVO memberVO=memberDAO.getMember(id);
		    		System.out.println(memberVO.getPassword());
				      if(memberVO.getPassword().equals(pwd)){    
					    	  System.out.println("아이디 비밀번호 일치 시 작동 ");
					        session.removeAttribute("id");
					        
					        System.out.println("일반회원 로그인 : " );
							System.out.println("이메일 : " + memberVO.getEmail());
							System.out.println("핸드폰 : " + memberVO.getPhone());
							System.out.println("이름 : " + memberVO.getName());
							System.out.println("주소 : " + memberVO.getAddress());
							
							String[] AddSplit = memberVO.getAddress().split("/");
							
							session.setAttribute("loginUser", memberVO.getEmail());
							session.setAttribute("loginPhone", memberVO.getPhone());
							session.setAttribute("loginName", memberVO.getName());
							session.setAttribute("member_post", AddSplit[0]);
							session.setAttribute("member_addr", AddSplit[1]);
							session.setAttribute("member_addr_detail", AddSplit[2]);
							
					        
					        System.out.println("일반회원 로그인 성공전");
					        url="MbergerServlet?command=index";
			    		}else {
			    			url="jsp/login_fail.jsp";
			    		}  
			    	}else if(tonum.equals("[2]")) {
			    		System.out.println("토글 2 작동");
			    		MemberVO memberVO=memberDAO.getAdminMember(id);

				    		if(memberVO.getPassword().equals(pwd)){    
						        session.removeAttribute("id");
						        session.setAttribute("loginUser", id);
						        System.out.println("관리자 로그인 성공전");
						        url="MbergerServlet?command=manager";
					    	}  
					    
			    	}else {
		    			url="jsp/login_fail.jsp";
		    		}  
	    request.getRequestDispatcher(url).forward(request, response);  
	  }

}

