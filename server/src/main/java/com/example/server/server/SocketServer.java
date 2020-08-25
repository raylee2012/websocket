package com.example.server.server;

import com.alibaba.fastjson.JSON;
import com.example.server.bean.Client;
import com.example.server.bean.IMMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{id}")
@Component
public class SocketServer {

    private Client client;
    private static ConcurrentHashMap<String, Client> webSocketSet = new ConcurrentHashMap<>();

    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "id") String id) {
        if (!StringUtils.isEmpty(id)) {
            client = new Client(id, session);
            webSocketSet.put(id, client);
        }

    }

    @OnClose
    public void OnClose() {
        if (!StringUtils.isEmpty(client.getId())) {
            webSocketSet.remove(this.client.getId());
        }
    }

    @OnMessage
    public void onMessage(String message) {
        try {
            IMMessage imMessage = JSON.parseObject(message, IMMessage.class);
            if (imMessage.isBroadcast()) {
                sendAll(message);
            } else {
                List<String> receiver = imMessage.getReceiver();
                for (String id : receiver) {
                    send(id, message);
                }
            }
        } catch (Exception e) {
        }
    }

    @OnError
    public void onError(Throwable error) {

    }
    /**
     * 给指定用户推送消息
     */
    public synchronized void send(String id, String message) {
        try {
            webSocketSet.get(id).getSession().getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 广播通知
     */
    public synchronized void sendAll(String message) {
        for (String id : webSocketSet.keySet()) {
            try {
                webSocketSet.get(id).getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
