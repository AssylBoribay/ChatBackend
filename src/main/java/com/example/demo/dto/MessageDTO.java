package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MessageDTO {
    private String fromUsername;
    private String toUsername;
    private String content;
    private LocalDateTime timestamp;

    public MessageDTO(String from, String to, String content, LocalDateTime timestamp) {
        this.fromUsername = from;
        this.toUsername = to;
        this.content = content;
        this.timestamp = timestamp;
    }


}
