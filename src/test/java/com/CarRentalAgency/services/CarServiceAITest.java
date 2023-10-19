package com.CarRentalAgency.services;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.repository.CarRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CarServiceAITest {


    // Saving a new car with a unique registration number should add it to the database
    @Test
    public void test_saveCar_uniqueRegistrationNumber() {
        // Arrange
        CarRepository carRepository = mock(CarRepository.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        Car car = new Car();
        car.setRegistrationNumber(12345);

        // Act
        when(carRepository.findCarByRegistrationNumber(12345)).thenReturn(Optional.empty());
        when(carRepository.save(car)).thenReturn(car);
        Car savedCar = carService.saveCar(car);

        // Assert
        assertEquals(car, savedCar);
        verify(carRepository, times(1)).findCarByRegistrationNumber(12345);
        verify(carRepository, times(1)).save(car);
    }

    // Updating an existing car with a unique registration number should update it in the database
    @Test
    public void test_updateCar_uniqueRegistrationNumber() {
        // Arrange
        CarRepository carRepository = mock(CarRepository.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        Car existingCar = new Car();
        existingCar.setId(1L);
        existingCar.setRegistrationNumber(12345);

        Car newCar = new Car();
        newCar.setId(1L);
        newCar.setRegistrationNumber(54321);

        // Act
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        when(carRepository.findCarByRegistrationNumber(54321)).thenReturn(Optional.empty());
        when(carRepository.save(existingCar)).thenReturn(existingCar);
        Car updatedCar = carService.updateCar(1L, newCar);

        // Assert
        assertEquals(newCar, updatedCar);
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).findCarByRegistrationNumber(54321);
        verify(carRepository, times(1)).save(existingCar);
    }

    // Deleting an existing car by ID should remove it from the database
    @Test
    public void test_deleteCarById_existingCar() {
        // Arrange
        CarRepository carRepository = mock(CarRepository.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        Car existingCar = new Car();
        existingCar.setId(1L);

        // Act
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        doNothing().when(carRepository).deleteCarById(1L);
        carService.deleteCarById(1L);

        // Assert
        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).deleteCarById(1L);
    }

    // Finding all cars in the database should return a list of all cars
    @Test
    public void test_findAll() {
        // Arrange
        CarRepository carRepository = mock(CarRepository.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        List<Car> cars = new ArrayList<>();
        cars.add(new Car());
        cars.add(new Car());

        // Act
        when(carRepository.findAll()).thenReturn(cars);
        List<Car> allCars = carService.findAll();

        // Assert
        assertEquals(cars, allCars);
        verify(carRepository, times(1)).findAll();
    }

    // Finding a car by ID should return the car with the specified ID
    @Test
    public void test_findCarById_existingCar() {
        // Arrange
        CarRepository carRepository = mock(CarRepository.class);
        CarServiceImpl carService = new CarServiceImpl(carRepository);
        Car existingCar = new Car();
        existingCar.setId(1L);

        // Act
        when(carRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        Car foundCar = carService.findCarById(1L);

        // Assert
        assertEquals(existingCar, foundCar);
        verify(carRepository, times(1)).findById(1L);
    }

}
