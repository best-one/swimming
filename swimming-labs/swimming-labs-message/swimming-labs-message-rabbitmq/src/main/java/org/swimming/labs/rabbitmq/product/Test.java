package org.swimming.labs.rabbitmq.product;

import com.rabbitmq.client.*;
import org.springframework.util.CollectionUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

public class Test {
//    Channel noticeChannel;
//
//    public void noticeRegister(Set<String> userFlags, String customerId, String token, Session session) throws Exception {
//        customerId = "";//parseCustomer(customerId);
//        String exchange = NOTICE_EXCHANGE_PREFIX + customerId;
//       // Channel noticeChannel = //getChannel(session);
//        noticeChannel.exchangeDeclare(exchange, "topic", false, true, null);
//        noticeChannels.put(session.getId(), noticeChannel);
//        log.info("init exchange success;sessionId:{};exchange:{}", session.getId(), exchange);
//        Set<String> queueSet = queueSetMap.getOrDefault(session.getId(), new HashSet<>());
//        for (String flag : userFlags) {
//            String queue = NOTICE_QUEUE_PREFIX + flag + System.currentTimeMillis();
//            noticeChannel.queueDeclare(queue, false, false, true, null);
//            log.info("notice init queue success:{}", queue);
//            noticeChannel.queueBind(queue, exchange, "#." + flag + ".#");
//            log.info("notice bind queue and exchange success...");
//            Consumer consumer = new DefaultConsumer(noticeChannel) {
//                @Override
//                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    //处理监听得到的消息
//                    String message = new String(body, "UTF-8");
//                    ;
//                    try {
//                        //消息处理逻辑
//                        /*sendMessage(message);*/
//                        sendMessage(message, "notice", session);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                        noticeChannel.abort();
//                    } finally {
//                        log.info("[x] Done.");
//                        noticeChannel.basicAck(envelope.getDeliveryTag(), false);
//                    }
//                    log.info("[x] Received notice:{}", message);
//                }
//            };
//
//            noticeChannel.basicConsume(queue, false, consumer);
//            queueSet.add(queue);
////            log.info("notice bind consumer and queue success...");
//        }
//        if (!CollectionUtils.isEmpty(queueSet)) {
//            queueSetMap.put(session.getId(), queueSet);
//        }
//    }
}
