package com.worksb.hi.board.mapper;

import java.util.List;

import com.worksb.hi.board.service.AllTaskBoardVO;
import com.worksb.hi.board.service.BoardVO;
import com.worksb.hi.board.service.ScheVO;
import com.worksb.hi.board.service.TaskVO;
import com.worksb.hi.board.service.VoteVO;

public interface BoardMapper {
	// 이진
	// 게시글 등록
	public int insertBoard(BoardVO boardVO);
	// 업무글 등록 - 상위, 하위
	public int insertTask(TaskVO taskVO);
	// 투표글 등록
	public int insertVote(VoteVO voteVO);
	// 투표 항목 등록
	public int insertVoteList(VoteVO voteVO);
	// 일정글 등록
	public int insertSche(ScheVO scheVO);
	
	// 일정 조회
	public ScheVO getScheInfo(ScheVO scheVO);
	// 투표 조회
	public List<VoteVO> getVoteInfo(VoteVO voteVO);
	// 투표 항목 조회
	public List<VoteVO> getVoteList(VoteVO voteVO);
	// 상위 업무 조회
	public List<AllTaskBoardVO> getHighTask(AllTaskBoardVO allTaskBoardVO);
	// 상위 업무 taskId 조회
	public int getHighTaskId(AllTaskBoardVO allTaskBoardVO);
	// 상위 업무 담당자 조회
	public List<AllTaskBoardVO> getHighManager(AllTaskBoardVO allTaskBoardVO);
	// 하위 업무 조회
	public List<AllTaskBoardVO> getSubTask(int TaskId);
	
	// 업무 담당자 등록
	public int insertTaskManager(TaskVO taskVO);
	
	// 프로젝트 업무글 탭 리스트
	public List<AllTaskBoardVO> getTaskList(int projectId);
	
	
	
	
//주현
	
	
}
