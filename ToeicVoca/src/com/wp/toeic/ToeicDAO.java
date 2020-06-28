package com.wp.toeic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		//로그인처리 및 사용자 이름 찾아내기
		public String login(String id , String pw) throws ClassNotFoundException, SQLException {
			connectDB();
			PreparedStatement pstmt = null;
			
			String name=null;
			
			String sql = "select * from studentinfo where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();	
			
			if(rs.next()) //아이디 존재하면 사용자의 이름 저장
			{
				name = rs.getString("name");
			}
			rs.close();
			pstmt.close();
			disconnectDB();
			return name;
		}
		
		//회원가입 처리
		public int insertuser(ToeicDTO dto) throws ClassNotFoundException, SQLException {
			int check=0;
			connectDB();
			PreparedStatement pstmt = null;
			
			String sql = "insert into studentinfo(id,pw,name) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());		
			pstmt.executeUpdate();
			
			pstmt.close();
			disconnectDB();
			return check;
		}
		
		//문제 불러오기
		public ToeicDTO research(int i) throws ClassNotFoundException, SQLException {
			connectDB();
			ToeicDTO dto = new ToeicDTO();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from questinfo where num=?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			if (rs.next()) {					
				dto.setNum(rs.getString("num"));
				dto.setMatter(rs.getNString("matter"));		
			}
			
			rs.close();
			pstmt.close();
			disconnectDB();
			return dto;
		}
		
		//점수 계산
		public int totalScore(List<Integer> resultScore) {
			int score=0;
			for(Integer correct_matter : resultScore) {
				score += correct_matter;
			}
			return score;
		}
		
		//점수 등록
		public int inserscore(ToeicDTO dto) throws ClassNotFoundException, SQLException {
			int check=0;
			connectDB();
			PreparedStatement pstmt = null;
			
			String sql = "insert into resultinfo(id,name,result) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getScore());		
			pstmt.executeUpdate();
			
			pstmt.close();
			disconnectDB();
			return check;
		}
		
		//결과 리스트
		public List<ToeicDTO> getSaveResult(String id) throws ClassNotFoundException, SQLException {
			ArrayList<ToeicDTO> savelist = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			connectDB();
			
			String sql = "select * from resultinfo where id=?";
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			savelist = new ArrayList<ToeicDTO>();
			while(rs.next()) {
				ToeicDTO dto = new ToeicDTO();
				dto.setName(rs.getString("name"));
				dto.setScore(rs.getInt("result"));
				savelist.add(dto);
			}
			return savelist;
		}
				
}
