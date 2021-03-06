package com.army.service.Moive.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.MoiveMapper;
import com.army.dao.OperateMapper;
import com.army.service.Moive.MoiveService;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.OperateInfo;
import com.army.vo.VedioInfo;

@Service("/MoiveService")
public class MoiveServiceImpl implements MoiveService{

	private final static Logger log = LoggerFactory.getLogger(MoiveServiceImpl.class);
	
	@Autowired
	private OperateMapper operateMapper;
	
	@Autowired
	private MoiveMapper moiveMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject updateMoive(HttpServletRequest request, VedioInfo vinfo)  {
		JSONObject obj = new JSONObject();
		try {
			moiveMapper.updateMoive(vinfo);
			VedioInfo find = moiveMapper.findVedioById(vinfo);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();

			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("修改影视");
			opt.setOptType("update");
			StringBuilder sb = new StringBuilder();
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",修改影视，修改《"+find.getVedioName()+"》");
			sb.append(vinfo.getValid().equals("N") ? ",修改状态为:无效":",修改状态为:有效");
			opt.setOptRemark(sb.toString());
			opt.setTypeId(vinfo.getVedioId());

			operateMapper.inserObject(opt);

			log.info("影视修改成功[ {} ]" + obj);
		} catch (Exception e) {
				obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
				obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
				log.error("程序异常，影视修改失败[ {} ]" + e.getStackTrace());
		}
		return obj;
	}

	@Override
	public JSONArray findAllMoive(VedioInfo vedio) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<VedioInfo> vinfo = moiveMapper.findAllMoive(vedio);
		for(VedioInfo v : vinfo) {
			arry.add(v);
		}
		return arry;
	}

	@Override
	public JSONArray findOneMoiveByName(VedioInfo vedio) throws Exception {
		JSONArray arry = new JSONArray();
		
		List<VedioInfo> vinfo = moiveMapper.findOneMoiveByName(vedio);
		for(VedioInfo v : vinfo){
			arry.add(v);
		}
		return arry;
	}

	@Override
	public JSONArray findAllVedioManeger(VedioInfo vedio) throws Exception {
		
		if(vedio.getCreateName().equals("admin")) {
			vedio.setCreateName(null);
			vedio.setValid(null);
		}
		
		int pages = moiveMapper.findVedioCount(vedio);
		
		double db = Math.ceil((double) pages / (double) vedio.getSize());
		
		JSONArray arry = new JSONArray();
		
		List<VedioInfo> vinfo = moiveMapper.findAllVedioManeger(vedio);
		if(vinfo.size() > 0) {
			vinfo.get(0).setTotalPages((int) db);
		} else {
			VedioInfo o = new VedioInfo();
			o.setTotalPages(-1);
			vinfo.add(o);
		}
		for(VedioInfo v : vinfo){
			arry.add(v);
		}
		return arry;
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public JSONObject insertMoive(HttpServletRequest request,VedioInfo vedio) {
		JSONObject obj = new JSONObject();

		try {
			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
			
			VedioInfo v = moiveMapper.findVedioById(vedio);
			
			if(v == null) {
			
			vedio.setValid(ValidEnum.VALID.getValidStatus());
			vedio.setCreateName(sessionObj.getString("userName"));
			moiveMapper.insertMoive(vedio);
            
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());


			OperateInfo opt = new OperateInfo();
			opt.setOptType("insert");
			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("添加视频");
			opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "添加视频，名字《"+ vedio.getVedioName() + "》,导演:"+vedio.getVedioDirector());
			opt.setTypeId(vedio.getVedioId());

			operateMapper.inserObject(opt);

			log.info("添加视频成功[ {} ]" + obj);
			} 
		} catch (Exception e) {
            e.printStackTrace();
            obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.error("程序异常，添加视频失败[ {} ]" + e.getStackTrace());
		}
		
		return obj;
	}

	@Override
	public JSONObject deleteVedio(HttpServletRequest request,VedioInfo imgs) throws Exception {
		JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		VedioInfo find = moiveMapper.findVedioById(imgs);
		File f = new File("" + File.separator + find.getVedioSrc());
		if(f.exists()) {
			f.delete();
		}
		JSONObject obj = new JSONObject();
		OperateInfo opt = new OperateInfo();
		opt.setOptType("update");
		opt.setOptUserId(sessionObj.getLong("userId"));
		opt.setOptName("删除视频");
		opt.setOptRemark(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + ",删除视频，名字《"+ find.getVedioName() + "》,导演:"+find.getVedioDirector());
		opt.setTypeId(find.getVedioId());
		moiveMapper.deleteVedio(imgs);

		operateMapper.inserObject(opt);
		obj.put("success", StatusEnum.SSUCCESS.getNum());
		return obj;
	}

}
