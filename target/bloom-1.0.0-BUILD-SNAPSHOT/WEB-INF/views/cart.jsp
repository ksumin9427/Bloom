<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--fmt 태그를 사용하기 위해서  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/resources/css/cart.css?ver21">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>

	<%@include file="../views/includes/header.jsp"%>
			
			<div class="content_area">
				
				<!-- <div class="content_subject"><span>장바구니</span></div> -->
				<!-- 장바구니 리스트 -->
				<div class="content_middle_section">
				<!-- 장바구니 가격 합계 -->
				<!-- cartInfo -->
				<div class="content_totalCount_section">
				
					<div class="all_check_input_div">
						<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_check_span">전체선택</span>
					</div>
					
					<table class="subject_table">
						<caption>표 제목 부분</caption>
						<tbody>
							<tr>
								<th class="td_width_1"></th>
								<th class="td_width_2"></th>
								<th class="td_width_3">상품명</th>
								<th class="td_width_4">가격</th>
								<th class="td_width_4">수량</th>
								<th class="td_width_4">합계</th>
								<th class="td_width_4">삭제</th>
							</tr>
						</tbody>
					</table>
					
					<table class="cart_table">
						<caption>표 내용 부분</caption>
						<tbody>
							<c:forEach items="${cartInfo}" var="ci">
								<tr>
									<td class="td_width_1 cart_info_td" >
										<input type="checkbox" class="individual_cart_checkbox input_size_20" checked="checked">
										<input type="hidden" class="individual_bookPrice_input" value="${ci.bookPrice}">
										<input type="hidden" class="individual_salePrice_input" value="${ci.salePrice}">
										<input type="hidden" class="individual_bookCount_input" value="${ci.bookCount}">
										<input type="hidden" class="individual_totalPrice_input" value="${ci.salePrice * ci.bookCount}">
										<input type="hidden" class="individual_point_input" value="${ci.point}">
										<input type="hidden" class="individual_totalPoint_input" value="${ci.totalPoint}">
										<input type="hidden" class="individual_bookId_input" value="${ci.bookId}">
									</td>
									
									<td class="td_width_2">
										<div class="image_wrap" data-bookid="${ci.imageList[0].bookId}" data-path="${ci.imageList[0].uploadPath}" data-uuid="${ci.imageList[0].uuid}" data-filename="${ci.imageList[0].fileName}">
											<img>
										</div>
									</td>
									
									<td class="td_width_3">${ci.bookName}</td>
									
									<td class="td_width_4 price_td">
										<del>정가 : <fmt:formatNumber value="${ci.bookPrice}" pattern="#,### 원" /></del><br>
										판매가 : <span class="red_color"><fmt:formatNumber value="${ci.salePrice}" pattern="#,### 원" /></span><br>
										마일리지 : <span class="green_color"><fmt:formatNumber value="${ci.point}" pattern="#,###" /></span>
									</td>
									
									<td class="td_width_4 table_text_align_center">
										<div class="table_text_align_center quantity_div">
											<input type="text" value="${ci.bookCount}" class="quantity_input">	
											<button class="quantity_btn plus_btn">+</button>
											<button class="quantity_btn minus_btn">-</button>
										</div>
										<a class="quantity_modify_btn" data-cartId="${ci.cartId}">변경</a>
									</td>
									
									<td class="td_width_4 table_text_align_center">
										<fmt:formatNumber value="${ci.salePrice * ci.bookCount}" pattern="#,### 원" />
									</td>
									
									<td class="td_width_4 table_text_align_center">
										<button class="delete_btn" data-cartId="${ci.cartId}">삭제</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table class="list_table">
					</table>
				</div>
				<!-- 가격 종합 -->
				<div class="content_total_section">
					<div class="total_wrap">
						<table>
							<tr>
								<td>
									<table>
										<tr>
											<td>총 상품 가격</td>
											
											<td>
												<span class="totalPrice_span">70000</span> 원
											</td>
										</tr>
										
										<tr>
											<td>배송비</td>
											<td>
												<span class="delivery_price">3000</span>원
											</td>
										</tr>	
																		
										<tr>
											<td>총 주문 상품수</td>
											<td><span class="totalKind_span"></span>종 <span class="totalCount_span"></span>권</td>
										</tr>
									</table>
								</td>
								<td>
									<table>
										<tr>
											<td></td>
											<td></td>
										</tr>
									</table>							
								</td>
							</tr>
						</table>
						<div class="boundary_div">구분선</div>
						<table>
							<tr>
								<td>
									<table>
										<tbody>
											<tr>
												<td>
													<strong>총 결제 예상 금액</strong>
												</td>
												<td>
													<span class="finalTotalPrice_span">70000</span> 원
												</td>
											</tr>
										</tbody>
									</table>
								</td>
								<td>
									<table>
										<tbody>
											<tr>
												<td>
													<strong>총 적립 예상 마일리지</strong>
												
												</td>
												<td>
													<span class="totalPoint_span">70000</span> 원
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- 구매 버튼 영역 -->
				<div class="content_btn_section">
					<a class="order_btn">주문하기</a>
				</div>
			</div><!-- content_middle_section -->
				
				<!-- 수량 조절 form -->
				<form action="/cart/update" method="post" class="quantity_update_form">
					<input type="hidden" name="cartId" class="update_cartId">
					<input type="hidden" name="bookCount" class="update_bookCount">
					<input type="hidden" name="memberId" value="${member.memberId}">
				</form>
				
				<!-- 삭제 form -->
				<form action="/cart/delete" method="post" class="quantity_delete_form">
					<input type="hidden" name="cartId" class="delete_cartId">
					<input type="hidden" name="memberId" value="${member.memberId}">
				</form>
				
				<form action="/order/${member.memberId}" method="get" class="order_form">
					 
				</form>
			</div><!-- content_area -->
			
			<%@include file="../views/includes/footer.jsp"%>
        
       
	
	<script>
	
	$(document).ready(function(){
	/* 종합 정보 섹션에 정보 삽입 */	
		setTotalInfo();	
	
	$(".image_wrap").each(function(i,obj){
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
	
	$(".individual_cart_checkbox").on("click", function(){
		setTotalInfo($(".cart_info_td"));
	});
	
	$(".all_check_input").on("click", function(){
		if($(".all_check_input").prop("checked")){
			$(".individual_cart_checkbox").attr("checked", true);
		} else {
			$(".individual_cart_checkbox").attr("checked", false);
		}
		setTotalInfo($(".cart_info_td"));
	});
	
	/* 총 주문 정보 세팅 */
	function setTotalInfo(){
		
		let totalPrice = 0;
		let totalCount = 0;
		let totalKind = 0;
		let totalPoint = 0;
		let deliveryPrice = 0;
		let finalTotalPrice = 0;
		
		$(".cart_info_td").each(function(index, element){
				
			if($(element).find(".individual_cart_checkbox").is(":checked") === true){
				// 총 가격
				totalPrice += parseInt($(element).find(".individual_totalPrice_input").val());
				// 총 갯수
				totalCount += parseInt($(element).find(".individual_bookCount_input").val());
				// 총 종류
				totalKind += 1;
				// 총 마일리지
				totalPoint += parseInt($(element).find(".individual_totalPoint_input").val());
			}
							
		});

		
		
		if(totalPrice >= 30000){
			deliveryPrice = 0;
		} else if(totalPrice == 0){
			deliveryPrice = 0;
		} else {
			deliveryPrice = 3000;
		}
		
		finalTotalPrice = totalPrice + deliveryPrice;
		
		// 총 가격
		$(".totalPrice_span").text(totalPrice.toLocaleString());
		// 총 갯수
		$(".totalCount_span").text(totalCount);
		// 총 종류
		$(".totalKind_span").text(totalKind);
		// 총 마일리지
		$(".totalPoint_span").text(totalPoint.toLocaleString());
		// 배송비
		$(".delivery_price").text(deliveryPrice);	
		// 최종 가격(총 가격 + 배송비)
		$(".finalTotalPrice_span").text(finalTotalPrice.toLocaleString());		
	}
	
	$(".plus_btn").on("click", function(){
		let quantity = $(this).parent("div").find("input").val();
		$(this).parent("div").find("input").val(++quantity);
	});
	
	$(".minus_btn").on("click", function(){
		let quantity = $(this).parent("div").find("input").val();
		if(quantity > 1){
			$(this).parent("div").find("input").val(--quantity);
		}
	});
	
	$(".quantity_modify_btn").on("click", function(){
		let cartId = $(this).data("cartid");
		let bookCount = $(this).parent("td").find("input").val();
		$(".update_cartId").val(cartId);
		$(".update_bookCount").val(bookCount);
		$(".quantity_update_form").submit();
	});
	
	$(".delete_btn").on("click", function(e){
		e.preventDefault();
		const cartId = $(this).data("cartid");
		$(".delete_cartId").val(cartId);
		$(".quantity_delete_form").submit();

	}); 
	
	$(".order_btn").on("click", function(){
		let form_contents ='';
		let orderNumber = 0;
		$(".cart_info_td").each(function(index, element){
			
			if($(element).find(".individual_cart_checkbox").is(":checked") === true){
				
				let bookId = $(element).find(".individual_bookId_input").val();
				let bookCount = $(element).find(".individual_bookCount_input").val();
				
				let bookId_input = "<input name='orders["+orderNumber+"].bookId' type='hidden' value='"+bookId+"'>";
				form_contents += bookId_input;
				
				let bookCount_input = "<input name='orders["+orderNumber+"].bookCount' type='hidden' value='"+bookCount+"'>";
				form_contents += bookCount_input;
				
				orderNumber += 1;
			}
			
			$(".order_form").html(form_contents);
			$(".order_form").submit();
			
		});
	});
	
	</script>
	

</body>
</html>