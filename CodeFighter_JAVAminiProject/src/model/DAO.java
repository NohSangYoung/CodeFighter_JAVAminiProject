package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	protected Connection conn = null;
	protected PreparedStatement psmt = null;
	protected ResultSet rs = null;
	
	DAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void get_Conn() {
		String url = "jdbc:oracle:thin:@project-db-cgi.smhrd.com:1524:xe";
		String dbId = "mp_21K_bigdata24_p1_4";
		String dbPw = "smhrd4";
		try {
			conn = DriverManager.getConnection(url,dbId,dbPw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected void get_Close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
