package org.example.cars.controller;

import org.example.cars.config.AppConfig;
import org.example.cars.dto.CarDto;
import org.example.cars.exception.InvalidSortingFieldException;
import org.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@Controller
public class CarController {

    private CarService carService;

    @Autowired
    AppConfig appConfig;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String listCars(Model model, @RequestParam(required = false) Integer count,
                                        @RequestParam(required = false) String sortBy) {
        model.addAttribute("cars", carService.getCars(count, sortBy, appConfig.getSorting()));
        return "cars";
    }

    @PostMapping("/cars/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        return new ResponseEntity<>(carService.createCar(carDto), HttpStatus.CREATED);
    }
}
