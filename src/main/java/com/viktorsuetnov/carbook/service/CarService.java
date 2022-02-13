package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.User;
import com.viktorsuetnov.carbook.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Iterable<Car> getAllUserCars(){
        return carRepository.findAll();
    }

    @Transactional
    public List<Car> getCarListForUser(User owner) {
        return carRepository.findCarsByOwner(owner);
    }

    @Transactional
    public Car getCarById(Long id) {
        return carRepository.findCarsById(id);
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }
}
