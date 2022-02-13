package com.viktorsuetnov.carbook.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String carBrand;

    private String carModel;

    private String yearOfIssue;

    private String fuelType;

    private Double currentMileage;

    private String color;

    private Double engineCapacity;

    private Double enginePower;

    private String transmission;

    private String bodyType;

    private String vin;

    private String vrp;

    private String odometer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> eventSet;




}
