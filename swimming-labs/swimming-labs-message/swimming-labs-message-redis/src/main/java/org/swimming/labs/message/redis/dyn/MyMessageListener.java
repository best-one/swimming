package org.swimming.labs.message.redis.dyn;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.alibaba.fastjson.JSONObject;

public class MyMessageListener  implements MessageListener {
	
//    private Session session;


    @Override
    public void onMessage(Message message, byte[] bytes) {
        //消息获取到后的处理消息
        if (message != null) {
            String channel = new String(message.getChannel());
            String body = new String(message.getBody());
//            log.info("channel:{},body:{}",channel,body);

            String[] arr = channel.split(",");
            String device = arr[1];
            String point = arr[2];

            Map<String, Map<String, Object>> deviceMap = new HashMap<>();
            Map<String, Object> pointMap = new HashMap<>();
//            JSONObject jsonObj = JSONObject.fromObject(body);
//            pointMap.put(point, jsonObj);
//            deviceMap.put(device, pointMap);

//            log.info("send 2 brower::{}", JSON.toJSONString(deviceMap));

//            try {
//                WebSocketController.sendMessage2Brower(JSON.toJSONString(deviceMap), "current", session);
//            } catch (Exception e) {
//                log.warn("发送消息失败：{}",e.getMessage());
//            }
        }
    }
}
