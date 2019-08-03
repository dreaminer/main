package com.java.wp.model;

// work의 내용을 담는 model(DTO)
public class Work {
	private String emailId;
	private String workTitle;
	private String work;
	private String workReg;
	private String workDesc;
	private String workStatus;
	private String workTag;
	private String workNum;
	private int workView;
	private int workPrice;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getWorkReg() {
		return workReg;
	}
	public void setWorkReg(String workReg) {
		this.workReg = workReg;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	public String getWorkTag() {
		return workTag;
	}
	public void setWorkTag(String workTag) {
		this.workTag = workTag;
	}
	public String getWorkNum() {
		return workNum;
	}
	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}
	public int getWorkView() {
		return workView;
	}
	public void setWorkView(int workView) {
		this.workView = workView;
	}
	public int getWorkPrice() {
		return workPrice;
	}
	public void setWorkPrice(int workPrice) {
		this.workPrice = workPrice;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	
	
}
