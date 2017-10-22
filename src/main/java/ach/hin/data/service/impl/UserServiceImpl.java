package ach.hin.data.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ach.hin.data.entity.Friends;
import ach.hin.data.entity.User;
import ach.hin.data.repository.CarRepository;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.UserRepository;
import ach.hin.data.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@Autowired
	private CarRepository carRepository;

	@Override
	public User save(User user1) {
		return userRepository.save(user1);
	}

	@Override
	public User addFriend(User user, User fiend) {
		Friends friends = user.getFriends();
		if (friends == null) {
			user.setFriends(new Friends());
		}
		user.getFriends().getUsers().add(fiend);
		user.setFriends(friendsRepository.save(user.getFriends()));
		return userRepository.save(user);
	}

}
