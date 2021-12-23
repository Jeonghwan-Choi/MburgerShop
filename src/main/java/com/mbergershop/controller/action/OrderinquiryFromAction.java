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


public class OrderinquiryFromAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		
		
		String url = "jsp/orderinquiry.jsp";
		
	    HttpSession session=request.getSession();
	    String id = (String) session.getAttribute("loginUser");
	    String ordernum = request.getParameter("detailNum");
	    System.out.println("ON1:"+ordernum);
	    
	    session.setAttribute("orderNum", ordernum);
	
		System.out.println(id);
		System.out.println("ON2:"+(String) session.getAttribute("orderNum"));
			
		OrderDAO orderDAO = OrderDAO.getInstance();
		ArrayList<OrderVO> orderNumVO = orderDAO.OrderNumList(id);	
		

		
		request.setAttribute("orderNumList", orderNumVO);
		


//		ArrayList<OrderVO> orderVO = orderDAO.OrderList(ordernum);
//		
//
//		
//		request.setAttribute("orderList", orderVO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
