package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mbergershop.dto.CartVO;


import util.DBManager;

public class CartDAO2 {
	
	private CartDAO2() {
		}
		
	private static CartDAO2 instance = new CartDAO2();
		
	public static CartDAO2 getInstance() {
		return instance;
	}
	

	
	  public int insertCart(CartVO cartVO) {
		  int result = 0;
		  
		  String sql = "insert into cart(state, email, name, grade,";
		  sql += " grade2, kcal, price ,side, total) values(?, ?, ?, ?, ?, ?, ?, ? ,?)";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1, "1");      
		      pstmt.setString(2, cartVO.getEmail());
		      pstmt.setString(3, cartVO.getName());
		      pstmt.setString(4, cartVO.getGrade());
		      pstmt.setString(5, cartVO.getGrade2());
		      pstmt.setInt(6, cartVO.getKcal());
		      pstmt.setInt(7, cartVO.getPrice());
		      pstmt.setString(8, cartVO.getSide());
		      pstmt.setInt(9, cartVO.getTotal());
		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	  


}
