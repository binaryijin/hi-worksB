package com.worksb.hi.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.worksb.hi.admin.mapper.AdminMapper;
import com.worksb.hi.admin.service.AdminService;
import com.worksb.hi.common.PagingVO;
import com.worksb.hi.company.service.CompanyVO;
import com.worksb.hi.company.service.DepartmentVO;
import com.worksb.hi.company.service.JobVO;
import com.worksb.hi.member.service.MemberVO;
import com.worksb.hi.project.service.FileDataVO;
import com.worksb.hi.project.service.PrjParticirVO;
import com.worksb.hi.project.service.ProjectVO;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminMapper adminMapper;
	
	@Override
	public List<FileDataVO> downloadList(int companyId,PagingVO pagingVO) {
		return adminMapper.downloadList(companyId,pagingVO);
	}

	@Override
	public int downloadCount(int companyId) {
		return adminMapper.downloadCount(companyId);
	}

	@Override
	public int prjcount(int companyId) {
		return adminMapper.prjcount(companyId);
	}

	@Override
	public List<ProjectVO> projectList(int companyId, PagingVO pagingVO) {
		return adminMapper.projectList(companyId, pagingVO);
	}
	
	//수정
	@Override
		public int updateCompany(CompanyVO companyVO) {
		return adminMapper.updateCompany(companyVO);
		}

	// 회사 구성원 리스트
	@Override
	public List<MemberVO> companyMemberList(int companyId) {
		return adminMapper.companyMemberList(companyId);
	}

	// 구성원 상세조회
	@Override
	public MemberVO companyMemberInfo(MemberVO memberVO) {
		return adminMapper.companyMemberInfo(memberVO);
	}
	
	// 구성원 정보 수정
	@Override
	public String updateMember(MemberVO memberVO) {
		int result = adminMapper.updateMember(memberVO);
	
    if(result == 1) {
			return memberVO.getMemberId();
		}else {
			return null;
		}
	}
  
	// ajax로 수정
	@Override
	public List<MemberVO> companyMemberLists(int companyId) {
		return adminMapper.companyMemberLists(companyId);
	}
	// 승인대기중인 구성원 리스트
	@Override
	public List<MemberVO> memberAccpList(int companyId) {
		return adminMapper.memberAccpList(companyId);
	}
	//가입 대기중 회원 리스트 ajax
	public List<MemberVO> memberAccpLista(int companyId){
		return adminMapper.memberAccpLista(companyId);
	}
	// 가입 승인
	@Override
	public String memberAccpUpdate(MemberVO memberVO) {
		int result = adminMapper.memberAccpUpdate(memberVO);

		if(result == 1) {
			return memberVO.getMemberId();
		}else {
			return null;
		}
	}
	
	@Override
	public List<DepartmentVO> departmentList(int companyId) {
		return adminMapper.departmentList(companyId);
	}

	@Override
	public List<JobVO> jobList(int companyId) {
		return adminMapper.jobList(companyId);
	}

	@Override
	public int insertDept(DepartmentVO vo) {
		if(adminMapper.insertDept(vo)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public int deleteDept(int deptId) {
		if(adminMapper.deleteDept(deptId)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public int insertRole(JobVO vo) {
		if(adminMapper.insertRole(vo)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public int deleteRole(int roleId) {
		if(adminMapper.deleteRole(roleId)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public DepartmentVO getNewestDep() {
		return adminMapper.getNewestDep();
	}

	@Override
	public int updateDept(DepartmentVO vo) {
		if(adminMapper.updateDept(vo)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public int updateRole(JobVO vo) {
		if(adminMapper.updateRole(vo)<1) {
			return -1;
		}
		return 1;
	}

	@Override
	public List<ProjectVO> getPrjName(int deptId) {
		return adminMapper.getPrjName(deptId);
	}

	@Override
	public int[] getPrjId(int deptId) {
		return adminMapper.getPrjId(deptId);
	}

	@Override
	public int updateProjectName(Map<String,String>pjIdAndName) {
		return adminMapper.updateProjectName(pjIdAndName);
		//plsql블럭으로 for문안돌리고 여기서 for문돌려도 됨 for문 돌린 갯수가 update갯수가 되겟죵?
	}

	@Override
	public List<String> getDeptName(ProjectVO vo) {
		return adminMapper.getDeptName(vo);
	}

	@Override
	public List<PrjParticirVO> getManager(String memberId,int projectId) {
		return adminMapper.getManager(memberId,projectId);
	}

	@Override
	public int updateManager(PrjParticirVO vo) {
		return adminMapper.updateManager(vo);
	}

	@Override
	public int deleteManager(PrjParticirVO vo) {
		return adminMapper.deleteManager(vo);
	}

	@Override
	public int updateFileAccess(ProjectVO vo) {
		return adminMapper.updateFileAccess(vo);
	}

	@Override
	public int countDownloadByDate(int companyId, Date startDate, Date endDate) {
		return adminMapper.countDownloadByDate(companyId, startDate, endDate);
	}

	@Override
	public List<FileDataVO> filteredFileList(FileDataVO filedataVO, PagingVO pagingVO) {
		return adminMapper.filteredFileList(filedataVO, pagingVO);
	}


	
}
