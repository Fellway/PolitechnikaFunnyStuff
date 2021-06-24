package com.example.websocket.service;

import com.example.websocket.db.model.CarEntity;
import com.example.websocket.dto.Content;
import com.example.websocket.dto.Message;
import com.example.websocket.dto.request.CreateRequest;
import com.example.websocket.dto.request.DeleteRequest;
import com.example.websocket.dto.request.FindRequest;
import com.example.websocket.dto.request.UpdateRequest;
import com.example.websocket.dto.response.CarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class MessageService {

    private final CarService carService;

    @Autowired
    public MessageService(final CarService carService) {
        this.carService = carService;
    }

    public void proceedMessage(final Message message, final Session session) {
        if (message != null) {
            final Content data = message.getData();
            switch (message.getOperation()) {
                case CREATE:
                    carService.saveCar(new CreateRequest(data.getName(), data.getProductionYear()));
                    break;
                case READ:
                    readCar(session, data);
                    break;
                case DELETE:
                    carService.deleteById(new DeleteRequest(data.getId()));
                    break;
                case UPDATE:
                    carService.updateCar(new UpdateRequest(data.getId(), data.getName(), data.getProductionYear()));
                    break;
            }
        }
    }

    private void readCar(final Session session, final Content data) {
        try {
            final Optional<CarEntity> optionalEntity = carService.getCarById(new FindRequest(data.getId()));
            if (optionalEntity.isPresent()) {
                session.getBasicRemote().sendObject(CarResponse.from(optionalEntity.get()));
                log.debug("Sending response {}", optionalEntity.get());
            } else {
                session.getBasicRemote().sendText("Car not found!");
            }
        } catch (IOException | EncodeException e) {
            log.debug("Cannot send response for request {}", data);
        }
    }
}
