package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.mbergershop.dao.CartDAO2;
import com.mbergershop.dto.CartVO;



public class InsertCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");


		String[] toggle = request.getParameterValues("toggle");
		System.out.println(Arrays.toString(toggle));
		
		
		String tonum = Arrays.toString(toggle);
		System.out.println(tonum);
		

		
		
		String url="MbergerServlet?command=menu";
		
		HttpSession session= request.getSession();
		
		CartVO cartVO = new CartVO();
		
		cartVO.setEmail(request.getParameter("email"));
		cartVO.setName(request.getParameter("order_name"));	
		cartVO.setGrade(request.getParameter("order_grade"));  

		
		
		if(tonum.equals("[1]")) {
			cartVO.setGrade2(request.getParameter("order_grade2_1")); 
			cartVO.setPrice(Integer.parseInt(request.getParameter("order_price1")));
			cartVO.setTotal(Integer.parseInt(request.getParameter("order_price1")));
			cartVO.setSide(request.getParameter("side_1_1")+","+request.getParameter("side_1_2")+","+request.getParameter("side_1_3"));
			
		}else if(tonum.equals("[2]")) {
			cartVO.setGrade2(request.getParameter("order_grade2_2")); 
			cartVO.setPrice(Integer.parseInt(request.getParameter("order_price2")));
			cartVO.setTotal(Integer.parseInt(request.getParameter("order_price2")));
			cartVO.setSide(request.getParameter("side_2_1")+","+request.getParameter("side_2_2")+","+request.getParameter("side_2_3"));
		}else if(tonum.equals("[3]")) {
			cartVO.setGrade2(request.getParameter("order_grade2_3")); 
			cartVO.setPrice(Integer.parseInt(request.getParameter("order_price3")));
			cartVO.setTotal(Integer.parseInt(request.getParameter("order_price3")));
			cartVO.setSide(request.getParameter("side_3_1")+","+" "+","+" ");
		}
		


		
		


		
	    
	    CartDAO2 cartDAO = CartDAO2.getInstance();
	    cartDAO.insertCart(cartVO);
	    
	    session.setAttribute("email",request.getParameter("email"));
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}

}
