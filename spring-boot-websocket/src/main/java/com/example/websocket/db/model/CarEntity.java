package com.example.websocket.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "cars")
@Table(name = "cars")
@NoArgsConstructor
@RequiredArgsConstructor
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    @SequenceGenerator(name = "id_sequence", sequenceName = "ID_SEQ")
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private Long productionYear;
}
