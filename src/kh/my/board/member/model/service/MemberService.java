package kh.my.board.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kh.my.board.comm.JDBCTemplate;
import kh.my.board.member.model.dao.MemberDao;
import kh.my.board.member.model.vo.Member;
import static kh.my.board.comm.JDBCTemplate.*;

public class MemberService {

	public MemberService() {}
	//login
	public int login(String id, String pwd) {
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		
		result = new MemberDao().login(conn, id, pwd);
		JDBCTemplate.close(conn);
		return result;
	}
	//create
	public int insertMember(Member vo) {
		int result = -1, result2 = -1;
		Connection conn = JDBCTemplate.getConnection();
		//여기에 넣는 경우 : DML 두개일 때
		JDBCTemplate.setAutoCommit(conn, false);
		//강사님은 이걸 더 선호
			
		result = new MemberDao().checkDuplicatedMember(conn, vo);
		//기존 회원 있으면 2, 없으면 0, 오류발생하면 -1
		if(result == 0) {
			//입력 받은 값으로 회원가입
			result = new MemberDao().insertMember(conn, vo);
			
			//회원가입시 event로 point 500
			//가입한 회원 id에 point 수정
			result2 = new MemberDao().updatePointMember(conn, vo.getId(), 500);
		}
		if(result > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.Rollback(conn);
		}
		JDBCTemplate.Rollback(conn);
		JDBCTemplate.close(conn);  //DML 한개일 때는 여기서 커밋
		return result;
	}
	//read
	public ArrayList<Member> selectMember() {
		ArrayList<Member> voList = null;
		Connection conn = getConnection();
		
		voList = new MemberDao().selectMember(conn);
		JDBCTemplate.close(conn);
		return voList;
	}
	//update
	public int updateMember(Member vo, String checkPwd) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().updateMember(conn, vo, checkPwd);
		JDBCTemplate.close(conn);
		return result;
	}
	//delete
	public int deleteMember(String id, String pwd) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().deleteMember(conn, id, pwd);
		JDBCTemplate.close(conn);
		return result;
	}
}