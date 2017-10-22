package ach.hin.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.Model;

public interface ModelRepository extends MongoRepository<Model, String> {

}
