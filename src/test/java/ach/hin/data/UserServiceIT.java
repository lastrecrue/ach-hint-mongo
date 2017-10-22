package ach.hin.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ach.hin.data.entity.Car;
import ach.hin.data.entity.Friends;
import ach.hin.data.entity.Model;
import ach.hin.data.entity.User;
import ach.hin.data.repository.FriendsRepository;
import ach.hin.data.repository.UserRepository;
import ach.hin.data.service.UserService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
@Slf4j
public class UserServiceIT {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FriendsRepository friendsRepository;

	@Test
	public void initBase() {
		userRepository.deleteAll();
		friendsRepository.deleteAll();
		User user1 = new User("name1");
		User user2 = new User("name2");
		User user3 = new User("name3");
		User user4 = new User("name4");

		Model model1 = new Model("model1");
		Model model2 = new Model("model2");
		Model model3 = new Model("model3");
		Car car1 = new Car("reg", model1);
		Car car2 = new Car("reg", model1);
		Car car3 = new Car("reg", model1);
		Car car4 = new Car("reg", model2);
		Car car5 = new Car("reg", model3);
		Car car6 = new Car("reg", model3);
		Car car7 = new Car("reg", model3);

		user1 = userService.save(user1);
		user2 = userService.save(user2);
		user3 = userService.save(user3);
		//
		user1 = userService.addFriend(user1, user2);
		user1 = userService.addFriend(user1, user3);
		user2 = userService.addFriend(user2, user1);

	}

}
