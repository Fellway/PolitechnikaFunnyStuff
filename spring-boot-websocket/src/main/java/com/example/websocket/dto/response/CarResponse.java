package com.example.websocket.dto.response;

import com.example.websocket.db.model.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarResponse {

    private String name;
    private Long productionYear;


    public static CarResponse from(final CarEntity carEntity) {
        return new CarResponse(carEntity.getName(), carEntity.getProductionYear());
    }
}
