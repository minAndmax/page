package com.army.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.army.dao.NewsMapper;
import com.army.dao.OperateMapper;
import com.army.service.news.impl.NewsServiceImpl;
import com.army.vo.ImgsHisInfo;
import com.army.vo.NewsInfo;
import com.army.vo.OperateInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 获取急速新闻
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("deprecation") // 抑制过期方法警告
@Component
public class Get {
	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);
	public static final String APPKEY = "d603840915de0140";// 你的appkey
	public static final String URL = "http://api.jisuapi.com/news/get";
	public static final String channel = "头条";// utf8 新闻频道(头条,财经,体育,娱乐,军事,教育,科技,NBA,股票,星座,女性,健康,育儿)
	public static final int num = 5;// 数量 默认10，最大40
	public static int n = 0;

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private OperateMapper operateMapper;

	@Scheduled(cron = "0 0 6,10,14,17,19,21 * * ?") // 每天8点18点执行执行一次
	public void getToken() {
		try {
			newGet();
			log.info("执行极速数据拉取，当前时间" + new Date().toLocaleString() + ",n的值n=" + n);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//
//	 public static void main(String[] args) {
//	 Get g = new Get();
//	 System.out.println(g);
//	 try {
//	 g.newGet();
//	 } catch (Exception e) {
//	 e.printStackTrace();
//	 }
//	 }

	@Scheduled(cron = "0 0 00 * * ?")
	public void clearn() {
		try {
			n = 0;
			log.info("凌晨十二点，重置n:" + n + ",当前时间:" + new Date().toLocaleString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void newGet() throws Exception {
		String curtTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String result = null;
		String pic = "";
		String url = URL + "?channel=" + URLEncoder.encode(channel, "utf-8") + "&num=" + num + "&appkey=" + APPKEY
				+ "&start=" + n;

		try {
			result = HttpUtil.sendGet(url, "utf-8");
			JSONObject json = JSONObject.fromObject(result);
			if (json.getInt("status") != 0) {
				System.out.println(json.getString("msg"));
			} else {
				JSONObject resultarr = (JSONObject) json.opt("result");
				String channel = resultarr.getString("channel");
				String num = resultarr.getString("num");
				System.out.println(channel + " " + num);
				JSONArray list = resultarr.optJSONArray("list");
				NewsInfo news = null;
				String ss = "";
				ImgsHisInfo img = null;
				for (int i = 0; i < list.size(); i++) {
					news = new NewsInfo();
					JSONObject obj = (JSONObject) list.opt(i);
					if (obj.getString("time").indexOf(curtTime) == -1) {
						continue;
					}
					// String title = obj.getString("title");
					// String time = obj.getString("time");
					// String src = obj.getString("src");
					// String category = obj.getString("category");
					// String pic = obj.getString("pic");
					// String content = obj.getString("content");
					news.setCreateTime(obj.getString("time"));
					news.setNewName(obj.getString("title"));
					pic = obj.getString("pic");
					// System.out.println(obj.getString("pic"));
					if (pic.lastIndexOf("/") != -1 && pic.lastIndexOf(".") != -1) {
						img = new ImgsHisInfo();
						ss = "/upload/image/" + pic.substring(pic.lastIndexOf("/") + 1, pic.length());// 保存图片路径
						news.setShowImg(ss);
						news.setNewContent("<p style='text-align: center;'><img src='" + ss + "'/></p>"
								+ obj.getString("content"));
						img.setImgsHisSrc(pic);
						img.setImgsNewSrc("/upload/image/" + pic.substring(pic.lastIndexOf("/") + 1, pic.length()));
//						ImgsHisInfo imfo = operateMapper.findBySrc(img);
//						if (imfo == null) {
//							operateMapper.insertImgs(img);
//						}
					} else {
						news.setNewContent(obj.getString("content"));
					}
					news.setNewImags(pic);
					news.setNewType("极速数据");
					news.setValid(ValidEnum.VALID.getValidStatus());
//					downloadImg(obj.getString("pic")); // 获取的图片写入到文件夹
					news.setNewAuthor("极速数据");
					news.setCreateName("系统");
					news.setNewRemark("系统自动添加数据，当前时间：" + new Date().toLocaleString());
					 System.out.println(pic+"<----->");
					List<NewsInfo> ls = newsMapper.findByImgs(news);
					if(ls.isEmpty()) {
						newsMapper.insertNews(news);
					}
					// String url1 = obj.getString("url");
					// String weburl = obj.getString("weburl");
					// System.out.println( time+ "---" + title);
				}

				log.info("执行极速api,添加极速新闻成功,共" + list.size() + "条");
				OperateInfo opt = new OperateInfo();
				opt.setOptType("system");
				opt.setOptName("系统记录");
				opt.setOptUserId((long) 1);
				opt.setOptRemark("执行极速数据拉取，当前时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",添加极速新闻成功,共" + list.size() + "条,n=" + n);
				operateMapper.inserObject(opt);
				n += 6;

			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("程序异常" + e);
		}
	}

//	public static void downloadImg(String ImgUrl) throws IOException {
//		if(ImgUrl == null) {
//			return;
//		}
//		if(ImgUrl.indexOf("http://") == -1) {
//			ImgUrl = "http://" + ImgUrl;
//		}
//		// System.out.println("ImgUrl：" + ImgUrl.substring(ImgUrl.lastIndexOf("/") + 1,
//		// ImgUrl.lastIndexOf(".")));
//		// 下载图片
//		if (!ImgUrl.endsWith(".png") || !ImgUrl.endsWith(".jpg") || !ImgUrl.endsWith(".jif")
//				|| !ImgUrl.endsWith(".jpeg")) {
//		String str = "D:" + File.separator + "/upload/image/";// 保存下载图片文件夹
//		String ss = str + ImgUrl.substring(ImgUrl.lastIndexOf("/") + 1, ImgUrl.length());// 保存图片路径
//		URL url = new URL(ImgUrl); // 构造URL
//		URLConnection uc = url.openConnection(); // 打开连接
//		uc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//		uc.setReadTimeout(50000);
//		InputStream is = uc.getInputStream(); // 输入流
//		File file = new File(ss); // 创建文件
//		if (!new File(str).exists()) {
//			new File(str).mkdirs();
//		}
//		if (file.exists()) {
//			file.delete();
//		}
//		FileOutputStream out = new FileOutputStream(file); // 输出的文件流
//		byte[] bs = new byte[1024];
//		// 读取到的数据长度
//		int len;
//		// 开始读取
//		while ((len = is.read(bs)) != -1) {
//			out.write(bs, 0, len);
//			out.flush();
//		}
//
//		// 完毕，关闭所有链接
//		out.close();
//		is.close();
//		}
//	}

}