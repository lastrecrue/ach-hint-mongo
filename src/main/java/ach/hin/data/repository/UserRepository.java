package ach.hin.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
}
