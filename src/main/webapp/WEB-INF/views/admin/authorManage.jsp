<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<<<<<<< HEAD
<link rel="stylesheet" href="../resources/css/admin/authorManage.css?ver14">
=======
<link rel="stylesheet" href="../resources/css/admin/authorManage.css?ver11">
>>>>>>> 19c0ea79dbeef91537ccb2cb46df1d4b27629800

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
</head>
<body>

		<%@include file= "../includes/admin/adminHeader.jsp"%>
				
			<div class="content_area">
				
                <div class="admin_content_wrap">
					<div class="author_table_wrap">
						<!-- 게시물 O -->
						<c:if test="${listCheck != 'empty' }">
	                    	<table class="author_table">
	                    		<thead>
	                    			<tr>
	                    				<td class="th_column_1">작가 번호</td>
	                    				<td class="th_column_2">작가 이름</td>
	                    				<td class="th_column_3">작가 국가</td>
	                    				<td class="th_column_4">등록 날짜</td>
	                    				<td class="th_column_5">수정 날짜</td>
	                    			</tr>
	                    		</thead>
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td><c:out value="${list.authorId}"></c:out> </td>
	                    			<td>
	                    				<a class="move" href='<c:out value="${list.authorId}"/>'>
	                    					<c:out value="${list.authorName}"></c:out>
	                    				</a> 	                    				
	                    			</td>
	                    			<td><c:out value="${list.nationName}"></c:out> </td>
	                    			<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
	                    			<td><fmt:formatDate value="${list.updateDate}" pattern="yyyy-MM-dd"/></td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>						
						</c:if>
						
                		<!-- 게시물 x -->
                		<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				등록된 작가가 없습니다.
                			</div>
                		</c:if> 						
                			
                    </div> <!-- author_table_wrap -->
                    
                    <!-- 검색 영역 -->
                    <div class="admin_searchWrap">
                    	<form id="admin_searchForm" action="/admin/authorManage" method="get">
                    		<div class="admin_searchInput">
                    			<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"></c:out>'>
                    			<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                    			<button class='searchBtnM'>검 색</button>
                    		</div>
                    	</form>
                    </div>                    

                    <!-- 페이지 이동 인터페이스 영역 -->
                    <div class="pageMaker_wrap" >
	                    <ul class="pageMaker">
	                    
	                    	<!-- 이전 버튼 -->
	                    	<c:if test="${pageMaker.prev}">
	                    		<li class="pageMaker_btn prev">
	                    			<a href="${pageMaker.pageStart - 1}">이전</a>
	                    		</li>
	                    	</c:if>
	                    	
	                    	<!-- 페이지 번호 -->
	                    	<!-- 현재 페이지일 경우 class 속성 값이 pageMaker_btn active가 되는데
                    			이는 css설정을 사용해서 현재페이지라는 것을 알려주기 위해서 -->
	                    	<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
	                    		<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
	                    			<a href="${num}">${num}</a>
	                    		</li>
	                    	</c:forEach>
	                    	
	                    	<!-- 다음 버튼 -->
	                    	<c:if test="${pageMaker.next}">
	                    		<li class="pageMaker_btn next">
	                    			<a href="${pageMaker.pageEnd + 1 }">다음</a>
	                    		</li>
	                    	</c:if>
	                    </ul>
                    </div>
                    
					<form id="moveForm" action="/admin/authorManage" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					</form>                    
                                       
                </div>
 			</div><!-- content_area -->
 				<%@include file="../includes/admin/adminFooter.jsp" %>

<script>
$(function(){
	/* let은 변수 재할당이 가능하다, 스크립트 코드 공격을 막기 위해 */
	let result = '<c:out value="${enroll_result}"/>';
	checkResult(result);
	
	function checkResult(result){
		
		if(result === ''){
			return;
		}
		alert("작가'${enroll_result}'을 등록하였습니다.");
	}
	
});

	let mresult = '<c:out value="${modify_result}"/>';
	checkmResult(mresult);
	
	function checkmResult(mresult){
		
		if(mresult === '1'){
			alert("작가 정보 수정을 완료하였습니다.");
		} else if(mresult === '0') {
			alert("작가 정부 수정을 하지 못하였습니다.")	
		}
		
	}
	
	/* 삭제 후 경고창 */
	let delete_result = '${delete_result}';
	
	if(delete_result == 1){
		alert("삭제 완료");
	} else if(delete_result == 2){
		alert("해당 작가 데이터를 사용하고 있는 데이터가 존재하여 삭제 할 수 없습니다.");
	}
	
	let moveForm = $('#moveForm');
	
	//* 페이지 이동 버튼 */
	$(".pageMaker_btn a").on("click", function(e){
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.attr("action", "/admin/authorManage");
		
		moveForm.submit();
		
	});

	/* 작거 검색 버튼 동작 */
	let admin_searchForm = $('#admin_searchForm');
	
	$('#admin_searchForm button').on("click", function(e){
		e.preventDefault();
		
		 if(!admin_searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하십시오");
			return false;
		} 
		
		admin_searchForm.find("input[name='pageNum']").val("1");
		
		admin_searchForm.submit();
		
	});
	
	/* 작가 상세 페이지 이동 */
	$(".move").on("click", function(e){
		e.preventDefault();
		
		moveForm.append("<input type='hidden' id='authorId' name='authorId' value='"+ $(this).attr("href") + "'>");
		
		moveForm.attr("action", "/admin/authorDetail");
		
		moveForm.submit();
		
<<<<<<< HEAD
		
=======
		/* 뒤로가기 시 bookId삭제  */
		$(window).bind("pageshow", function (event) {
			if(event.originalEvent.persisted){
			$('#authorId').remove();
			
			}
		}) 
>>>>>>> 19c0ea79dbeef91537ccb2cb46df1d4b27629800
		
	});

</script>

</body>
</html>