package ach.hin.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.Car;

public interface CarRepository extends MongoRepository<Car, String	> {

}
