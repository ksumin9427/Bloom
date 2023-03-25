<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/orderList.css?ver25">

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
					<div class="order_table_wrap">
						<c:if test="${listCheck != 'empty'}">
	                    	<table class="order_table">
	                    		
	                    		<thead>
	                    			<tr>
	                    				<td class="th_column_1">주문 번호</td>
	                    				<td class="th_column_2">주문 아이디</td>
	                    				<td class="th_column_3">주문 날짜</td>
	                    				<td class="th_column_4">주문 상태</td>
	                    				<td class="th_column_5">취소</td>
	                    			</tr>
	                    		</thead>
	                    		<c:forEach items="${list}" var="list">
	                    			<tr>
	                    				<td><c:out value="${list.orderId}"></c:out></td>
	                    				<td><c:out value="${list.memberId}"></c:out></td>
	                    				<td><fmt:formatDate value="${list.orderDate}" pattern="yyyy-MM-dd"></fmt:formatDate></td>
	                    				<td><c:out value="${list.orderState}"></c:out></td>
	                    				<td>
	                    					<c:if test="${list.orderState == '배송준비' }">
	                    						<button class="delete_btn" data-orderid="${list.orderId}">취소</button>
	                    					</c:if>
	                    				</td>
	                    			</tr>
	                    		</c:forEach>				
							</table>
						</c:if>	
						
                		<!-- 게시물 x -->
                		<c:if test="${listCheck == 'empty'}">
                			<div class="table_empty">
                				현재 주문이 없습니다.
                			</div>
                		</c:if> 						
                		
                	</div><!-- order_table_wrap -->
                    
		                    <div class="orderlist_searchWrap">
		                    	<form id="orderlist_searchForm" action="/admin/orderList" method="get">
		                    		<div class="orderlist_searchInput">
		                    			<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"></c:out>'>
		                    			<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum }"></c:out>'>
		                    			<input type="hidden" name="amount" value='${pageMaker.cri.amount}'>
		                    			<button class='searchBtnM'>검 색</button>
		                    		</div>
		                    	</form>
		                    </div>  
		                  
		                      <div class="pageMaker_wrap" >
			                    <ul class="pageMaker">
			                    	<c:if test="${pageMaker.prev}">
			                    		<li class="pageMaker_btn prev">
			                    			<a href="${pageMaker.pageStart - 1}">이전</a>
			                    		</li>
			                    	</c:if>
			                    	
			                    	<c:forEach begin="${pageMaker.pageStart}" end="${pageMaker.pageEnd}" var="num">
			                    		<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? "active":""}">
			                    			<a href="${num}">${num}</a>
			                    		</li>
			                    	</c:forEach>
			                    	
			                    	<c:if test="${pageMaker.next}">
			                    		<li class="pageMaker_btn next">
			                    			<a href="${pageMaker.pageEnd + 1 }">다음</a>
			                    		</li>
			                    	</c:if>
			                    </ul>
		                    </div>
		                    
							<form id="moveForm" action="/admin/orderList" method="get">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
								<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
							</form>   
							
							<form id="deleteForm" action="/admin/orderCancle" method="post">
								<input type="hidden" name="orderId">
								<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
								<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
								<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
								<input type="hidden" name="memberId" value="${member.memberId}">
							</form>
					                 
                   </div><!--  admin_content_wrap  -->                  
                </div>  <!-- content_area -->
 
 				<%@include file="../includes/admin/adminFooter.jsp" %>

	<script>
		let orderlist_searchForm =$('#orderlist_searchForm');
		let moveForm =$('#moveForm');
		
		$('#orderlist_searchForm button').on("click", function(e){
			e.preventDefault();
			
			if(!orderlist_searchForm.find("input[name='keyword']").val()){
				alert("키워드를 입력하십시오.")
				return false;
			}
			
			orderlist_searchForm.find("input[name='pageNum']").val("1");
			orderlist_searchForm.submit();
		});
		
		$('.pageMaker_btn a').on("click", function(e){
			e.preventDefault();
			
			moveForm.find("input[name='pageNum']").val($(this).attr("href"));
			moveForm.submit();
			
		});
		
		$('.delete_btn').on("click", function(e){
			e.preventDefault();
			
			let id = $(this).data("orderid");
			
			$('#deleteForm').find("input[name='orderId']").val(id);
			$('#deleteForm').submit();
			
		});

	</script>

</body>
</html>