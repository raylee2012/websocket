package com.example.client2.controller;

import com.example.client2.bean.IMMessage;
import com.example.client2.client.SocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/websocket")
@Controller
public class MeesageController {

    @Autowired
    private SocketClient webScoketClient;


    @GetMapping("/sendMessage")
    public @ResponseBody
    IMMessage sendMessage(String message){
        webScoketClient.sendAll(message);
        IMMessage imMessage = new IMMessage();
        imMessage.setContent("123");
        return imMessage;
    }
}
