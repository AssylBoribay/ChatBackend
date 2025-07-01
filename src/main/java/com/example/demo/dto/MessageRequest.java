package com.example.demo.dto;


import lombok.Data;

@Data
public class MessageRequest {
    private String toUsername;
    private String content;
}
