package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mbergershop.controller.action.MyAuthentication;
import com.mbergershop.dto.MemberVO;

import util.DBManager;

public class MemberDAO {
	  private MemberDAO() {
	  }
	
	  private static MemberDAO instance = new MemberDAO();
	
	  
	  public static MemberDAO getInstance() {
	    return instance;
	  }
	  
	  public int confirmID(String userid) {
		  
		  String sql= "select * from member where email = ?";
		  int result = -1;
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, userid);
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	  result = 1;
		      }else {
		    	  result = -1;
		      }
		      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		  
		  return result;
	  }
	  
	  public MemberVO NaverLogin(String email) {
		  
		  String sql= "select count(*)'count' from member where email = ? ";
		  MemberVO memberVO = null;
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, email);
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	  memberVO = new MemberVO();
		  
		          memberVO.setCount(rs.getString("count"));


		      }
		      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		  
		  return memberVO;
	  }
	  
	  public int insertMember(String email, String name, String gender, String admin, String phone, String password, String address) {

			
		  
		  
		  int result = 0;
		  
		  String sql = "insert into member(name, gender, admin, phone, email,";
		  sql += " password, access, address) values(?, ?, ?, ?, ?, ?, ?, ?)";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1, name);      
		      pstmt.setString(2, gender);
		      pstmt.setString(3, admin);
		      pstmt.setString(4, phone);
		      pstmt.setString(5, email);
		      pstmt.setString(6, password);
		      pstmt.setString(7, "2");
		      pstmt.setString(8, address);
		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	  
	  
	  public int AccessMember(String email) {

			
		  
		  
		  int result = 0;
		  
		  String sql = "update member set access='2' where email = ? ";
		    
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		   
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      
		      pstmt.setString(1,email);

		     
		      result = pstmt.executeUpdate();
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt);
			}
		  return result;
	  }
	  
	  public MemberVO getMember(String id) { 
		  String sql= "select * from member where email = ? and admin ='2'";
		  MemberVO memberVO = null;
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	  memberVO = new MemberVO();
		  
		          memberVO.setName(rs.getString("name"));
		          memberVO.setGender(rs.getString("gender"));
		          memberVO.setPhone(rs.getString("phone"));
		          memberVO.setEmail(rs.getString("email"));
		          memberVO.setPassword(rs.getString("password"));
		          memberVO.setAddress(rs.getString("address"));

		      }
		      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		  
		  return memberVO;
	  }
	  
	  public MemberVO getAdminMember(String id) { 
		  String sql= "select * from member where email = ? and admin= '1'";
		  MemberVO memberVO = null;
		  
		  Connection conn = null;
		  PreparedStatement pstmt = null;
		  ResultSet rs = null;
		  
		  try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, id);
		      rs = pstmt.executeQuery();
		      
		      if(rs.next()) {
		    	  memberVO = new MemberVO();
		  
		          memberVO.setName(rs.getString("name"));
		          memberVO.setGender(rs.getString("gender"));
		          memberVO.setPhone(rs.getString("phone"));
		          memberVO.setEmail(rs.getString("email"));
		          memberVO.setPassword(rs.getString("password"));
		          memberVO.setAddress(rs.getString("address"));

		      }
		      
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt, rs);
			}
		  
		  return memberVO;
	  }
	  
	  
	  public int checkId(String usrid) throws Exception{
		   Connection con = null;
		   PreparedStatement pstmt = null;
		   int re = 0;
		   try{
		          con = DBManager.getConnection();
		    String selectSQL="select * from member where email=?";
		    pstmt = con.prepareStatement(selectSQL);
		    pstmt.setString(1,usrid);
		    ResultSet rs = pstmt.executeQuery();
		    if(rs.next()){
		     re = 1;
		    }
		   }finally{
			   DBManager.close(con,pstmt);

		   }
		   return re;
		 }//end list()
	  
	  
	  
	  
	  
	  
	  
	  
}
