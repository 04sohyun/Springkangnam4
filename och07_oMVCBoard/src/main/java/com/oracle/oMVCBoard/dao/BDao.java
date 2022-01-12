package com.oracle.oMVCBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.oracle.oMVCBoard.dto.BDto;

public class BDao {
	DataSource dataSource;
	public BDao() {
		
		try {//JDNI
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception e) {
			System.out.println("생성자 dataSource-->"+e.getMessage());
			//e.printStackTrace();
		}
		
	}
	
	//게시판 목록
	public ArrayList<BDto> list() {
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			//mvc_board list조회
			String sql = "select * from mvc_board order by bGroup desc, bStep asc";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int bId = resultSet.getInt(1);
				String bName = resultSet.getString(2);
				String bTitle = resultSet.getString(3);
				String bContent = resultSet.getString(4);
				Timestamp bDate = resultSet.getTimestamp(5);
				int bHit = resultSet.getInt(6);
				int bGroup = resultSet.getInt(7);
				int bStep = resultSet.getInt(8);
				int bIndent = resultSet.getInt(9);
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
				dtos.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	//게시글 보기
	public BDto contentView(String strID) {
		upHit(strID);
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			//mvc_board bId를 가지고 dto를 담아서 return
			String sql = "select * from mvc_board where bId=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, strID);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt(1);
				String bName = resultSet.getString(2);
				String bTitle = resultSet.getString(3);
				String bContent = resultSet.getString(4);
				Timestamp bDate = resultSet.getTimestamp(5);
				int bHit = resultSet.getInt(6);
				int bGroup = resultSet.getInt(7);
				int bStep = resultSet.getInt(8);
				int bIndent = resultSet.getInt(9);
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return dto;
	}

	//조회수 올리기
	private void upHit(String strID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "update mvc_board set BHIT=BHIT+1 where bId=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(strID));
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());		
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		
	}

	//게시글 수정하기
	public void modify(String bId, String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "update mvc_board set bName=?, bTitle=?, bContent=? where bId=? ";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bId));
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//게시글 작성하기
	public void write(String bName, String bTitle, String bContent) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into mvc_board values(mvc_board_seq.nextval, ?, ?, ?, sysdate, 0, mvc_board_seq.currval, 0, 0)";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//답변 화면 열기
	public BDto reply_view(String str) {
		BDto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select * from mvc_board where bId=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, str);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int bId = resultSet.getInt(1);
				String bName = resultSet.getString(2);
				String bTitle = resultSet.getString(3);
				String bContent = resultSet.getString(4);
				Timestamp bDate = resultSet.getTimestamp(5);
				int bHit = resultSet.getInt(6);
				int bGroup = resultSet.getInt(7);
				int bStep = resultSet.getInt(8);
				int bIndent = resultSet.getInt(9);
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
			}
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(resultSet!=null) resultSet.close();
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}	
		return dto;
		
	
	}
	//답변 등록하기
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep,
			String bIndent) {
		//원글에대한 답변은 항상 bStep=0, bIndent=0을 얻어옴
		//답변에 대한 답변은 bStep=1, bIndent=1을 얻어옴
		//bGroup이 같고 bStep이 현재 값보다 크면 bStep+1
		replyShape(bGroup, bStep);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "insert into mvc_board values(mvc_board_seq.nextval, ?, ?, ?, sysdate, 0, ?, ?, ?)";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bName);
			preparedStatement.setString(2, bTitle);
			preparedStatement.setString(3, bContent);
			preparedStatement.setInt(4, Integer.parseInt(bGroup));
			preparedStatement.setInt(5, Integer.parseInt(bStep)+1);
			preparedStatement.setInt(6, Integer.parseInt(bIndent)+1);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	//이전 답변들 step수 하나씩 늘리기 
	private void replyShape(String bGroup, String bStep) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "update mvc_board set bStep = bStep+1 where bGroup=? and bStep>?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(bGroup));
			preparedStatement.setInt(2, Integer.parseInt(bStep));
			
			preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}   
	}

	//게시글 삭제
	public void delete(String bId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "delete from mvc_board where bId = ?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(bId));
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("list dataSource SQLException-->"+e.getMessage());
		}finally {
			try {
				if(preparedStatement!=null) preparedStatement.close();
				if(connection!=null) connection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	
}
