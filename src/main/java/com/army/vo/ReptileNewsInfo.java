package com.army.vo;

import java.io.Serializable;

public class ReptileNewsInfo extends BaseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long reptileId;
	
	private String reptileTitle;
	
	private String reptileContent;
	
	private String pretileImgSrc;
	
	private String reptileTime;
	
	private String reptileSource;
	

	public String getReptileSource() {
		return reptileSource;
	}

	public void setReptileSource(String reptileSource) {
		this.reptileSource = reptileSource;
	}

	public Long getReptileId() {
		return reptileId;
	}

	public void setReptileId(Long reptileId) {
		this.reptileId = reptileId;
	}

	public String getReptileTitle() {
		return reptileTitle;
	}

	public void setReptileTitle(String reptileTitle) {
		this.reptileTitle = reptileTitle;
	}

	public String getReptileContent() {
		return reptileContent;
	}

	public void setReptileContent(String reptileContent) {
		this.reptileContent = reptileContent;
	}

	public String getPretileImgSrc() {
		return pretileImgSrc;
	}

	public void setPretileImgSrc(String pretileImgSrc) {
		this.pretileImgSrc = pretileImgSrc;
	}

	public String getReptileTime() {
		return reptileTime;
	}

	public void setReptileTime(String reptileTime) {
		this.reptileTime = reptileTime;
	}
	
	
	

}
