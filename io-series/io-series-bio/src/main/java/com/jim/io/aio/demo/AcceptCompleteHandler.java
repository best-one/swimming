package com.jim.io.aio.demo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompleteHandler implements CompletionHandler<AsynchronousSocketChannel, ServerHandler>{
	AsynchronousServerSocketChannel serverSocketChannel;
	public AcceptCompleteHandler(AsynchronousServerSocketChannel serverSocketChannel) {
		this.serverSocketChannel = serverSocketChannel;
	}
	
	@Override
	public void completed(final AsynchronousSocketChannel channel, ServerHandler attachment) {
		attachment.serverSocketChannel.accept(attachment, this);
		System.out.println("客户端进来消息----");
		ByteBuffer readBuffer = ByteBuffer.allocate(1024);
		
		channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {

			@Override
			public void completed(Integer result, ByteBuffer attachment) {
				attachment.flip();
				byte[] bytes = new byte[attachment.remaining()];
				attachment.get(bytes);
				System.out.println("客户端发来的消息内容："+new String(bytes));
				
				String sendMsg = "服务端返回的数据：收到消息，哈哈";
				ByteBuffer writeBuffer = ByteBuffer.allocate(sendMsg.getBytes().length);
				writeBuffer.put(sendMsg.getBytes());
				writeBuffer.flip();
				channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
					@Override
					public void completed(Integer result, ByteBuffer attachment) {
						if(attachment.hasRemaining()) {
							channel.write(attachment, attachment, this);
						}
					}

					@Override
					public void failed(Throwable exc, ByteBuffer attachment) {
						System.out.println("服务端出错！！");
						try {
							channel.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				});
			}

			@Override
			public void failed(Throwable exc, ByteBuffer attachment) {
				System.out.println("服务端出错！！");
				try {
					serverSocketChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void failed(Throwable exc, ServerHandler attachment) {
		System.out.println("服务端链接异常");
		exc.printStackTrace();
		
	}

}
