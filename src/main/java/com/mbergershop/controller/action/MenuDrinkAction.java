package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mbergershop.dao.CartDAO;
import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.ProductVO;

public class MenuDrinkAction implements Action {

	  @Override
	  public void execute(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		  
		  	
		  
		  	String url = "jsp/menu.jsp";
			
			String grade = "drink";
			
		    HttpSession session=request.getSession();

		    String id = (String) session.getAttribute("loginUser");
		
			System.out.println(id);
		
			System.out.println(grade);
			ProductDAO productDAO = ProductDAO.getInstance();
			ArrayList<ProductVO> productVO = productDAO.gradeProduct(grade);
			
			CartDAO cartDAO = CartDAO.getInstance();
			ArrayList<CartVO> cartVO = cartDAO.CartList(id);
			
			
			
			request.setAttribute("CartList", cartVO);
			
			request.setAttribute("gradeProductList", productVO);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		
	  }
}

