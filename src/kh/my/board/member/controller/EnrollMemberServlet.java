package kh.my.board.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.member.model.service.MemberService;
import kh.my.board.member.model.vo.Member;


/**
 * Servlet implementation class EnrollMemberServlet
 */
@WebServlet("/enroll")  // 회원가입
public class EnrollMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id = "kyy806";
		String pwd = "kyy0806";
		String name = "KimYeeun";
		char gender = 'F';
		String email = "kyy806@gmail.com";
		String phone = "010-0000-0000";
		String address = "대한민국";
		int age = 24;
		
		Member vo = new Member(id, pwd, name, gender, email, phone, address, age);
		int result = new MemberService().insertMember(vo);
		//오류 발생-1, 가입성공 1, 가입실패 0, 기존회원있으면 2, 가장큰수 0xFF
		if(result ==1) {
			out.println(id + "님 가입되었습니다. 환영합니다.");
		}else if(result == 2) {
			out.println("기존회원 id가 존재합니다.");
		}else {
			out.println("예기치 못한 오류 발생");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
