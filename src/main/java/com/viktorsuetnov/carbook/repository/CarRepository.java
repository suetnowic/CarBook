package com.viktorsuetnov.carbook.repository;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findCarsByOwner(User owner);

    Car findCarsById(long id);
}
