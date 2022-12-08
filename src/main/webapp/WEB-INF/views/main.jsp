<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="resources/css/main.css?ver56">
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

	<%@include file="../views/includes/header.jsp"%>
			
			<div class="content_area">
				<div class="slide_div_wrap">
					<div class="slide_div">
					
						<div>
							<a>
								<img src="../resources/img/slide1.jpg">
							</a>
						</div>
						<div>
							<a>
								<img src="../resources/img/slide2.jpg">
							</a>
						</div>
						<div>
							<a>
								<img src="../resources/img/slide3.jpg">
							</a>
						</div>	
									
					</div>	
				</div>
				
				<div class="ls_wrap">
					<div class="ls_div_subject">
						평점순 상품
					</div>
					<div class="ls_div">
						<c:forEach items="${ls}" var="ls">
							<a href="/goodsDetail/${ls.bookId}">
								<div class="ls_div_content_wrap">
									<div class="ls_div_content">
										<div class="image_wrap" data-bookid="${ls.imageList[0].bookId}" data-path="${ls.imageList[0].uploadPath}" data-uuid="${ls.imageList[0].uuid}" data-filename="${ls.imageList[0].fileName}">
											<img>
										</div>	
									</div>
									<div class="ls_wrap_2">
											<div class="ls_category">
												${ls.cateName}
											</div>
											<div class="ls_rating">
												${ls.ratingAvg}
											</div>
											<div class="ls_bookName">
												${ls.bookName}
											</div>							
										</div>
								</div>
							</a>					
						</c:forEach>					
					</div>
				</div>
			</div>
			
		<%@include file="../views/includes/footer.jsp"%>
		
	
	<script>
		
	$(document).ready(function(){
		$(".slide_div").slick(
				{
					dots: true,
					autoplay: true,
					autoplaySpeed: 5000
				}
		);
		
		$(".ls_div").slick({
			slidesToShow: 4,
			slidesToScroll: 4,
			prevArrow : "<button type='button' class='ls_div_content_prev'>이전</button>",		
			nextArrow : "<button type='button' class='ls_div_content_next'>다음</button>",
		});
				
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
		
		$(".image_wrap").each(function(i, obj){
			
			const bobj = $(obj);
			
			if(bobj.data("bookid")){
				const uploadPath = bobj.data("path");
				const uuid = bobj.data("uuid");
				const fileName = bobj.data("filename");
				
				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
				
				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
			} else {
				$(this).find("img").attr('src', '/resources/img/NoImage.png');
			}
			
		});
		
		let dresult = '<c:out value="${delete_result}"/>';
		checkDresult(dresult);
		
		function checkDresult(dresult){
			
			if(dresult === '1'){
				alert("탈퇴하였습니다.")
			} else if(dresult === '0'){
				alert("탈퇴하지 못하였습니다.")
			}
		}
	</script>
	

</body>
</html>