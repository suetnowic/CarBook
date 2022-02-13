package com.viktorsuetnov.carbook.controller;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.User;
import com.viktorsuetnov.carbook.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.viktorsuetnov.carbook.util.Utilities.getParseDouble;
import static com.viktorsuetnov.carbook.util.Utilities.isEquals;

@Controller
@RequestMapping("/user-cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/add")
    public String addCar(){
        return "carEdit";
    }

    @PostMapping("/add")
    public String createCar(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("carBrand") String carBrand,
            @RequestParam("carModel") String carModel,
            @RequestParam("yearOfIssue") String yearOfIssue,
            @RequestParam("fuelType") String fuelType,
            @RequestParam("color") String color,
            @RequestParam("engineCapacity") String engineCapacity,
            @RequestParam("enginePower") String enginePower,
            @RequestParam("transmission") String transmission,
            @RequestParam("bodyType") String bodyType,
            @RequestParam("vin") String vin,
            @RequestParam("vrp") String vrp,
            @RequestParam("odometer") String odometer
    ){
        Car car = new Car();
        car.setOwner(currentUser);
        createAndUpdateCar(carBrand, carModel, yearOfIssue, fuelType, color, engineCapacity, enginePower,
                transmission, bodyType, vin, vrp, odometer, car);

        return "redirect:/user-cars/cars";
    }

    @GetMapping("/cars")
    public String getAllUserCars(
            @AuthenticationPrincipal User currentUser,
            Model model
    ) {
        List<Car> cars = carService.getCarListForUser(currentUser);
        model.addAttribute("cars", cars);
        return "carsList";
    }

    @GetMapping("/edit/{car}")
    public String carEditForm(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Car car,
            Model model){
        Car carFromDB = null;
        if (isEquals(currentUser, car)){
            carFromDB = carService.getCarById(car.getId());
        }
        model.addAttribute("car", carFromDB);

        return "carEdit";
    }

    @PostMapping("/edit/{id}")
    public String updateCar(
            @AuthenticationPrincipal User currentUser,
            @PathVariable("id") Long id,
            @RequestParam(value = "carBrand") String carBrand,
            @RequestParam(value = "carModel") String carModel,
            @RequestParam(value = "yearOfIssue") String yearOfIssue,
            @RequestParam(value = "fuelType") String fuelType,
            @RequestParam(value = "color") String color,
            @RequestParam(value = "engineCapacity") String engineCapacity,
            @RequestParam(value = "enginePower") String enginePower,
            @RequestParam(value = "transmission") String transmission,
            @RequestParam(value = "bodyType") String bodyType,
            @RequestParam(value = "vin") String vin,
            @RequestParam(value = "vrp") String vrp,
            @RequestParam(value = "odometer") String odometer
    ){
        Car carFromDb = carService.getCarById(id);

        if (isEquals(currentUser, carFromDb)){
            createAndUpdateCar(carBrand, carModel, yearOfIssue, fuelType, color, engineCapacity, enginePower,
                    transmission, bodyType, vin, vrp, odometer, carFromDb);
        }
        return "redirect:/user-cars/cars";
    }

    private void createAndUpdateCar(String carBrand, String carModel, String yearOfIssue, String fuelType,
                                    String color, String engineCapacity, String enginePower, String transmission,
                                    String bodyType, String vin, String vrp, String odometer, Car car
    ) {
        car.setCarBrand(carBrand);
        car.setCarModel(carModel);
        car.setYearOfIssue(yearOfIssue);
        car.setFuelType(fuelType);
        car.setColor(color);
        car.setEngineCapacity(getParseDouble(engineCapacity));
        car.setEnginePower(getParseDouble(enginePower));
        car.setTransmission(transmission);
        car.setBodyType(bodyType);
        car.setVin(vin);
        car.setVrp(vrp);
        car.setOdometer(odometer);

        carService.save(car);
    }


}
