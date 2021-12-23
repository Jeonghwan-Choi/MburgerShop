package com.mbergershop.controller.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.CartDAO;
import com.mbergershop.dao.OrderDAO2;
import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.OrderVO;
import com.mbergershop.dto.ProductVO;


public class MenuFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubx	
		


		
		ProductDAO productDAO = ProductDAO.getInstance();

		ArrayList<ProductVO> productVO = productDAO.listBestProduct();
		
		
		request.setAttribute("gradeProductList", productVO);
		
		
		String url = "jsp/menu.jsp";
	    HttpSession session=request.getSession();

	    String id = (String) session.getAttribute("loginUser");
	
		System.out.println(id);
		CartDAO cartDAO = CartDAO.getInstance();
		CartVO cartT = cartDAO.getCartTotal(id);
		
		System.out.println("카트갯수: "+ cartT.getTotal());
		int ct = cartT.getTotal();

		if(ct != 0) {
			
			ArrayList<CartVO> cartVO = cartDAO.CartList(id);
			int lastIndex = cartVO.size() - 1;
//			System.out.println(cartVO.get(lastIndex).getTotal());
			
			if(cartVO!=null){
				int Total = cartVO.get(lastIndex).getTotal();
				request.setAttribute("CartList", cartVO);
				session.setAttribute("Total", Total);
			}
		}
		
		
		OrderDAO2 oDAO = new OrderDAO2();
		
		OrderVO getO = oDAO.getOrderNum();
		
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
			OrderNUm = time1+(Integer.parseInt(LastNum1)+1);
		}
		session.setAttribute("newordernum", OrderNUm);
		
		System.out.println(session.getAttribute("newordernum"));

		
		

		RequestDispatcher dispatcher=request.getRequestDispatcher(url);
	    dispatcher.forward(request, response); 
	}

}
