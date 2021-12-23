<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Connection conn = null;

	try{
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/mssql");
		conn = ds.getConnection();
		
		out.print("연결성공");
		System.out.print("suc");
	}catch(Exception e){
		out.print("연결실패");
		System.out.print("fail");
		e.printStackTrace();
	}
	
%>
</body>
</html>