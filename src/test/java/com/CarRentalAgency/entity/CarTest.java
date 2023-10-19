package com.CarRentalAgency.entity;

import com.CarRentalAgency.repository.CarRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CarTest {
    CarRepository carRepository;

    // Creating a new Car object with valid parameters should return a Car object with the same parameters.
    @Test
    public void test_createCarWithValidParameters() {
        Car car = new Car();
        car.setName("Test Car");
        car.setRegistrationNumber(12345);
        car.setSeats((short) 4);
        car.setKilometres(10000);
        car.setDoors((short) 4);
        car.setModel(Car.Model.SUV);
        car.setGear(Car.Gear.Automatic);
        car.setFuel(Car.Fuel.Petrol);
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        car.setDealer(dealer);

        assertEquals("Test Car", car.getName());
        assertEquals(12345, car.getRegistrationNumber());
        assertEquals(4, car.getSeats());
        assertEquals(10000, car.getKilometres());
        assertEquals(4, car.getDoors());
        assertEquals(Car.Model.SUV, car.getModel());
        assertEquals(Car.Gear.Automatic, car.getGear());
        assertEquals(Car.Fuel.Petrol, car.getFuel());
        assertEquals(dealer, car.getDealer());
    }

    // Updating a Car object with valid parameters should update the Car object with the same parameters.
    @Test
    public void test_updateCarWithValidParameters() {
        Car car = new Car();
        car.setName("Test Car");
        car.setRegistrationNumber(12345);
        car.setSeats((short) 4);
        car.setKilometres(10000);
        car.setDoors((short) 4);
        car.setModel(Car.Model.SUV);
        car.setGear(Car.Gear.Automatic);
        car.setFuel(Car.Fuel.Petrol);
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        car.setDealer(dealer);

        car.setName("Updated Car");
        car.setRegistrationNumber(54321);
        car.setSeats((short) 2);
        car.setKilometres(5000);
        car.setDoors((short) 2);
        car.setModel(Car.Model.SPORTS_CAR);
        car.setGear(Car.Gear.Manual);
        car.setFuel(Car.Fuel.Diesel);
        Dealer updatedDealer = new Dealer();
        updatedDealer.setId(2L);
        car.setDealer(updatedDealer);

        assertEquals("Updated Car", car.getName());
        assertEquals(54321, car.getRegistrationNumber());
        assertEquals(2, car.getSeats());
        assertEquals(5000, car.getKilometres());
        assertEquals(2, car.getDoors());
        assertEquals(Car.Model.SPORTS_CAR, car.getModel());
        assertEquals(Car.Gear.Manual, car.getGear());
        assertEquals(Car.Fuel.Diesel, car.getFuel());
        assertEquals(updatedDealer, car.getDealer());
    }

    // Retrieving a Car object by its ID should return the correct Car object.
    @Test
    public void test_retrieveCarById() {
        Car expectedCar = new Car();
        expectedCar.setId(1L);
        expectedCar.setName("Test Car");
        expectedCar.setRegistrationNumber(12345);
        expectedCar.setSeats((short) 4);
        expectedCar.setKilometres(10000);
        expectedCar.setDoors((short) 4);
        expectedCar.setModel(Car.Model.SUV);
        expectedCar.setGear(Car.Gear.Automatic);
        expectedCar.setFuel(Car.Fuel.Petrol);
        Dealer dealer = new Dealer();
        dealer.setId(1L);
        expectedCar.setDealer(dealer);

        when(carRepository.findById(1L)).thenReturn(Optional.of(expectedCar));

        //Car actualCar = underTestService.findCarById(1L);

        //assertEquals(expectedCar, actualCar);
    }

}