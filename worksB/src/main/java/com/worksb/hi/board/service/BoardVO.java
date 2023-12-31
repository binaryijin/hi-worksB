package com.worksb.hi.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardVO {
/* 이진
PRJ_BOARD_ID      NOT NULL NUMBER         
PRJ_BOARD_TITLE   NOT NULL VARCHAR2(100)  
PRJ_BOARD_SUBJECT NOT NULL VARCHAR2(3000) 
PRJ_BOARD_REGDATE NOT NULL DATE           sysdate
MEMBER_ID         NOT NULL VARCHAR2(80)   
INSP_YN           NOT NULL VARCHAR2(20)   공개범위 E1관리자만, E2전체공개
PIN_YN                     VARCHAR2(5)    고정글여부 null -> 글 등록할때는 X, 피드에서 update
BOARD_TYPE        NOT NULL VARCHAR2(20)   원글구분 C5게시글, C6일정, C7투표, C8업무
PROJECT_ID        NOT NULL NUMBER         
COORDINATE                 VARCHAR2(60)   
 */
	private int prjBoardId;
	private String prjBoardTitle;
	private String prjBoardSubject;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date prjBoardRegdate;
	private String memberId;
	private String inspYn;
	private String pinYn;
	private String boardType;
	private int projectId;
	private String coordinate;
	private String memberName;
	private String realProfilePath;
	private String boardIconName;
	private int bookmarkByMember; //개인 회원 별 북마크 여부 (북마크O -> 1 / 북마크X -> 0)
	private int bookmarkId;
	private int boardId;
	private int likeId;
	
	//프로젝트 캘린더 검색단어
	private String searchKeyword;
	//북마크 유저 id
	private String markedUserId;
}
