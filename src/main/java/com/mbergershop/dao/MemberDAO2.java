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

public class MemberDAO2 {
	
	  
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
