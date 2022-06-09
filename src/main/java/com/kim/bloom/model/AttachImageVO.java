package com.kim.bloom.model;

public class AttachImageVO {
	
	/* 경로 */
	private String uploadPath;
	
	/* UUID(식별자,일련번호) */
	private String uuid;
	
	/* 파일 이름 */
	private String fileName;
	
	/* 상품 ID */
	private int bookId;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	
	
	
}
