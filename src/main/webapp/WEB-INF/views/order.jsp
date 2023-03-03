<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bloom</title>
<link rel="stylesheet" href="/resources/css/order.css?ver10">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

	
			<%@include file="../views/includes/header.jsp"%>
			
			<div class="content_area">
				
				<div class="content_main">
					<!-- 주문자 정보 -->
					<div class="member_info_div">
						<table class="table_text_align_center memberInfo_table">
							<tbody>
								<tr>
									<th style="width: 25%;">주문자</th>
									<td style="width: *">${memberInfo.memberName} | ${memberInfo.memberMail}</td>
								</tr>
							</tbody>
						</table>
					</div>
					
					<!-- 배송지 -->
					<div class="addressInfo_div">
						<div class="addressInfo_button_div">
							<button class="address_btn address_btn_1" onclick="showAddress('1')" style="background-color: #3c3838;">사용자 정보 주소록</button>
							<button class="address_btn address_btn_2" onclick="showAddress('2')">직접 입력</button>
						</div>
					
					
					<div class="addressInfo_input_div_wrap">
						<div class="addressInfo_input_div addressInfo_input_div_1" style="display: block">
							<table>
								<colgroup>
									<col width="25%">
									<col width="*">
								</colgroup>
								<tbody>
									<tr>
										<th>이름</th>
										<td>
											${memberInfo.memberName}
										</td>
									</tr>
									<tr>
										<th>주소</th>
										<td>
											${memberInfo.memberAddr1} ${memberInfo.memberAddr2}<br>${memberInfo.memberAddr3}
											<input class="selectAddress" value="T" type="hidden">
											<input class="addressee_input" value="${memberInfo.memberName}" type="hidden">
											<input class="address1_input" type="hidden" value="${memberInfo.memberAddr1}">
											<input class="address2_input" type="hidden" value="${memberInfo.memberAddr2}">
											<input class="address3_input" type="hidden" value="${memberInfo.memberAddr3}">										
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="addressInfo_input_div addressInfo_input_div_2">
							<table>
								<colgroup>
									<col width="25%">
									<col width="*">
								</colgroup>
								<tbody>
									<tr>
										<th>이름</th>
										<td>
											<input class="addressee_input">
										</td>
									</tr>
									<tr>
										<th>주소</th>
										<td>
											<input class="selectAddress" value="F" type="hidden">
											<input class="address1_input" readonly="readonly"> <a class="address_search_btn" onclick="execution_daum_address()">주소 찾기</a><br>
											<input class="address2_input" readonly="readonly"><br>
											<input class="address3_input" readonly="readonly">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div> <!-- addressInfo_input_div_wrap -->
				</div>
				
				<div class="orderGoods_div">
					<!-- 상품 종류 -->
					<div class="goods_kind_div">
						주문상품 <span class="goods_kind_div_kind"></span>종 <span class="goods_kind_div_count"></span>개
					</div>
					<!-- 상품 테이블 -->
					<table class="goods_subject_table">
						<colgroup>
							<col width="15%">
							<col width="45%">
							<col width="40%">
						</colgroup>
						<tbody>
							<tr>
								<th>이미지</th>
								<th>상품 정보</th>
								<th>판매가</th>
							</tr>
						</tbody>
					</table>
					<table class="goods_table">
						<colgroup>
							<col width="15%">
							<col width="45%">
							<col width="40%">
						</colgroup>					
						<tbody>
							<c:forEach items="${orderList}" var="ol">
								<tr>
									<td>
										<div class="image_wrap" data-bookid="${ol.imageList[0].bookId}" data-path="${ol.imageList[0].uploadPath}" data-uuid="${ol.imageList[0].uuid}" data-filename="${ol.imageList[0].fileName}">
											<img>
										</div>
									</td>
									<td>${ol.bookName}</td>
									<td class="goods_table_price_td">
										<fmt:formatNumber value="${ol.salePrice}" pattern="#,### 원" /> | 수량 ${ol.bookCount}개
										<br><fmt:formatNumber value="${ol.totalPrice}" pattern="#,### 원" />
										<br>[<fmt:formatNumber value="${ol.totalPoint}" pattern="#,### 원" />P]
										<input type="hidden" class="individual_bookPrice_input" value="${ol.bookPrice}">
										<input type="hidden" class="individual_salePrice_input" value="${ol.salePrice}">
										<input type="hidden" class="individual_bookCount_input" value="${ol.bookCount}">
										<input type="hidden" class="individual_totalPrice_input" value="${ol.salePrice * ol.bookCount}">
										<input type="hidden" class="individual_point_input" value="${ol.point}">
										<input type="hidden" class="individual_totalPoint_input" value="${ol.totalPoint}">
										<input type="hidden" class="individual_bookId_input" value="${ol.bookId}">
									</td>
								</tr>							
							</c:forEach>
			
						</tbody>
					</table>
				</div>					
						
				<!-- 포인트-->
				<div class="point_div">
					<div class="point_div_subject">포인트 사용</div>
					<table class="point_table">
						<colgroup>
							<col width="25%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th>포인트 사용</th>
								<td>
									${memberInfo.point} | <input class="order_point_input" value="0">원 
									<a class="order_point_input_btn order_point_input_btn_N" data-state="N">모두사용</a>
									<a class="order_point_input_btn order_point_input_btn_Y" data-state="Y" style="display: none;">사용취소</a>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="total_info_div">
					<!-- 가격 종합 정보 -->
					<div class="total_info_price_div">
						<ul>
							<li>
								<span class="price_span_label">상품 금액</span>
								<span class="totalPrice_span"></span>원
							</li>
							<li>
								<span class="price_span_label">배송비</span>
								<span class="delivery_price_span"></span>원
							</li>
																					<li>
								<span class="price_span_label">할인금액</span>
								<span class="usePoint_span"></span>원
							</li>
							<li class="price_total_li">
								<strong class="price_span_label total_price_label">최종 결제 금액</strong>
								<strong class="strong_red">
									<span class="total_price_red finalTotalPrice_span">
										
									</span>원
								</strong>
							</li>
							<li class="point_li">
								<span class="price_span_label">적립예정 포인트</span>
								<span class="totalPoint_span">원</span>
							</li>
						</ul>
					</div>
					<!-- 버튼 영역 -->
					<div class="total_info_btn_div">
						<a class="order_btn">결제하기</a>
					</div>
				</div>
				
			</div> <!-- content_main -->
				
				<!-- 주문 요청 form -->
				<form class="order_form" action="/order" method="post">
					<input name="memberId" value="${memberInfo.memberId}" type="hidden">	
					<input name="addressee" type="hidden">	
					<input name="memberAddr1" type="hidden">	
					<input name="memberAddr2" type="hidden">	
					<input name="memberAddr3" type="hidden">	
					<input name="usePoint" type="hidden">	
				</form>
				
			
		</div> 	<!-- content_area -->
		
		<%@include file="../views/includes/footer.jsp"%>	
	
	
	<script>
		
		$(document).ready(function(){
			setTotalInfo();
			
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
		});
	
		/* 주소 입력란 버튼 동작 */
		function showAddress(className){
			
			$(".addressInfo_input_div").css('display', 'none');
			
			$(".addressInfo_input_div_"+className).css('display', 'block');
			
			$(".address_btn").css('backgroundColor', '#555');
			
			$(".address_btn_"+className).css('backgroundColor', '#3c3838');
			
			$(".addressInfo_input_div").each(function(i,obj){
				$(obj).find(".selectAddress").val("F");
			});
			
			$(".addressInfo_input_div_"+className).find(".selectAddress").val("T");
			
		}
	
		/* 다음 주소 연동 */
		function execution_daum_address(){
		 		console.log("동작");
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
		                 	// 추가해야할 코드
		                    // 주소변수 문자열과 참고항목 문자열 합치기
		                      addr += extraAddr;
		                
		                } else {
		                	addr += ' ';
		                }
		 
		             	// 제거해야할 코드
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                $(".address1_input").val(data.zonecode);
		                $(".address2_input").val(addr);				
		                // 커서를 상세주소 필드로 이동한다.
		                $(".address3_input").attr("readonly", false);
		                $(".address3_input").focus();	 
			            
			            
			        }
			    }).open();  	
			
		}
		

		$(".order_point_input").on("propertychange change keyup paste input", function(){
			
			const maxPoint = parseInt('${memberInfo.point}');
			let inputValue = parseInt($(this).val());
			
			if(inputValue < 0){
				$(this).val(0);
			} else if(inputValue > maxPoint){
				$(this).val(maxPoint);
				
			}
			/* 주문 정보 최신화 */
			setTotalInfo();
		});
		
		$(".order_point_input_btn").on("click", function(){
			
			const maxPoint = parseInt('${memberInfo.point}');
			
			let state = $(this).data("state");
			
			if(state == 'N'){
				
				console.log("N동작");
				$(".order_point_input").val(maxPoint);
				
				$(".order_point_input_btn_Y").css("display", "inline-block");
				$(".order_point_input_btn_N").css("display", "none");
				
			} else if(state == 'Y'){
				
				console.log("Y동작");
				$(".order_point_input").val(0);
				
				$(".order_point_input_btn_Y").css("display", "none");
				$(".order_point_input_btn_N").css("display", "inline-block");
				
			}
			/* 주문 정보 최신화 */
			setTotalInfo();
			
		});
		
		/* 총 주문 정보 세팅 */
		function setTotalInfo(){
			
			let totalPrice = 0;				
			let totalCount = 0;				
			let totalKind = 0;				
			let totalPoint = 0;				
			let deliveryPrice = 0;			
			let usePoint = 0;				
			let finalTotalPrice = 0; 
			
			$(".goods_table_price_td").each(function(index, element){
				
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				totalCount += parseInt($(element).find(".individual_bookCount_input").val());
				totalKind += 1;
				totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
				
			});
			
			if(totalPrice >= 30000){
				deliveryPrice = 0;
			} else if(totalPrice == 0){
				deliveryPrice = 0;
			} else {
				deliveryPrice = 3000;
			}
			
			/* finalTotalPrice = totalPrice + deliveryPrice; */
			
			finalTotalPrice = totalPrice + deliveryPrice;
			
			usePoint = $(".order_point_input").val();
			
			/* finalTotalPrice = totalPrice - usePoint; */
			finalTotalPrice = finalTotalPrice - usePoint;
			
			$(".totalPrice_span").text(totalPrice.toLocaleString());
			$(".goods_kind_div_count").text(totalCount);
			$(".goods_kind_div_kind").text(totalKind);
			$(".totalPoint_span").text(totalPoint.toLocaleString());
			$(".delivery_price_span").text(deliveryPrice.toLocaleString());	
			$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());		
			$(".usePoint_span").text(usePoint.toLocaleString());
		}
		
		$(".order_btn").on("click", function(){
			
			let totalPrice = 0;				
			let totalCount = 0;				
			let totalKind = 0;				
			let totalPoint = 0;				
			let deliveryPrice = 0;			
			let usePoint = 0;				
			let finalTotalPrice = 0; 
			
			$(".goods_table_price_td").each(function(index, element){
				
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				totalCount += parseInt($(element).find(".individual_bookCount_input").val());
				totalKind += 1;
				totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
				
			});
			
			if(totalPrice >= 30000){
				deliveryPrice = 0;
			} else if(totalPrice == 0){
				deliveryPrice = 0;
			} else {
				deliveryPrice = 3000;
			}
			
			
			finalTotalPrice = totalPrice + deliveryPrice; 
			
			//원래 가격
			let oriFinalTotalPrice = totalPrice + deliveryPrice;
			
			usePoint = $(".order_point_input").val();
			
			//포인트를 뺀 가격
			finalTotalPrice = finalTotalPrice - usePoint;
			
			
			/* finalTotalPrice가 충전금액과 포인트를 초과할 경우 */
			let money ='${member.money}';
			money = parseInt(money);
			
			let usingPoint = usePoint;
			usingPoint = parseInt(usingPoint);
			
			// 현재 지불할 수 있는 금액
			let payingMoney = money + usingPoint;
			
			if(oriFinalTotalPrice > payingMoney) {
				alert("금액과 사용할 포인트가 부족합니다.");
				return false;
				
			} else {
				$(".addressInfo_input_div").each(function(i, obj){
					if($(obj).find(".selectAddress").val() === 'T'){
						$("input[name='addressee']").val($(obj).find(".addressee_input").val());
						$("input[name='memberAddr1']").val($(obj).find(".address1_input").val());
						$("input[name='memberAddr2']").val($(obj).find(".address2_input").val());
						$("input[name='memberAddr3']").val($(obj).find(".address3_input").val());
					}
				});
				
				$("input[name='usePoint']").val($(".order_point_input").val());
				
				let form_contents = ''; 
				$(".goods_table_price_td").each(function(index, element){
					let bookId = $(element).find(".individual_bookId_input").val();
					let bookCount = $(element).find(".individual_bookCount_input").val();
					let bookId_input = "<input name='orders[" + index + "].bookId' type='hidden' value='" + bookId + "'>";
					form_contents += bookId_input;
					let bookCount_input = "<input name='orders[" + index + "].bookCount' type='hidden' value='" + bookCount + "'>";
					form_contents += bookCount_input;
				});	
				
				$(".order_form").append(form_contents);	
				
				$(".order_form").submit();		
					
			}
			
		});
		
	</script>
	

</body>
</html>