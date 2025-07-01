package com.example.demo.service;

import com.example.demo.model.ChatRoom;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.ChatRoomRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public void sendMessage(String senderUsername, String recipientUsername, String content) {
        User sender = userRepository.findByUsername(senderUsername)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User recipient = userRepository.findByUsername(recipientUsername)
                .orElseThrow(() -> new RuntimeException("Recipient not found"));

        ChatRoom chatRoom = chatRoomRepository
                .findByUser1AndUser2OrUser2AndUser1(sender, recipient, sender, recipient)
                .orElseGet(() -> {
                    ChatRoom newRoom = ChatRoom.builder()
                            .user1(sender)
                            .user2(recipient)
                            .build();
                    return chatRoomRepository.save(newRoom);
                });

        Message message = Message.builder()
                .chatRoom(chatRoom)
                .sender(sender)
                .content(content)
                .recipient(recipient)
                .timestamp(LocalDateTime.now())
                .build();

        messageRepository.save(message);
    }

    public List<Message> getChatMessages(String username1, String username2) {
        User user1 = userRepository.findByUsername(username1)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = userRepository.findByUsername(username2)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ChatRoom chatRoom = chatRoomRepository
                .findByUser1AndUser2OrUser2AndUser1(user1, user2, user1, user2)
                .orElseThrow(() -> new RuntimeException("ChatRoom not found"));

        return messageRepository.findByChatRoomOrderByTimestampAsc(chatRoom);
    }

    public List<Message> getChatHistory(String user1, String user2) {
        ChatRoom chatRoom = chatRoomRepository
                .findByUsers(user1, user2)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        return messageRepository.findByChatRoomOrderByTimestampAsc(chatRoom);
    }
}
