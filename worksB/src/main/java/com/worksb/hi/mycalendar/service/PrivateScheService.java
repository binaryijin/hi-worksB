package com.worksb.hi.mycalendar.service;

import java.util.List;

import com.worksb.hi.board.service.BoardVO;

public interface PrivateScheService {
	
	//단건조회
	public PrivateScheVO selectPsche(int scheId);
	
	//전체조회
	public List<PrivateScheVO> selectAllPsche(PrivateScheVO privateScheVO);
	
	//일정등록
	public int insertPsche(PrivateScheVO privateScheVO);
	
	//일정수정
	public int updatePsche(PrivateScheVO privateScheVO);
	
	//일정삭제
	public int deletePsche(int scheId);
	
	//개인이 맡은 프로젝트 업무게시글 조회
	public List<BoardVO> searchMyTask(PrivateScheVO privateScheVO);
}
