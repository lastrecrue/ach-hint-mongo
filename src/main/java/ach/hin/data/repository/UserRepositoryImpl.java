package ach.hin.data.repository;

import java.util.List;

import org.assertj.core.util.IterableUtil;
import org.springframework.beans.factory.annotation.Autowired;

import ach.hin.data.entity.User;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> saveFriends(Iterable<User> users) {
//		for (User user : users) {
//			saveFriends(user);
//		}
		return IterableUtil.nonNullElementsIn(users);
	}

	@Override
	public User saveFriends(User user) {
//		if (user.getId() == null && user.getFriends().isEmpty()) {
//			userRepository.save(user);
//		} else {
//			saveFriends(user);
//		}
		return user;
	}

}
