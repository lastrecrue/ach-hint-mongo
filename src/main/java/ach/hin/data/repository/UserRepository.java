package ach.hin.data.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.User;

public interface UserRepository extends MongoRepository<User, String> ,UserRepositoryCustom {

//	Set<User> findUsers();
//
//	Set<User> findUsersWithCars();
	
	

	

}
