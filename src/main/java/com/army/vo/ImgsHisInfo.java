package com.army.vo;

import java.io.Serializable;

public class ImgsHisInfo extends BaseInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long imgsId;
	
	private String imgsHisSrc;
	
	private String imgsNewSrc;

	public Long getImgsId() {
		return imgsId;
	}

	public void setImgsId(Long imgsId) {
		this.imgsId = imgsId;
	}

	public String getImgsHisSrc() {
		return imgsHisSrc;
	}

	public void setImgsHisSrc(String imgsHisSrc) {
		this.imgsHisSrc = imgsHisSrc;
	}

	public String getImgsNewSrc() {
		return imgsNewSrc;
	}

	public void setImgsNewSrc(String imgsNewSrc) {
		this.imgsNewSrc = imgsNewSrc;
	}
	
	
	

}
