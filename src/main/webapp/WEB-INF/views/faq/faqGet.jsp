<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/faq/faqGet.css?ver18">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
</script>
<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/ckeditor.js"></script>
<style>
<style type ="text /css ">

</style>
</head>
<body>

	<%@include file="../includes/faq/faqHeader.jsp"%>

	<div class="content_area">


		<div class="wrapper_faq">
		
			<div class="titles">
						카테고리<br><br>
						작성자<br><br>
						제목<br>
			</div>
		
			<div class="faq_form">
			
				<div class="input_wrap">
					 <input type="hidden" name="fno" readonly="readonly"
						value='<c:out value="${pageMaker.fno}"/>'>
				</div>
				
				<div class="cate_wrap">	
	                 <select class="cate2" name="cateCode" disabled>
	                   <option value="none">선택</option>
	                  </select>
	            </div>
	             
				<div class="input_wrap_writer">
					 <input name="writer" readonly="readonly"
						value='<c:out value="${pageMaker.writer}"/>'>
				</div>
				<div class="input_wrap_title">
					 <input name="title" readonly="readonly"
						value='<c:out value="${pageMaker.title}"/>'>
				</div>
				<div class="input_wrap">
					<textarea name="content" id="content_textarea" disabled><c:out value="${pageMaker.content}" /></textarea>
				</div>
				
				

			<div class="faq_button_wrap">
				<a class="faq_button" id="list_btn">목록 페이지</a>
				<c:if test="${member.adminCk == 1 }">
					<a class="faq_button"id="modify_btn">수정 하기</a>
				</c:if>
			</div>
			
			<form id="infoForm" method="get">
				<input type="hidden" id="fno" name="fno" value='<c:out value="${pageMaker.fno}"/>'>
				<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
				<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
				<input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
			</form>
		</div><!-- faq_form -->

			</div><!-- wrapper_get -->
	</div>
	<!-- content_area -->

	<%@include file="../includes/faq/faqFooter.jsp"%>


	<script>
		let infoForm = $("#infoForm");

		$("#list_btn").on("click", function(e) {
			/* form.find("#fno").remove(); */
			infoForm.attr("action", "/faq/faqList");
			infoForm.submit();
		});

		$("#modify_btn").on("click", function(e) {
			infoForm.attr("action", "/faq/faqModify");
			infoForm.submit();
		});
		
		$(document).ready(function(){
			
			
			
			let cateList = JSON.parse('${cateList}');
			
			let cate2Array = new Array();
			let cate2Obj = new Object();
			let cateSelect2 = $(".cate2");
			
			/* 'cateList'의 데이터 중 "tier"가 같은 데이터들만 꺼내서 cateArray 변수에 저장 시키도록 아래의 코드를 작성 */
			function makeCateArray(obj,array,cateList, tier){ 
				for(let i = 0; i < cateList.length; i++){
					if(cateList[i].tier === tier){
						obj = new Object();
						
						obj.cateName = cateList[i].cateName;
						obj.cateCode = cateList[i].cateCode;
						obj.cateParent = cateList[i].cateParent;
						
						array.push(obj);				
						
					}
				}
			}	
			
			makeCateArray(cate2Obj,cate2Array,cateList,2);
			
			let targetCate2 = '${pageMaker.cateCode}';
			
			for(let i=0; i < cate2Array.length; i++){
				if(targetCate2 === cate2Array[i].cateCode){
					targetCate2 = cate2Array[i];
				}
			}
			
			for(let i = 0; i < cate2Array.length; i++){
				if(targetCate2.cateParent === cate2Array[i].cateParent){
					cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");
				}
			}
			
			$(".cate2 option").each(function(i,obj){
				if(targetCate2.cateCode === obj.value){
					$(obj).attr("selected", "selected");
				}
			});
			
			/*ckeditor로 책 소개 가져오기*/
	 		ClassicEditor
	 			.create(document.querySelector('#content_textarea'))
	 			.then(editor => { /* 내용 수정 못하게 하기*/
	 				console.log(editor);
	 				editor.isReadOnly = true;
	 	 		})	
	 			.catch(error=>{
	 	 			console.error(error);
	 			});
		});
		
		/* ClassicEditor
		.create(document.querySelector('#content_textarea'))
		.catch(error=>{
			console.error(error);
		}); */
		
		
	</script>


</body>
</html>