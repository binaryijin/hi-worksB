package com.worksb.hi.mycalendar.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.worksb.hi.board.service.BoardService;
import com.worksb.hi.board.service.BoardVO;
import com.worksb.hi.board.service.ScheVO;
import com.worksb.hi.board.service.TaskVO;
import com.worksb.hi.member.service.MemberVO;
import com.worksb.hi.mycalendar.service.PrivateScheService;
import com.worksb.hi.mycalendar.service.PrivateScheVO;
import com.worksb.hi.mycalendar.service.ToDoListService;
import com.worksb.hi.mycalendar.service.ToDoListVO;

//2023-08-18 김정현 개인일정관리

@Controller
public class PrivateScheController {
	
	@Autowired
	PrivateScheService privateScheService;
	
	@Autowired
	ToDoListService toDoListService;
	
	@Autowired
	BoardService boardService;
	
	//개인일정 페이지에서 ajax호출 url
	@SuppressWarnings("unchecked")
	@GetMapping("privateScheList")
	@ResponseBody
	public List<Map<String, Object>> myCalendar(HttpSession session) {
		
		//session에서 사용자 id값 가져와서 개인일정/todoList 검색
		MemberVO vo = (MemberVO) session.getAttribute("memberInfo");
		PrivateScheVO searchVO = new PrivateScheVO();
		searchVO.setMemberId(vo.getMemberId());
		//개인 일정 리스트
		List<PrivateScheVO> priScheList = privateScheService.selectAllPsche(searchVO);
		//개인 todoList 리스트
		List<ToDoListVO> tdlList = toDoListService.selectAllTdl(searchVO);
		//개인 업무 리스트
		List<BoardVO> myTaskList = privateScheService.searchMyTask(searchVO);
		//상위업무/하위업무 나누기
		List<TaskVO> myTask = new ArrayList<TaskVO>();
		for(int i=0;i<myTaskList.size();i++) {
			int prjBoardId = myTaskList.get(i).getPrjBoardId();
			TaskVO tVO = boardService.searchTaskCal(prjBoardId);
			System.out.println(tVO);
			if(Objects.equals(tVO,null)) {
				tVO = boardService.searchLowerTaskCalendar(prjBoardId);
				if(Objects.equals(tVO,null)) {
					continue;
				}else {
					myTask.add(boardService.searchLowerTaskCalendar(prjBoardId));
					System.out.println(myTask);
				}
			}else {
				myTask.add(tVO);
				System.out.println(myTask);
			}
		}
		System.out.println(myTask);
		//json객체 리스트화
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		for(int i=0;i<priScheList.size();i++) {
			hash.put("id", priScheList.get(i).getScheId());//단건조회용 sche_id 입력
			hash.put("title", priScheList.get(i).getScheTitle()); //제목
			String strStartDate = simpleDateFormat.format(priScheList.get(i).getStartDate());
			hash.put("start", strStartDate); //시작일자
			String strEndDate = simpleDateFormat.format(priScheList.get(i).getEndDate()); 
			hash.put("end", strEndDate); //종료일자
			hash.put("color", "rgba(249, 166, 52, 0.4)");

			
			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		for(int i=0;i<tdlList.size();i++) {
			hash.put("id", "t"+tdlList.get(i).getListId());
			hash.put("title", tdlList.get(i).getListTitle());
			hash.put("start", tdlList.get(i).getApplyDate());
			hash.put("end", "");
			hash.put("allDay", "true");
			hash.put("color", "rgb(28 215 31 / 50%) ");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		for(int i=0;i<myTask.size();i++) {
			hash.put("id", "W"+myTask.get(i).getPrjBoardId());
			hash.put("title", myTask.get(i).getPrjBoardTitle());
			String taskStartDate = simpleDateFormat.format(myTask.get(i).getStartDate());
			hash.put("start", taskStartDate);
			String taskEndDate = simpleDateFormat.format(myTask.get(i).getEndDate());
			hash.put("end", taskEndDate);
			hash.put("allDay", "true");
			hash.put("color", "rgba(156, 187, 58, 0.4)");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		
		return jsonArr;
	}
	
	//개인 일정/업무 조회
	@SuppressWarnings("unchecked")
	@GetMapping("scheTaskView")
	@ResponseBody
	public List<Map<String, Object>> scheTaskView(HttpSession session) {
		
		
		//session에서 사용자 id값 가져와서 개인일정/todoList 검색
		MemberVO vo = (MemberVO) session.getAttribute("memberInfo");
		PrivateScheVO searchVO = new PrivateScheVO();
		searchVO.setMemberId(vo.getMemberId());
		//개인 일정 리스트
		List<PrivateScheVO> priScheList = privateScheService.selectAllPsche(searchVO);
		//개인 업무 리스트
		List<BoardVO> myTaskList = privateScheService.searchMyTask(searchVO);
		//상위업무/하위업무 나누기
		List<TaskVO> myTask = new ArrayList<TaskVO>();
		for(int i=0;i<myTaskList.size();i++) {
			int prjBoardId = myTaskList.get(i).getPrjBoardId();
			TaskVO tVO = boardService.searchTaskCal(prjBoardId);
			System.out.println(tVO);
			if(Objects.equals(tVO,null)) {
				tVO = boardService.searchLowerTaskCalendar(prjBoardId);
				if(Objects.equals(tVO,null)) {
					continue;
				}else {
					myTask.add(boardService.searchLowerTaskCalendar(prjBoardId));
					System.out.println(myTask);
				}
			}else {
				myTask.add(tVO);
				System.out.println(myTask);
			}
		}
		System.out.println(myTask);
		//json객체 리스트화
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		//일정
		for(int i=0;i<priScheList.size();i++) {
			hash.put("id", priScheList.get(i).getScheId());//단건조회용 sche_id 입력
			hash.put("title", priScheList.get(i).getScheTitle()); //제목
			String strStartDate = simpleDateFormat.format(priScheList.get(i).getStartDate());
			hash.put("start", strStartDate); //시작일자
			String strEndDate = simpleDateFormat.format(priScheList.get(i).getEndDate()); 
			hash.put("end", strEndDate); //종료일자
			hash.put("color", "rgba(249, 166, 52, 0.4)");

			
			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		//업무
		for(int i=0;i<myTask.size();i++) {
			hash.put("id", "W"+myTask.get(i).getPrjBoardId());
			hash.put("title", myTask.get(i).getPrjBoardTitle());
			String taskStartDate = simpleDateFormat.format(myTask.get(i).getStartDate());
			hash.put("start", taskStartDate);
			String taskEndDate = simpleDateFormat.format(myTask.get(i).getEndDate());
			hash.put("end", taskEndDate);
			hash.put("allDay", "true");
			hash.put("color", "rgba(156, 187, 58, 0.4)");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		
		return jsonArr;
	}
	
	//tdl 조회
	//개인일정 페이지에서 ajax호출 url
	@SuppressWarnings("unchecked")
	@GetMapping("tdlView")
	@ResponseBody
	public List<Map<String, Object>> tdlView(HttpSession session) {
		
		//session에서 사용자 id값 가져와서 개인일정/todoList 검색
		MemberVO vo = (MemberVO) session.getAttribute("memberInfo");
		PrivateScheVO searchVO = new PrivateScheVO();
		searchVO.setMemberId(vo.getMemberId());
		//개인 todoList 리스트
		List<ToDoListVO> tdlList = toDoListService.selectAllTdl(searchVO);
		
		//json객체 리스트화
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 

		for(int i=0;i<tdlList.size();i++) {
			hash.put("id", "t"+tdlList.get(i).getListId());
			hash.put("title", tdlList.get(i).getListTitle());
			hash.put("start", tdlList.get(i).getApplyDate());
			hash.put("end", "");
			hash.put("allDay", "true");
			hash.put("color", "rgb(28 215 31 / 50%) ");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		
		return jsonArr;
	}
	//개인 스케줄 검색 조회
	@GetMapping("searchPrivateCalendar")
	@ResponseBody
	public List<Map<String, Object>> searchCalendar(String searchKeyword,HttpSession session){
		MemberVO vo = (MemberVO) session.getAttribute("memberInfo");
		PrivateScheVO searchVO = new PrivateScheVO();
		searchVO.setMemberId(vo.getMemberId());
		searchVO.setSearchKeyword(searchKeyword);
		//session에서 사용자 id값 가져와서 개인일정/todoList 검색
		//개인 일정 리스트
		List<PrivateScheVO> priScheList = privateScheService.selectAllPsche(searchVO);
		//개인 todoList 리스트
		List<ToDoListVO> tdlList = toDoListService.selectAllTdl(searchVO);
		//개인 업무 리스트
		List<BoardVO> myTaskList = privateScheService.searchMyTask(searchVO);
		//상위업무/하위업무 나누기
		List<TaskVO> myTask = new ArrayList<TaskVO>();
		for(int i=0;i<myTaskList.size();i++) {
			int prjBoardId = myTaskList.get(i).getPrjBoardId();
			TaskVO tVO = boardService.searchTaskCal(prjBoardId);
			System.out.println(tVO);
			if(Objects.equals(tVO,null)) {
				tVO = boardService.searchLowerTaskCalendar(prjBoardId);
				if(Objects.equals(tVO,null)) {
					continue;
				}else {
					myTask.add(boardService.searchLowerTaskCalendar(prjBoardId));
					System.out.println(myTask);
				}
			}else {
				myTask.add(tVO);
				System.out.println(myTask);
			}
		}
		System.out.println(myTask);
		//json객체 리스트화
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();
		HashMap<String, Object> hash = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		for(int i=0;i<priScheList.size();i++) {
			hash.put("id", priScheList.get(i).getScheId());//단건조회용 sche_id 입력
			hash.put("title", priScheList.get(i).getScheTitle()); //제목
			String strStartDate = simpleDateFormat.format(priScheList.get(i).getStartDate());
			hash.put("start", strStartDate); //시작일자
			String strEndDate = simpleDateFormat.format(priScheList.get(i).getEndDate()); 
			hash.put("end", strEndDate); //종료일자
			hash.put("color", "rgba(249, 166, 52, 0.4)");

			
			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		for(int i=0;i<tdlList.size();i++) {
			hash.put("id", "t"+tdlList.get(i).getListId());
			hash.put("title", tdlList.get(i).getListTitle());
			hash.put("start", tdlList.get(i).getApplyDate());
			hash.put("end", "");
			hash.put("allDay", "true");
			hash.put("color", "rgb(28 215 31 , 0.5)");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		for(int i=0;i<myTask.size();i++) {
			hash.put("id", "W"+myTask.get(i).getPrjBoardId());
			hash.put("title", myTask.get(i).getPrjBoardTitle());
			String taskStartDate = simpleDateFormat.format(myTask.get(i).getStartDate());
			hash.put("start", taskStartDate);
			String taskEndDate = simpleDateFormat.format(myTask.get(i).getEndDate());
			hash.put("end", taskEndDate);
			hash.put("allDay", "true");
			hash.put("color", "rgba(156, 187, 58, 0.4)");

			jsonObj = new JSONObject(hash);
			jsonArr.add(jsonObj);
		}
		
		return jsonArr;
	}
	
	
	//개인일정 페이지 불러오기
	@GetMapping("privateSche")
	public String viewPriCalendar() {
		
		return "mypage/prCalendar";
		//return "mypage/privateCalendar";
	}	

	//개인일정 입력
	@PostMapping("priScheInsert")
	@ResponseBody
	public String priScheInsert(PrivateScheVO vo) {
		int insertResult = privateScheService.insertPsche(vo);
		String resultMsg =null;
		if(insertResult == -1) {
			resultMsg = "등록실패";
		}else {
			resultMsg = "등록성공";
		}
		return resultMsg;
	}

	
	
	//개인일정 단건조회
	@GetMapping("selectPsche")
	@ResponseBody
	public PrivateScheVO viewPsche(@RequestParam int scheId) {
		PrivateScheVO scheVO = privateScheService.selectPsche(scheId);
		//클라이언트시간에 맞게 날짜값 수정
//		Date startDate = scheVO.getStartDate();
//		scheVO.setStartDate(null);
		return scheVO;
	}
	
	
	//개인일정 수정
	@PostMapping("updatePsche")
	@ResponseBody
	public String updatePsche(PrivateScheVO vo) {
		int result = privateScheService.updatePsche(vo);
		String resultMsg;
		if(result==1) {
			resultMsg = "success";
		}else {
			resultMsg = "fail";
		}
		return resultMsg;
	}
	
	//개인일정 삭제
	@GetMapping("deletePsche")
	@ResponseBody
	public String deletePshce(PrivateScheVO vo) {
		int result = privateScheService.deletePsche(vo.getScheId());
		String resultMsg;
		if(result==1) {
			resultMsg = "success";
		}else {
			resultMsg = "fail";
		}
		return resultMsg;
	}
	
	//todoList 단건조회
	@GetMapping("selectTdl")
	@ResponseBody
	public Map<String, List<ToDoListVO>> viewTodoList(int listId) {
		ToDoListVO tdlVO= toDoListService.selectTdl(listId);
		List<ToDoListVO> listVO = new ArrayList<ToDoListVO>(); 
		listVO.add(tdlVO);
		List<ToDoListVO> listItemVO = toDoListService.selelctTdlItem(tdlVO.getListId());
		
		HashMap<String, List<ToDoListVO>> map = new HashMap<String, List<ToDoListVO>>();
		map.put("todoList", listVO);
		map.put("item", listItemVO);
		
		return map;
	};
	
	//ToDo List 입력
	@PostMapping("todoListInsert")
	@ResponseBody
	public String todoListInsert(@RequestBody List<ToDoListVO> tdlList) {
		System.out.println(tdlList);
		//리스트의 첫번째는 todoList이므로 List테이블에 인서트
		ToDoListVO tdlListVO = tdlList.get(0);
		int result = toDoListService.insertTdl(tdlListVO);
		//리스트의 두번째부터는 todoListItem이므로 Item테이블에 인서트
		ToDoListVO tdlItemVO = new ToDoListVO();
		int itemResult =0;
		for(int i = 1;i<tdlList.size();i++) {
			if(tdlList.get(i).getContent()!=null) {
				tdlItemVO.setListId(tdlListVO.getListId());
				tdlItemVO.setContent(tdlList.get(i).getContent()); 
				tdlItemVO.setSuccess(tdlList.get(i).getSuccess());
				itemResult =+ toDoListService.insertItem(tdlItemVO);
			}
		}
		String resultMsg;
		if(result ==1 && itemResult != 0) {
			resultMsg = itemResult+"success";
		}else {
			resultMsg = "fail";
		}
		return resultMsg;
	}
	//todoList 수정
	@PostMapping("updateToDoList")
	@ResponseBody
	public String updateTdlList(@RequestBody Map<String, List<ToDoListVO>> tdlData) {
	    List<ToDoListVO> todoList = tdlData.get("todoList");
	    List<ToDoListVO> updateList = tdlData.get("updateItem");
	    List<ToDoListVO> deleteList = tdlData.get("deleteItem");
	    List<ToDoListVO> insertList = tdlData.get("insertItem");
	    if(todoList!=null) {
	    	toDoListService.updateTdl(todoList.get(0));
	    }
	    if(updateList!=null) {
	    	for(int i=0;i<updateList.size();i++) {
	    		toDoListService.updateItem(updateList.get(i));
	    	}
	    }
	    if(deleteList!=null) {
	    	for(int i=0;i<deleteList.size();i++) {
	    		toDoListService.deleteItem(deleteList.get(i).getItemId());
	    	}
	    }
	    if(insertList!=null) {
	    	for(int i=0;i<insertList.size();i++) {
	    		insertList.get(i).setListId(todoList.get(0).getListId());
	    		toDoListService.insertItem(insertList.get(i));
	    	}
	    }
	    String result = "success";
	    return result;
	}
	
	//todoList 삭제
	@GetMapping("deleteToDoList")
	@ResponseBody
	public String deleteTdlList(ToDoListVO vo) {
		int result = toDoListService.deleteTdl(vo.getListId());
		toDoListService.deleteItemList(vo.getListId());
		String resultMsg;
		if(result==1) {
			resultMsg = "success";
		}else {
			resultMsg = "fail";
		}
		return resultMsg;
	}
	
	//todoList ITEM 성공여부 업데이트
	@PostMapping("updateTdlItem")
	@ResponseBody
	public String updateTdlItem(@RequestBody ToDoListVO vo) {
		System.out.println(vo);
		int result = toDoListService.updateItem(vo);
		String resultMsg;
		if(result==1) {
			resultMsg = "success";
		}else {
			resultMsg = "fail";
		}
		return resultMsg;
	}
	
}
