package com.chen.jin.pachong.jvpeteer.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;

/**
 * 启动浏览器
 * @author jim
 *
 */
public class A {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		// 设置基本的启动配置,这里选择了‘有头’模式启动
		ArrayList<String> argList = new ArrayList<>();
		// 自动下载，第一次下载后不会再下载
		BrowserFetcher.downloadIfNotExist(null);
		LaunchOptions options = new LaunchOptionsBuilder().withArgs(argList).withHeadless(false).build();
		argList.add("--no-sandbox");
		argList.add("--disable-setuid-sandbox");
		Puppeteer.launch(options);
	}
}
