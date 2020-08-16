package com.chen.jin.pachong.jvpeteer;

import com.ruiyun.jvppeteer.core.Puppeteer;
import com.ruiyun.jvppeteer.options.LaunchOptions;
import com.ruiyun.jvppeteer.options.LaunchOptionsBuilder;
import com.ruiyun.jvppeteer.core.browser.Browser;
import com.ruiyun.jvppeteer.core.page.Page;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PageContentExample {

	public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
		//获取一个utf8格式的chrome浏览器路径
		String path = new String(
				"C:\\Users\\jim\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe".getBytes(),
				"UTF-8");

//       String  path ="D:\\develop\\project\\toString\\chrome-win\\chrome.exe";
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add("--no-sandbox");
		arrayList.add("--disable-setuid-sandbox");
		LaunchOptions options = new LaunchOptionsBuilder().withArgs(arrayList).withHeadless(false).withExecutablePath(path)
				.build();
//		BrowserOptions options = new BrowserOptions();
//		options.setArgs(arrayList);
		
		Browser browser = Puppeteer.launch(options);
		Page page = browser.newPage();
		page.goTo("http://m.7zxs.cc/wapbook/30474_8905836_4.html");
		String content = page.content();
		FileOutputStream fout = new FileOutputStream("D:\\tmp\\ab.html");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fout));
		bw.write(content);
		bw.flush();
		bw.close();
		fout.close();
		System.out.println("=======================content==============" + content);
		browser.close();
	}
}
