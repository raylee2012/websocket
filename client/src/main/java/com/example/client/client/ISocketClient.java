package com.example.client.client;

public interface ISocketClient {
    void send(String id, String msg);

    void sendAll(String msg);
}
