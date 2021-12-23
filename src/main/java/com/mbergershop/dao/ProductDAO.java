package com.mbergershop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mbergershop.dto.ProductDetailVO;
import com.mbergershop.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	private static ProductDAO instance = new ProductDAO();
	
	private ProductDAO() {
	}
	
	public static ProductDAO getInstance() {
		 return instance;
	}
	
	
	//grade Item
	public ArrayList<ProductVO> gradeProduct(String grade){
		
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql= "select * from product where grade = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, grade);
		      rs = pstmt.executeQuery();
		      
		      while (rs.next()) {
		          ProductVO product = new ProductVO();
		          
		          product.setCode(rs.getString("code"));
			        product.setRecommand(rs.getInt("recommand"));
			        product.setName(rs.getString("name"));
			        product.setGrade(rs.getString("grade"));
			        product.setKcal(rs.getInt("kcal"));
			        product.setPrice(rs.getInt("price"));
			        
		          productList.add(product);
		      }		      
		} catch (Exception e) {
		      e.printStackTrace();
		} finally {
		  DBManager.close(conn, pstmt, rs);
		}
		
		return productList;
	}
	
	//Best Item
	public ArrayList<ProductVO> listBestProduct() {
		
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql= "select * from product where recommand = 1";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		  conn = DBManager.getConnection();
	      pstmt = conn.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      while (rs.next()) {
	        ProductVO product = new ProductVO();

	        product.setCode(rs.getString("code"));
	        product.setRecommand(rs.getInt("recommand"));
	        product.setName(rs.getString("name"));
	        product.setGrade(rs.getString("grade"));
	        product.setKcal(rs.getInt("kcal"));
	        product.setPrice(rs.getInt("price"));
	        
	        productList.add(product);
	      }
		} catch (Exception e) {
		      e.printStackTrace();
		} finally {
		  DBManager.close(conn, pstmt);
		}
		
		return productList;	
	}
	
	public ArrayList<ProductDetailVO> getProduct(String code) {
		
		System.out.println("getProduct" + code);
		
		ArrayList<ProductDetailVO> productList = new ArrayList<ProductDetailVO>();
		String sql = "select * from ProductDetail where code = ? order by price desc ";
		ProductDetailVO product = null;
		
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      	pstmt.setString(1, code);
		      System.out.println(sql);
		      rs = pstmt.executeQuery();
		      while(rs.next()) {
		    	  product = new ProductDetailVO();
		    	  
		    	  	product.setCode(rs.getString("code"));
			        product.setName(rs.getString("name"));
			        product.setGrade(rs.getString("grade"));
			        product.setGrade2(rs.getString("grade2"));
			        product.setKcal(rs.getInt("kcal"));
			        product.setPrice(rs.getInt("price"));
			        
			        productList.add(product);
			        System.out.println(product.getName());
			        System.out.println(productList.size());
			        System.out.println(product.getGrade2());
			        
		      }
			} catch (Exception e) {
			      e.printStackTrace();
			} finally {
			  DBManager.close(conn, pstmt,rs);
			}
		
		return productList;
	}
	
	public ArrayList<ProductVO> listKindProduct(String kind){
		
		ArrayList<ProductVO> productList = new ArrayList<ProductVO>();
		String sql= "select * from product where kind = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			  conn = DBManager.getConnection();
		      pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, kind);
		      rs = pstmt.executeQuery();
		      
		      while (rs.next()) {
		          ProductVO product = new ProductVO();
		          
		          product.setCode(rs.getString("code"));
			        product.setRecommand(rs.getInt("recommand"));
			        product.setName(rs.getString("name"));
			        product.setGrade(rs.getString("grade"));
			        product.setKcal(rs.getInt("kcal"));
			        product.setPrice(rs.getInt("price"));
			        
		          productList.add(product);
		      }		      
		} catch (Exception e) {
		      e.printStackTrace();
		} finally {
		  DBManager.close(conn, pstmt, rs);
		}
		
		return productList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
