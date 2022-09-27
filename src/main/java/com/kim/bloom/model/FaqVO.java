package com.kim.bloom.model;

import java.util.Date;

import lombok.Data;

@Data
public class FaqVO {
	
	private int fno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
	
}
