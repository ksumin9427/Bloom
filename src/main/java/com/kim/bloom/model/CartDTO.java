package com.kim.bloom.model;

import java.util.List;

public class CartDTO {
	
	private int cartId;
	    
	private String memberId;
	    
	private int bookId;
	    
	private int bookCount;
	    
	private String bookName;
	    
	private int bookPrice;
	    
	private double bookDiscount;
	    
	/* 할인을 적용한 판매 가격 */
	private int salePrice;
	    
	/* 총 가격 = 판매 가격 X 수량  */
	private int totalPrice;
	 
	private int point;
	
	private int totalPoint;
	 
	private List<AttachImageVO> imageList;
	 
	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	public int getPoint() {
		return point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public double getBookDiscount() {
		return bookDiscount;
	}

	public void setBookDiscount(double bookDiscount) {
		this.bookDiscount = bookDiscount;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
	@Override
	public String toString() {
		return "CartDTO [cartId=" + cartId + ", memberId=" + memberId + ", bookId=" + bookId + ", bookCount="
				+ bookCount + ", bookName=" + bookName + ", bookPrice=" + bookPrice + ", bookDiscount=" + bookDiscount
				+ ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + ", point=" + point +", totalPoint=" +
				totalPoint+ ", imageList="+ imageList + "]";
	}
	
	public void initSaleTotal() {
		this.salePrice = (int) (this.bookPrice * (1 - this.bookDiscount));
		this.totalPrice = this.salePrice * this.bookCount;
		this.point = (int) (Math.floor(this.salePrice * 0.05));
		this.totalPoint = this.point * this.bookCount;
	}
}
