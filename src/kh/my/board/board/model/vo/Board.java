package kh.my.board.board.model.vo;

import java.sql.Date;

//CREATE TABLE BOARD(
//	    BNO NUMBER PRIMARY KEY,
//	    TITLE VARCHAR2(50) NOT NULL,
//	    CONTENT VARCHAR2(400) NOT NULL,
//	    CREATE_DATE DATE DEFAULT SYSDATE,
//	    WRITER VARCHAR2(20),
//	    DELETE_YN CHAR(2) DEFAULT 'N',


//	    FOREIGN KEY (WRITER) REFERENCES MEMBER(MEMBER_ID),
//	    CHECK(DELETE_YN IN('Y','N'))
//	);
//
//	-- BOARD 테이블의 PK로 사용될 시퀀스
//	CREATE SEQUENCE SEQ_BOARD;
public class Board {
	 private int bno;
	 private String title;
	 private String content;
	 private Date createDate;
	 private String writer;
	 private String deleteYn;
	 private int bref;  //진짜 원본 조상글
	 private int breLevel;   //원본글 0, 답글 1, 답답글 2
	 private int breStep;    //화면에 보여질 순서, 기준은 원본글 1, ...
	
	
	
	public Board() {
		
	}
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	
	
	public Board(int bno, String title, String content, String writer, int bref, int breLevel, int breStep) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.bref = bref;
		this.breLevel = breLevel;
		this.breStep = breStep;
	}


	
	
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", writer=" + writer + ", deleteYn=" + deleteYn + ", bref=" + bref + ", breLevel=" + breLevel
				+ ", breStep=" + breStep + "]";
	}

	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDelete_yn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public int getBref() {
		return bref;
	}
	public void setBref(int bref) {
		this.bref = bref;
	}
	public int getBreLevel() {
		return breLevel;
	}
	public void setBreLevel(int breLevel) {
		this.breLevel = breLevel;
	}
	public int getBreStep() {
		return breStep;
	}
	public void setBreStep(int breStep) {
		this.breStep = breStep;
	}
	
}
