<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/myRoom/myRoomInfo.css?ver2">
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
		
					<form id="info_form">
					<div class="wrap_info">
						<div class="subject">
							<span>나의정보확인</span>
						</div>
						
						<div class="id_wrap">
							<div class="id_name">아이디</div>
							<div class="id_input_box">
								<input class="id_input" name="memberId"  value="${member.memberId}" readonly="readonly">
							</div>
							
						</div>
						
						<div class="user_wrap">
							<div class="user_name">이름</div>
							<div class="user_input_box">
								<input class="user_input" name="memberName"  value="${member.memberName}" readonly="readonly">
							</div>
						</div>
						
						<div class="mail_wrap">
							<div class="mail_name">이메일</div> 
							<div class="mail_input_box">
								<input class="mail_input" name="memberMail"  value="${member.memberMail}" readonly="readonly">
							</div>
						</div>
						
						
						
						<div class="address_wrap">
							<div class="address_name">주소</div>
							<div class="address_input_1_wrap">
								<div class="address_input_1_box">
									<input class="address_input_1" name="memberAddr1" readonly="readonly" value="${member.memberAddr1}">
								</div>
								<div class="clearfix"></div>
							</div>
							<div class ="address_input_2_wrap">
								<div class="address_input_2_box">
									<input class="address_input_2" name="memberAddr2" readonly="readonly" value="${member.memberAddr2}">
								</div>
							</div>
							<div class ="address_input_3_wrap">
								<div class="address_input_3_box">
									<input class="address_input_3" name="memberAddr3" readonly="readonly" value="${member.memberAddr3}">
								</div>
							</div>
						</div>
						
						
					</div>
					</form>
				</div><!-- wrapper_info -->
				
				
			</div><!-- content_area -->
			
		<%@include file="../includes/myRoom/myRoomFooter.jsp" %>
		
		<script>
		let mresult = '<c:out value="${modify_result}"/>';
		checkmResult(mresult);
		
		function checkmResult(mresult){
			
			if(mresult === '1'){
				alert("정보 수정을 완료하였습니다.");
			} else if(mresult === '0') {
				alert("정보 수정을 하지 못하였습니다.")	
			}
			
		}
		</script>
	

</body>
</html>