package com.example.websocket.service;

import com.example.websocket.db.model.CarEntity;
import com.example.websocket.dto.request.CreateRequest;
import com.example.websocket.dto.request.DeleteRequest;
import com.example.websocket.dto.request.FindRequest;
import com.example.websocket.dto.request.UpdateRequest;
import com.example.websocket.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public void saveCar(final CreateRequest createRequest) {
        carRepository.save(new CarEntity(createRequest.getName(), createRequest.getProductionYear()));
    }

    public Optional<CarEntity> getCarById(final FindRequest findRequest) {
        return carRepository.findById(findRequest.getId());
    }

    public void deleteById(final DeleteRequest deleteRequest) {
        carRepository.deleteById(deleteRequest.getId());
    }

    @Transactional
    public void updateCar(final UpdateRequest updateRequest) {
        carRepository.updateCar(updateRequest.getId(), updateRequest.getName());
    }
}
