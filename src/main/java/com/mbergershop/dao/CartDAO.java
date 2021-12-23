package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.OrderVO;

import util.DBManager;

public class CartDAO {
	
	private CartDAO() {
	}
	
	private static CartDAO instance = new CartDAO();
		
	public static CartDAO getInstance() {
		return instance;
	}
		

	
	public ArrayList<CartVO> CartList(String userid) {
		
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		String sql= "select * from cart where email = ? and state = 1";
		

		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total = 0;
		int total1 = 0;
		
		
		try {
		  conn = DBManager.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      pstmt.setString(1,userid);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	    	  CartVO cart = new CartVO();
	        
	        
	    	  cart.setId(rs.getInt("id"));
	    	  cart.setState(rs.getString("state"));
	    	  cart.setEmail(rs.getString("email"));
	    	  cart.setName(rs.getString("name"));
	    	  cart.setGrade(rs.getString("grade"));
	    	  cart.setGrade2(rs.getString("grade2"));    	  
	    	  cart.setKcal(rs.getInt("kcal"));
	    	  cart.setPrice(rs.getInt("price"));
	    	  cart.setSide(rs.getString("side"));
	    	 
	    	  
	    	  String side = rs.getString("side");
	    	  int price = rs.getInt("price");
	    	  total = price;
	    	  
  			
	  			String[] mobNum = side.split(",");
	  			

	  			
	  			
	  			String side1 = mobNum[0];
	  			String side2 = mobNum[1];
	  			String side3 = mobNum[2];
	    	  
	  		  cart.setSide1(side1);
	  		  cart.setSide2(side2);
	  		  cart.setSide3(side3);
	    	  
	    	  
	    	  total1 = total1+total;
	    	 
	    	  cart.setTotal(total1);
	        
	        cartList.add(cart);
	      }
		} catch (Exception e) {
		      e.printStackTrace();
		} finally {
		  DBManager.close(conn, pstmt, rs);
		}
		int total2 = total1;
		System.out.println(total1);
		return cartList;	
	}
	
	
	
	public CartVO getCartTotal(String userid) {
		System.out.println("getCartTotal 실행");
		String sql = "select sum(price)'price' from cart where email = ? and state = 1 ";
		CartVO cart = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, userid);
		      rs = pstmt.executeQuery();
		      if(rs.next()) {
		    	  cart = new CartVO();
		    	  cart.setTotal(rs.getInt("price"));
		    	  System.out.println("Sum Price :" + rs.getInt("price"));
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		
		return cart;
	}
	
	public OrderVO getCartCount(String userid) {
		
		String sql = "select count(price)'count' from cart where email = ? and state = 1 ";
		OrderVO order = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, userid);
		      rs = pstmt.executeQuery();
		      if(rs.next()) {
		    	  order = new OrderVO();
		    	  order.setCount(rs.getInt("count"));
		    	  
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		
		return order;
	}
	
	  public int updateCart(CartVO cartVO) {
		  int result = 0;
		  
		  String sql = "update cart set state = ? where id = ?";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1, "2");      
		      pstmt.setInt(2, cartVO.getId());
		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	  
	  public int DeleteCart(String cartNum) {
		  int result = 0;
		  

		  
		  String sql = "delete from cart where id = ? ";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1,cartNum);

		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	


}
