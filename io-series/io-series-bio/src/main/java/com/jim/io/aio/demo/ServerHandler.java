package com.jim.io.aio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.TimeUnit;

/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ServerHandler extends Thread{

	AsynchronousServerSocketChannel serverSocketChannel;
	
	public ServerHandler() {
		try {
			serverSocketChannel = AsynchronousServerSocketChannel.open();
			serverSocketChannel.bind(Const.SocketAddress);
			System.out.println("server 初始化成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		serverSocketChannel.accept(this, new AcceptCompleteHandler(serverSocketChannel));
		try {
			TimeUnit.MILLISECONDS.sleep(10000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
