//package com.example.demo.controller;
//
//import com.example.demo.dto.WebSocketMessage;
//import com.example.demo.model.Message;
//import com.example.demo.model.User;
//import com.example.demo.service.MessageService;
//import com.example.demo.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import java.security.Principal;
//import java.time.LocalDateTime;
//import java.util.Map;
//
//@Controller
//@RequiredArgsConstructor
//public class WebSocketController {
//
//    private final MessageService messageService;
//    private final SimpMessagingTemplate messagingTemplate;
//    private final UserRepository userRepository;
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public Message handleMessage(@Payload WebSocketMessage message, Principal principal) {
//        if (principal == null) {
//            throw new RuntimeException("Unauthenticated WebSocket request");
//        }
//
//        String senderUsername = principal.getName();
//        messageService.sendMessage(senderUsername, message.getToUsername(), message.getContent());
//
//        Message saved = new Message();
//        saved.setSender(new User(senderUsername));
//        saved.setRecipient(new User(message.getToUsername()));
//        saved.setContent(message.getContent());
//        saved.setTimestamp(LocalDateTime.now());
//
//        return saved;
//    }
//


//}
//