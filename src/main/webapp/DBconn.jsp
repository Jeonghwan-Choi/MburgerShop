<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "java.sql.DriverManager" %>
<%@ page import = "java.sql.ResultSet" %>
<%@ page import = "java.sql.Statement" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "java.sql.ResultSetMetaData" %>


<%



   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;

   String url = "jdbc:sqlserver://221.154.8.88:1433;DataBaseName=mber";
   String id = "pswel1"; 
   String pwd = "1234";
   String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

   String sql = "select code from product where recommand = 1";




%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>DB Connection Test</title>

</head>

<body>





   <%  System.out.print("Hello World!");
       System.out.println("Godo bye World!"); %>
   <table width ='800' border = '1' >
      <tr background = 'red'>

      </tr>
      <%
      


      
      
      try{
         Class.forName(driver);
         conn = DriverManager.getConnection(url,id,pwd);
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         
         
   

         
               
         
         
         
         while(rs.next()){
            out.println("<tr  >");
            out.println("<td>" + rs.getString("code")+"</td>");

            /*   out.println("<td>" + rs.getString("name")+"</td>");
            out.println("<td>" + rs.getString("grade")+"</td>");
            out.println("<td>" + rs.getString("grade2")+"</td>"); 
            out.println("<td >" + rs.getString("email")+"</td>");
           out.println("<td>" + rs.getString("id")+"</td>");
             out.println("<td>" + rs.getString("state")+"</td>");
              out.println("<td>" + rs.getString("price")+"</td>");*/

            
            

	
            out.println("</tr>");
         }
      }catch(Exception e){
         e.printStackTrace();
      }finally{
         try{
            if(rs != null)
               rs.close();
            if(stmt != null)
               stmt.close();
            if(conn != null)
               conn.close();
               
            
         }catch(Exception e){
   
            e.printStackTrace();
            
         }
      }
   
      %>
   </table>
   
</body>
</html>