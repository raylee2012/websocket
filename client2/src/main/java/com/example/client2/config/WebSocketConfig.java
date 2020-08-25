package com.example.client2.config;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class WebSocketConfig {
    @Bean
    public WebSocketClient webSocketClient() {
        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://172.20.10.4:8085/websocket/2"), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.print("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    System.out.print("[websocket] 收到消息=" + message);

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.print("[websocket] 退出连接");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.print("[websocket] 连接错误={}" + ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
