package com.example.websocket.dto;

import lombok.Data;

@Data
public class Message {

    private OperationType operation;
    private Content data;

}
