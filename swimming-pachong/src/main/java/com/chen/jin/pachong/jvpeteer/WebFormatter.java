package com.chen.jin.pachong.jvpeteer;

import java.util.*;

import javax.swing.text.Document;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Do some format on web display.
 *
 * @author Xuefeng
 */
public class WebFormatter {
	public static void main(String[] args) {
		BufferedReader br;

		String HtmlStr = "";
		try {
			br = new BufferedReader(new FileReader("D:\\tmp\\ab.html"));

			String t = null;
			while ((t = br.readLine()) != null) {// 读一行
				HtmlStr = HtmlStr + t;
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// HtmlStr="<div>&nbsp; 三、改进措施</div><div> 1、
		// 多关注优等生，让他们一定要保持稳定。多关注中等生，在一定程度上能够让他们与优等生逐一雌雄。多鼓励下游生！努力使两极分化最小化！</div><div>
		// 2、一如既往抓学风。要做到勤说（每天每时常提醒）、勤问（每天能够与个别学生进行交流）、勤查（每天检查各科作业的完成情况），加大管理力度，让学生能在安静的环境中学习。</div><div>
		// 3、做好学生的思想工作，阐明各校之间竞争的严峻形势，让学生有忧患意识，从而调动学习的积极性。</div><div> 4、
		// 多与各科教师联系，及时了解学生动态，接受科任老师的建议。多与家长交流，形成合力，共同督促学生学习，使其进步。</div><div> 5、
		// 继续进行“一帮一”互助学习帮教活动，让优生带动差生学习，共同取得进步。</div><div> 6、
		// 学生进行深刻的自我反思，对自己的学习提出具体的要求。</div><div> 7、
		// 举行一次学法交流会，促成每个学生形成适合自己的良好学习方法。</div>";
		String str = html2text(HtmlStr);
		System.out.println(str);
	}

	public static String html2text(String html) {
		if (html == null || html == "") {
			return "";
		}
		StringBuffer sb = new StringBuffer(html.length());
		char[] data = html.toCharArray();
		int start = 0;
		boolean previousIsPre = false;
		Token token = null;
		for (;;) {
			token = parse(data, start, previousIsPre);
			if (token == null)
				break;
			previousIsPre = token.isPreTag();
			sb = sb.append(token.getText());
			start += token.getLength();
		}
		return sb.toString();
	}

	private static Token parse(char[] data, int start, boolean previousIsPre) {
		if (start >= data.length)
			return null;
		// try to read next char:
		char c = data[start];
		if (c == '<') {
			// this is a tag or comment or script:
			int end_index = indexOf(data, start + 1, '>');
			if (end_index == (-1)) {
				// the left is all text!
				return new Token(Token.TOKEN_TEXT, data, start, data.length, previousIsPre);
			}
			String s = new String(data, start, end_index - start + 1);
			// now we got s="<...>":
			if (s.startsWith("<!--")) { // this is a comment!
				int end_comment_index = indexOf(data, start + 1, "-->");
				if (end_comment_index == (-1)) {
					// illegal end, but treat as comment:
					return new Token(Token.TOKEN_COMMENT, data, start, data.length, previousIsPre);
				} else
					return new Token(Token.TOKEN_COMMENT, data, start, end_comment_index + 3, previousIsPre);
			}
			
			String s_lowerCase = s.toLowerCase();
			if (s_lowerCase.startsWith("<script")) { // this is a script:
				int end_script_index= indexOf(data, start + 1, "</script>");
				if (end_script_index == (-1))
					// illegal end, but treat as script:
					return new Token(Token.TOKEN_SCRIPT, data, start, data.length, previousIsPre);
				else
					return new Token(Token.TOKEN_SCRIPT, data, start, end_script_index + 9, previousIsPre);
			}else if(s_lowerCase.startsWith("<style")) { // this is a script:
				int end_script_index= indexOf(data, start + 1, "</style>");
				if (end_script_index == (-1))
					// illegal end, but treat as script:
					return new Token(Token.TOKEN_SCRIPT, data, start, data.length, previousIsPre);
				else
					return new Token(Token.TOKEN_SCRIPT, data, start, end_script_index + 8, previousIsPre);
			} 
			else { // this is a tag:
				return new Token(Token.TOKEN_TAG, data, start, start + s.length(), previousIsPre);
			}

			
			
		}
		// this is a text:
		int next_tag_index = indexOf(data, start + 1, '<');
		if (next_tag_index == (-1))
			return new Token(Token.TOKEN_TEXT, data, start, data.length, previousIsPre);
		return new Token(Token.TOKEN_TEXT, data, start, next_tag_index, previousIsPre);
	}

	private static int indexOf(char[] data, int start, String s) {
		char[] ss = s.toCharArray();
		// TODO: performance can improve!
		for (int i = start; i < (data.length - ss.length); i++) {
			// compare from data[i] with ss[0]:
			boolean match = true;
			for (int j = 0; j < ss.length; j++) {
				if (data[i + j] != ss[j]) {
					match = false;
					break;
				}
			}
			if (match)
				return i;
		}
		return (-1);
	}

	private static int indexOf(char[] data, int start, char c) {
		for (int i = start; i < data.length; i++) {
			if (data[i] == c)
				return i;
		}
		return (-1);
	}
}

class Token {
	public static final int TOKEN_TEXT = 0; // html text.
	public static final int TOKEN_COMMENT = 1; // comment like <!-- comments... -->
	public static final int TOKEN_TAG = 2; // tag like <pre>, <font>, etc.
	public static final int TOKEN_SCRIPT = 3;
	private static final char[] TAG_BR = "<br".toCharArray();
	private static final char[] TAG_P = "<p".toCharArray();
	private static final char[] TAG_LI = "<li".toCharArray();
	private static final char[] TAG_PRE = "<pre".toCharArray();
	private static final char[] TAG_HR = "<hr".toCharArray();
	private static final char[] END_TAG_TD = "</td>".toCharArray();
	private static final char[] END_TAG_TR = "</tr>".toCharArray();
	private static final char[] END_TAG_LI = "</li>".toCharArray();
	private static final Map SPECIAL_CHARS = new HashMap();
	private int type;
	private String html; // original html
	private String text = null; // text!
	private int length = 0; // html length
	private boolean isPre = false; // isPre tag?
	static {
		SPECIAL_CHARS.put("&quot;", "\"");
		SPECIAL_CHARS.put("&lt;", "<");
		SPECIAL_CHARS.put("&gt;", ">");
		SPECIAL_CHARS.put("&amp;", "&");
		SPECIAL_CHARS.put("&reg;", "(r)");
		SPECIAL_CHARS.put("&copy;", "(c)");
		SPECIAL_CHARS.put("&nbsp;", " ");
		SPECIAL_CHARS.put("&pound;", "?");
	}

	public Token(int type, char[] data, int start, int end, boolean previousIsPre) {
		this.type = type;
		this.length = end - start;
		this.html = new String(data, start, length);
		// System.out.println("[Token] html=" + html + ".");
		parseText(previousIsPre);
		// System.out.println("[Token] text=" + text + ".");
	}

	public int getLength() {
		return length;
	}

	public boolean isPreTag() {
		return isPre;
	}

	private void parseText(boolean previousIsPre) {
		if (type == TOKEN_TAG) {
			char[] cs = html.toCharArray();
			if (compareTag(TAG_BR, cs) || compareTag(TAG_P, cs))
				text = "\n";
			else if (compareTag(TAG_LI, cs))
				text = "\n* ";
			else if (compareTag(TAG_PRE, cs))
				isPre = true;
			else if (compareTag(TAG_HR, cs))
				text = "\n--------\n";
			else if (compareString(END_TAG_TD, cs))
				text = "\t";
			else if (compareString(END_TAG_TR, cs) || compareString(END_TAG_LI, cs))
				text = "\n";
		}
		// text token:
		else if (type == TOKEN_TEXT) {
			text = toText(html, previousIsPre);
		}
	}

	public String getText() {
		return text == null ? "" : text;
	}

	private String toText(String html, final boolean isPre) {
		char[] cs = html.toCharArray();
		StringBuffer buffer = new StringBuffer(cs.length);
		int start = 0;
		boolean continueSpace = false;
		char current, next;
		for (;;) {
			if (start >= cs.length)
				break;
			current = cs[start]; // read current char
			if (start + 1 < cs.length) // and next char
				next = cs[start + 1];
			else
				next = '\0';
			if (current == ' ') {
				if (isPre || !continueSpace)
					buffer = buffer.append(' ');
				continueSpace = true;
				// continue loop:
				start++;
				continue;
			}
			// not ' ', so:
			if (current == '\r' && next == '\n') {
				if (isPre)
					buffer = buffer.append('\n');
				// continue loop:
				start += 2;
				continue;
			}
			if (current == '\n' || current == '\r') {
				if (isPre)
					buffer = buffer.append('\n');
				// continue loop:
				start++;
				continue;
			}
			// cannot continue space:
			continueSpace = false;
			if (current == '&') {
				// maybe special char:
				int length = readUtil(cs, start, ';', 10);
				if (length == (-1)) { // just '&':
					buffer = buffer.append('&');
					// continue loop:
					start++;
					continue;
				} else { // check if special character:
					String spec = new String(cs, start, length);
					String specChar = (String) SPECIAL_CHARS.get(spec);
					if (specChar != null) { // special chars!
						buffer = buffer.append(specChar);
						// continue loop:
						start += length;
						continue;
					} else { // check if like '&#1234':
						if (next == '#') { // maybe a char
							String num = new String(cs, start + 2, length - 3);
							try {
								int code = Integer.parseInt(num);
								if (code > 0 && code < 65536) { // this is a special char:
									buffer = buffer.append((char) code);
									// continue loop:
									start++;
									continue;
								}
							} catch (Exception e) {
							}
							// just normal char:
							buffer = buffer.append("&#");
							// continue loop:
							start += 2;
							continue;
						} else { // just '&':
							buffer = buffer.append('&');
							// continue loop:
							start++;
							continue;
						}
					}
				}
			} else { // just a normal char!
				buffer = buffer.append(current);
				// continue loop:
				start++;
				continue;
			}
		}
		return buffer.toString();
	}

	// read from cs[start] util meet the specified char 'util',
	// or null if not found:
	private int readUtil(final char[] cs, final int start, final char util, final int maxLength) {
		int end = start + maxLength;
		if (end > cs.length)
			end = cs.length;
		for (int i = start; i < start + maxLength; i++) {
			if (cs[i] == util) {
				return i - start + 1;
			}
		}
		return (-1);
	}

	// compare standard tag "<input" with tag "<INPUT value=aa>"
	private boolean compareTag(final char[] ori_tag, char[] tag) {
		if (ori_tag.length >= tag.length)
			return false;
		for (int i = 0; i < ori_tag.length; i++) {
			if (Character.toLowerCase(tag[i]) != ori_tag[i])
				return false;
		}
		// the following char should not be a-z:
		if (tag.length > ori_tag.length) {
			char c = Character.toLowerCase(tag[ori_tag.length]);
			if (c < 'a' || c > 'z')
				return true;
			return false;
		}
		return true;
	}

	private boolean compareString(final char[] ori, char[] comp) {
		if (ori.length > comp.length)
			return false;
		for (int i = 0; i < ori.length; i++) {
			if (Character.toLowerCase(comp[i]) != ori[i])
				return false;
		}
		return true;
	}

	public String toString() {
		return html;
	}
}