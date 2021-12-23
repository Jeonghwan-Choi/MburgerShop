package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mbergershop.dto.CartVO;
import com.mbergershop.dto.OrderVO;
import com.mbergershop.dto.ProductVO;

import util.DBManager;

public class OrderDAO {

	
	private OrderDAO() {
	}
	
	private static OrderDAO instance = new OrderDAO();
		
	public static OrderDAO getInstance() {
		return instance;
	}
	
	
	  public int insertOrder(OrderVO orderVO) {
		  int result = 0;
		  
		  SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd/HH:mm:ss");

		  		
		  Date time = new Date();
		  		
		  String time1 = format1.format(time);

		  		
		  System.out.println(time1);
		  
		  String sql = "insert into orderlist(date, ordernum, state, email, name,";
		  sql += " grade2, price ,side, total,address) values(?, ? , ? , ?, ?, ?, ?, ?, ?, ?)";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1,time1);
		      pstmt.setString(2,orderVO.getOrdernum());
		      pstmt.setString(3, "1");      
		      pstmt.setString(4, orderVO.getEmail());
		      pstmt.setString(5, orderVO.getName());
		      pstmt.setString(6, orderVO.getGrade2());
		      pstmt.setInt(7, orderVO.getPrice());
		      pstmt.setString(8, orderVO.getSide());
		      pstmt.setInt(9, orderVO.getTotal());
		      pstmt.setString(10, orderVO.getAddress());
		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	  
	  
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
		
		
		public ArrayList<OrderVO> OrderList(String ordernum) {
			
			ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
			String sql= "select * from orderlist where ordernum = ?";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);

		      pstmt.setString(1,ordernum);
		      rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  OrderVO order = new OrderVO();
		    	  
		    	  order.setId(rs.getInt("id"));
		    	  order.setDate(rs.getString("date"));
		    	  order.setEmail(rs.getString("email"));
		    	  order.setOrdernum(rs.getString("ordernum"));
		    	  order.setAddress(rs.getString("address"));
		    	  order.setName(rs.getString("name"));
		    	  order.setGrade2(rs.getString("grade2"));
		    	  order.setPrice(rs.getInt("price"));
		    	  order.setSide(rs.getString("side"));
		    	  order.setTotal(rs.getInt("Total"));
		    	  
		    	  String side = rs.getString("side");
		    	  
		  			String[] mobNum = side.split(",");
		  			

		  			
		  			
		  			String side1 = mobNum[0];
		  			String side2 = mobNum[1];
		  			String side3 = mobNum[2];
		    	  
		  			order.setSide1(side1);
		  			order.setSide2(side2);
		  			order.setSide3(side3);
		        
		        orderList.add(order);
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
			
			return orderList;	
		}
		

		
		public ArrayList<OrderVO> OrderNumList(String id) {
		
			
			
			
			
			ArrayList<OrderVO> orderNumList = new ArrayList<OrderVO>();
			String sql= "select ordernum,state,email,address,sum(price)'price' from orderlist where email = ? group by ordernum,state,email,address";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1,id);
		      rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  OrderVO order = new OrderVO();

		    	  order.setOrdernum(rs.getString("ordernum"));
		    	  order.setState(rs.getString("state"));
		    	  order.setEmail(rs.getString("email"));
		    	  order.setAddress(rs.getString("address"));
		    	  order.setPrice(rs.getInt("price"));
   	  
		    	  
		    	  
		        
		    	  orderNumList.add(order);
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
			
			return orderNumList;	
		}
		
		public ArrayList<OrderVO> ManagerOrderList() {
			
			
			
			
			
			ArrayList<OrderVO> orderNumList = new ArrayList<OrderVO>();
			String sql= "select date,ordernum,state,email,address,sum(price)'price' from orderlist where len(ordernum)>5 and (convert(varchar(10),date,23) = convert(varchar(10), getdate(),23)) group by date,ordernum,state,email,address order by ordernum desc";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);

		      rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  OrderVO order = new OrderVO();
	
		    	 
		    	  order.setOrdernum(rs.getString("ordernum"));
		    	  order.setDate(rs.getString("date"));
		    	  order.setState(rs.getString("state"));
		    	  order.setEmail(rs.getString("email"));
		    	  order.setAddress(rs.getString("address"));
		    	  order.setPrice(rs.getInt("price"));
		    	  
		    	  
