package org.example.cars.service;

import org.example.cars.dto.CarDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarService {
    List<CarDto> getCars(Integer count, String SortBy);
}
