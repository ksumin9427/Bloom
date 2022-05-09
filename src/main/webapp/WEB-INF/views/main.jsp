<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bloom</title>
<link rel="stylesheet" href="/resources/css/main.css?ver=12">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
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
							<a id="gnb_logout_button">로그아웃</a>
						</li>
						<li>
							마이룸
						</li>
						<li>
							장바구니
						</li>
					</c:if>
					<li>
						고객센터
					</li>
				</ul>
			</div>
			
			<div class="top_area">
				<div class="logo_area">
					<h1>logo area</h1>
				</div>
				
				<div class="search_area">
					<h1>Search area</h1>
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
				<h1>navi area</h1>
			</div>
			
			<div class="content_area">
				<h1>content area</h1>
			</div>
			
		</div>
	</div>
	
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