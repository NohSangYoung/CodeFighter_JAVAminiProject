package model;

import java.sql.SQLException;

public class CharacterDAO extends DAO {

	public CharacterDAO() {
		super();
	}
	
	public int join(CharacterDTO dto) {
		//회원가입
		//2.데이터 받아서 서버 넘기기
		int row = 0;
		get_Conn();
		
		String sql = "INSERT INTO TB_USER VALUES(?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1,dto.getId());
			psmt.setString(2,dto.getPw());
			row = psmt.executeUpdate();
			psmt.close();
			
			if(row==0) {
				throw new Exception();
			}
			
			sql = "INSERT INTO TB_CHARACTER VALUES(?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getName());
			psmt.setString(3,dto.getJob());
			//hp,mp,at,de
			psmt.setInt(4, 100);
			psmt.setInt(5, 100);
			psmt.setInt(6, 10);
			psmt.setInt(7, 10);
			
			row = psmt.executeUpdate();
			psmt.close();
			
			if(row==0) {
				throw new Exception();
			}
			
			sql = "INSERT INTO TB_EQUIPMENT VALUES(?,0,0,0,0)";
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			row = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		return row;
	}

	public CharacterDTO login(CharacterDTO dto) {
		get_Conn();
		
		String sql = "SELECT TB_CHARACTER.NAME_, TB_CHARACTER.JOB_NM, TB_CHARACTER.HP, TB_CHARACTER.MP, TB_CHARACTER.AT, TB_CHARACTER.DE, TB_EQUIPMENT.HEAD_NO, TB_EQUIPMENT.BODY_NO, TB_EQUIPMENT.WP_NO, TB_EQUIPMENT.INVEN FROM TB_USER JOIN TB_CHARACTER ON TB_USER.ID_CD = TB_CHARACTER.ID_CD JOIN TB_EQUIPMENT ON TB_USER.ID_CD = TB_EQUIPMENT.ID_CD WHERE TB_USER.ID_CD = ? AND TB_USER.PW = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPw());
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setName(rs.getString(1));
				dto.setJob(rs.getString(2));
				dto.setHp(rs.getInt(3));
				dto.setMp(rs.getInt(4));
				dto.setAttack(rs.getInt(5));
				dto.setDefense(rs.getInt(6));
				dto.setWpHead(rs.getInt(7));
				dto.setWpBody(rs.getInt(8));
				dto.setWpArm(rs.getInt(9));
				dto.setInven(rs.getString(10));
			}else {
				dto = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			get_Close();
		}
		
		return dto;
	}

	public CharacterDTO save(CharacterDTO dto) {
		get_Conn();
		
		String sql = "UPDATE TB_CHARACTER SET HP = ?, MP = ?, AT = ?, DE = ? WHERE ID_CD = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getHp());
			psmt.setInt(2, dto.getMp());
			psmt.setInt(3, dto.getAttack());
			psmt.setInt(4, dto.getDefense());
			psmt.setString(5, dto.getId());
			int row = psmt.executeUpdate();
			if(row==0) {
				throw new Exception("Save 함수 - Update 1째줄 안됨");
			}
			psmt.close();

			sql = "UPDATE TB_EQUIPMENT SET HEAD_NO = ?, BODY_NO = ?, WP_NO = ?, INVEN = ? WHERE ID_CD = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getWpHead());
			psmt.setInt(2, dto.getWpBody());
			psmt.setInt(3, dto.getWpArm());
			psmt.setString(4, dto.getInven());
			psmt.setString(5, dto.getId());
			row = psmt.executeUpdate();
			if(row==0) {
				throw new Exception("Save 함수 - Update 2째줄 안됨");
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
		
		return dto;
	}
	
}
