package com.example.websocket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRequest {

    private String name;
    private Long productionYear;

}
