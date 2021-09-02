package com.jim.io.aio.demo;

import java.util.concurrent.TimeUnit;

public class Server {

	public static void main(String[] args) {
		new ServerHandler().start();
		try {
			TimeUnit.MINUTES.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
