package kh.my.board.board.model.service;

import static kh.my.board.comm.JDBCTemplate.*;


import java.sql.Connection;
import java.util.ArrayList;

import kh.my.board.board.model.dao.BoardDao;
import kh.my.board.board.model.vo.Board;
import kh.my.board.comm.JDBCTemplate;

public class BoardService {

	public BoardService() {}
	
	public Board getBoard(int bno) {
		Board vo = null;
		Connection conn = JDBCTemplate.getConnection();
		
		vo = new BoardDao().getBoard(conn, bno);
		JDBCTemplate.close(conn);
		return vo;
	}
	
	
	
	//총 글수
	public int getBoardCount() {
		//0이나 -1이나 똑같음~ 읽은게 없다는뜻은 똑같다
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		
		result = new BoardDao().getBoardCount(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<Board> selectBoard(int start , int end) {
		ArrayList<Board> voList = null;
		Connection conn = getConnection();
		
		voList = new BoardDao().selectBoardList(conn, start, end);
		JDBCTemplate.close(conn);
		return voList;
	}
	public ArrayList<Board> selectBoardList() {
		ArrayList<Board> volist = null;
		Connection conn = JDBCTemplate.getConnection();
		
		volist = new BoardDao().selectBoardList(conn);
		
		JDBCTemplate.close(conn);
		return volist;
	}
	//create
	public int insertBoard(Board vo) {
		int result = -1;
		Connection conn = JDBCTemplate.getConnection();
		//여기에 넣는 경우 : DML 두개일 때
		
		//강사님은 이걸 더 선호
		
		result = new BoardDao().insertBoard(conn, vo);
			
		
		JDBCTemplate.close(conn);
		return result;	
	}
	
	
	//read
	//update
	public int updateBoard(Board vo, String writer) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().updateBoard(conn, vo, writer);
		JDBCTemplate.close(conn);
		return result;
	}
	//delete
	public int deleteBoard(int bno, String writer) {
		int result = -1;
		Connection conn = getConnection();
		
		result = new BoardDao().deleteBoard(conn, bno, writer);

		JDBCTemplate.close(conn);
		return result;
	}
}