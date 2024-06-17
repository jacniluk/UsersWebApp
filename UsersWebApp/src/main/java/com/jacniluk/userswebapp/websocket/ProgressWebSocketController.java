package com.jacniluk.userswebapp.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ProgressWebSocketController
{
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    public void sendSection(String section)
    {
        messagingTemplate.convertAndSend("/topic/section", section);
    }

    public void sendProgress(float progress)
    {
        messagingTemplate.convertAndSend("/topic/progress", progress);
    }
}
