package org.example.cars.service;

import org.example.cars.properties.CarProperties;
import org.example.cars.dto.CarDto;
import org.example.cars.exception.InvalidSortingFieldException;
import org.example.cars.model.Car;
import org.example.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarProperties carProperties;


    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarDto> getCars(Integer count, String sortBy) {
        List<Car> cars;
        if (sortBy != null && !Arrays.asList(carProperties.getSorting().get("fields")).contains(sortBy)) {
            throw new InvalidSortingFieldException("Sorting field '" + sortBy + "' is not supported.");
        }

        if (sortBy != null) {
            cars = carRepository.findAll(Sort.by(sortBy));
        } else {
            cars = carRepository.findAll();
        }

        if (count == null || count <= 0 || count > carProperties.getMaxCars()) {
            return cars.stream().map(this::mapToDto).collect(Collectors.toList());
        } else {
            return cars.stream().map(this::mapToDto).limit(count).collect(Collectors.toList());
        }
    }

    private CarDto mapToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setMileage(car.getMileage());
        carDto.setYear(car.getYear());
        return carDto;
    }
}
