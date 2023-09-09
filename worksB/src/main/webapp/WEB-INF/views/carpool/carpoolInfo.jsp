<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<title>Insert title here</title>
<style>
.carpool-board-box {
	width: 60%;
	margin: 60px auto 0; 
	color: var(--color-dark-grey);
	font-size: var(--font-micro);
}

.insert {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px 20px;
}

.insert h2 {
	margin: 0;
	font-size: var(--font-regular);
	font-weight: var(--weight-bold);
	color: var(--color-green);
}
.carpool-icon {
	width: 20px;
	height: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th,td {
	border: 1px solid var(--color-dark-beigie);
}

th, tfoot td {
	height: 40px;
}

.table-title ,.table-writer, .table-hit, .table-reg,
.table-departure, .table-passenger, .table-arrival, .table-departureDate {
	width: 15%;
	text-align: center;
	background-color: var(--color-dark-beigie);
}

.title-content, .hit-content, .writer-content, .reg-content,
.departure-content, .passenger-content, .arrival-content, .departureDate-content {
	width: 35%;
	text-align: left;
	padding: 0 20px;
	font-weight: var(--weight-semi-bold);
}

.table-content {
	margin: 0 auto;
	height: 350px;
	overflow: auto;
	overflow-x : hidden;
	padding: 10px 20px;
	vertical-align: top;
}
.carBtn {
	width: 85px;
	height: 30px;
	background-color: var(--color-dark-beigie);
	border-radius: 5px;
	color: var(--color-dark-grey);
	font-weight: var(--weight-bold);
}

.carBtn:hover {
	background-color: var(--color-white);
	color: var(--color-dark-red);
	border: 1px solid var(--color-dark-red);
}
.carBtn-td{
	margin: 0 auto;
}

.table-content::-webkit-scrollbar {
		width: 10px;
	}
	
	.table-content::-webkit-scrollbar-thumb {
		background-color: #2f3542;
		border-radius: 10px;
		background-clip: padding-box;
		border: 2px solid transparent;
		background-color: var(--color-dark-beigie);
	}
	
	.table-content::-webkit-scrollbar-track {
		background-color: grey;
		border-radius: 10px;
		box-shadow: inset 0px 0px 5px white;
		background-color: var(--color-dark-beigie);
	}

 	.table-footer {
 		display: flex;
 		justify-content: flex-start;
 		margin: 10px 0;
 	}

	#likeButton {
		background-color: transparent;
	}	
	
	.like-btn {
		width: 20px;
		height: 20px;
		margin-right: 10px;
	}
	
	#likeCount {
		font-weight: var(--weight-bold);
	}
	
	.buttons button[type=button] {
		background-color: transparent;
		color: var(--color-dark-red);
		margin: 0 10px;
		font-weight: var(--weight-bold);
	}
	
	#commentContent {
		width: 97%;
		resize: none;
		height: 99px;
		border-color: var(--color-dark-white);
		overflow: scroll;
		overflow-x: hidden;
		color: var(--color-dark-grey);
		padding: 15px;
	}
	
	.cmtList {
		display: flex;
		justify-content: space-between;
		align-items:center;
		font-weight: var(--weight-bold);
		text-align: left;
	}
	
	.cmtList-item {
		padding: 10px 20px;
		border: 1px solid var(--color-dark-beigie);
	}
	
	#insertButton {
		width: 70px;
		height: 30px;
		color: var(--color-dark-grey);
		background-color: var(--color-dark-beigie);
		border-radius: 5px;
		font-weight: var(--weight-bold);
	}
	
	.profile {
		width: 50px;
		height: 50px;
		border-radius: 5px;
		margin-right: 20px;
	}
	
	.boardCmtList {
		margin: 30px 0;
	}
	
	.boardCmtList h2 {
		margin: 20px 0;
		font-size: var(--font-micro);
	}
	
	.cmt-title {
		font-weight: var(--weight-bold);
		margin-bottom: 10px;
	}
	
	.cmt-content {
		width: 100%;
		display: block;
		word-break:break-all;
		resize: none;
		border: none;
		outline: none;
		background-color: white;
	}
	
	.cmt-update span:hover, .reply-update span:hover {
		color: black;
	}
	
	.cmt-plus-arrow {
		width: 15px; 
		height:15px;
		margin-right: 20px;
	}
	
	.reply {
		margin-top: 30px; 
		text-align: right; 
		padding-right: 10px;
		cursor: pointer;
	}
	
	.reply:hover {
		color: black;
	}
	
	.d-flex {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	
	.cursor {
		cursor: pointer;
	}






</style>
</head>
<body>
	<div class="carpool-board-box">
		<div class="insert">
			<h2>
				<img class="notice-icon" src="${pageContext.request.contextPath }/resources/icon/car-solid.svg" alt="카풀 아이콘">
				같이 타요
			</h2>
		</div>
		<form action="carpoolInfo" method="post">
			<table class="table">
				<thead>
					<tr class="main__p">
						<th class="table-title">
							제목
						</th>
						<th class="title-content">
						<c:choose>
							<c:when test="${carpoolInfo.category eq 'B1' }">
								[태워드립니다] 
							</c:when>
							<c:otherwise>
								[태워주세요] 
							</c:otherwise>
						</c:choose>
							${carpoolInfo.boardTitle }
						</th>
						<th class="table-hit">
							조회수
						</th>
						<th class="hit-content">
							${carpoolInfo.boardHit }
						</th>
					</tr>
					<tr>
						<th class="table-writer">
							작성자
						</th>
						<th class="writer-content">
							${carpoolInfo.memberName}
						</th>
						<th class="table-reg">
							작성시간
						</th>
						<th class="reg-content">
							<fmt:formatDate value="${carpoolInfo.boardRegdate}" pattern="yyyy-MM-dd hh:mm"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="table-content" colspan="4">
							${carpoolInfo.boardContent }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div class="carBtn-td">
								<button class="carBtn" onclick="participate('${carpoolInfo.boardId}',${carpoolInfo.passenger },${ participantsCounting},'${carpoolInfo.memberId }','${memberId }')">참여하기</button>
								<button class="carBtn" onclick="cancel('${carpoolInfo.boardId}','${memberId}')">취소하기</button>
							</div>
							<div>
								<div id="participants">
								<c:forEach items="${ participantList}" var="list">
									<p class="m-info" data-id="${list.memberId }">${list.memberName }</p>
								</c:forEach>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="table-departure">
							출발
						</td>
						<td class="departure-content">
							${carpoolInfo.departure } 
						</td>
						<td class="table-passenger">
							탑승 인원수
						</td>
						<td class="passenger-content">
							${carpoolInfo.passenger } 
						</td>
					</tr>
					<tr>
						<td class="table-arrival">
							도착
						</td>
						<td class="arrival-content">
							${carpoolInfo.arrival } 
						</td>
						<td class="table-departureDate">
							출발 시간
						</td>
						<td class="departureDate-content">
							<fmt:formatDate value="${carpoolInfo.departureDate }" pattern="yyyy/MM/dd HH:mm"/>
						</td>
					</tr>
				</tfoot>
			</table>
			<div class="table-footer d-flex">
				<div class="d-flex">
					 <!-- 좋아요 버튼 -->
				    <button id="likeButton" type="button" onclick="toggleLike('${carpoolInfo.boardId}', '${memberInfo.memberId}')">
				    	<img class="like-btn" src="${pageContext.request.contextPath }/resources/icon/heart-solid.svg">
				    </button>
			    	<!-- 좋아요 수 표시 -->
			    	<span id="like">
				    	<span id="likeCount">
				    		${likeCount}
				    	</span>
				    	<c:if test="${checkLike eq true }">
				    		좋아요 취소
				    	</c:if>
						<c:if test="${checkLike eq false}">
				    		좋아요
				    	</c:if>				    	
				    </span>
				</div>
				<div class="buttons">
					<c:if test="${memberInfo.memberId eq carpoolInfo.memberId}">
						<button type="button" class="buttonss__button" onclick="location.href='${pageContext.request.contextPath}/carpoolUpdate?boardId=${carpoolInfo.boardId}'">수정</button>
						<button type="button" class="buttonss__button" onclick="location.href='${pageContext.request.contextPath}/carpoolDelete?boardId=${carpoolInfo.boardId}'">삭제</button>
					</c:if>
					<button type="button" class="buttonss__button" onclick="location.href='${pageContext.request.contextPath}/carpoolList'">목록</button>
				</div>
			</div>
		</form>
		
		<!-- ajax로 댓글 생성하는 공간 -->
		<div class="boardCmtList">
			<h2>댓글</h2>
			<ul>
			</ul>
		</div>
		
		<!-- 댓글 작성 폼 -->
		<form id="cmtInsert" method="post">
			<div class="cmtList">
				<p class="cmtList__cmtInsert">댓글 작성 | ${memberInfo.memberName }</p>
				<button type="button" id="insertButton">작성</button>
			</div>
			<div id="cmtInsert__text">
				<textarea id="commentContent" name="commentContent" placeholder="댓글을 입력하세요"></textarea>
			</div>
		</form>
		<!-- 대댓글 작성 폼 -->
		<div id="replyInsert" class="replyInsert">
			<form id="replyInsertForm" class="replyInsertForm" method="post">
				<div class="cmtList">
	    			<p class="cmtList__cmtInsertc">댓글 작성 | ${memberInfo.memberName }</p>
	    			<div id="cmtInsert__textc">
	        			<textarea class="commentContentc" name="commentContentc" placeholder="댓글을 입력하세요"></textarea>
	        			<button type="button" id="insertButtonc">등록</button>
	        			<button type="button" id="closeReply">닫기</button>
	    			</div>
    			</div>
			</form>
		</div>
		
		<!-- 수정 폼 / 댓글 대댓글 공유-->
		<div id="UpdateFromModal">
			<form class="UpdateContent" method="post">
				<div>
    				<p>댓글 수정</p>
    				<textarea id="newCommentContent" name="newCommentContent"></textarea>
    				<button type="button" id="cmtUpdateButton">수정</button>
    				<button type="button" id="closeUpdate">닫기</button>
  				</div>
			</form>
		</div>
	</div>
</body>
<script>
	$(document).ready(function(){
		// 댓글 리스트 출력
		getcmtList();
	});
	
	/* ------------------------------------댓글 시작---------------------------------- */
	// 댓글 리스트를 출력하는 함수
	function getcmtList(){
		let cmt = $(".boardCmtList");
		let str = "";
		$.get("boardCmtList",{boardId : ${carpoolInfo.boardId}, boardType : 'C3'},function(list) {
			for(let i = 0 , len = list.length || 0; i < len; i++ ){
				/* 부모/자식 댓글 구분 */
				
				if(list[i].commentContent == '삭제된 댓글 입니다.'){
					str += "<ul>" + "<li class='cmtName'>" + list[i].memberName + "</li>";
					str += "<li class='cmtInsert'>" + "<button type='button' id='cmtInsertFormButton' class='cmtInsertFormButton'>" + "답글작성" + "</button>" + "</li>";
					str += "<li class='cmtContent'>" + list[i].commentContent + "</li>"
					/* 밑으론 hidden */
					str += "<li>" + "<input type='hidden' class='commentId' value='" + list[i].commentId +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardType' value='" + list[i].boardType +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardId' value='" + list[i].boardId +" '>" + "</li>" + "</ul>";
				} else if(list[i].parentId == 0){
					/* 부모 댓글일 경우 */
					str += "<ul>" + "<li class='cmtName'>" + list[i].memberName + "</li>";
				 	str += "<li class='cmtInsert'>" + "<button type='button' id='cmtInsertFormButton' class='cmtInsertFormButton'>" + "답글작성" + "</button>" + "</li>";
				 	/* 내가 쓴 글일경우 */
				 	if(list[i].memberId == "${memberInfo.memberId}"){
				 		str += "<li class='cmtInsert'>" + "<button type='button' class='cmtUpdate'>" + "수정" + "</button>" + "</li>";
				 		str += "<li class='cmtInsert'>" + "<button type='button' class='cmtDelete'>" + "삭제" + "</button>" + "</li>";
				 	}
					str += "<li class='cmtDate'>" + list[i].commentRegdate + " |" + "</li>";
					str += "<li class='cmtContent'>" + list[i].commentContent + "</li>";
					/* 밑으론 히든 */
					str += "<li>" + "<input type='hidden' class='commentId' value='" + list[i].commentId +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardType' value='" + list[i].boardType +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardId' value='" + list[i].boardId +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='commentParent' value='" + list[i].parentId + " '>" + "</li>" + "</ul>";
				} else {
					/* 자식 댓글일 경우 */
					str +=  "<ul class = 'cmts'>" + "<li class='cmtNamec'>" + list[i].memberName + "</li>";
				 	/* 내가 쓴 글일경우 */
				 	if(list[i].memberId == "${memberInfo.memberId}"){
				 		str += "<li class='cmtInsert'>" + "<button type='button' class='cmtUpdate' >" + "수정" + "</button>" + "</li>";
				 		str += "<li class='cmtInsert'>" + "<button type='button' class='cmtDelete'>" + "삭제" + "</button>" + "</li>";
				 	}
					str += "<li class='cmtDatec'>" + list[i].commentRegdate + " |" + "</li>";
					str += "<li class='cmtContent' style='margin-left: 50px'>" + list[i].commentContent + "</li>";
					/* 밑으론 히든 */
					str += "<li>" + "<input type='hidden' class='commentId' value='" + list[i].commentId +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardType' value='" + list[i].boardType +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='boardId' value='" + list[i].boardId +" '>" + "</li>";
					str += "<li>" + "<input type='hidden' class='commentParent' value='" + list[i].parentId + " '>" + "</li>" + "</ul>";
				}
				
			}
			cmt.html(str);
		})
	};
	
	// 댓글 등록 ajax
	document.getElementById('insertButton').addEventListener('click', function(){
		let commentContent = $("textarea[name='commentContent']");
		
		console.log(commentContent);
    	$.post("boardCmtInsert", {boardId : ${carpoolInfo.boardId} , 
    							  boardType : 'C3', 
    							  memberId : '${memberInfo.memberId}' ,
    							  commentContent : commentContent.val() ,
    							  parentId : 0 
    	}, function (response) {
    		commentContent.val('');
		    	
	    	getcmtList();
		    });
	});
	
	/* 대댓글 작성 */
	$(document).on('click', '.cmtInsertFormButton', function(e){
		let commentId = $(this).closest("ul").find(".commentId").val();
		let boardType = $(this).closest("ul").find(".boardType").val();
		let boardId = $(this).closest("ul").find(".boardId").val();
		console.log(commentId);
		console.log(boardType);
		console.log(boardId);
		
		let replyInsert = document.getElementById("replyInsert");
		let replyInsertForm = document.getElementsByClassName("replyInsertForm");
		let closeReply = document.getElementById("closeReply");
		let myParent = $(this).closest("ul").find(".commentParent").val();
		
		// 모달창 열기
		replyInsert.style.display = "block";
		document.body.style.overflow = "hidden"; // 스크롤바 제거
		
		// 모달창 닫기
		closeReply.addEventListener("click", () => {
		replyInsert.style.display = "none";
		document.body.style.overflow = "auto"; // 스크롤바 보이기
		});
		
		$(document).on('click', '#insertButtonc', function(e){
			
			let commentContentc = $("textarea[name='commentContentc']").val();
			$.post("boardCmtInsert", {boardId : ${carpoolInfo.boardId},
									  boardType : 'C3',
									  memberId : '${memberInfo.memberId}',
									  commentContent : commentContentc,
									  parentId : commentId 
				},function(response){
					$('.commentContentc').text('');
					console.log("댓글 등록 성공!");
					getcmtList();
					
					replyInsert.style.display = "none";
					document.body.style.overflow = "auto"; // 스크롤바 보이기
				});
		});
	});
	/* 대댓글 작성 끝 */
	
	// 댓글 수정 / 성공은 했는데 한 페이지에서 새로고침 없이 또 수정하면 중복수정됨 / 수정 필수
	$(document).on('click', '.cmtUpdate', function(e){
		let commentId = $(this).closest("ul").find(".commentId").val();
		let boardType = $(this).closest("ul").find(".boardType").val();
		let boardId = $(this).closest("ul").find(".boardId").val();
		let cmtContent = $(this).closest("ul").find(".cmtContent").text();
		console.log('댓글 아이디');
		console.log(commentId);
		console.log('보드 타입');
		console.log(boardType);
		console.log('게시글id');
		console.log(boardId);
		console.log('댓글 내용');
		console.log(cmtContent);
		
		newCommentContent.value = cmtContent;
		
		let UpdateFromModal = document.getElementById("UpdateFromModal");
		let UpdateContent = document.getElementsByClassName("UpdateContent");
		let closeUpdate = document.getElementById("closeUpdate");
		
		// 모달창 열기
		UpdateFromModal.style.display = "block";
		document.body.style.overflow = "hidden"; // 스크롤바 제거
		
		// 모달창 닫기
		closeUpdate.addEventListener("click", () => {
		UpdateFromModal.style.display = "none";
		document.body.style.overflow = "auto"; // 스크롤바 보이기
		});
		
		$('#cmtUpdateButton').click(function(){
			let newCommentContent = $("textarea[name='newCommentContent']").val();
			console.log('작성된댓글내용');
			console.log(newCommentContent);
			$.post("boardCmtUpdate", {commentId : commentId ,
									  boardType : 'C3' ,
									  boardId : ${carpoolInfo.boardId} ,
									  commentContent : newCommentContent
		}, function(response){
			if(response.success){
				$('newCommentContent').val("");
				alert("댓글 수정이 완료되었습니다.");
				UpdateFromModal.style.display = "none";
				document.body.style.overflow = "auto";
				console.log("성공했을때 나오는 텍스트")
				console.log(newCommentContent);
				getcmtList();
				
			} else {
				$('newCommentContent').val(' ');
				alert("댓글을 수정했습니다.")
				UpdateFromModal.style.display = "none";
				document.body.style.overflow = "auto";
				console.log("실패했을때 나오는 텍스트!")
				console.log(newCommentContent);
				getcmtList();
			};
		})
		})
	});
	/* 댓글 수정 끝 */
	
	// 댓글 삭제 ajax
		$(document).on('click', '.cmtDelete', function(e){
			
			let commentId = $(this).closest("ul").find(".commentId").val();
			let boardType = $(this).closest("ul").find(".boardType").val();
			let boardId = $(this).closest("ul").find(".boardId").val();
			let cmtContent = $(this).closest("ul").find(".cmtContent").text();
			let findParent = $(this).closest("ul").next().find(".commentParent").val();
			let myParent = $(this).closest("ul").find(".commentParent").val();
			
			console.log('댓글 아이디');
			console.log(commentId);
			console.log('보드 타입');
			console.log(boardType);
			console.log('게시글id');
			console.log(boardId);
			console.log('댓글 내용');
			console.log(cmtContent);
			console.log('내 댓글이 0이면 부모 1이상이면 자식');
			console.log(myParent);
			console.log('다음글의 부모')
			console.log(findParent);
			
			if ( myParent == 0 && findParent > 0 ){
				console.log("삭제하면 안되는 댓글")
				$.post("boardCmtDelete", {boardId : ${carpoolInfo.boardId} ,
									   boardType : '${carpoolInfo.boardType}',
									   commentId : commentId }, 
									   
					function(response){		  
						getcmtList();
			})
			} else {
				console.log("삭제해도 되는 댓글!");
				$.post("realCmtDelete", {boardId : ${carpoolInfo.boardId} ,
					   					 boardType : '${carpoolInfo.boardType}',
					   					 commentId : commentId }, 
					function(response){
						getcmtList();
			})
			}
			
		});
		/* 삭제 끝 */
	/* --------------------------댓글 끝------------------------- */
	
	 // 좋아요 버튼 클릭 시 호출되는 함수
	    function toggleLike(boardId, memberId) {
	        // 서버로 비동기 요청을 보냄
	        $.get("${pageContext.request.contextPath}/member/like", { boardType: 'C3', boardId: boardId, memberId: memberId }, function (response) {
	            // 서버로부터 받은 값에 따라 동작을 결정함
	            $('#likeCount').remove();
	            $('#like').empty();
	            
				let likeCount = `<span id="likeCount"> \${response.count} </span>`;
	            
	            if (response.result == 'liked') {
	                // 좋아요 상태일 경우 
	                $("#like").text("좋아요 취소");
	            } else if (response.result == "unliked") {
	                // 좋아요 해제 상태일 경우
	                $("#like").text("좋아요");
	            }
	            
	            $('#like').prepend(likeCount);
	        })
		 };

	function participate(boardId,available,counted,writer,memberId){
		if(writer==memberId){
			alert('작성자는 신청하지 못합니다')
			return;
		}

		let requestList=[];
		let countChildren=$('#participants').children()
		requestList.length=countChildren.length
		if(requestList.length==0){
			
		}
		for(let i=0;i<requestList.length;i++){
			if(memberId==$('.m-info').data('id')){
				alert('이미 신청하였습니다.')
				return;
			}
		}

		if(available<=counted){
			alert('마감되었습니다.')
			return;
		}
		$.ajax({
			url:"${pageContext.request.contextPath }/applyCarpool",
			data:{'boardId':boardId},
			method:'GET'
		})
		.done(data=>{
			let pTag=$('<p>').text(data.memberName);
			pTag.addClass('m-info');
			pTag.attr('data-id', data.memberId);
			$('#participants').append(pTag)
		})
		.fail(reject=>{
			console.log(reject)
		})
	}
	$('.m-info').click(function(){
		
	})
	
	function cancel(boardId,memberId){
		let pList=[];
		pList.length=$('[data-id]').length;
		let count=pList.length;
		//참여자인원수만큼포문돌리기
		for(let i=0;i<pList.length;i++){
			let cancelData=$('[data-id]')[i];		
			console.log($('.m-info').data('id'))
			//로그인한 아이디와 일치하는지 확인
			if($('.m-info').data('id')==memberId){
				$.ajax({
					url:'${pageContext.request.contextPath }/cancelCarpool',
					data:{'boardId':boardId},
					method:'GET'
				})
				.done(data=>{
					if(data==null){
						alert('취소를 실패했습니다.다시 시도해 주세요');
						count--;
					}
					cancelData.remove();	
				})
				.fail(reject=>{
					console.log(reject)
				})
				return;
			}
		}
		if(count==pList.length){
			alert('참여신청을 하지 않았습니다')		
		}

		
	}
</script>
</html>