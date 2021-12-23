package com.mbergershop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.mbergershop.dao.OrderDAO;
import com.mbergershop.dto.OrderVO;



public class UpdateOrderState2 implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");

		String url="MbergerServlet?command=manager";
		
		HttpSession session= request.getSession();		
		String getordernum = request.getParameter("ordnum");
		System.out.println("getOrderNum: " + getordernum);
		
		OrderVO orderVO = new OrderVO();
		
		orderVO.setEmail(request.getParameter("email"));

		OrderDAO orderDAO = OrderDAO.getInstance();
		orderDAO.UpdateState3(getordernum);
		System.out.println("제조완료로 변경완료");
	
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
