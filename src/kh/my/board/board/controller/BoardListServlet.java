package kh.my.board.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kh.my.board.board.model.service.BoardService;
import kh.my.board.board.model.vo.Board;

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
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
		
		final int PAGE_SIZE = 5;  //한페이지당 글 수 
		final int PAGE_BLOCK = 3;  //한화면에 나타날 페이지 링크 수
		int bCount = 0;  //총 글수
		int pageCount = 0;  //총페이지 수 
		int startPage = 1;  //화면에 나타날 시작페이지
		int endPage = 1;  //화면에 나타날 마지막페이지
		int currentPage =1;  //눌려진 페이지
		int startRnum = 1; //화면에 나타날 글 번호
		int endRnum = 1; //화면에 나타날 글 번호
		
		String pageNum = request.getParameter("pagenum");
		if(pageNum != null) {
			currentPage = Integer.parseInt(pageNum);  //눌려진 페이지
		}
		//총 글수
		bCount = new BoardService().getBoardCount();
		//총 페이지수 = (총글수/페이지당 글수) + (총글개수에서 페이지당글수로 나눈 나머지가 0이 아니라면 페이지개수를 12증가)
		pageCount = (bCount/PAGE_SIZE) + (bCount%PAGE_SIZE == 0 ? 0 : 1);

		startRnum = (currentPage - 1) * PAGE_SIZE + 1;
		endRnum = startRnum + PAGE_SIZE -1;
		if(endRnum > bCount) {
			endRnum = bCount;
		}
		
		if(currentPage%PAGE_BLOCK == 0) {
			startPage = (currentPage/PAGE_BLOCK - 1) *PAGE_BLOCK + 1;
		}else {
			startPage = (currentPage/PAGE_BLOCK) *PAGE_BLOCK + 1;
		}
		
		endPage = startPage + PAGE_BLOCK - 1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		//DB가서 값 읽어오기
		ArrayList<Board> voList = new BoardService().selectBoard(startRnum, endRnum);
		
		
		//Data전달을 위해서 request에 셋
		request.setAttribute("boardvolist", voList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		//Page 이동하면서 Data도 전달함.
		request.getRequestDispatcher("/boardlist.jsp").forward(request, response);
		
		
		
		//member 리스트를 화면에 출력
		//if(voList.size() > 0) {
//			for(Board vo: voList) {
//				out.println("<p>" + vo.toString()+"</p>");
//			}
//		//}
//		if(startPage > 1) {
//			out.println("이전 ");
//		}
//		for(int i = startPage; i <= endPage; i++) {
//			out.println(i);
//			if(i != endPage) {
//				out.println(", ");
//			}
//		}
//		if(endPage < pageCount) {
//			out.println(" 다음");
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
