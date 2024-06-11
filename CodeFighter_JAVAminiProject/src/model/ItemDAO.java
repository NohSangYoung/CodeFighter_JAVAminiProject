package model;

import java.sql.SQLException;

public class ItemDAO extends DAO {
	
	public ItemDTO getItem(int itemNo) {
		ItemDTO idto = null;
		get_Conn();
		
		String sql = "SELECT * FROM TB_ITEM WHERE WP_NO=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, itemNo);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				idto = new ItemDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idto;
	}

}
