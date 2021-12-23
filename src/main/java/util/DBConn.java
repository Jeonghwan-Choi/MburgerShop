package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConn {

	public static void main(String[] args) throws NamingException, SQLException {
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs    = null;
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/mssql");
		conn = ds.getConnection();

		 

		   stmt = conn.createStatement();
		   rs = stmt.executeQuery("SELECT *  FROM Member");
		   while(rs.next()) {
		    System.out.println(rs.getString("name"));
		   }

		 

		   rs.close();

		   stmt.close();

		   conn.close();

	}

}
