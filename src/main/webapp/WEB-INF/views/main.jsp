<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="resources/css/main.css?after">
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

	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">
				<ul class="list">
					<c:if test="${member == null}"> <!--로그인을 하지 않았을 경우  -->
						<li>
							<a href="/member/login">로그인</a>
						</li>
						<li>
							<a href="/member/join">회원가입</a>
						</li>
					</c:if>
					
					<c:if test="${member != null}"> <!--로그인을 했을 경우  -->
						<c:if test="${member.adminCk == 1 }">
							<li><a href="/admin/main">관리자 페이지</a></li>
						</c:if>
						<li>
							<a id="gnb_logout_button" href="/member/logout.do">로그아웃</a>
						</li>
						<li>
							<a href="/myRoom/myRoomMain">마이룸</a>
						</li>
						<li>
							<a href="/cart/${member.memberId}">장바구니</a>
						</li>
					</c:if>
					<li>
						고객센터
					</li>
				</ul>
			</div>
			
			<div class="top_area">
				<div class="logo_area">
					<a href="/main"><img src="resources/img/Logo.png"></a>
				</div>
				
				<div class="search_area">
					<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">책 제목</option>
                					<option value="A">작가</option>
                				</select>
                				<input type="text" name="keyword">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
				</div>
				
				<div class="login_area">
				
					<!--로그인 하지 않은 상태  -->
					<c:if test="${member == null }">
						<div class="login_button">
							<a href="/member/login">로그인</a>
						</div>
						<span><a href="/member/join">회원가입</a></span>
					</c:if>
					
					<!--로그인 상태  -->
					<c:if test="${member != null }">
						<div class="login_success_area">
							<span>회원 :  ${member.memberName}</span>
							<span>충전금액 : <fmt:formatNumber value="${member.money}" pattern="\#,###.##"></fmt:formatNumber></span>
							<span>포인트 : <fmt:formatNumber value="${member.point}" pattern="#,###"></fmt:formatNumber></span>
							<a href="/member/logout.do">로그아웃</a>
						</div>
					</c:if>
					
				</div>
				
			
				
				<div class="clearfix"></div>
			</div>
			
			<div class="navi_bar_area">
				<div class="dropdown">
					<button class="dropbtn">국내도서
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cate1}" var="cate">
								<a href="/search?type=C&cateCode=${cate.cateCode}&cateParent=${cate.cateCode}">${cate.cateName}</a>
						</c:forEach>
					</div>
				</div>
				
				<div class="dropdown">
					<button class="dropbtn">국외도서
						<i class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content">
						<c:forEach items="${cate2}" var="cate">
								<a href="/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
						</c:forEach>
					</div>
				</div>
			</div>
			
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
			
			
			
			<!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
                <ul>
                    <li>회사소개</li>
                    <span class="line">|</span>
                    <li>이용약관</li>
                    <span class="line">|</span>
                    <li>고객센터</li>
                    <span class="line">|</span>
                    <li>광고문의</li>
                    <span class="line">|</span>
                    <li>채용정보</li>
                    <span class="line">|</span>
                </ul>
            </div>
        </div> <!-- class="footer_nav" -->
        
        <div class="footer">
            <div class="footer_container">
                
                <div class="footer_left">
                    <img src="resources/img/Logo.png">
                </div>
                <div class="footer_right">
                    (주) Bloom  대표이사 : 김수민
                    <br>
                    사업자등록번호 : 111-11-11111
                    <br>
                    대표전화 : 1111-1111(발신자 부담전화)
                    <br>
                    <br>
                    COPYRIGHT(C) <strong>kimsumin.tistory.com</strong>    ALL RIGHTS RESERVED.
                </div>
                <div class="clearfix"></div>
            </div>
        </div> <!-- class="footer" --> 
			
			
		</div>
	</div>
	
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
	</script>
	

</body>
</html>