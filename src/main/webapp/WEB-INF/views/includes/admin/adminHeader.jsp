<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div class="wrapper">
		<div class="wrap">
			<div class="wrap_for_top_gnb_area">
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
								<a class="info" href="/myRoom/myRoomMain">마이룸</a>
							</li>
							<li>
								<a href="/cart/${member.memberId}">장바구니</a>
							</li>
							<li>
								<a href="/faq/faqList">FAQ</a>
							</li>
						</c:if>
						
					</ul>
				</div>
			</div> <!-- class="wrap_for_top_gnb_area" -->
			
			<div class="top_area">
				<div class="logo_area">
					<a href="/main"><img src="/resources/img/Bloom_logo.jpg"></a>
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
                    			<button class="searchBtn">검 색</button>                				
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
		</div> <!-- top_area -->
			
			<div class="wrap_for_navi_bar_area">
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
						<c:forEach  items="${cate2}" var="cate">
								<a href="/search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
						</c:forEach>
					</div>
				</div>
			</div>
			</div><!-- class="wrap_for_navi_bar_area" -->
			
			<div class="admin_wrap">
			
			<div class="admin_navi_wrap">
                
                <div class="admin_navi_menu"><ul>상품</ul></div>
                  <ul>
                      <li>
                         <a href="/admin/goodsEnroll">상품 등록</a>
                      
                         <a href="/admin/goodsManage">상품 관리</a>
                      </li>
                  </ul> 
                    
                <div class="admin_navi_menu"><ul>작가</ul></div>
                  <ul>
                      <li>
                         <a href="/admin/authorEnroll">작가 등록</a>                            
                      
                         <a href="/admin/authorManage">작가 관리</a>     
                      </li>
                  </ul>  
                   
                <div class="admin_navi_menu"><ul>회원</ul></div>
                  <ul>
                      <li>
                         <a href="/admin/memberManage">회원 관리</a>                            
                      </li>
                  </ul>  
                    
		</div><!-- admin_navi_wrap -->
	</div><!-- myRoom_wrap -->	
			
			
		
		


</body>
</html>