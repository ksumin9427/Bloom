<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/myRoom/memberDelete.css">
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
</head>
<body>

		<%@include file="../includes/myRoom/myRoomHeader.jsp" %>
			
			<div class="content_area">
				
				<div class="wrapper_info">
		
					<form class="info_delete_form" method="post">
					<div class="wrap_info">
					
						<div class="subject">
							<span>회원탈퇴</span>
						</div>
						
						<div class="pw_wrap">
							<div class="pw_name">비밀번호</div>
							<div class="pw_input_box">
								<input class="pw_input" name="memberPw">
							</div>
						</div>
						
						<div class="delete_button_wrap">
							<!-- <input type="submit" class="modify_button" value="수정하기"> -->
							<button class="delete_button">탈퇴하기</button>
						</div>
						
						<input type="hidden" name="memberId" value="${member.memberId}">
						
					</div>
					</form>
					
				</div><!-- wrapper_info -->
				
				
			</div><!-- content_area -->
			
		<%@include file="../includes/myRoom/myRoomFooter.jsp" %>
	
	<script>
	/* var pwCheck = false; */
	
	$(".delete_button").on("click", function(e){
		e.preventDefault();
		
		$(".info_delete_form").attr("action", "/myRoom/memberDelete.do")
		$(".info_delete_form").submit();
	});
	
	
	
	
	
	</script>

</body>
</html>