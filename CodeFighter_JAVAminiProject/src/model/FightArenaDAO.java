package model;

import java.sql.SQLException;

public class FightArenaDAO extends DAO{
	public FightArenaDAO() {
		super();
	}

	public int join(CharacterDTO dto) {
		get_Conn();
		int result = 0;
		String sql = "INSERT INTO TB_BATTLE VALUES(?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setInt(2, 0);
			psmt.setInt(3,0);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		return result;
	}

	public int isJoin() {
		get_Conn();
		int result = 0;
		String sql = "SELECT COUNT(*) FROM TB_BATTLE";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		
		return result;
	}

	public CharacterDTO getEnemy(CharacterDTO dto) {
		get_Conn();
		CharacterDTO edto = null;
		String sql = "select * from TB_BATTLE where id <> ? ";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			
			rs = psmt.executeQuery();
			rs.next();
			String enemyID = rs.getString(1);
			psmt.close();
			
			sql = "SELECT * FROM TB_CHARACTER where ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, enemyID);
			
			rs = psmt.executeQuery();
			rs.next();
			
			edto = new CharacterDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7));
			
		} catch (SQLException e) {
			// TODO Autof-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		
		
		return edto;
	}

	public void update(FightArenaDTO fdto) {
		get_Conn();
		
		String sql = "UPDATE TB_BATTLE SET ENTER=?, COUNT_=? WHERE ID_CD=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, fdto.getEnter());
			psmt.setInt(2, fdto.getCount());
			psmt.setString(3, fdto.getId());
			
			int row = psmt.executeUpdate();
			
			if(row == 0) {
				throw new Exception("FightArena Update 에러");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		
	}

	public boolean isReady(FightArenaDTO fdto) {
		boolean result = false;
		
		get_Conn();
		
		String sql = "SELECT COUNT(*) from TB_BATTLE WHERE COUNT_=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, fdto.getCount());
			
			rs = psmt.executeQuery();
			int count = rs.getInt(1);
			if(count ==2) {
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	public FightArenaDTO getEnemy(FightArenaDTO fenemy) {
		get_Conn();
		
		String sql = "SELECT * FROM TB_BATTLE WHERE ID=?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fenemy.getId());
			
			rs = psmt.executeQuery();
			rs.next();
			fenemy.setEnter(rs.getInt(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return fenemy;
	}
	
	
}
