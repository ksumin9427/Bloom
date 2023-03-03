package com.kim.bloom.model;

import java.util.List;

public class OrderPageItemDTO {
	
	private int bookId;
	
	private int bookCount;
	
	private String bookName;
	
	private int bookPrice;
	
	private double bookDiscount;
	
	private int salePrice;
	
	private int totalPrice;
	
	private int point;
	
	private int totalPoint;
	
	private List<AttachImageVO>	imageList; 
	
	
	public void initSaleTotal() {
		this.salePrice = (int) (this.bookPrice * (1-this.bookDiscount));
		this.totalPrice = this.salePrice*this.bookCount;
		this.point = (int)(Math.floor(this.salePrice*0.01));
		this.totalPoint =this.point * this.bookCount;
	}
	
	

	public List<AttachImageVO> getImageList() {
		return imageList;
	}



	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
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

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
	
	@Override
	public String toString() {
		return "OrderPageItemDTO [bookId=" + bookId + ", bookCount=" + bookCount + ", bookName=" + bookName
				+ ", bookPrice=" + bookPrice + ", bookDiscount=" + bookDiscount + ", salePrice=" + salePrice
				+ ", totalPrice=" + totalPrice + ", point=" + point + ", totalPoint=" + totalPoint + ", imageList="
				+ imageList + "]";
	}
	
	
}
