package com.kim.bloom.model;

import java.sql.Date;

public class DesignerVO {
	
	private int designerId;
	
	private String designerName;
	
	private String designerPhone;
	
	

	private Date regDate;
	
	private Date updateDate;

	public int getDesignerId() {
		return designerId;
	}

	public void setDesignerId(int designerId) {
		this.designerId = designerId;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	
	public String getDesignerPhone() {
		return designerPhone;
	}

	public void setDesignerPhone(String designerPhone) {
		this.designerPhone = designerPhone;
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
	
	
}
