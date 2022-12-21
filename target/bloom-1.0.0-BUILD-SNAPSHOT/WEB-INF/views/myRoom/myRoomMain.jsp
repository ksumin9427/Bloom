<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/myRoom/myRoomMain.css?ver230">
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
			<div class="wrap_content_area">
				<div class="content_area">
					
					<%
						HttpSession sess = request.getSession();
						sess.getAttribute("member");
					%>
					<div class="comments">
						<div class="comments_in">이달의 책</div>
					</div>
						
					<div class="slide_div1">
						
						<div>
								<img class="pic1" src="../resources/img/pic1.jpg">
						</div>
						<div>
								<img class="pic1" src="../resources/img/pic2.jpg">
						</div>
						<div>
								<img class="pic1" src="../resources/img/pic3.jpg">
						</div>
					</div>
					<br>
					<br>	
					<div class="comments">
						<div class="comments_in">이달의 사은품</div>
					</div>
					
					<div class="slide_div2">
						
						<div>
								<img class="pic2" src="../resources/img/pic7.jpg">
						</div>
						<div>
								<img class="pic2" src="../resources/img/pic5.jpg">
						</div>
						<div>
								<img class="pic2" src="../resources/img/pic6.jpg">
						</div>
					</div>


					
				</div><!-- content_area -->
			</div><!-- wrap_content_area -->
			
		<%@include file="../includes/myRoom/myRoomFooter.jsp" %>
		
	
	<script>
		
	$(document).ready(function(){
		$(".slide_div1").slick(
				{
					autoplay : true,
					autoplaySpeed: 3000
					
				}	 
		);
		$(".slide_div2").slick(
				{
					autoplay : true,
					autoplaySpeed: 3000
					
				}	 
		);
		
		
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
		
	
	</script>
	

</body>
</html>