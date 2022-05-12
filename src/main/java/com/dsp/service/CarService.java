package com.dsp.service;

import com.dsp.domain.Car;

import java.util.List;

public interface CarService {

    List<Car> getCarAndLike(Car param);

    List<Car> getCarList();

    int updateCar(Car car);

    Car getCarById(Integer id);

    int delCarById(String ids);

    int addCar(Car car);
}
