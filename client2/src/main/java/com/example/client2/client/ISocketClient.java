package com.example.client2.client;

public interface ISocketClient {
    void send(String id, String msg);

    void sendAll(String msg);
}
