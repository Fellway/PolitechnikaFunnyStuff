package com.example.websocket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRequest {

    private Integer id;
    private String name;
    private Long productionYear;

}
