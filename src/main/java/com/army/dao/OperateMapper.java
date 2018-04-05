package com.army.dao;

import java.util.List;

import com.army.vo.ImgsHisInfo;
import com.army.vo.OperateInfo;

public interface OperateMapper {
	
	void inserObject(OperateInfo operate)throws Exception;
	
	List<OperateInfo> findOpt(OperateInfo operate)throws Exception;
	
	List<ImgsHisInfo> findAllImgs(ImgsHisInfo img)throws Exception;   //图片
	
	void updateImgs(ImgsHisInfo img)throws Exception;
	
	void insertImgs(ImgsHisInfo img)throws Exception;
	
	int findAllImgsCount()throws Exception;
	
	ImgsHisInfo findBySrc(ImgsHisInfo img)throws Exception;

	void deleteImg(ImgsHisInfo img)throws Exception;

}
