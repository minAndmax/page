package com.army.service.opt.impl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.army.dao.MoiveMapper;
import com.army.dao.MusicMapper;
import com.army.dao.NewsMapper;
import com.army.dao.NoticeMapper;
import com.army.dao.OperateMapper;
import com.army.dao.ReptileMapper;
import com.army.service.opt.OperateService;
import com.army.util.FindNews;
import com.army.util.KeyWord;
import com.army.util.StatusEnum;
import com.army.util.ValidEnum;
import com.army.vo.ImgsHisInfo;
import com.army.vo.MusicInfo;
import com.army.vo.NewsInfo;
import com.army.vo.NoticeInfo;
import com.army.vo.OperateInfo;
import com.army.vo.ReptileNewsInfo;
import com.army.vo.UserMessage;
import com.army.vo.VedioInfo;

@Service("operateService")
public class OperateServiceImpl implements OperateService {

	@Autowired
	private OperateMapper operateMapper;

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private MusicMapper musicMapper;

	@Autowired
	private MoiveMapper moiveMapper;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private ReptileMapper reptileMapper;

	private static final Logger log = LoggerFactory.getLogger(OperateServiceImpl.class);
	
	@Override
//	@Cacheable(value="findOpt")
	public JSONArray findOpt(HttpServletRequest request, String tip) throws Exception {
		
		
		OperateInfo op = new OperateInfo();
		JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		if (!sessionObj.getString("userName").equals("admin")) {
			op.setOptUserId(sessionObj.getLong("userId"));
		}

		if (tip.equals("update")) {

			op.setOptType("update");

		} else if (tip.equals("insert")) {

			op.setOptType("insert");

		} else if (tip.equals("system")) {

			op.setOptType("system");

		}

		List<OperateInfo> isf = operateMapper.findOpt(op);
		JSONArray arr = new JSONArray();
		for (OperateInfo s : isf) {
			arr.add(s);
		}

		return arr;
	}

	@Override
//	@Cacheable(value="findAllCount")
	public JSONArray findAllCount(HttpServletRequest request) throws Exception {

		JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);
		NewsInfo n = new NewsInfo();
		NoticeInfo b = new NoticeInfo();
		MusicInfo c = new MusicInfo();
		VedioInfo v = new VedioInfo();
		ReptileNewsInfo rep = new ReptileNewsInfo();
		if (!sessionObj.getString("userName").equals("admin")) {
			n.setCreateName(sessionObj.getString("userName"));
			b.setCreateName(sessionObj.getString("userName"));
			c.setCreateName(sessionObj.getString("userName"));
			v.setCreateName(sessionObj.getString("userName"));
		}
		n.setValid(ValidEnum.VALID.getValidStatus());
		int newNum = newsMapper.findCount(n);

		b.setValid(ValidEnum.VALID.getValidStatus());
		int noticeNum = noticeMapper.findAllNoticeCount(b);

		c.setValid(ValidEnum.VALID.getValidStatus());
		int musicNum = musicMapper.findMusicCount(c);

		v.setValid(ValidEnum.VALID.getValidStatus());
		int vedioNum = moiveMapper.findVedioCount(v);

		rep.setValid(ValidEnum.VALID.getValidStatus());
		int repNum = reptileMapper.findCount(rep);

		JSONObject obj = new JSONObject();
		obj.put("newNum", newNum);
		obj.put("noticeNum", noticeNum);
		obj.put("musicNum", musicNum);
		obj.put("vedioNum", vedioNum);
		obj.put("repNum", repNum);

		JSONArray arr = new JSONArray();

		arr.add(obj);

		return arr;
	}

	@Override
	@Cacheable(value="findAllImgs")
	public JSONArray findAllImgs(ImgsHisInfo img) throws Exception {

		int pages = operateMapper.findAllImgsCount();

		double db = Math.ceil((double) pages / (double) img.getSize());

		JSONArray arr = new JSONArray();
		List<ImgsHisInfo> nws = operateMapper.findAllImgs(img);
		if (nws.size() != 0) {
			nws.get(0).setTotalPages((int) db);
			nws.get(0).setCount(pages);
		} else {
			ImgsHisInfo ws = new ImgsHisInfo();
			nws.get(0).setCount(0);
			ws.setTotalPages(-1);
			ws.setSize(0);
			nws.add(ws);
		}

		for (ImgsHisInfo info : nws) {
			arr.add(info);
		}
		return arr;
	}

	@Override
	public JSONArray updateImgs(ImgsHisInfo img) throws Exception {

		return null;
	}

	@Override
	public JSONArray insertImgs(ImgsHisInfo img) throws Exception {

		return null;
	}

	@Override
