package org.example.cars.service;

import org.example.cars.dto.CarDto;
import org.example.cars.model.Car;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Component
public interface CarService {

    CarDto createCar(CarDto carDto);

    List<CarDto> getCars(Integer count, String SortBy, String[] sortingFields);

}
