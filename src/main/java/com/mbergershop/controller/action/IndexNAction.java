package com.mbergershop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mbergershop.dao.ProductDAO;
import com.mbergershop.dto.ProductVO;

public class IndexNAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/indexN.jsp";
		
		ProductDAO productDAO = ProductDAO.getInstance();

		ArrayList<ProductVO> bestProductList = productDAO.listBestProduct();
		

		request.setAttribute("bestProductList", bestProductList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
	    dispatcher.forward(request, response);
	}


}
