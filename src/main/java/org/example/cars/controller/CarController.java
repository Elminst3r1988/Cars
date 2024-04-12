package org.example.cars.controller;

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

    private String[] sortingFields;

    @Autowired
    public CarController(CarService carService, @Value("${sorting.fields}") String[] sortingFields) {
        this.carService = carService;
        this.sortingFields = sortingFields;
    }

    @GetMapping("/cars")
    public String listCars(Model model, @RequestParam(required = false) Integer count,
                                        @RequestParam(required = false) String sortBy) {
        if (sortBy != null && !Arrays.asList(sortingFields).contains(sortBy)) {
            throw new InvalidSortingFieldException("Sorting field '" + sortBy + "' is not supported.");
        }
        model.addAttribute("cars", carService.getCars(count, sortBy, sortingFields));
        return "cars";
    }

    @PostMapping("/cars/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto carDto) {
        return new ResponseEntity<>(carService.createCar(carDto), HttpStatus.CREATED);
    }
}
