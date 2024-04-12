package org.example.cars.service;

import org.example.cars.dto.CarDto;
import org.example.cars.model.Car;
import org.example.cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    @Value("${maxCars}")
    private int maxCount;


    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createCar(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setMileage(carDto.getMileage());

        Car newCar = carRepository.save(car);
        CarDto carResponse = new CarDto();
        carResponse.setId(newCar.getId());
        carResponse.setModel(newCar.getModel());
        carResponse.setYear(newCar.getYear());
        carResponse.setMileage(newCar.getMileage());

        return carResponse;
    }

    @Override
    public List<CarDto> getCars(Integer count, String sortBy, String[] sortingFields) {
        List<Car> cars = carRepository.findAll();
        switch (sortBy) {
            case "model":
                cars = cars.stream().sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
                break;
            case "year":
                cars = cars.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
                break;
            case "mileage":
                cars = cars.stream().sorted(Comparator.comparing(Car::getMileage)).collect(Collectors.toList());
                break;
        }

        if (count == null || count <= 0 || count > maxCount) {
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

    private Car mapToEntity(CarDto carDto) {
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setMileage(carDto.getMileage());
        return car;
    }
}
