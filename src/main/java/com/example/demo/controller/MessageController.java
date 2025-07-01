package com.example.demo.controller;

import com.example.demo.dto.MessageDTO;
import com.example.demo.model.Message;
import com.example.demo.dto.MessageRequest;
import com.example.demo.service.JwtService;
import com.example.demo.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final JwtService jwtService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest request, HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader("Authorization").replace("Bearer ", "");
        String senderUsername = jwtService.extractUsername(token);

        messageService.sendMessage(senderUsername, request.getToUsername(), request.getContent());
        return ResponseEntity.ok("Message sent");
    }

    @GetMapping("/history")
    public ResponseEntity<List<MessageDTO>> getChatHistory(
            @RequestParam("user") String otherUsername,
            Principal principal
    ) {
        String currentUsername = principal.getName();
        List<Message> messages = messageService.getChatHistory(currentUsername, otherUsername);

        List<MessageDTO> dtos = messages.stream()
                .map(m -> new MessageDTO(
                        m.getSender().getUsername(),
                        m.getRecipient() != null ? m.getRecipient().getUsername() : null,
                        m.getContent(),
                        m.getTimestamp()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

}
