package com.CarRentalAgency.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.CarRentalAgency.entity.Car;
import com.CarRentalAgency.services.CarService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

  @Mock
  private CarService carService;

  @InjectMocks
  private CarController underTest;


  @Test
  public void testSaveCar() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<Car> responseEntity = underTest.saveCar(car);
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
  }

  @Test
  public void testUpdateCar() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<Car> responseEntity = underTest.updateCar(1L, car);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
  }

  @Test
  public void testDeleteCarById() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<Void> responseEntity = underTest.deleteCarById(1L);
    assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
  }

  @Test
  public void testFindCarById() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    when(carService.findCarById(1L)).thenReturn(car);
    ResponseEntity<Car> responseEntity = underTest.findCarById(1L);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(car, responseEntity.getBody());

  }

  @Test
  public void testFindAllCars() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    List<Car> carList = underTest.findAllCars();
    assertEquals(carList.size(), 0);

  }

  @Test
  public void testFindCarByRegistrationNumber() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    when(carService.findCarByRegistrationNumber(1234)).thenReturn(car);
    ResponseEntity<Car> responseEntity = underTest.findCarByRegistrationNumber(1234);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(car, responseEntity.getBody());
  }

  @Test
  public void testFindCarsByCarName() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<List<Car>> carList = underTest.findCarsByCarName("test");
    assertEquals(carList.getStatusCode(), HttpStatus.OK);
    assertEquals(carList.getBody().size(), 0);
  }

  @Test
  public void testFindCarsByModel() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<List<Car>> carList = underTest.findCarsByModel(Car.Model.SEDAN);
    assertEquals(carList.getStatusCode(), HttpStatus.OK);
    assertEquals(carList.getBody().size(), 0);
  }

  @Test
  public void testFindCarsByKilometresLessThanEqual() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<List<Car>> carList = underTest.findCarsByKilometresLessThanEqual(1000);
    assertEquals(carList.getStatusCode(), HttpStatus.OK);
    assertEquals(carList.getBody().size(), 0);
  }

  @Test
  public void testFindCarsByKilometresGreaterThanEqual() {
    Car car = new Car();
    car.setModel(Car.Model.SEDAN);
    car.setRegistrationNumber(1234);
    car.setKilometre(1000);
    car.setCarName("test");
    ResponseEntity<List<Car>> carList = underTest.findCarsByKilometresGreaterThanEqual(1000);
    assertEquals(carList.getStatusCode(), HttpStatus.OK);
    assertEquals(carList.getBody().size(), 0);
  }


}
