package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.OrderDAO;
import com.mbergershop.dto.OrderVO;


public class ManagerDetailFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "jsp/managerdetail.jsp";
		
	    HttpSession session=request.getSession();
	    String id = (String) session.getAttribute("loginUser");
	    String ordernum = request.getParameter("ordernum");
	    System.out.println("ON1:"+ordernum);
	    
	    session.setAttribute("orderNum", ordernum);
	    
	    
	    String getordernum = (String) session.getAttribute("orderNum");

	    
	
		System.out.println(id);
		System.out.println("MD getOrderNum:" + getordernum);	
			
		OrderDAO orderDAO = OrderDAO.getInstance();
		

		ArrayList<OrderVO> orderVO = orderDAO.OrderList(getordernum);
		
		request.setAttribute("orderList", orderVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
