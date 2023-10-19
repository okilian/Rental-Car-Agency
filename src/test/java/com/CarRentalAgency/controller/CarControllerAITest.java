package com.CarRentalAgency.controller;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarControllerAITest {

    @Test
    public void test_saveCar_withValidCar_shouldSaveCarAndReturnIt() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        Car car = new Car();
        when(carService.saveCar(car)).thenReturn(car);

        // Act
        ResponseEntity<Car> response = carController.saveCar(car);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    // Should be able to delete a car by id
    @Test
    public void test_deleteCarById_withValidId() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        Long id = 1L;

        // Act
        ResponseEntity<Void> response = carController.deleteCarById(id);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(carService, times(1)).deleteCarById(id);
    }

    // Should be able to find a car by id
    @Test
    public void test_findCarById_withValidId() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        Long id = 1L;
        Car car = new Car();
        when(carService.findCarById(id)).thenReturn(car);

        // Act
        ResponseEntity<Car> response = carController.findCarById(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    // Should be able to find all cars
    @Test
    public void test_findAllCars() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        List<Car> cars = new ArrayList<>();
        when(carService.findAll()).thenReturn(cars);

        // Act
        List<Car> result = carController.findAllCars();

        // Assert
        assertEquals(cars, result);
    }

    // Should be able to find a car by registration number
    @Test
    public void test_findCarByRegistrationNumber_withValidRegistrationNumber() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        int registrationNumber = 12345;
        Car car = new Car();
        when(carService.findCarByRegistrationNumber(registrationNumber)).thenReturn(car);

        // Act
        ResponseEntity<Car> response = carController.findCarByRegistrationNumber(registrationNumber);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(car, response.getBody());
    }

    // Should return 404 when trying to find a car by an invalid id
    @Test
    public void test_findCarById_withInvalidId() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        Long id = 1L;
        when(carService.findCarById(id)).thenReturn(null);

        // Act
        ResponseEntity<Car> response = carController.findCarById(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Should return 404 when trying to find a car by an invalid registration number
    @Test
    public void test_findCarByRegistrationNumber_withInvalidRegistrationNumber() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        int registrationNumber = 12345;
        when(carService.findCarByRegistrationNumber(registrationNumber)).thenReturn(null);

        // Act
        ResponseEntity<Car> response = carController.findCarByRegistrationNumber(registrationNumber);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Should return 404 when trying to find cars by an invalid car name
    @Test
    public void test_findCarsByCarName_withInvalidCarName() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        String carName = "Invalid";
        when(carService.findCarsByCarName(carName)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Car>> response = carController.findCarsByCarName(carName);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Should return 404 when trying to find cars by an invalid model
    @Test
    public void test_findCarsByModel_withInvalidModel() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        Car.Model model = Car.Model.SUV;
        when(carService.findCarsByModel(model)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Car>> response = carController.findCarsByModel(model);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Should return an empty list when trying to find cars by kilometers less than or equal to a negative value
    @Test
    public void test_findCarsByKilometresLessThanEqual_withNegativeKilometre() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        int kilometre = -100;
        when(carService.findCarsByKilometresLessThanEqual(kilometre)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Car>> response = carController.findCarsByKilometresLessThanEqual(kilometre);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

    // Should return an empty list when trying to find cars by kilometers greater than or equal to a negative value
    @Test
    public void test_findCarsByKilometresGreaterThanEqual_withNegativeKilometre() {
        // Arrange
        CarService carService = mock(CarService.class);
        CarController carController = new CarController(carService);
        int kilometre = -100;
        when(carService.findCarsByKilometresGreaterThanEqual(kilometre)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Car>> response = carController.findCarsByKilometresGreaterThanEqual(kilometre);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }

}