package com.jim.io.aio.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
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

public class AioClientHandler extends Thread implements CompletionHandler<Void, AioClientHandler>{

	AsynchronousSocketChannel socketChannel;
	
	public AioClientHandler() {
		try {
			socketChannel = AsynchronousSocketChannel.open();
			System.out.println("client 初始化成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		socketChannel.connect(Const.SocketAddress);
	}

	@Override
	public void completed(Void result, AioClientHandler attachment) {
		System.out.println("client 链接成功");
		String sendMsg="0101，我是客户端";
		byte[] bytesWrite = sendMsg.getBytes();
		final ByteBuffer writeBuffer = ByteBuffer.allocate(bytesWrite.length);
		writeBuffer.put(bytesWrite);
		
		socketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				if(attachment.hasRemaining()) {
					socketChannel.write(writeBuffer,attachment,this);
				}else {
					final ByteBuffer readBuffer = ByteBuffer.allocate(1024);
					socketChannel.read(readBuffer,readBuffer,new CompletionHandler<Integer, ByteBuffer>() {

						@Override
						public void completed(Integer result, ByteBuffer attachment) {
							readBuffer.flip();
							byte[] bytes = new byte[readBuffer.remaining()];
							readBuffer.get(bytes);
							System.out.println("读取到的信息---"+new String(bytes));
						}

						@Override
						public void failed(Throwable exc, ByteBuffer attachment) {
							System.out.println("客户读取失败");
							try {
								socketChannel.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					
					});
				}
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("客户写入失败");
				try {
					socketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void failed(Throwable exc, AioClientHandler attachment) {
		System.out.println("客户端异常失败");
		try {
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
