package com.mbergershop.controller.action;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.CartDAO;
import com.mbergershop.dao.MemberDAO;
import com.mbergershop.dao.OrderDAO;
import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.OrderVO;

public class OrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "jsp/ordersuc.jsp";
	    HttpSession session=request.getSession();

	    String id = (String) session.getAttribute("loginUser");
	
		System.out.println("Id:"+id);
		CartDAO cartDAO = CartDAO.getInstance();
		OrderVO cartC = cartDAO.getCartCount(id);
		
		CartVO cartVO = new CartVO();
		

		
		System.out.println(cartC.getTotal());
		int Count = cartC.getCount();
		System.out.println("Count:" + Count);

		OrderDAO orderDAO = OrderDAO.getInstance();
		OrderVO getO = orderDAO.getOrderNum();
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
		Date time = new Date();
		String time1 = format1.format(time); 
		
		String LastNum = getO.getOrdernum();
		
		
		
		String OrderNUm = null;
		
		if(LastNum == null) {
			OrderNUm = time1+(Integer.parseInt("0000"))+1;
		}else{
			String LastNum1 = LastNum.substring(8); 
			System.out.println(LastNum1);
			OrderNUm = request.getParameter("new_order_num");
		}
		
		System.out.println("Last OrderNum :" + OrderNUm);
		
		
		
		OrderVO orderVO = new OrderVO();
		
		
		for(int i=1; i<=Count; i++) {
		
			orderVO.setOrdernum(request.getParameter("new_order_num"));
			orderVO.setEmail(id);
			orderVO.setName(request.getParameter("name_"+i));
			orderVO.setGrade2(request.getParameter("grade2_"+i));  
			orderVO.setPrice(Integer.parseInt(request.getParameter("price_"+i)));
			orderVO.setSide(request.getParameter("side_"+i+"_1")+","+request.getParameter("side_"+i+"_2")+","+request.getParameter("side_"+i+"_3"));
			orderVO.setTotal(Integer.parseInt(request.getParameter("order_total")));
			orderVO.setAddress(request.getParameter("member_addr") + " " + request.getParameter("member_addr_1"));
		    orderDAO.insertOrder(orderVO);
		    
		    cartVO.setId(Integer.parseInt(request.getParameter("id_"+i)));
		    cartDAO.updateCart(cartVO);
			
		}
		

		 session.setAttribute("orderNum", OrderNUm);
		
		

		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
	    dispatcher.forward(request, response); 
	}

}
