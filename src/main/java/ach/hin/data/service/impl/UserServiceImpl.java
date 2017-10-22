package ach.hin.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ach.hin.data.entity.Car;
import ach.hin.data.entity.Friends;
import ach.hin.data.entity.User;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.UserRepository;
import ach.hin.data.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@Override
	public User addFriend(User user, User fiend) {
		Friends friends = user.getFriends();
		if (friends == null) {
			user.setFriends(new Friends(user));
		}
		user.getFriends().getUsers().add(fiend);
		user.setFriends(friendsRepository.save(user.getFriends()));
		return userRepository.save(user);
	}

	@Override
	public User addCarsToUser(User user1, Car car1) {
		user1.getCars().add(car1);
		return userRepository.save(user1);
	}

}
