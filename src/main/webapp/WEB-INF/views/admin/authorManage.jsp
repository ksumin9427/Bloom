<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/authorManage.css?ver=6">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
</head>
<body>
 
    	<%@include file="../includes/admin/header.jsp" %>
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>작가 관리</span></div>
                    	<div class="author_table_wrap">
                    	
                    	<c:if test="${listCheck != 'empty'}">
                    		<table class="author_table">
                    			<thead>
                    				<tr>
                    					<td class="th_column_1">작가 번호</td>
                    					<td class="th_column_2">작가 이름</td>
                    					<td class="th_column_3">작가 국가</td>
                    					<td class="th_column_4">등록 날짜</td>
                    					<td class="th_column_5">수정 번호</td>
                    				</tr>
                    			</thead>
                    			
                    			<c:forEach items="${list}" var="list">
	                    			<tr>
	                    				<td><c:out value="${list.authorId}"></c:out></td>
	                    				<td><c:out value="${list.authorName}"></c:out></td>
	                    				<td><c:out value="${list.nationName}"></c:out></td>
	                    				<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/>
	                    				<td><fmt:formatDate value="${list.updateDate}" pattern="yyyy-MM-dd"/>
	                    			</tr>	
                    			</c:forEach>
                    		</table>
                    		</c:if>
                    		
                    		<c:if test="${listCheck == 'empty'}">
                    			<div class="table_empty">
                    				등록된 작가가 없습니다.
                    			</div>
                    		</c:if>
                    	</div>
                    	
                    	<div class="search_wrap">
                    		<form id="searchForm" action="/admin/authorManage" method="get">
                    			<div class="search_input">
                    				<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    				<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"></c:out>'>
                    				<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
                    				<button class="btn search_btn">검색</button>
                    			</div>
                    		</form>
                    	</div>
                    
                    <div class="pageMaker_wrap">
                    	<ul class="pageMaker">
                    		
                    		<c:if test="${pageMaker.prev}">
                    			<li class="pageMaker_btn prev">
                    				<a href="${pageMaker.pageStart - 1}">이전</a>
                    			</li>
                    		</c:if>
                    		
                    		<!-- 페이지 번호 -->
                    		<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
                    			<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
                    				<a href="${num}">${num}</a>
                    			</li>
                    		</c:forEach>
                    		
                    		<c:if test="${pageMaker.next}">
                    			<li class="pageMaker_btn next">
                    				<a href="${pageMaker.pageEnd + 1}">다음</a>
                    			</li>
                    		</c:if>
                    		
                    	</ul>
                    </div>
                    
                    	<form id="moveForm" action="/admin/authorManage"  method="get">
                    		<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}" >
                    		<input type="hidden" name="amount" value="${pageMaker.cri.amount}" >
                    		<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}" >
                    	</form>
                    
                </div>
         <%@include file="../includes/admin/footer.jsp" %>      
 
 <script>
 	$(function(){
 		let result = '<c:out value="${enroll_result}"/>'; /* let은 변수 재할당이 가능하다, 스크립트 코드 공격을 막기 위해 */
 		checkResult(result);
 		
 		function checkResult(result) {
 			if(result === ''){
 				return;
 			}
 			alert("작가 '${enroll_result}' 을 등록하였습니다.")
 		}
 		
 	});
 	
 	let moveForm = $('#moveForm');
 	
 	$('.pageMaker_btn a').on('click', function(e){
 		e.preventDefault();
 		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
 		moveForm.submit();
 		/* '숫자 버튼'을 누르게 되면 <a> 태그의 동작을 멈추고,
 		<a> 태그에 저장된 href속성 값을 <form> 태그의 내부에 있는 pageNum <input> 태그 값으로 저장을 시킨 뒤 
 		<fomr> 태그 속성에 설정되어 있는 url 경로와 method 방식으로 form을 서버로 전송 */
 		
 	});
 	
 	let searchForm = $('#searchForm');
 	
 	$("#searchForm button").on("click", function(e){
 		e.preventDefault();
 		
 		if(!searchForm.find("input[name='keyword']").val()){
 			alert("키워드를 입력하십시오.");
 			return false;
 		}
 		
 		searchForm.find("input[name='pageNum']").val('1');
 		searchForm.submit();
 	});
 	
 	
 </script>
 
</body>
</html>
