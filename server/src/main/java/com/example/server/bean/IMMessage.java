package com.example.server.bean;


import java.io.Serializable;
import java.util.List;

public class IMMessage implements Serializable {
    private String content;//消息内容
    private int type;//消息类型
    private String time;//发送消息时间
    private List<String> receiver;//消息接收者
    private String sender;//消息发送者
    private boolean broadcast;//是否是广播

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    @Override
    public String toString() {
        return "IMMessage{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", time='" + time + '\'' +
                ", receiver=" + receiver +
                ", sender='" + sender + '\'' +
                ", broadcast=" + broadcast +
                '}';
    }
}
