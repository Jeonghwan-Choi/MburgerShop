package com.mbergershop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mbergershop.controller.action.Action;

/**
 * Servlet implementation class MbergerServlet
 */
@WebServlet("/MbergerServlet")
public class MbergerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String command = request.getParameter("command");
	    System.out.println("Mberger에서 요청을 받음을 확인 : " + command);
	    
	    ActionFactory af = ActionFactory.getInstance();
	    Action action = af.getAction(command);
	    
	    if(action != null) {
	    	action.execute(request, response);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
