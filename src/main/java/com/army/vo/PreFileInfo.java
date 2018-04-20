package com.army.vo;

import java.io.Serializable;
import java.util.List;

public class PreFileInfo extends BaseInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long preFileId;
	
	private String preFileName;
	
	private List<TVListInfo> tvlist;
	
	public List<TVListInfo> getTvlist() {
		return tvlist;
	}

	public void setTvlist(List<TVListInfo> tvlist) {
		this.tvlist = tvlist;
	}

	public long getPreFileId() {
		return preFileId;
	}

	public void setPreFileId(long preFileId) {
		this.preFileId = preFileId;
	}

	public String getPreFileName() {
		return preFileName;
	}

	public void setPreFileName(String preFileName) {
		this.preFileName = preFileName;
	}

}
