<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/faq/faqMain.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>

		<%@include file="../includes/faq/faqHeader.jsp" %>
			
			<div class="content_area">
				
				<div class="wrapper_faq">
		
					<form id="faq_form">
						<div class="wrap_faq">
							<div class="subject">
								<span>FAQ</span>
							</div>
							
							
							
							
							
							
							
							
							
							
						</div><!-- wrap_faq -->
					</form><!-- faq_form -->
					
				</div><!-- wrapper_faq -->
				
			</div> <!-- content_area -->
			
		<%@include file="../includes/faq/faqFooter.jsp" %>
		
	
	<script>
		
	
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
		
	
	</script>
	

</body>
</html>