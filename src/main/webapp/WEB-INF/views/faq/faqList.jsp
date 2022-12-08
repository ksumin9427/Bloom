<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/faq/faqList.css?ver41">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>

</head>
<body>

		<%@include file="../includes/faq/faqHeader.jsp" %>
			
			<div class="content_area">
								<div class="table_wrap">
									<div class="mini_wrap">
									<c:if test="${member.adminCk == 1 }">
										<a href="/faq/faqEnroll" class="top_btn">글쓰기</a>
									</c:if>
									
									<div class="faq_search_wrap">
				                		<form id="faq_searchForm" action="/faq/faqList" method="get">
				                			<div class="faq_search_input">
								                <select name="type">
									                <option value="T" <c:out value="${pageMaker.cri.type eq 'T'?'selected':'' }"/>>제목</option>
									                <option value="C" <c:out value="${pageMaker.cri.type eq 'C'?'selected':'' }"/>>카테고리</option>
				                				</select>
				                				<c:if test="${pageMaker.cri.type == C}">
					                				<input type="hidden" name="cateName" value="${pageMaker.cri.keyword}">
				                				</c:if>
				                				<input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
				                				
				                				<input type="hidden" name="pageNum" value='<c:out value="${pageMaker.cri.pageNum}"/>'>
				                				<input type="hidden" name="amount" value='<c:out value="${pageMaker.cri.amount}"/>'>
				                    			<button class='btn faq_search_btn'>검 색</button>                				
				                			</div>
				                		</form>
				                	</div>
				                	
									<table>
										<thead>
											<tr>
												<th class="fno_width">글번호</th>
												<th class="title_width">제목</th>
												<th class="writer_width">작성자</th>
											</tr>
										</thead>
										<c:forEach items="${list}" var="list">
											<tr>
												<td><c:out value="${list.fno}"/></td>
												<td>
													<a class="move" href='<c:out value="${list.fno}"/>'>
														<c:out value="${list.title}"/>
													</a>
												</td>
												<td><c:out value="${list.writer}"/></td>
											</tr>
										</c:forEach>
										
									</table>
									
									<div class="wrap_pageMaker">
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
									</div>
										
										<form id="moveForm" method="get">
											<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
											<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
											<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
										</form>
								</div>		
								</div>
								
			
				
			</div> <!-- content_area -->
			
		<%@include file="../includes/faq/faqFooter.jsp" %>
		
		<script>
		
		
		$(document).ready(function(){
			let result = '<c:out value="${result}"/>';
			
			function checkAlert(result){
				if(result === "enroll_success"){
					alert("게시글이 등록되었습니다.");
				}
				if(result === "modify_success"){
					alert("게시글이 수정되었습니다.");
				}
				if(result === "delete_success"){
					alert("게시글이 삭제되었습니다.");
				}
				if(result === ""){
					return;
				}
			}
			checkAlert(result);
			
		});
		
			$("#gnb_logout_button").click(function(){
				$.ajax({
					type:"POST",
					url:"/member/logout.do",
					success:function(data){
						alert("로그아웃 성공");
						document.location.reload();
					}
				});
			});
			
			let moveForm = $("#moveForm");
			let faq_searchForm = $("#faq_searchForm");
			
			
			/* 작가 검색 버튼 동작 */
			$('#faq_searchForm button').on("click", function(e){
				
				e.preventDefault();
				
				if(!faq_searchForm.find("input[name='keyword']").val()){
					alert("키워드를 입력하십시오.");
					return false;
				}
				
				faq_searchForm.find("input[name='pageNum']").val("1");
				
				faq_searchForm.submit();
				
			});
			
			$(".move").on("click", function(e){
				e.preventDefault();
				/* moveForm.empty(); */
				moveForm.append("<input type='hidden' name='fno' value='"+$(this).attr("href")+"'>");
				moveForm.attr("action","/faq/faqGet");
				moveForm.submit();
			});
			
			$(".pageMaker a").on("click", function(e){
				e.preventDefault();
				moveForm.find("input[name='pageNum']").val($(this).attr("href"));
				moveForm.attr("action","/faq/faqList");
				moveForm.submit();
			});
			
			/* 위지윅 적용 */
			 
			ClassicEditor
				.create(document.querySelector('#content_textarea'))
				.catch(error=>{
					console.error(error);
				});
			
		
		</script>
		
	

		
	

</body>
</html>