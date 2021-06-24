package com.example.websocket.repository;

import com.example.websocket.db.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {

    @Modifying
    @Query("update cars c set c.name = :name where c.id = :id")
    void updateCar(@Param(value = "id") int id, @Param(value = "name") String name);

}
