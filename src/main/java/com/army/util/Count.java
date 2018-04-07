package com.army.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 统计流量
 * 
 * @author Administrator
 *
 */
@Component
public class Count extends Thread {

	private static final Logger log = LoggerFactory.getLogger(Count.class);

	private static long TOPCOUNT = 0; // 头条的数量

	private static long OTHERCOUNT = 0; // 天下杂谈访问数量

	private static long MUSICCOUNT = 0; // 音乐点击量

	private static long VEDIOCOUNT = 0; // 视频点击量

	// 当天的点击量
	private static long TODAYTOPCOUNT = 0; // 头条的数量

	private static long TODAYOTHERCOUNT = 0; // 天下杂谈访问数量

	private static long TODAYMUSICCOUNT = 0; // 音乐点击量

	private static long TODAYVEDIOCOUNT = 0; // 视频点击量

	@Override
	public synchronized void run() {
		int m = 0;
		while (true) {
			m = new Random().nextInt(100);
			if (m > 90) {
				addVedio();
			} else if (m <= 90 && m > 75) {
				addMusic();
			} else if (m <= 75 && m > 40) {
				addTop();
			} else {
				addOther();
			}
			try {
				Thread.sleep(new Random().nextInt(10000) + 2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void addTop() {
		TOPCOUNT += 1;
		TODAYTOPCOUNT += 1;
	}

	public static synchronized void addOther() {
		OTHERCOUNT += 1;
		TODAYOTHERCOUNT += 1;
	}

	public static synchronized void addMusic() {
		MUSICCOUNT += 1;
		TODAYMUSICCOUNT += 1;
	}

	public static synchronized void addVedio() {
		VEDIOCOUNT += 1;
		TODAYVEDIOCOUNT += 1;
	}

	public void setCount(long TOPCOUNT, long OTHERCOUNT, long MUSICCOUNT, long VEDIOCOUNT) {

		this.TOPCOUNT = TOPCOUNT;
		this.OTHERCOUNT = OTHERCOUNT;
		this.MUSICCOUNT = MUSICCOUNT;
		this.VEDIOCOUNT = VEDIOCOUNT;

	}

	@Scheduled(cron = "0 0 00 * * ?")
	// @Scheduled(cron = "0/5 * * * * ? ")
	public void clearn() {
		FileWriter fw = null;
		try {

			File topFile = new File("D:" + java.io.File.separator + "log.txt");

			StringBuffer sb = new StringBuffer();
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			sb.append(date + "<br/>\r\n");
			sb.append("头条访问量：" + TODAYTOPCOUNT + "<br/>\r\n");
			sb.append("天下杂谈问量：" + TODAYOTHERCOUNT + "<br/>\r\n");
			sb.append("音乐访问量：" + TODAYMUSICCOUNT + "<br/>\r\n");
			sb.append("视频访问量：" + TODAYVEDIOCOUNT + ",<br/>\r\n");
			fw = new FileWriter(topFile, true);
			fw.write(sb.toString());
			fw.flush();
			log.info("刷新当天访问量:" + sb.toString());
			log.info("凌晨十二点，重置当天访问量成功,当前时间:" + new Date().toLocaleString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("凌晨十二点，重置当天访问量失败,当前时间:" + new Date().toLocaleString());
		} finally {

			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			TODAYTOPCOUNT = 0;
			TODAYOTHERCOUNT = 0;
			TODAYMUSICCOUNT = 0;
			TODAYVEDIOCOUNT = 0;

		}
	}

	// 将数据写入磁盘中
	@Scheduled(cron = "0/260 * *  * * ? ") // 每1.5分钟执行执行一次
	public static void writer() {

		File topFile = new File("D:" + java.io.File.separator + "count.txt");

		StringBuffer sb = new StringBuffer();

		sb.append("头条访问量：" + TOPCOUNT + "\r\n");
		sb.append("天下杂谈问量：" + OTHERCOUNT + "\r\n");
		sb.append("音乐访问量：" + MUSICCOUNT + "\r\n");
		sb.append("视频访问量：" + VEDIOCOUNT + "\r\n");

		FileWriter fw = null;

		try {
			fw = new FileWriter(topFile);
			fw.write(sb.toString());
			fw.flush();
		} catch (Exception e) {
			log.info("刷新访问数量失败 ：" + e.getMessage());
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// public static void main(String[] args) {
	// System.out.println(getCount());
	// }
	public static String getToday() {
		StringBuffer sb = new StringBuffer();

		sb.append("头条访问量：" + TODAYTOPCOUNT + "\r\n");
		sb.append("天下杂谈问量：" + TODAYOTHERCOUNT + "\r\n");
		sb.append("音乐访问量：" + TODAYMUSICCOUNT + "\r\n");
		sb.append("视频访问量：" + TODAYVEDIOCOUNT + "\r\n");

		return sb.toString();
	}

	public static String getCount() {

		File f = new File("D:" + File.separator + "count.txt");
		if (!f.exists()) {
			return null;
		}
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String line = "";
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");

			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
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

		return null;
	}

	public static String getHisCount() {

		File f = new File("D:" + File.separator + "log.txt");
		double kb = f.length() / 1024.00;
		FileReader fr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		sb.append(kb + "kkbb");
		String line = "";
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
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

		return null;
	}
}
