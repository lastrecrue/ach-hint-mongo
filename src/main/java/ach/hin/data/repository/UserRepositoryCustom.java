package ach.hin.data.repository;

import java.util.List;

import ach.hin.data.entity.User;

public interface UserRepositoryCustom {
	List<User> saveFriends(Iterable<User> entites);

	User saveFriends(User user);
}
