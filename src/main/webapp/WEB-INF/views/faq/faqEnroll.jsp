<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/faq/faqEnroll.css?ver35">
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
							
				
				<div class="wrapper_faq">
					<div class="titles">
						카테고리<br><br>
						작성자<br><br>
						제목<br>
					</div>
		
					<form id="faq_form"  method="post">
						<div class="wrap_faq">
								<div class="cate_wrap">
							        <select class="cate2" name="cateCode">
							    	 	<option selected value="none">선택</option>
							    	</select>
							    </div>
							    
							    
							    <div class="input_wrap_writer">
							        <input name="writer" value="관리자" readonly="readonly">
							    </div>
							    
								<div class="input_wrap_title">
							        <input name="title">
							    </div>
							    <div class="input_wrap_content">
							        <textarea name="content" id="content_textarea"></textarea>
							    </div>
							   
							    <div class="faq_button_wrap">
							    	<input type="button" class="faq_button" value="등록하기">
							    </div>
							    
						</div><!-- wrap_faq -->
					</form><!-- faq_form -->
					
				</div><!-- wrapper_faq -->
				
			</div> <!-- content_area -->
			
		<%@include file="../includes/faq/faqFooter.jsp" %>
		
	
	<script>
		
	
	
		$(".faq_button").click(function(){
			
			let cateCheck = false;
			let titleCheck = false;
			let contentCheck = false;
			
			
			let cate = $("select[name='cateCode']").val();
			let title = $("input[name='title']").val();
			let content = $(".input_wrap_content p").html();
			
			
			if(cate == 'none'){
				alert("카테고리를 선택해주세요.")
				cateCheck = false;
			} else{
				cateCheck = true;
				console.log($(".input_wrap_content p").html());
			}
			if(title == ''){
				alert("제목을 입력해주세요.")
				titleCheck = false;
			} else{
				titleCheck = true;
			}
			if(content != '<br data-cke-filler="true">'){
				contentCheck = true;
			} else{
				alert("내용을 기입해주세요.")
				contentCheck = false;
			}
			
			if(cateCheck && titleCheck && contentCheck == true){
				$("#faq_form").attr("action","/faq/faqEnroll.do")
				$("#faq_form").submit();
			}else {
				return false;
			}
			
			
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
		
		let cateList = JSON.parse('${cateList}');
		
		let cate2Array = new Array();
		let cate2Obj = new Object();
		
		let cateSelect2 = $(".cate2");
		
		function makeCateArray(obj,array,cateList,tier){
			for(let i = 0; i < cateList.length; i++){
				if(cateList[i].tier === 2){
					cate2Obj = new Object();
					
					cate2Obj.cateName = cateList[i].cateName;
					cate2Obj.cateCode = cateList[i].cateCode;
					cate2Obj.cateParent = cateList[i].cateParent;
					
					cate2Array.push(cate2Obj);				
					
				}
			}		
		}
		
		makeCateArray(cate2Obj,cate2Array,cateList,2);
		
		for(let i = 0; i < cate2Array.length; i++){
			cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>"+cate2Array[i].cateName+"</option>");
		}
		
		/* 위지윅 적용 */
		 
		ClassicEditor
			.create(document.querySelector('#content_textarea'))
			.catch(error=>{
				console.error(error);
			});
			
		
		
	
	</script>
	

</body>
</html>