//	@Cacheable(value="loadImg")
	public JSONObject loadImg(ImgsHisInfo img) throws Exception {

		ImgsHisInfo im = operateMapper.findBySrc(img);

		File f = new File("D:" + File.separator + im.getImgsNewSrc());
		if (f.exists()) {
			f.delete();
		}
		new FindNews().downloadImg(im.getImgsHisSrc(), 0, operateMapper);

		JSONObject obj = new JSONObject();
		obj.put("success", StatusEnum.SSUCCESS.getNum());

		return obj;
	}

	@Override
	public JSONObject deleteImg(ImgsHisInfo img) throws Exception {
		ImgsHisInfo im = operateMapper.findBySrc(img);

		File f = new File("D:" + File.separator + im.getImgsNewSrc());
		if (f.exists()) {
			f.delete();
		}
		operateMapper.deleteImg(img);
//		new FindNews().downloadImg(im.getImgsHisSrc(), 0, operateMapper);

		JSONObject obj = new JSONObject();
		obj.put("success", StatusEnum.SSUCCESS.getNum());

		return obj;
	}

	@Override
	public JSONObject submitmsg(UserMessage msg) throws Exception{
		
		operateMapper.userMessage(msg);
		JSONObject obj = new JSONObject();
		obj.put("success", StatusEnum.SSUCCESS.getNum());
		
		return obj;
	}

	@Override
	public JSONArray findAllMsg(UserMessage msg) throws Exception {
		
		if (msg.getCreateName().equals("admin")) {
			msg.setCreateName(null);
			msg.setValid(null);
		}

		int pages = operateMapper.findAllMsgCount(msg);

		double db = Math.ceil((double) pages / (double) msg.getSize());

		JSONArray arry = new JSONArray();

		List<UserMessage> minfo = operateMapper.findAllMsg(msg);
		if (minfo.size() > 0) {
			minfo.get(0).setTotalPages((int) db);
		} else {
			UserMessage f = new UserMessage();
			f.setTotalPages(-1);
			minfo.add(f);
		}
		for (UserMessage m : minfo) {
			arry.add(m);
		}
		return arry;
		
	}

	@Override
	public JSONObject reader(HttpServletRequest request, UserMessage msg) {
		
		JSONObject obj = new JSONObject();

		try {
			operateMapper.reader(msg);

			JSONObject sessionObj = (JSONObject) request.getSession().getAttribute(KeyWord.USERSESSION);

			OperateInfo opt = new OperateInfo();
			opt.setOptType("update");
			opt.setOptUserId(sessionObj.getLong("userId"));
			opt.setOptName("确认反馈");
			StringBuilder sb = new StringBuilder();
			
			sb.append(sessionObj.getString("roleName") + "-" + sessionObj.getString("userName") + "确认了：" + msg.getMsgId() + "反馈");
			opt.setOptRemark(sb.toString());
			
			opt.setTypeId((long)msg.getMsgId());

			operateMapper.inserObject(opt);
			
			obj.put(KeyWord.TIPSTATUS, StatusEnum.SSUCCESS.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.SSUCCESS.getValue());
			
			log.info("用户反馈确认成功[ {} ]" + obj);

		} catch (Exception e) {

			obj.put(KeyWord.TIPSTATUS, StatusEnum.FAIL.getNum());
			obj.put(KeyWord.TIPSTATUSCONTEN, StatusEnum.FAIL.getValue());
			log.info("程序异常，用户反馈确认失败[ {} ]" + e);
		}

		return obj;
	}

	@Override
	public int findAllMsgCount(UserMessage msg) {
		int num = 0;
		try {
			num = operateMapper.findAllMsgCount(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
