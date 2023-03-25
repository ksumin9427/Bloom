<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/memberManage.css?ver4">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
 
   		<%@include file= "../includes/admin/adminHeader.jsp"%>
   		
            <div class="content_area">
                <div class="admin_content_wrap">
					<div class="member_table_wrap">
						<!-- 게시물 O -->
						<c:if test="${listCheck != 'empty' }">
	                    	<table class="member_table">
	                    		<thead>
	                    			<tr>
	                    				<td class="th_column_1">ID</td>
	                    				<td class="th_column_2">회원 이름</td>
	                    				<td class="th_column_3">회원 메일</td>
	                    				<td class="th_column_4">등록 날짜</td>
	                    			</tr>
	                    		</thead>
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td><c:out value="${list.memberId}"></c:out> </td>
	                    			<td>
	                    					<c:out value="${list.memberName}"></c:out>
	                    			</td>
	                    			<td><c:out value="${list.memberMail}"></c:out> </td>
	                    			<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>						
						</c:if>
						
                		<!-- 게시물 x -->
                		<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				등록된 회원이 없습니다.
                			</div>
                		</c:if> 						
                			
                    </div> 
                    
                    <!-- 검색 영역 -->
                    <div class="member_searchWrap">
                    	<form id="member_searchForm" action="/admin/memberManage" method="get">
                    		<div class="member_searchInput">
                    			<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
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
                    
					<form id="moveForm" action="/admin/memberManage" method="get">
						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					</form>                    
                                       
                </div>
                
                
            </div>
                
        <%@include file="../includes/admin/adminFooter.jsp" %>
<script>

let moveForm = $('#moveForm');

$(".pageMaker_btn a").on("click", function(e){
	e.preventDefault();
	
	moveForm.find("input[name='pageNum']").val($(this).attr("href"));
	
	moveForm.submit();
	
});

/* 작거 검색 버튼 동작 */
let member_searchForm = $('#member_searchForm');

$('#member_searchForm button').on("click", function(e){
	e.preventDefault();
	
	 if(!member_searchForm.find("input[name='keyword']").val()){
		alert("키워드를 입력하십시오");
		return false;
	} 
	
	 member_searchForm.find("input[name='pageNum']").val("1");
	
	 member_searchForm.submit();

});
</script>
</body>
</html>
