package com.worksb.hi.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.worksb.hi.common.PagingVO;
import com.worksb.hi.company.service.CompanyVO;
import com.worksb.hi.company.service.DepartmentVO;
import com.worksb.hi.company.service.JobVO;
import com.worksb.hi.member.service.MemberVO;
import com.worksb.hi.project.service.FileDataVO;
import com.worksb.hi.project.service.PrjParticirVO;
import com.worksb.hi.project.service.ProjectVO;

public interface AdminService {
	//downloadList
	public List<FileDataVO> downloadList(int companyId,PagingVO pagingVO);
	//downloadlist의 갯수
	public int downloadCount(int companyId);
	//날짜설정에 기반한 갯수
	public int countDownloadByDate(int companyId,Date startDate, Date endDate);
	//날짜설정에 기반한 내용
	public List<FileDataVO> filteredFileList(FileDataVO filedataVO,PagingVO pagingVO);
	
	//prjlist갯수
	public int prjcount(int companyId);
	//projectlist
	public List<ProjectVO>projectList(int companyId,PagingVO pagingVO);
	
	// 회사 정보 수정
	public int updateCompany(CompanyVO companyVO);
	// 구성원 리스트
	public List<MemberVO> companyMemberList(int companyId);
	// 구성원 단건 조회
	public MemberVO companyMemberInfo(MemberVO memberVO);
	// 구성원 정보 수정
	public String updateMember(MemberVO memberVO);
	// 구성원 리스트 ajax
	public List<MemberVO> companyMemberLists(int companyId);
	//가입 대기중 회원 리스트
	public List<MemberVO> memberAccpList(int companyId);
	//가입 대기중 회원 리스트 ajax
	public List<MemberVO> memberAccpLista(int company);
	// 가입 승인
	public String memberAccpUpdate(MemberVO memberVO);

	//부서목록
	public List<DepartmentVO>departmentList(int companyId);
	//직급목록
	public List<JobVO>jobList(int companyId);
	
	//부서insert
	public int insertDept(DepartmentVO vo);
	//업데이트된 부서 받아오기
	public DepartmentVO getNewestDep();
	//부서삭제
	public int deleteDept(int deptId);
	//부서업데이트
	public int updateDept(DepartmentVO vo);
	
	
	//해당 부서번호에 해당하는 프로젝트의 이름 불러오이
	public List<ProjectVO>getPrjName(int deptId);
	//해당 부서번호에 해당하는 프로젝트아이디를 불러오기
	public int[] getPrjId(int deptId);
	//부서이름변경
	public int updateProjectName(Map<String,String>pjIdAndName);
	//파일업로드권한 변경
	public int updateFileAccess(ProjectVO vo);
	
	//직급insert
	public int insertRole(JobVO vo);
	//업데이트된 직급 받아오기
	//직급삭제
	public int deleteRole(int jobId);
	//직급업데이트
	public int updateRole(JobVO vo);
	
	//부서정보가져요기
	public List<String> getDeptName(ProjectVO vo);
	
	//매니저정보가져오기
	public List<PrjParticirVO> getManager(String memberId,int projectId);
	//매니저정보 수정하기
	public int updateManager(PrjParticirVO vo);
	//기존의 매니저는 삭제하기
	public int deleteManager(PrjParticirVO vo);
}
