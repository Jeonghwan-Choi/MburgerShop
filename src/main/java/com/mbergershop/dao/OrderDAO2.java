package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.mbergershop.dto.OrderVO;


import util.DBManager;

public class OrderDAO2 {


	public OrderVO getOrderNum() {
		
		String sql = "select ordernum from orderlist order by ordernum desc ";
		OrderVO order = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);

		      rs = pstmt.executeQuery();
		      if(rs.next()) {
		    	  order = new OrderVO();
		    	  order.setOrdernum(rs.getString("ordernum"));
		    	  
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		
		return order;
	}
	


		
		

	
}

