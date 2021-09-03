package kh.my.board.board.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.my.board.board.model.vo.Board;
import kh.my.board.comm.JDBCTemplate;

public class BoardDao {
	public BoardDao() {}
	
	
	

	
	
	
	
	
	
	
	public Board getBoard(Connection conn, int bno) {
		Board vo = null;
		
//		private String title;
//		 private String content;
//		 private Date createDate;
//		 private String writer;
//		 private char deleteYn;
//		 private int bref;
		
		String query = "select bno,bref,bre_level,bre_step"
				+ " ,title, content, create_Date, writer, delete_Yn "
				+ " from board_r where bno = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new Board();
				vo.setBno(rs.getInt(1));
				vo.setBref(rs.getInt(2));
				vo.setBreLevel(rs.getInt(3));
				vo.setBreStep(rs.getInt(4));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_Date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_Yn"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return vo;
	}
	
	
	
	//총 글수
	public int getBoardCount(Connection conn) {
		int result = 0;
		String query = "select count(bno) from board_r";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			//-1
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	
	
	

	//read
	public ArrayList<Board> selectBoardList(Connection conn, int start , int end) {
		ArrayList<Board> voList = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from (select ROWNUM r, t.* from " + 
				" (select * from board_r b order by bref desc, bre_step asc) " + 
				" t) t2" + 
				" where r between ? and ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			voList = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_Date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_Yn"));
				vo.setBref(rs.getInt("bref"));
				vo.setBreLevel(rs.getInt("bre_level"));
				vo.setBreStep(rs.getInt("bre_step"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return voList;
	}
	
	
	
	//create
		public int insertBoard(Connection conn, Board vo) {
			int result = -1;
			
			
			String sqlUpdate = "UPDATE board_r set bre_step=bre_step+1 where bref=? and bre_step > ?";

			
			String sqlInsert = "INSERT INTO"
						+ " board_r"
						+ "(BNO, TITLE, CONTENT, WRITER, bref, bre_level, bre_step)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			String sqlSeqNextVal = "select SEQ_BOARD.nextval from dual";
			
			int bref = 0;
			int bre_level = 0;
			int bre_step = 1;
			int nextVal = 0;
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sqlSeqNextVal);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					nextVal = rs.getInt(1);
				}
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
				
				if(vo.getBno() != 0) {     //답....글쓰기
					bref = vo.getBref();
					bre_step = vo.getBreStep();
					ps = conn.prepareStatement(sqlUpdate);
					ps.setInt(1, bref);
					ps.setInt(2, bre_step);
					result = ps.executeUpdate();
					JDBCTemplate.close(ps);
					
					
					
					bre_level = vo.getBreLevel()+1;
					bre_step++;
				}
					
					ps = conn.prepareStatement(sqlInsert);
					if(vo.getBno() != 0) {
						ps.setInt(5, bref);				
				}else {
					ps.setInt(5, nextVal);
				}
				ps.setInt(6, bre_level);
				ps.setInt(7, bre_step);
				ps.setInt(1, nextVal);
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getWriter());
				result = ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rs);
				JDBCTemplate.close(ps);
			}
			return result;
		}
	
	
	
	
	
	
	public ArrayList<Board> selectBoardList(Connection conn) {
		ArrayList<Board> voList = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select *"
				+ " from (select ROWNUM r, t.* from (select b.*  from board_r b where b.delete_yn <> 'Y' order by create_date desc, bno desc) t) t2"
				+ " where r between ? and ?";
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			voList = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_Date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_Yn"));
				vo.setBref(rs.getInt("bref"));
				vo.setBreLevel(rs.getInt("bre_level"));
				vo.setBreStep(rs.getInt("bre_step"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return voList;
	}
	
	
	
	//update
		public int updateBoard(Connection conn, Board vo, String writer) {
			int result = -1;
			String query = "update board_r set title = ?, content = ? where bno like ? and writer like ? and delete_yn <> 'Y'";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getContent());
				ps.setInt(3, vo.getBno());
				ps.setString(4, writer);
				result = ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			return result;
		}
		//delete
		public int deleteBoard(Connection conn, int bno, String writer) {
			int result = -1;
			//String query = "delete from board where bno like ? and member_id like ?";
			String query = "update board set delete_Yn = 'Y' where bno like ? and writer like ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1,  bno);
				ps.setString(2,  writer);
				result = ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			return result;
		}
}