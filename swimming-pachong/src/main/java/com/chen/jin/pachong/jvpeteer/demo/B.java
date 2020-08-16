package com.chen.jin.pachong.jvpeteer.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.browser.BrowserFetcher;
import com.ruiyun.jvppeteer.core.page.Page;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;

/**
 * 导航至某个页面
 * @author jim
 *
 */
public class B {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		//自动下载，第一次下载后不会再下载
	    BrowserFetcher.downloadIfNotExist(null);
	    ArrayList<String> argList = new ArrayList<>();
	    LaunchOptions options = new LaunchOptionsBuilder().withArgs(argList).withHeadless(false).build();
	    argList.add("--no-sandbox");
	    argList.add("--disable-setuid-sandbox");
	    Browser browser = Puppeteer.launch(options);
	    Browser browser2 = Puppeteer.launch(options);
	    Page page = browser.newPage();
	    page.goTo("https://www.taobao.com/about/");
	    browser.close();
	    System.out.println(page.toString());
	    
	    Page page1 = browser2.newPage();
	    page1.goTo("https://www.taobao.com/about/");
	}
}
