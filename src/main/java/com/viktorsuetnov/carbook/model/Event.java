package com.viktorsuetnov.carbook.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "events")
public class Event {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    private Date dateEvent;

    private String typeOfWork;

    private String consumables;

    private Double numberOfLitres;

    private Double price;

    private Double odometerReading;

    private String note;

    public Event() {
    }

    public Event(Car car, Date dateEvent, String typeOfWork, String consumables, Double numberOfLitres, Double price, Double odometerReading, String note) {
        this.car = car;
        this.dateEvent = dateEvent;
        this.typeOfWork = typeOfWork;
        this.consumables = consumables;
        this.numberOfLitres = numberOfLitres;
        this.price = price;
        this.odometerReading = odometerReading;
        this.note = note;
    }
}
