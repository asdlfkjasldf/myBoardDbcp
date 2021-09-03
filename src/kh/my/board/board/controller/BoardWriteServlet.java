package kh.my.board.board.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.board.model.service.BoardService;
import kh.my.board.board.model.vo.Board;

/**
 * Servlet implementation class BoardReWriteServlet
 */
@WebServlet("/boardrewrite")
public class BoardReWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReWriteServlet() {
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
		
		
		//답글이므로 어느 글에 답글을 달것인가 정보가 전달되어 올것임
		Board oVo = null;
		
		String viewBno = request.getParameter("bno");
		if(viewBno == null) {
			oVo = new Board();
		}else {
			int bno = Integer.parseInt(viewBno); 
			//bref, bre_level, bre_step
		    oVo = new BoardService().getBoard(bno);
		    System.out.println("ovo : "+ oVo.toString());
		}
//		if(oVo == null) {
//			oVo = new Board();
//		}
		
		
		//화면에 전달되어 옴
		//http://localhost:8090/myBoard/boardwrite?t=title&c=content
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = (String)request.getSession().getAttribute("memberLoginInfo");
		if(writer == null) {
			writer = "user01";  //TODO : 임시코드 user 실행
		}
		out.println("입력된 title:" + title);
		out.println("<br>입력된 content" + content);
		
		Board vo = new Board(oVo.getBno(), title, content, writer, oVo.getBref(), oVo.getBreLevel(), oVo.getBreStep());
		System.out.println("입력될 글:"+ vo.toString());
		int result = new BoardService().insertBoard(vo);
		if(result ==0) {
			out.println("<br>게시글 되지 않았습니다. <br>작성된 글에 비속어가 포함되어있습니다. <br>다시작성해주세요.");
		}else {
			out.println("<br>게시글이 입력되었습니다.");
		}
		
//		request.getRequestDispatcher("boardlist").forward(request, response);
		response.sendRedirect("boardlist");
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
