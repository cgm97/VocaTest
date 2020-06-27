package com.wp.toeic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ToeicDAO {
	private String jdbcDriver;
	private String dbUrl;
	private String dbUserId;
	private String dbPasswd;
	
	private Connection conn = null;
	private Statement stmt = null;

	public ToeicDAO(String jdbcDriver, String dbUrl, String dbUserId, String dbPasswd) {
		this.jdbcDriver = jdbcDriver;
		this.dbUrl = dbUrl;
		this.dbUserId = dbUserId;
		this.dbPasswd = dbPasswd;
	}

	private void connectDB() throws ClassNotFoundException,SQLException{
		Class.forName(jdbcDriver);
		conn = DriverManager.getConnection(dbUrl,dbUserId,dbPasswd);
		if(conn != null)
		{
			stmt = conn.createStatement();
		}
	}
	
	private void disconnectDB() throws SQLException {
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}	
		if (conn != null) {
			conn.close();
			conn = null;
		}	
	}
		//�α���ó�� �� ����� �̸� ã�Ƴ���
		public String login(String id , String pw) throws ClassNotFoundException, SQLException {
			connectDB();
			PreparedStatement stmt = null;
			
			String name=null;
			
			String sql = "select * from studentinfo where id=? and pw=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pw);
			ResultSet rs = stmt.executeQuery();	
			
			if(rs.next()) //���̵� �����ϸ� ������� �̸� ����
			{
				name = rs.getString("name");
			}
			rs.close();
			disconnectDB();
			return name;
		}
		
		//ȸ������ ó��
		public int insertuser(ToeicDTO dto) throws ClassNotFoundException, SQLException {
			int check=0;
			connectDB();
			PreparedStatement stmt = null;
			
			String sql = "insert into studentinfo(id,pw,name) values(?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dto.getId());
			stmt.setString(2, dto.getPw());
			stmt.setString(3, dto.getName());		
			stmt.executeUpdate();
			
			disconnectDB();
			return check;
		}
		
		//���� �ҷ�����
		public ToeicDTO research(int i) throws ClassNotFoundException, SQLException {
			connectDB();
			ToeicDTO dto = new ToeicDTO();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			String sql = "select * from questinfo where num=?";
			stmt = conn.prepareStatement(sql);	
			stmt.setInt(1, i);
			rs = stmt.executeQuery();
			if (rs.next()) {					
				dto.setNum(rs.getString("num"));
				dto.setMatter(rs.getNString("matter"));		
			}
			return dto;
		}
				
}
