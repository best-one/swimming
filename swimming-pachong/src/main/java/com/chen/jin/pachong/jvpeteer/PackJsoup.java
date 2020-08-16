package com.chen.jin.pachong.jvpeteer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PackJsoup {

	/**
	 * 获取所有的url
	 * @return
	 * @throws IOException 
	 */
	public static void url(List<String> urls) throws IOException {
//		List<String> list = new ArrayList<>();
		BufferedWriter output = new BufferedWriter(new FileWriter("d:/tmp/abc.txt",true));//true,则追加写入text文本
		for(String url : urls) {
			Document doc = Jsoup.connect(url).get();
			///html/body/div[3]/ul/li[1]/a
//			Elements contes =doc.getElementsByClass("div.cover<ul.chapter<li<a");
			Elements contes =doc.select("ul.chapter > li > a");
			for(Element ele : contes) {
//				String title = ele.text();
				String content = getContent(ele.text(),ele.absUrl("href"));
				output.write(content);
//				break;
//				System.out.println(ele.text()+"----"+ele.absUrl("href"));
			}
//			break;
			
			
		}
	    output.flush();    
	    output.close();
//		return list;
	}
	
	public static String getContent(String title,String url) throws IOException {
		StringBuilder sb = new StringBuilder(title);
		Document doc = Jsoup.connect(url).get();
		Elements pages =doc.select("#nr > center:first-of-type");
		Elements content =doc.select("#nr > #nr1");
		String pageNumsText = pages.text();
		String total = pageNumsText.substring(pageNumsText.indexOf("/")+1,pageNumsText.indexOf(")"));
		sb.append(System.lineSeparator());
		sb.append(WebFormatter.html2text(content.html()));
		int  pageEnd = Integer.valueOf(total);
		
		for(int i=2;i<=pageEnd;i++) {
			String newUrl = "";
			if(i>1) {
				newUrl = url.substring(0,url.lastIndexOf("."))+"_"+i+url.substring(url.lastIndexOf("."));
			}
			Document docs = Jsoup.connect(newUrl).get();
			Elements content_ = docs.select("#nr > #nr1");
			sb.append(System.lineSeparator());
			sb.append(WebFormatter.html2text(content_.html()));
		}
//		System.out.println("=========head");
//		System.out.println(pages.text());
//		System.out.println("================body");
//		System.out.println(content.text());
//		System.out.println("=========foot");
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		//http://m.7zxs.cc/wapbook-30474/
		//http://m.7zxs.cc/wapbook-30474_2/
		List<String> urls = Arrays.asList("http://m.7zxs.cc/wapbook-30474/","http://m.7zxs.cc/wapbook-30474_2/");
		url(urls);
		
//		String pageNumsText= "第(1/3)页";
//		System.out.println( pageNumsText.substring(pageNumsText.indexOf("/")+1,pageNumsText.indexOf(")")));
	}
}
