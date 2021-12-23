package com.mbergershop.controller.action;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.CartDAO;
import com.mbergershop.dao.OrderDAO;
import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.OrderVO;



public class DeleteCart implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");

		String url="MbergerServlet?command=menu";
		
		HttpSession session= request.getSession();		
		String getcartnum = request.getParameter("CartNum");
		System.out.println("getOrderNum: " + getcartnum);
		
		CartVO cartVO = new CartVO();
		

		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.DeleteCart(getcartnum);
		System.out.println("카트항목 삭제");
	
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
