package ach.hin.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ach.hin.data.entity.Friends;

public interface FriendsRepository extends MongoRepository<Friends, String>{

}
