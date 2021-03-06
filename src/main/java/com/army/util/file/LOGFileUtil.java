package com.army.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class LOGFileUtil {

	private static final Logger log = LoggerFactory.getLogger(LOGFileUtil.class);
	private static final List<String> srcs = new ArrayList<String>();
	
	static {
		srcs.add("log-debug.log");
		srcs.add("log-dao-debug.log");
		srcs.add("log-info.log");
		srcs.add("log-impl-info.log");
		srcs.add("log-error.log");
		srcs.add("log-FindNews-new.log");
		srcs.add("log-warn.log");
	}

	public static JSONArray getLogFile(String type, String src, String date) {

		if ("delete".equals(type)) {

			deleteSrc(src);
			log.info("删除文件:" + src);
			return null;
		}

		File f = new File("/root/app/log");
		File[] files = f.listFiles();
		JSONObject obj = null;
		JSONArray arr = new JSONArray();
		Date d = new Date();
		if (date.indexOf("-") == -1) {
			d.setTime(Long.valueOf(date));
			date = new SimpleDateFormat("yyyy-MM-dd").format(d);
		}
		if (date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))) {
			for (File file : files) {
				if (file.getName().indexOf(date) != -1 || srcs.contains(file.getName())) {
					obj = new JSONObject();
					obj.put("fileName", file.getName());
					obj.put("fileSize", (file.length() / 1024.00 / 1024));
					d.setTime(file.lastModified());
					obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(d));
					obj.put("fileContent", readFile(file.getName()));
					arr.add(obj);
				}
			}
		} else {
			for (File file : files) {
				if (file.getName().indexOf(date) != -1) {
					obj = new JSONObject();
					obj.put("fileName", file.getName());
					obj.put("fileSize", (file.length() / 1024.00 / 1024));
					d.setTime(file.lastModified());
					obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd").format(d));
					obj.put("fileContent", readFile(file.getName()));
					arr.add(obj);
				}
			}
		}
		return arr;
	}

	private static void deleteSrc(String src) {

		File f = new File("/root/app/log" + File.separator + src);

		if (f.exists()) {
			f.delete();
		}

	}

	private static String readFile(String string) {
		File f = new File("/root/app/log" + File.separator + string);
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String line = "";
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			while ((line = br.readLine()) != null) {
				if (line.indexOf("===") == -1) {
					continue;
				}
				sb.append(Pattern.compile("<[^>]+>", Pattern.CASE_INSENSITIVE).matcher(line).replaceAll("")
						+ "<br/>\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("log文件错误：" + e.getStackTrace());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
}
