<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.mbergershop.dao.MemberDAO2"%>
<%@ page import = "java.util.List" %>
<%
String id = request.getParameter("u_id");
MemberDAO2 dao = new MemberDAO2();
int re = dao.checkId(id);
System.out.println(re);
%>
<%=re%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>
<body>

</body>
</html>