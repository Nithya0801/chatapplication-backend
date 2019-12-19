package com.example.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.demo.model.ChatMessage;
import com.example.demo.model.ChatUser;
import com.example.demo.repository.ChatUserRepository;



@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    @Autowired
    ChatUserRepository userRepo;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        logger.info("called disconnected....");
        String username = (String) headerAccessor.getSessionAttributes().get("userId");
//        logger.info("called disconnected...."+username);
        if(username != null) {
            logger.info("User Disconnected : " + username);
            ChatUser user=userRepo.getUserById(username);
//            logger.info("User Disconnected : " + user);
            user.setStatus("offline");
            userRepo.saveAndFlush(user);
            messagingTemplate.convertAndSend("/topic/public", user);
        }
    }
}
