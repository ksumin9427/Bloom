<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/goodsManage.css?ver29">
 
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
 
    <%@include file="../includes/admin/adminHeader.jsp"%>
    			<div class="content_area">
                <div class="admin_content_wrap">
                    <div class="goods_table_wrap">
                    	<!-- 상품 리스트 O -->
	                    <c:if test="${listcheck != 'empty'}">
	                    	<table class="goods_table">
	                    		<thead>
	                    			<tr>
										<td class="th_column_1">상품 번호</td>
	                    				<td class="th_column_2">상품 이름</td>
	                    				<td class="th_column_3">작가 이름</td>
	                    				<td class="th_column_4">카테고리</td>
	                    				<td class="th_column_5">재고</td>
	                    				<td class="th_column_6">등록날짜</td>
	                    			</tr>
	                    		</thead>	
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td><c:out value="${list.bookId}"></c:out></td>
	                    			<td>
		                    			<a class="move" href='<c:out value="${list.bookId}"/>'>
		                    				<c:out value="${list.bookName}"></c:out>
		                    			</a>
	                    			</td>
	                    			<td><c:out value="${list.authorName}"></c:out></td>
	                    			<td><c:out value="${list.cateName}"></c:out></td>
	                    			<td><c:out value="${list.bookStock}"></c:out></td>
	                    			<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>
	                    </c:if>
	                    <!-- 상품 리스트 X -->
                		<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				등록된 작가가 없습니다.
                			</div>
                		</c:if> 
                	</div>
                	
                	<!-- 검색 영역 -->
                	<div class="admin_searchWrap">
                		<form id="admin_searchForm" action="/admin/goodsManage" method="get">
                			<div class="admin_searchInput">
                    			<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
                    			<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount}"/>'>
                    			<input type="hidden" name="type" value="G">
                    			<button class='searchBtnM'>검 색</button>                				
                			</div>
                		</form>
                	</div>
                	
                	<!-- 페이지 이름 인터페이스 영역 -->
                	<div class="pageMaker_wrap">
                		<ul class="pageMaker">
                			
                			<!-- 이전 버튼 -->
                			<c:if test="${pageMaker.prev }">
                				<li class="pageMaker_btn prev">
                					<a href="${pageMaker.pageStart -1}">이전</a>
                				</li>
                			</c:if>
                			
                			<!-- 페이지 번호 -->
                			<c:forEach begin="${pageMaker.pageStart }" end="${pageMaker.pageEnd }" var="num">
                				<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active':''}">
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
                	
                	<form id="moveForm" action="/admin/goodsManage" method="get" >
 						<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
						<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
						<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
                	</form>
                    
                </div>
    			</div><!-- content_area -->
                
     <%@include file="../includes/admin/adminFooter.jsp" %> 
     
<script>
	$(function(){
		
		let eResult = '<c:out value = "${enroll_result}"/>';
		checkResult(eResult);
		
		function checkResult(result){
			
			if(result === ''){
				return;
			}
			alert("상품'"+eResult+"'을 등록하였습니다.");
			
		}
		
		/* 수정 성공하였을 시의 이벤트 */
		let modify_result = '${modify_result}';
		
		if(modify_result == 1){
			alert("수정완료");
		}
		
		/* 삭제시 경고창 */
		let delete_result = "${delete_result}";
		
		if(delete_result == 1){
			alert("삭제완료");
			
		}
		
	});
	
	let admin_searchForm = $('#admin_searchForm');
	let moveForm = $('#moveForm');
	
	/* 작가 검색 버튼 동작 */
	$('#admin_searchForm button').on("click", function(e){
		
		e.preventDefault();
		
		if(!admin_searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하십시오.");
			return false;
		}
		
		admin_searchForm.find("input[name='pageNum']").val("1");
		
		admin_searchForm.submit();
		
	});
	
	/* 페이지 이동 버튼 */
	$(".pageMaker_btn a").on("click", function(e){
		
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.submit();
		
	});


	/* 페이지 이동 버튼 */
	$(".pageMaker_btn a").on("click", function(e){
		
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.submit();
		
	});
	
	$(".move").on("click", function(e){
		
		e.preventDefault();
		
		moveForm.empty();
		
		moveForm.append("<input type='hidden' name='bookId' value='"+$(this).attr("href")+"'>");
		
		moveForm.attr("action", "/admin/goodsDetail");
		
		moveForm.submit();
		
		
	});
		
		

</script>     
 
</body>
</html>
