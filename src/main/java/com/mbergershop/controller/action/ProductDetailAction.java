package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.ProductDetailVO;


public class ProductDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "jsp/order.jsp";
		
		System.out.println(request.getParameter("code"));
		
		String code = request.getParameter("code");
		ProductDAO productDAO = ProductDAO.getInstance();
		ArrayList<ProductDetailVO> productlVO = productDAO.getProduct(code);
		
		request.setAttribute("gradeProductList", productlVO);
		
		System.out.println(productlVO.size());
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	    
	    

	    
	    
	}

}
