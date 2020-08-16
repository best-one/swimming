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
import com.ruiyun.jvppeteer.options.PDFOptions;

/**
 * 生成页面的 PDF
 * @author jim
 *
 */
public class C {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		// 自动下载，第一次下载后不会再下载
		BrowserFetcher.downloadIfNotExist(null);
		ArrayList<String> arrayList = new ArrayList<>();
		// 生成pdf必须在无厘头模式下才能生效
		LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(true).build();
		arrayList.add("--no-sandbox");
		arrayList.add("--disable-setuid-sandbox");
		Browser browser = Puppeteer.launch(options);
		Page page = browser.newPage();
		page.goTo("https://www.baidu.com/?tn=98012088_10_dg&ch=3");
		PDFOptions pdfOptions = new PDFOptions();
		pdfOptions.setPath("test.pdf");
		page.pdf(pdfOptions);
		page.close();
		browser.close();
	}
}
