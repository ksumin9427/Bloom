<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/myRoom/myRoomInfoModify.css?ver17">
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
		
					<form class="info_modify_form" method="post">
					<div class="wrap_info">
						<div class="subject">
							<span>나의 정보 수정</span>
						</div>
						
						
						<div class="user_wrap">
							<div class="user_name">이름</div>
							<div class="user_input_box">
								<input class="user_input" name="memberName"  value= "${member.memberName}">
							</div>
						</div>
						
						<div class="mail_wrap">
							<div class="mail_name">이메일</div> 
							<div class="mail_input_box">
								<input class="mail_input" name="memberMail"  value="${member.memberMail}">
							</div>
						</div> 
						
						<div class="address_wrap">
							<div class="address_name">주소</div>
							<div class="address_input_1_wrap">
								<div class="address_input_1_box">
									<input class="address_input_1" name="memberAddr1" value="${member.memberAddr1}">
								</div>
								<div class="address_button" onclick="execution_daum_address()">
									<span>주소 찾기</span>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class ="address_input_2_wrap">
								<div class="address_input_2_box">
									<input class="address_input_2" name="memberAddr2" value="${member.memberAddr2}">
								</div>
							</div>
							<div class ="address_input_3_wrap">
								<div class="address_input_3_box">
									<input class="address_input_3" name="memberAddr3" value="${member.memberAddr3}">
								</div>
							</div>
						</div>
						
						<div class="modify_button_wrap">
							<input type="button" class="modify_button" value="수정하기">
						</div>
						
						<input type="hidden" name="memberId" value="${member.memberId}">
						
					</div>
					</form>
				</div><!-- wrapper_info -->
				
				
			</div><!-- content_area -->
			
		<%@include file="../includes/myRoom/myRoomFooter.jsp" %>
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>	
	<script>
	
	
	$(".modify_button").on("click", function(e){
		e.preventDefault();
		$(".info_modify_form").attr("action", "/myRoom/memberUpdate")
		$(".info_modify_form").submit();
	});
	
	
	
	/* 주소 검색 기능 메서드 */
	function execution_daum_address() {
		new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
 
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
 
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                 // 주소변수 문자열과 참고항목 문자열 합치기
                    addr += extraAddr;
                
                } else {
                	 addr += ' ';
                }
 
                $(".address_input_1").val(data.zonecode);
                $(".address_input_2").val(addr);
                
                // 커서를 상세주소 필드를 사용할 수 있도록 변경하고 커서를 이동해주는 코드
				$(".address_input_3").attr("readonly", false);
                $(".address_input_3").focus();
	        }
	    }).open();
	}
	
	</script>

</body>
</html>