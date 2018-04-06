package com.army.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@SuppressWarnings("unused") // 抑制没被使用过的代码的警告
@Component
public class TimerTask implements CommandLineRunner {

	private final static Logger log = LoggerFactory.getLogger(TimerTask.class);

	private static final int M5 = 90 * 1000;

	private void start() {

		String record = Count.getCount();
		if (record != null) {

			String[] arr = record.split("\r\n");
			long[] count = new long[arr.length];
			for (int i = 0; i < arr.length; i++) {
				count[i] = Long.valueOf(arr[i].split("：")[1]);
			}

			new Count().setCount(count[0], count[1], count[2], count[3]);
		}

	}

	public static void exec() {

		String url = "http://www.bianyl.cn:8080/data/findAllReptileNews";
		String url1 = "http://www.bianyl.cn:8080/data/findAllNews";
		String url2 = "http://www.bianyl.cn:8080/data/findAllMusic";
		String url3 = "http://www.bianyl.cn:8080/data/findAllMoive";
		HttpUtil h1 = null;
		HttpUtil h2 = null;
		HttpUtil h3 = null;
		HttpUtil h4 = null;

		h1 = new HttpUtil(url);
		h2 = new HttpUtil(url1);
		h3 = new HttpUtil(url2);
		h4 = new HttpUtil(url3);

		h1.start();
		h2.start();
		h3.start();
		h4.start();

	}

	@Override
	public void run(String... arg0) throws Exception {
		log.info("start.............");
		start();
		exec();
		// start();
	}

}
