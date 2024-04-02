package com.miniproject.repositoryimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.dto.MemberDTO;
import com.miniproject.env.EnvProperties;
import com.miniproject.repository.MemberRepository;

public class MemberRepositoryImpl implements MemberRepository {
	Connection conn = null;
	PreparedStatement pstmt = null;
	CallableStatement cstmt = null;
	ResultSet rs = null;
	
	List<MemberDTO> memberList = new ArrayList<>();

	public MemberRepositoryImpl() {
		try {
			Class.forName(EnvProperties.getDriverClass());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void dbConn() {
		try {
			conn = DriverManager.getConnection(EnvProperties.getDataBaseConn(), EnvProperties.getName(),
					EnvProperties.getPwd());
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (cstmt != null)
				cstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void selectAllMember() {
		dbConn();
		
		try {
			pstmt = conn.prepareStatement(EnvProperties.getSelectAllMember());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberList.add(new MemberDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6)));
			}
			System.out.println(memberList.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn();
		}
	}

	@Override
	public void insertMember(MemberDTO memberDTO) {
		dbConn();
		
		try {
			pstmt.setString(1, memberDTO.getUid());
			pstmt.setString(2, memberDTO.getUid());
			pstmt.setString(3, memberDTO.getUid());
			pstmt.setString(4, memberDTO.getUid());
			pstmt.setString(5, memberDTO.getUid());
			
			pstmt.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbConn();
		}
	}
}
