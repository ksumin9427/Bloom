package com.kim.bloom.model;

import java.util.Date;

import lombok.Data;


public class FaqVO {
	
	private int fno;
	private String cateCode;
	private String cateParent;
	private String cateName;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	/* private int tier; */
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
		
	}
	
	public String getCateParent() {
		return cateParent;
	}
	public void setCateParent(String cateParent) {
		this.cateParent = cateParent;
	}
	@Override
	public String toString() {
		return "FaqVO [fno=" + fno + ", cateCode=" + cateCode + ", cateName=" + cateName + ", title=" + title
				+ ", content=" + content + ", writer=" + writer + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", cateParent=" + cateParent + "]";
	}
	
}
