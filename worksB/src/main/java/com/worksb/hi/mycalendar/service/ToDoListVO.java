package com.worksb.hi.mycalendar.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ToDoListVO {
//	LIST_ID      NOT NULL NUMBER        
//	LIST_TITLE   NOT NULL VARCHAR2(100) 
//	LIST_REGDATE NOT NULL DATE          
//	MEMBER_ID    NOT NULL VARCHAR2(80)  
//	APPLY_DATE   NOT NULL DATE   
	//LIST 항목
	private int listId;
	private String listTitle;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date listRegdate;
	private String memberId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date applyDate;
	
//	CONTENT NOT NULL VARCHAR2(100) 
//	SUCCESS NOT NULL VARCHAR2(5)   
//	ITEM_ID NOT NULL NUMBER        
//	LIST_ID NOT NULL NUMBER    
	//ITEM 항목
	private String content;
	private String success;
	private int itemId;
}
