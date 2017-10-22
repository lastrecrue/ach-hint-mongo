package ach.hin.data.service;

import ach.hin.data.entity.User;

public interface UserService {

	User save(User user1);
	
	User addFriend(User user,User fiend);

}
