package com.example.client2.client;

import com.alibaba.fastjson.JSON;
import com.example.client2.bean.IMMessage;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SocketClient implements ISocketClient{
    @Autowired
    private WebSocketClient webSocketClient;

    @Override
    public void send(String id, String msg) {
        IMMessage imMessage = new IMMessage();
        imMessage.setBroadcast(true);
        imMessage.setContent(msg);
        List<String> ids = new ArrayList<>();
        ids.add(id);
        imMessage.setReceiver(ids);
        imMessage.setSender("1");
        webSocketClient.send(JSON.toJSONString(imMessage));
    }

    @Override
    public void sendAll(String msg) {
        IMMessage imMessage = new IMMessage();
        imMessage.setBroadcast(true);
        imMessage.setContent(msg);
        imMessage.setReceiver(new ArrayList<>());
        imMessage.setSender("1");
        webSocketClient.send(JSON.toJSONString(imMessage));
    }
}
