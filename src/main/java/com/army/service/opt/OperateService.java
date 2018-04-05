package com.army.service.opt;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.vo.ImgsHisInfo;
import com.army.vo.TVListInfo;

public interface OperateService {

	JSONArray findOpt(HttpServletRequest request,String tip)throws Exception;
	
	JSONArray findAllCount(HttpServletRequest request)throws Exception;//获取新闻 ,音乐,视频,通知等状态为有效的数量
	
	JSONArray findAllImgs(ImgsHisInfo img)throws Exception;   //图片
	
	JSONArray updateImgs(ImgsHisInfo img)throws Exception;
	
	JSONArray insertImgs(ImgsHisInfo img)throws Exception;

	JSONObject loadImg(ImgsHisInfo img) throws Exception;

	JSONObject deleteImg(ImgsHisInfo imgs) throws Exception;
	
}
