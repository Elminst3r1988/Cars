package org.example.cars.controller;

import org.example.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String listCars(Model model, @RequestParam(required = false) Integer count,
                                        @RequestParam(required = false) String sortBy) {
        model.addAttribute("cars", carService.getCars(count, sortBy));
        return "cars";
    }
}
