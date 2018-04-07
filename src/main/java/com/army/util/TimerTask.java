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

		Count h1 = new Count();
		Count h2 = new Count();
		Count h3 = new Count();
		Count h4 = new Count();

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
	}

}