//시간계산=====================================================================================  	  
			    	
		    	  SimpleDateFormat format1 = new SimpleDateFormat ( "HH:mm:ss");
				  Date time = new Date(); 		
				  String time1 = format1.format(time);
				  
		    	    String str = rs.getString("date");
		    	    String[] datearray = str.split("/");
				  
		    	    

		  
		    	  
			  	    String dateStart = datearray[1];
				    String dateStop = time1;

				    // Custom date format
				    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");  

				    Date d1 = null;
				    Date d2 = null;
				    try {
				        d1 = format.parse(dateStart);
				        d2 = format.parse(dateStop);
				    } catch (ParseException e) {
				        e.printStackTrace();
				    }    

				    // Get msec from each, and subtract.
				    long diff = d2.getTime() - d1.getTime();
				    long diffSeconds = diff / 1000;         
				    long diffMinutes = diff / (60 * 1000);         
				    long diffHours = diff / (60 * 60 * 1000);                      
				    
				    String difM = Long.toString(diffMinutes);
				    
				    order.setElapsedtime(difM);
			    	  
	//===================================================================================== 		    	  
			 
		        
		    	  orderNumList.add(order);
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
			
			return orderNumList;	
		}
		
		
		public ArrayList<OrderVO> ManagerOrderCountList() {
			
			ArrayList<OrderVO> orderNumList = new ArrayList<OrderVO>();
			String sql= "select state, max(cnt) as cnt\n"
					+ "  from\n"
					+ "  (\n"
					+ "     select  state, count(*)as cnt\n"
					+ "  from \n"
					+ "  (select ordernum,state from orderlist where (convert(varchar(10),date,23) = convert(varchar(10), getdate(),23)) group by ordernum,state \n"
					+ "  ) a   \n"
					+ "  group by state\n"
					+ "   union\n"
					+ "   select '1',0 as cnt \n"
					+ "   union\n"
					+ "   select '2',0 as cnt \n"
					+ "   union\n"
					+ "   select '3',0 as cnt \n"
					+ "   union\n"
					+ "   select '4',0 as cnt \n"
					+ "  ) a \n"
					+ "  group by state\n"
					+ "  order by state asc";
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);

		      rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  OrderVO order = new OrderVO();
		    	  
		    	  order.setState(rs.getString("state"));
		    	  order.setId(rs.getInt("cnt"));

		    	  orderNumList.add(order);
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
			
			return orderNumList;	
			
			
			
		}
	
		

		  public int UpdateState2(String ordernum) {
			  int result = 0;
			  

			  
			  String sql = "update orderlist set state='2' where ordernum = ? ";
			    
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			   
			  try {
				  conn = DBManager.getConnection();
			      pstmt = conn.prepareStatement(sql);
			      
			      pstmt.setString(1,ordernum);

			     
			      result = pstmt.executeUpdate();
				} catch (Exception e) {
				      e.printStackTrace();
				} finally {
				  DBManager.close(conn, pstmt);
				}
			  return result;
		  }
		  

		  public int UpdateState3(String ordernum) {
			  int result = 0;
			  

			  
			  String sql = "update orderlist set state='3' where ordernum = ? ";
			    
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			   
			  try {
				  conn = DBManager.getConnection();
			      pstmt = conn.prepareStatement(sql);
			      
			      pstmt.setString(1,ordernum);

			     
			      result = pstmt.executeUpdate();
				} catch (Exception e) {
				      e.printStackTrace();
				} finally {
				  DBManager.close(conn, pstmt);
				}
			  return result;
		  }
		  
		  public int UpdateState4(String ordernum) {
			  int result = 0;
			  

			  
			  String sql = "update orderlist set state='4' where ordernum = ? ";
			    
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			   
			  try {
				  conn = DBManager.getConnection();
			      pstmt = conn.prepareStatement(sql);
			      
			      pstmt.setString(1,ordernum);

			     
			      result = pstmt.executeUpdate();
				} catch (Exception e) {
				      e.printStackTrace();
				} finally {
				  DBManager.close(conn, pstmt);
				}
			  return result;
		  }
		  
		  public int DeleteOrder(String ordernum) {
			  int result = 0;
			  

			  
			  String sql = "delete from orderlist where ordernum = ? ";
			    
			  Connection conn = null;
			  PreparedStatement pstmt = null;
			   
			  try {
				  conn = DBManager.getConnection();
			      pstmt = conn.prepareStatement(sql);
			      
			      pstmt.setString(1,ordernum);

			     
			      result = pstmt.executeUpdate();
				} catch (Exception e) {
				      e.printStackTrace();
				} finally {
				  DBManager.close(conn, pstmt);
				}
			  return result;
		  }
		  
	
		  
		  
		
		
}
