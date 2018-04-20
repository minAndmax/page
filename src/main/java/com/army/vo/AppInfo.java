package com.army.vo;

import java.io.Serializable;

public class AppInfo extends BaseInfo  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long appId;
	
	private String appName;
	
	private String appRemark;
	
	private String appSrc;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppRemark() {
		return appRemark;
	}

	public void setAppRemark(String appRemark) {
		this.appRemark = appRemark;
	}

	public String getAppSrc() {
		return appSrc;
	}

	public void setAppSrc(String appSrc) {
		this.appSrc = appSrc;
	}
	
	
	
}
