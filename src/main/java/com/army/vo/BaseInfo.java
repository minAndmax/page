package com.army.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class BaseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String createName;
	
	private String createTime;
	
	private String updateTime;
	
	private String valid;
	
	private JSONObject obj;
	
	private int page;  //当前页
	
	private int size;  //每页多少条
	
	private int totalPages;//总页数
	
	private long count;
	
	private String showImg;
	
	private List<ReptileNewsInfo> pretiles;
	
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<ReptileNewsInfo> getPretiles() {
		return pretiles;
	}

	public void setPretiles(List<ReptileNewsInfo> pretiles) {
		this.pretiles = pretiles;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public JSONObject getObj() {
		return obj;
	}

	public void setObj(JSONObject obj) {
		this.obj = obj;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
	
	
	

}
