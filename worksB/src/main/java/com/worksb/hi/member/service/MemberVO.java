package com.worksb.hi.member.service;

import lombok.Data;

@Data
public class MemberVO {
//	MEMBER_ID         NOT NULL VARCHAR2(80) 
//	MEMBER_NAME       NOT NULL VARCHAR2(80)  
//	MEMBER_PW         NOT NULL VARCHAR2(256) 
//	MEMBER_PHONE               VARCHAR2(80)  
//	PROFILE_PATH               VARCHAR2(200) 
//	REAL_PROFILE_PATH          VARCHAR2(200) 
//	MEMBER_GRADE      NOT NULL VARCHAR2(5)   
//	CON_IP            NOT NULL VARCHAR2(100) 
//	JOB_ID                     NUMBER        
//	DEPT_ID                    NUMBER        
//	EMP_STATUS                 VARCHAR2(5)   
//	CON_STATUS                 VARCHAR2(5)   
//	COMPANY_ID        NOT NULL NUMBER        
//	LOGIN_FAIL                 NUMBER        
//	LOGIN_LOCK        NOT NULL VARCHAR2(5)   
	private String memberId;
	private String memberName;
	private String memberPw;
	private String memberPhone;
	private String profilePath;
	private String realProfilePath;
	private String memberGrade;
	private String conIp;
	private int jobId;
	private int deptId;
	private String empStatus;
	private String conStatus;
	private int companyId;
	private int loginFail;
	private String loginLock;
}
