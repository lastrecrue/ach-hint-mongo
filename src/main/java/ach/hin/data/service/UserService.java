package ach.hin.data.service;

import ach.hin.data.entity.Car;
import ach.hin.data.entity.User;

public interface UserService {

	User addFriend(User user,User fiend);

	User addCarsToUser(User user1, Car car1);

}
